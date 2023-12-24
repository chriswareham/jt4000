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
     * The pattern that matches a valid name.
     */
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9 ]+");

    /**
     * The name text field.
     */
    private final JTextField nameTextField = new IdentifierTextField(NAME_PATTERN, 8, 8);

    /**
     * The source spinner model.
     */
    private final SpinnerNumberModel sourceSpinnerModel = new SpinnerNumberModel(0, 0, 31, 1);

    /**
     * The destination spinner model.
     */
    private final SpinnerNumberModel destinationSpinnerModel = new SpinnerNumberModel(0, 0, 31, 1);

    /**
     * The listener to notify when a patch has been updated.
     */
    private final PatchUpdatedListener listener;

    /**
     * The patch to edit.
     */
    private Patch patch;

    /**
     * Construct an instance of a panel for common parameters.
     *
     * @param listener the listener to notify when a patch has been updated
     */
    public CommonPanel(final PatchUpdatedListener listener) {
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

        nameTextField.setText(patch.getName());
        sourceSpinnerModel.setValue(patch.getSource());
        destinationSpinnerModel.setValue(patch.getDestination());
    }

    /**
     * Create the interface.
     */
    private void createInterface() {
        nameTextField.addActionListener(event -> updateName());
        nameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent event) {
                updateName();
            }
        });

        sourceSpinnerModel.addChangeListener(event -> updateSource());
        destinationSpinnerModel.addChangeListener(event -> updateDestination());

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
            .addCell("Name:")
            .addCell(nameTextField, true)
            .endRow()
            .addCell("Source Voice:")
            .addCell(new IntegerSpinner(sourceSpinnerModel, true), true)
            .endRow()
            .addCell("Destination Voice:")
            .addCell(new IntegerSpinner(destinationSpinnerModel, true), true)
            .endRow()
            .addExpandingRow();
    }

    /**
     * Update the name.
     */
    private void updateName() {
        if (patch != null) {
            patch.setName(nameTextField.getText());
            firePatchUpdated();
        }
    }

    /**
     * Update the source patch number.
     */
    private void updateSource() {
        if (patch != null) {
            patch.setSource(sourceSpinnerModel.getNumber().intValue());
        }
    }

    /**
     * Update the destination patch number.
     */
    private void updateDestination() {
        if (patch != null) {
            patch.setDestination(destinationSpinnerModel.getNumber().intValue());
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
