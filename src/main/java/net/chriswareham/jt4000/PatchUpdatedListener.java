package net.chriswareham.jt4000;

/**
 * This interface is implemented by classes that want to be notified when a
 * patch has been updated.
 */
public interface PatchUpdatedListener {
    /**
     * Notify the listener that a patch has been updated.
     */
    void updated();
}
