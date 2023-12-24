package net.chriswareham.jt4000;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import net.chriswareham.gui.GridBagPanel;
import net.chriswareham.gui.IdentifierTextField;
import net.chriswareham.gui.IntegerSpinner;

/**
 * This class provides a panel for editing common parameters.
 */
public class CommonPanel extends JPanel {
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The pattern that matches a valid patch name.
     */
    private static final Pattern PATCH_NAME_PATTERN = Pattern.compile("[a-zA-Z0-9 ]+");

    /**
     * The MIDI channel spinner model.
     */
    private final SpinnerNumberModel midiChannelSpinnerModel = new SpinnerNumberModel(1, 1, 16, 1);

    /**
     * The patch number spinner model.
     */
    private final SpinnerNumberModel patchNumberSpinnerModel = new SpinnerNumberModel(1, 1, 32, 1);

    /**
     * The patch name text field.
     */
    private final JTextField patchNameTextField = new IdentifierTextField(PATCH_NAME_PATTERN, 8, 8);

    /**
     * The patch to edit.
     */
    private Patch patch;

    /**
     * Construct an instance of a panel for common parameters.
     */
    public CommonPanel() {
        super(new GridLayout(1, 1, 4, 4));
        createInterface();
    }

    /**
     * Set the patch to edit.
     *
     * @param patch the patch to edit
     */
    public void setPatch(final Patch patch) {
        this.patch = patch;

        midiChannelSpinnerModel.setValue(patch.getMidiChannel() + 1);
        patchNumberSpinnerModel.setValue(patch.getPatchNumber() + 1);
        patchNameTextField.setText(patch.getPatchName());
    }

    /**
     * Create the interface.
     */
    private void createInterface() {
        midiChannelSpinnerModel.addChangeListener(event -> updateMidiChannel());

        patchNumberSpinnerModel.addChangeListener(event -> updatePatchNumber());

        patchNameTextField.addActionListener(event -> updatePatchName());
        patchNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent event) {
                updatePatchName();
            }
        });

        add(createPanel());
    }

    /**
     * Create the panel.
     *
     * @return the panel
     */
    private JPanel createPanel() {
        return new GridBagPanel()
            .addBorder(BorderFactory.createLineBorder(Color.BLACK))
            .addCell("MIDI Channel:")
            .addCell(new IntegerSpinner(midiChannelSpinnerModel, true), true)
            .endRow()
            .addCell("Patch Number:")
            .addCell(new IntegerSpinner(patchNumberSpinnerModel, true), true)
            .endRow()
            .addCell("Patch Name:")
            .addCell(patchNameTextField, true)
            .endRow()
            .addExpandingRow();
    }

    /**
     * Update the MIDI channel.
     */
    private void updateMidiChannel() {
        if (patch != null) {
            patch.setMidiChannel(midiChannelSpinnerModel.getNumber().intValue() - 1);
        }
    }

    /**
     * Update the patch number.
     */
    private void updatePatchNumber() {
        if (patch != null) {
            patch.setPatchNumber(patchNumberSpinnerModel.getNumber().intValue() - 1);
        }
    }

    /**
     * Update the patch name.
     */
    private void updatePatchName() {
        if (patch != null) {
            patch.setPatchName(patchNameTextField.getText());
        }
    }
}
