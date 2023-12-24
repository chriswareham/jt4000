package net.chriswareham.jt4000;

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * This class provides a base implementation for editor panels.
 */
public abstract class AbstractEditorPanel extends JPanel {
    /**
     * The listener to notify when a patch has been updated.
     */
    private final PatchUpdatedListener listener;

    /**
     * Construct an instance of an editor panel.
     *
     * @param layoutManager the layout manager to use
     * @param listener the listener to notify when a patch has been updated
     */
    public AbstractEditorPanel(final LayoutManager layoutManager, final PatchUpdatedListener listener) {
        super(layoutManager);
        this.listener = listener;
    }

    /**
     * Inform the listener that a patch has been updated.
     *
     * @param ccNumber the number of the control change
     * @param ccValue the value of the control change
     */
    protected void firePatchUpdated(final int ccNumber, final int ccValue) {
        if (listener != null) {
            listener.updated(ccNumber, ccValue);
        }
    }
}
