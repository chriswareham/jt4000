package net.chriswareham.jt4000;

/**
 * This enumeration describes the possible waves for oscillator 1.
 */
public enum Osc1Wave {
    /**
     * Off.
     */
    OFF("Off", 0),

    /**
     * Triangle.
     */
    TRIANGLE("Triangle", 18),

    /**
     * Square.
     */
    SQUARE("Square", 36),

    /**
     * PWM.
     */
    PWM("PWM", 54),

    /**
     * Saw.
     */
    SAW("Saw", 72),

    /**
     * Supersaw.
     */
    SUPERSAW("Supersaw", 90),

    /**
     * FM.
     */
    FM("FM", 108),

    /**
     * Noise.
     */
    NOISE("Noise", 126);

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
    Osc1Wave(final String description, final int ccValue) {
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
