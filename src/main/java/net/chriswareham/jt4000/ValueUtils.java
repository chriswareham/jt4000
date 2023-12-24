package net.chriswareham.jt4000;

import java.util.Map;

/**
 * This class provides utilities for mapping parameter values to control change
 * values.
 */
public class ValueUtils {
    private static final Map<Integer, Integer> SCALE_24 = Map.ofEntries(
        Map.entry(0, 0x00),
        Map.entry(1, 0x06),
        Map.entry(2, 0x0B),
        Map.entry(3, 0x10),
        Map.entry(4, 0x16),
        Map.entry(5, 0x1B),
        Map.entry(6, 0x20),
        Map.entry(7, 0x26),
        Map.entry(8, 0x2B),
        Map.entry(9, 0x30),
        Map.entry(10, 0x36),
        Map.entry(11, 0x3B),
        Map.entry(12, 0x40),
        Map.entry(13, 0x46),
        Map.entry(14, 0x4B),
        Map.entry(15, 0x50),
        Map.entry(16, 0x56),
        Map.entry(17, 0x5B),
        Map.entry(18, 0x60),
        Map.entry(19, 0x66),
        Map.entry(20, 0x6B),
        Map.entry(21, 0x70),
        Map.entry(22, 0x76),
        Map.entry(23, 0x7B),
        Map.entry(24, 0x7F)
    );

    private static final Map<Integer, Integer> SCALE_99 = Map.ofEntries(
        Map.entry(0, 0x00),
        Map.entry(1, 0x02),
        Map.entry(2, 0x03),
        Map.entry(3, 0x04),
        Map.entry(4, 0x06),
        Map.entry(5, 0x07),
        Map.entry(6, 0x08),
        Map.entry(7, 0x09),
        Map.entry(8, 0x0B),
        Map.entry(9, 0x0C),
        Map.entry(10, 0x0D),
        Map.entry(11, 0x0F),
        Map.entry(12, 0x10),
        Map.entry(13, 0x11),
        Map.entry(14, 0x12),
        Map.entry(15, 0x14),
        Map.entry(16, 0x16),
        Map.entry(17, 0x16),
        Map.entry(18, 0x18),
        Map.entry(19, 0x19),
        Map.entry(20, 0x1A),
        Map.entry(21, 0x1B),
        Map.entry(22, 0x1D),
        Map.entry(23, 0x1E),
        Map.entry(24, 0x1F),
        Map.entry(25, 0x21),
        Map.entry(26, 0x22),
        Map.entry(27, 0x23),
        Map.entry(28, 0x24),
        Map.entry(29, 0x26),
        Map.entry(30, 0x27),
        Map.entry(31, 0x28),
        Map.entry(32, 0x2A),
        Map.entry(33, 0x2B),
        Map.entry(34, 0x2C),
        Map.entry(35, 0x2D),
        Map.entry(36, 0x2F),
        Map.entry(37, 0x30),
        Map.entry(38, 0x31),
        Map.entry(39, 0x33),
        Map.entry(40, 0x34),
        Map.entry(41, 0x35),
        Map.entry(42, 0x36),
        Map.entry(43, 0x38),
        Map.entry(44, 0x39),
        Map.entry(45, 0x3A),
        Map.entry(46, 0x3C),
        Map.entry(47, 0x3D),
        Map.entry(48, 0x3E),
        Map.entry(49, 0x3F),
        Map.entry(50, 0x41),
        Map.entry(51, 0x42),
        Map.entry(52, 0x43),
        Map.entry(53, 0x44),
        Map.entry(54, 0x46),
        Map.entry(55, 0x47),
        Map.entry(56, 0x48),
        Map.entry(57, 0x4A),
        Map.entry(58, 0x4B),
        Map.entry(59, 0x4C),
        Map.entry(60, 0x4D),
        Map.entry(61, 0x4F),
        Map.entry(62, 0x50),
        Map.entry(63, 0x51),
        Map.entry(64, 0x53),
        Map.entry(65, 0x54),
        Map.entry(66, 0x55),
        Map.entry(67, 0x56),
        Map.entry(68, 0x58),
        Map.entry(69, 0x59),
        Map.entry(70, 0x5A),
        Map.entry(71, 0x5C),
        Map.entry(72, 0x5D),
        Map.entry(73, 0x5E),
        Map.entry(74, 0x5F),
        Map.entry(75, 0x61),
        Map.entry(76, 0x62),
        Map.entry(77, 0x63),
        Map.entry(78, 0x65),
        Map.entry(79, 0x66),
        Map.entry(80, 0x67),
        Map.entry(81, 0x68),
        Map.entry(82, 0x6A),
        Map.entry(83, 0x6B),
        Map.entry(84, 0x6C),
        Map.entry(85, 0x6E),
        Map.entry(86, 0x6F),
        Map.entry(87, 0x70),
        Map.entry(88, 0x71),
        Map.entry(89, 0x73),
        Map.entry(90, 0x74),
        Map.entry(91, 0x75),
        Map.entry(92, 0x77),
        Map.entry(93, 0x78),
        Map.entry(94, 0x79),
        Map.entry(95, 0x7A),
        Map.entry(96, 0x7C),
        Map.entry(97, 0x7D),
        Map.entry(98, 0x7E),
        Map.entry(99, 0x7F)
    );

    /**
     * Scale a sequential decimal value 0-24 to the system the JT-4000 uses.
     *
     * @param value the sequential decimal value
     * @return the scaled value
     */
    public static int scale24(final int value) {
        return SCALE_24.getOrDefault(value, 0);
    }

    /**
     * Scale a sequential decimal value 0-99 to the system the JT-4000 uses.
     *
     * @param value the sequential decimal value
     * @return the scaled value
     */
    public static int scale99(final int value) {
        return SCALE_99.getOrDefault(value, 0);
    }

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private ValueUtils() {
        super();
    }
}
