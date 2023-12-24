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
 * This class provides a panel for editing low frequency oscillator 2.
 */
public class Lfo2Panel extends JPanel {
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The wave combo box model.
     */
    private final DefaultComboBoxModel<LfoWave> waveComboBoxModel = new DefaultComboBoxModel<>();

    /**
     * The wave combo box.
     */
    private final JComboBox<LfoWave> waveComboBox = new JComboBox<>(waveComboBoxModel);

    /**
     * The rate slider.
     */
    private final SliderPanel rateSlider = new SliderPanel(0, 99, this::updateRate);

    /**
     * The amount slider.
     */
    private final SliderPanel amountSlider = new SliderPanel(0, 99, this::updateAmount);

    /**
     * The listener to notify when a patch has been updated.
     */
    private final PatchUpdatedListener listener;

    /**
     * The patch to edit.
     */
    private Patch patch;

    /**
     * Construct an instance of a panel for editing low frequency oscillator 2.
     *
     * @param listener the listener to notify when a patch has been updated
     */
    public Lfo2Panel(final PatchUpdatedListener listener) {
        super(new GridLayout(1, 1, 4, 4));
        this.listener = listener;
        createInterface();
    }

    /**
     * Set the patch to edit.
     *
     * @param patch the patch to edit
     */
    public void setPatch(final Patch patch) {
        this.patch = patch;

        waveComboBoxModel.setSelectedRow(patch.getLfo2Wave());
        rateSlider.setValue(patch.getLfo2Rate());
        amountSlider.setValue(patch.getLfo2Amount());
    }

    /**
     * Create the interface.
     */
    private void createInterface() {
        waveComboBoxModel.addRows(LfoWave.values());
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
            .addBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "LFO 2"))
            .addCell("Wave:")
            .addCell(waveComboBox, true)
            .endRow()
            .addCell("Rate:")
            .addCell(rateSlider, true)
            .endRow()
            .addCell("Amount:")
            .addCell(amountSlider, true)
            .endRow()
            .addExpandingRow();
    }

    /**
     * Update the low frequency oscillator wave.
     */
    private void updateWave() {
        if (patch != null) {
            patch.setLfo2Wave(waveComboBoxModel.getSelectedRow());
            firePatchUpdated();
        }
    }

    /**
     * Update the low frequency oscillator rate.
     */
    private void updateRate() {
        if (patch != null) {
            patch.setLfo2Rate(rateSlider.getValue());
            firePatchUpdated();
        }
    }

    /**
     * Update the low frequency oscillator amount.
     */
    private void updateAmount() {
        if (patch != null) {
            patch.setLfo2Amount(amountSlider.getValue());
            firePatchUpdated();
        }
    }

    /**
     * Inform the listener that a patch has been updated.
     */
    private void firePatchUpdated() {
        if (listener != null) {
            listener.updated();
        }
    }
}