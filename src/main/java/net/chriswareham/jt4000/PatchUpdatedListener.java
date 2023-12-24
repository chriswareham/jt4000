package net.chriswareham.jt4000;

/**
 * This interface is implemented by classes that want to be notified when a
 * patch has been updated.
 */
public interface PatchUpdatedListener {
    /**
     * Notify the listener that a patch has been updated.
     *
     * @param ccNumber the number of the control change
     * @param ccValue the value of the control change
     */
    void updated(int ccNumber, int ccValue);
}
