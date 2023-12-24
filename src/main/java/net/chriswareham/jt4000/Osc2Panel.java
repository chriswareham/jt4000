package net.chriswareham.jt4000;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import net.chriswareham.gui.DefaultComboBoxModel;
import net.chriswareham.gui.GridBagPanel;
import net.chriswareham.gui.SliderPanel;

/**
 * This class provides a panel for editing oscillator 2.
 */
public class Osc2Panel extends AbstractEditorPanel {
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The wave combo box model.
     */
    private final DefaultComboBoxModel<Osc2Wave> waveComboBoxModel = new DefaultComboBoxModel<>();

    /**
     * The wave combo box.
     */
    private final JComboBox<Osc2Wave> waveComboBox = new JComboBox<>(waveComboBoxModel);

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
     * The patch to edit.
     */
    private Patch patch;

    /**
     * Construct an instance of a panel for editing oscillator 2.
     *
     * @param listener the listener to notify when a patch has been updated
     */
    public Osc2Panel(final PatchUpdatedListener listener) {
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

        waveComboBoxModel.setSelectedRow(patch.getOsc2Wave());
        coarseTuneSlider.setValue(patch.getOsc2CoarseTune());
        fineTuneSlider.setValue(patch.getOsc2FineTune());
        modulationAmountSlider.setValue(patch.getOsc2ModAmount());
    }

    /**
     * Create the interface.
     */
    private void createInterface() {
        waveComboBoxModel.addRows(Osc2Wave.values());
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
            .addBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "OSC 2"))
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
            .addExpandingRow();
    }

    /**
     * Update the oscillator wave.
     */
    private void updateWave() {
        Osc2Wave value = waveComboBoxModel.getSelectedRow();
        firePatchUpdated(25, value.getCcValue());
        if (patch != null) {
            patch.setOsc2Wave(value);
        }
    }

    /**
     * Update the oscillator coarse tune.
     */
    private void updateCoarseTune() {
        int value = coarseTuneSlider.getValue();
        firePatchUpdated(116, ValueUtils.scale24(value));
        if (patch != null) {
            patch.setOsc2CoarseTune(value);
        }
    }

    /**
     * Update the oscillator fine tune.
     */
    private void updateFineTune() {
        int value = fineTuneSlider.getValue();
        firePatchUpdated(112, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setOsc2FineTune(value);
        }
    }

    /**
     * Update the oscillator modulation amount.
     */
    private void updateModulationAmount() {
        int value = modulationAmountSlider.getValue();
        firePatchUpdated(114, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setOsc2ModAmount(value);
        }
    }
}
