package net.chriswareham.jt4000;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.midi.Receiver;
import javax.sound.midi.SysexMessage;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.chriswareham.gui.AbstractFrame;
import net.chriswareham.gui.GridBagPanel;
import net.chriswareham.gui.MenuUtils;
import net.chriswareham.gui.StatusBar;
import net.chriswareham.midi.Device;

/**
 * This class provides an editor frame for the Behringer JT-4000.
 */
public class Editor extends AbstractFrame {
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Editor.class.getName());

    /**
     * The format string for the status bar.
     */
    private static final String STATUS_BAR_FORMAT = "Input device: %s Output device: %s";

    /**
     * Main entry point for running the editor.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        new Editor().open();
    }

    /**
     * The current input device.
     */
    private Device inputDevice;

    /**
     * The current output device.
     */
    private Device outputDevice;

    /**
     * The current patch.
     */
    private final Patch patch = new Patch();

    /**
     * The save patch menu item.
     */
    private final JMenuItem savePatchMenuItem = MenuUtils.createMenuItem("Save Patch", "S", "Save patch", event -> savePatch(), false);

    /**
     * The panel for editing common parameters.
     */
    private final CommonPanel commonPanel = new CommonPanel(this::patchUpdated);

    /**
     * The panel for editing oscillator 1.
     */
    private final Osc1Panel osc1Panel = new Osc1Panel(this::patchUpdated);

    /**
     * The panel for editing oscillator 2.
     */
    private final Osc2Panel osc2Panel = new Osc2Panel(this::patchUpdated);

    /**
     * The panel for editing low frequency oscillator 1.
     */
    private final Lfo1Panel lfo1Panel = new Lfo1Panel(this::patchUpdated);

    /**
     * The panel for editing low frequency oscillator 2.
     */
    private final Lfo2Panel lfo2Panel = new Lfo2Panel(this::patchUpdated);

    /**
     * The panel for editing the voltage controlled filter.
     */
    private final VcfPanel vcfPanel = new VcfPanel(this::patchUpdated);

    /**
     * The panel for editing the voltage controlled amplifier.
     */
    private final VcaPanel vcaPanel = new VcaPanel(this::patchUpdated);

    /**
     * The status bar.
     */
    private final StatusBar statusBar = new StatusBar(String.format(STATUS_BAR_FORMAT, "-", "-"));

    /**
     * Construct an instance of an editor frame for a Behringer JT-4000 patch.
     */
    public Editor() {
        super("Behringer JT-4000 Editor");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createInterface() {
        addWindowClosedListener(event -> System.exit(0));

        setJMenuBar(createMenuBar());

        GridBagPanel panel = new GridBagPanel()
            .addCell(commonPanel, 2)
            .endRow()
            .addCell(osc1Panel, true, true)
            .addCell(osc2Panel, true, true)
            .endRow()
            .addCell(lfo1Panel, true, true)
            .addCell(lfo2Panel, true, true)
            .endRow()
            .addCell(vcfPanel, true, true)
            .addCell(vcaPanel, true, true)
            .endRow()
            .addExpandingRow();

        getContentPane().add(panel, BorderLayout.CENTER);

        getContentPane().add(statusBar, BorderLayout.SOUTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void populateInterface() {
        initialisePatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void interfaceClosed() {
        closeInputDevice();
        closeOutputDevice();
    }

    /**
     * Create the menu bar.
     *
     * @return the menu bar
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = menuBar.add(MenuUtils.createMenu("File", "F", "File"));

        menu.add(MenuUtils.createMenuItem("Device", "D", "Device", event -> device()));

        menu.addSeparator();

        menu.add(MenuUtils.createMenuItem("Exit", "X", "Exit", event -> close()));

        menu = menuBar.add(MenuUtils.createMenu("Edit", "E", "Edit"));

        menu.add(MenuUtils.createMenuItem("Intitialise Patch", "I", "Initialise patch", event -> initialisePatch()));

        menu.add(savePatchMenuItem);

        return menuBar;
    }

    /**
     * Show the device dialog.
     */
    private void device() {
        new DeviceDialog(this, inputDevice, outputDevice)
            .addInputDeviceChangedListener(device -> inputDeviceChanged(device))
            .addOutputDeviceChangedListener(device -> outputDeviceChanged(device))
            .open();
    }

    /**
     * Handle the selection of an input device.
     *
     * @param device the selected input device
     */
    private void inputDeviceChanged(final Device device) {
        closeInputDevice();
        openInputDevice(device);
    }

    /**
     * Handle the selection of an output device.
     *
     * @param device the selected output device
     */
    private void outputDeviceChanged(final Device device) {
        closeOutputDevice();
        openOutputDevice(device);
    }

    /**
     * Open an input device.
     *
     * @param device the input device to open
     */
    private void openInputDevice(final Device device) {
        if (device != null) {
            call(() -> {
                if (!device.isOpen()) {
                    device.open();
                }
                inputDevice = device;
                updateStatusBar();
            });
        }
    }

    /**
     * Close the current input device.
     */
    private void closeInputDevice() {
        if (inputDevice != null) {
            if (inputDevice.isOpen()) {
                inputDevice.close();
            }
            inputDevice = null;
            updateStatusBar();
        }
    }

    /**
     * Open an output device.
     *
     * @param device the output device to open
     */
    private void openOutputDevice(final Device device) {
        if (device != null) {
            call(() -> {
                if (!device.isOpen()) {
                    device.open();
                }
                outputDevice = device;
                savePatchMenuItem.setEnabled(true);
                updateStatusBar();
            });
        }
    }

    /**
     * Close the current output device.
     */
    private void closeOutputDevice() {
        if (outputDevice != null) {
            if (outputDevice.isOpen()) {
                outputDevice.close();
            }
            outputDevice = null;
            savePatchMenuItem.setEnabled(false);
            updateStatusBar();
        }
    }

    /**
     * Initialise the current patch.
     */
    private void initialisePatch() {
        patch.initialise();

        commonPanel.setPatch(patch);
        osc1Panel.setPatch(patch);
        osc2Panel.setPatch(patch);
        lfo1Panel.setPatch(patch);
        lfo2Panel.setPatch(patch);
        vcfPanel.setPatch(patch);
        vcaPanel.setPatch(patch);
    }

    /**
     * Send the current patch via the current output device.
     */
    private void savePatch() {
        if (outputDevice != null) {
            call(() -> {
                try (Receiver receiver = outputDevice.getReceiver()) {
                    SysexMessage message = patch.serialise();
                    logMessage(message);
                    receiver.send(message, -1);
                }
            });
        }
    }

    /**
     * Update the status bar.
     */
    private void updateStatusBar() {
        statusBar.setText(String.format(STATUS_BAR_FORMAT, inputDevice != null ? inputDevice : "-", outputDevice != null ? outputDevice : "-"));
    }

    /**
     * Notified when a patch has been updated.
     */
    private void patchUpdated() {
        savePatch();
    }

    /**
     * Log a System Exclusive message.
     *
     * @param message the System Exclusive message to log
     */
    private void logMessage(final SysexMessage message) {
        if (LOGGER.isLoggable(Level.INFO)) {
            byte[] data = message.getMessage();
            StringBuilder sb = new StringBuilder("Message : ");
            for (int i = 0; i < data.length; ++i) {
                if (i > 0) {
                    sb.append(' ');
                }
                sb.append(String.format("%02X", data[i]));
            }
            LOGGER.info(sb.toString());
        }
    }
}
