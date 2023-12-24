package net.chriswareham.jt4000;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import net.chriswareham.gui.DefaultCheckBox;
import net.chriswareham.gui.DefaultComboBoxModel;
import net.chriswareham.gui.GridBagPanel;
import net.chriswareham.gui.SliderPanel;

/**
 * This class provides a panel for editing oscillator 1.
 */
public class Osc1Panel extends AbstractEditorPanel {
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The wave combo box model.
     */
    private final DefaultComboBoxModel<Osc1Wave> waveComboBoxModel = new DefaultComboBoxModel<>();

    /**
     * The wave combo box.
     */
    private final JComboBox<Osc1Wave> waveComboBox = new JComboBox<>(waveComboBoxModel);

    /**
     * The coarse tune slider.
     */
    private final SliderPanel coarseTuneSlider = new SliderPanel(0, 24, this::updateCoarseTune);

    /**
     * The fine tune slider.
     */
    private final SliderPanel fineTuneSlider = new SliderPanel(0, 99, this::updateFineTune);

    /**
     * The modulation amount slider.
     */
    private final SliderPanel modulationAmountSlider = new SliderPanel(0, 99, this::updateModulationAmount);

    /**
     * The ring modulator check box.
     */
    private final JCheckBox ringModulatorCheckBox = new DefaultCheckBox(event -> updateRingModulator());

    /**
     * The ring modulator amount slider.
     */
    private final SliderPanel ringModulatorAmountSlider = new SliderPanel(0, 99, this::updateRingModulatorAmount);

    /**
     * The portamento amount slider.
     */
    private final SliderPanel portamentoAmountSlider = new SliderPanel(0, 99, this::updatePortamentoAmount);

    /**
     * The patch to edit.
     */
    private Patch patch;

    /**
     * Construct an instance of a panel for editing oscillator 1.
     *
     * @param listener the listener to notify when a patch has been updated
     */
    public Osc1Panel(final PatchUpdatedListener listener) {
        super(new GridLayout(1, 1, 4, 4), listener);
        createInterface();
    }

    /**
     * Set the patch to edit.
     *
     * @param patch the patch to edit
     */
    public void setPatch(final Patch patch) {
        this.patch = patch;

        waveComboBoxModel.setSelectedRow(patch.getOsc1Wave());
        coarseTuneSlider.setValue(patch.getOsc1CoarseTune());
        fineTuneSlider.setValue(patch.getOsc1FineTune());
        modulationAmountSlider.setValue(patch.getOsc1ModAmount());
        ringModulatorCheckBox.setSelected(patch.isRingModEnabled());
        ringModulatorAmountSlider.setValue(patch.getRingModAmount());
        portamentoAmountSlider.setValue(patch.getPortamentoTime());
    }

    /**
     * Create the interface.
     */
    private void createInterface() {
        waveComboBoxModel.addRows(Osc1Wave.values());
        waveComboBox.addActionListener(event -> updateWave());

        add(createPanel());
    }

    /**
     * Create the panel.
     *
     * @return the panel
     */
    private JPanel createPanel() {
        return new GridBagPanel()
            .addBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "OSC 1"))
            .addCell("Wave:")
            .addCell(waveComboBox, true)
            .endRow()
            .addCell("Coarse Tune:")
            .addCell(coarseTuneSlider, true)
            .endRow()
            .addCell("Fine Tune:")
            .addCell(fineTuneSlider, true)
            .endRow()
            .addCell("Modulation Amount:")
            .addCell(modulationAmountSlider, true)
            .endRow()
            .addCell("Ring Modulator:")
            .addCell(ringModulatorCheckBox, true)
            .endRow()
            .addCell("Ring Modulator Amount:")
            .addCell(ringModulatorAmountSlider, true)
            .endRow()
            .addCell("Portamento Amount:")
            .addCell(portamentoAmountSlider, true)
            .endRow()
            .addExpandingRow();
    }

    /**
     * Update the oscillator wave.
     */
    private void updateWave() {
        Osc1Wave value = waveComboBoxModel.getSelectedRow();
        firePatchUpdated(24, value.getCcValue());
        if (patch != null) {
            patch.setOsc1Wave(value);
        }
    }

    /**
     * Update the oscillator coarse tune.
     */
    private void updateCoarseTune() {
        int value = coarseTuneSlider.getValue();
        firePatchUpdated(115, ValueUtils.scale24(value));
        if (patch != null) {
            patch.setOsc1CoarseTune(value);
        }
    }

    /**
     * Update the oscillator fine tune.
     */
    private void updateFineTune() {
        int value = fineTuneSlider.getValue();
        firePatchUpdated(111, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setOsc1FineTune(value);
        }
    }

    /**
     * Update the oscillator modulation amount.
     */
    private void updateModulationAmount() {
        int value = modulationAmountSlider.getValue();
        firePatchUpdated(113, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setOsc1ModAmount(value);
        }
    }

    /**
     * Update whether the ring modulator is enabled.
     */
    private void updateRingModulator() {
        boolean value = ringModulatorCheckBox.isSelected();
        firePatchUpdated(96, value ? 65 : 0);
        if (patch != null) {
            patch.setRingModEnabled(value);
        }
    }

    /**
     * Update the ring modulator amount.
     */
    private void updateRingModulatorAmount() {
        int value = ringModulatorAmountSlider.getValue();
        firePatchUpdated(95, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setRingModAmount(value);
        }
    }

    /**
     * Update the portamento amount.
     */
    private void updatePortamentoAmount() {
        int value = portamentoAmountSlider.getValue();
        firePatchUpdated(5, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setPortamentoTime(value);
        }
    }
}
