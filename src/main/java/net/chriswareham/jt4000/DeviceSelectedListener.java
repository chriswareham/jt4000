package net.chriswareham.jt4000;

import net.chriswareham.midi.Device;

/**
 * This interface is implemented by classes that want to be notified when a MIDI
 * device has been selected.
 */
public interface DeviceSelectedListener {
    /**
     * Notify the listener that a MIDI device has been selected.
     *
     * @param device the MIDI device that has been selected
     */
    void selected(Device device);
}
