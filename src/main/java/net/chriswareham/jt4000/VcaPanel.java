package net.chriswareham.jt4000;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.chriswareham.gui.GridBagPanel;
import net.chriswareham.gui.SliderPanel;

/**
 * This class provides a panel for editing the voltage controlled amplifier.
 */
public class VcaPanel extends JPanel {
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The envelope attack slider.
     */
    private final SliderPanel envAttackSlider = new SliderPanel(0, 99, this::updateEnvAttack);

    /**
     * The envelope decay slider.
     */
    private final SliderPanel envDecaySlider = new SliderPanel(0, 99, this::updateEnvDecay);

    /**
     * The envelope sustain slider.
     */
    private final SliderPanel envSustainSlider = new SliderPanel(0, 99, this::updateEnvSustain);

    /**
     * The envelope release slider.
     */
    private final SliderPanel envReleaseSlider = new SliderPanel(0, 99, this::updateEnvRelease);

    /**
     * The listener to notify when a patch has been updated.
     */
    private final PatchUpdatedListener listener;

    /**
     * The patch to edit.
     */
    private Patch patch;

    /**
     * Construct an instance of a panel for editing the voltage controlled
     * amplifier.
     *
     * @param listener the listener to notify when a patch has been updated
     */
    public VcaPanel(final PatchUpdatedListener listener) {
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

        envAttackSlider.setValue(patch.getVcaEnvAttack());
        envDecaySlider.setValue(patch.getVcaEnvDecay());
        envSustainSlider.setValue(patch.getVcaEnvSustain());
        envReleaseSlider.setValue(patch.getVcaEnvRelease());
    }

    /**
     * Create the interface.
     */
    private void createInterface() {
        add(createPanel());
    }

    /**
     * Create the panel.
     *
     * @return the panel
     */
    private JPanel createPanel() {
        return new GridBagPanel()
            .addBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "VCA"))
            .addCell("Envelope Attack:")
            .addCell(envAttackSlider, true)
            .endRow()
            .addCell("Envelope Decay:")
            .addCell(envDecaySlider, true)
            .endRow()
            .addCell("Envelope Sustain:")
            .addCell(envSustainSlider, true)
            .endRow()
            .addCell("Envelope Release:")
            .addCell(envReleaseSlider, true)
            .endRow()
            .addExpandingRow();
    }

    /**
     * Update the envelope attack.
     */
    private void updateEnvAttack() {
        if (patch != null) {
            patch.setVcaEnvAttack(envAttackSlider.getValue());
            firePatchUpdated();
        }
    }

    /**
     * Update the envelope decay.
     */
    private void updateEnvDecay() {
        if (patch != null) {
            patch.setVcaEnvDecay(envDecaySlider.getValue());
            firePatchUpdated();
        }
    }

    /**
     * Update the envelope sustain.
     */
    private void updateEnvSustain() {
        if (patch != null) {
            patch.setVcaEnvSustain(envSustainSlider.getValue());
            firePatchUpdated();
        }
    }

    /**
     * Update the envelope release.
     */
    private void updateEnvRelease() {
        if (patch != null) {
            patch.setVcaEnvRelease(envReleaseSlider.getValue());
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
