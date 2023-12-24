package net.chriswareham.jt4000;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.chriswareham.gui.GridBagPanel;
import net.chriswareham.gui.SliderPanel;

/**
 * This class provides a panel for editing the voltage controlled filter.
 */
public class VcfPanel extends AbstractEditorPanel {
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The cutoff slider.
     */
    private final SliderPanel cutoffSlider = new SliderPanel(0, 99, this::updateCutoff);

    /**
     * The resonance slider.
     */
    private final SliderPanel resonanceSlider = new SliderPanel(0, 99, this::updateResonance);

    /**
     * The envelope amount slider.
     */
    private final SliderPanel envAmountSlider = new SliderPanel(0, 99, this::updateEnvAmount);

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
     * filter.
     *
     * @param listener the listener to notify when a patch has been updated
     */
    public VcfPanel(final PatchUpdatedListener listener) {
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

        cutoffSlider.setValue(patch.getVcfCutoff());
        resonanceSlider.setValue(patch.getVcfResonance());
        envAmountSlider.setValue(patch.getVcfEnvAmount());
        envAttackSlider.setValue(patch.getVcfEnvAttack());
        envDecaySlider.setValue(patch.getVcfEnvDecay());
        envSustainSlider.setValue(patch.getVcfEnvSustain());
        envReleaseSlider.setValue(patch.getVcfEnvRelease());
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
            .addBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "VCF"))
            .addCell("Cutoff:")
            .addCell(cutoffSlider, true)
            .endRow()
            .addCell("Resonance:")
            .addCell(resonanceSlider, true)
            .endRow()
            .addCell("Envelope Amount:")
            .addCell(envAmountSlider, true)
            .endRow()
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
     * Update the cutoff.
     */
    private void updateCutoff() {
        int value = cutoffSlider.getValue();
        firePatchUpdated(74, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcfCutoff(value);
        }
    }

    /**
     * Update the resonance.
     */
    private void updateResonance() {
        int value = resonanceSlider.getValue();
        firePatchUpdated(71, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setLfo1Rate(value);
        }
    }

    /**
     * Update the envelope amount.
     */
    private void updateEnvAmount() {
        int value = envAmountSlider.getValue();
        firePatchUpdated(47, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcfEnvAmount(value);
        }
    }

    /**
     * Update the envelope attack.
     */
    private void updateEnvAttack() {
        int value = envAttackSlider.getValue();
        firePatchUpdated(85, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcfEnvAttack(value);
        }
    }

    /**
     * Update the envelope decay.
     */
    private void updateEnvDecay() {
        int value = envDecaySlider.getValue();
        firePatchUpdated(86, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcfEnvDecay(value);
        }
    }

    /**
     * Update the envelope sustain.
     */
    private void updateEnvSustain() {
        int value = envSustainSlider.getValue();
        firePatchUpdated(87, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcfEnvSustain(value);
        }
    }

    /**
     * Update the envelope release.
     */
    private void updateEnvRelease() {
        int value = envReleaseSlider.getValue();
        firePatchUpdated(88, ValueUtils.scale99(value));
        if (patch != null) {
            patch.setVcfEnvRelease(value);
        }
    }
}
