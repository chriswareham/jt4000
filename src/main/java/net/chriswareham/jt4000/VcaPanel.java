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
public class VcaPanel extends AbstractEditorPanel {
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
        int value = envAttackSlider.getValue();
        firePatchUpdated(81, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcaEnvAttack(value);
        }
    }

    /**
     * Update the envelope decay.
     */
    private void updateEnvDecay() {
        int value = envDecaySlider.getValue();
        firePatchUpdated(82, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcaEnvDecay(value);
        }
    }

    /**
     * Update the envelope sustain.
     */
    private void updateEnvSustain() {
        int value = envSustainSlider.getValue();
        firePatchUpdated(83, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcaEnvSustain(value);
        }
    }

    /**
     * Update the envelope release.
     */
    private void updateEnvRelease() {
        int value = envReleaseSlider.getValue();
        firePatchUpdated(84, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcaEnvRelease(value);
        }
    }
}
