package net.chriswareham.jt4000;

/**
 * This enumeration describes the possible waves for a low frequency
 * oscillator.
 */
public enum LfoWave {
    /**
     * Triangle.
     */
    TRIANGLE("Triangle", 0),

    /**
     * Square.
     */
    SQUARE("Square", 64),

    /**
     * Saw.
     */
    SAW("Saw", 127);

    /**
     * The description of the enumeration value.
     */
    private final String description;

    /**
     * The MIDI control change value of the enumeration value.
     */
    private final int ccValue;

    /**
     * Construct an instance of an enumeration value.
     *
     * @param description the description of the enumeration value
     * @param ccValue the MIDI control change value of the enumeration value
     */
    LfoWave(final String description, final int ccValue) {
        this.description = description;
        this.ccValue = ccValue;
    }

    /**
     * Get the MIDI control change value of the enumeration value.
     *
     * @return the MIDI control change value of the enumeration value
     */
    public int getCcValue() {
        return ccValue;
    }

    /**
     * Get the description of the enumeration value.
     *
     * @return the description of the enumeration value
     */
    @Override
    public String toString() {
        return description;
    }
}
