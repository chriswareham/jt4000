package net.chriswareham.jt4000;

import java.io.Serializable;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.SysexMessage;

import net.chriswareham.midi.MidiUtils;

/**
 * This class describes a Behringer JT-4000 patch.
 */
public class Patch implements Serializable {
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The patch dump header.
     */
    private static final byte[] PATCH_DUMP_HEADER = {0x43, 0x00, 0x7A, 0x00, 0x76};

    /**
     * The patch dump ID.
     */
    private static final byte[] PATCH_DUMP_ID = {'L', 'M', ' ', ' ', '0', '0', '1', '7', ' ', ' ', 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    /**
     * The buffer to encode a System Exclusive patch dump into.
     */
    private final byte[] buffer = new byte[126];

    /**
     * The MIDI channel.
     */
    private int midiChannel;

    /**
     * The patch number.
     */
    private int patchNumber;

    /**
     * The patch name.
     */
    private String patchName;

    /**
     * The oscillator 1 wave.
     */
    private Osc1Wave osc1Wave;

    /**
     * The oscillator 1 coarse tune.
     */
    private int osc1CoarseTune;

    /**
     * The oscillator 1 fine tune.
     */
    private int osc1FineTune;

    /**
     * The oscillator 1 modulation amount.
     */
    private int osc1ModAmount;

    /**
     * The oscillator 2 wave.
     */
    private Osc2Wave osc2Wave;

    /**
     * The oscillator 2 coarse tune.
     */
    private int osc2CoarseTune;

    /**
     * The oscillator 2 fine tune.
     */
    private int osc2FineTune;

    /**
     * The oscillator 2 modulation amount.
     */
    private int osc2ModAmount;

    /**
     * The low frequency oscillator 1 wave.
     */
    private LfoWave lfo1Wave;

    /**
     * The low frequency oscillator 1 rate.
     */
    private int lfo1Rate;

    /**
     * The low frequency oscillator 1 amount.
     */
    private int lfo1Amount;

    /**
     * The low frequency oscillator 1 destination.
     */
    private LfoDestination lfo1Destination;

    /**
     * The low frequency oscillator 2 wave.
     */
    private LfoWave lfo2Wave;

    /**
     * The low frequency oscillator 2 rate.
     */
    private int lfo2Rate;

    /**
     * The low frequency oscillator 2 amount.
     */
    private int lfo2Amount;

    /**
     * The voltage controlled filter cutoff.
     */
    private int vcfCutoff;

    /**
     * The voltage controlled filter resonance.
     */
    private int vcfResonance;

    /**
     * The voltage controlled filter envelope amount.
     */
    private int vcfEnvAmount;

    /**
     * The voltage controlled filter envelope attack.
     */
    private int vcfEnvAttack;

    /**
     * The voltage controlled filter envelope decay.
     */
    private int vcfEnvDecay;

    /**
     * The voltage controlled filter envelope sustain.
     */
    private int vcfEnvSustain;

    /**
     * The voltage controlled filter envelope release.
     */
    private int vcfEnvRelease;

    /**
     * The voltage controlled amplifier envelope attack.
     */
    private int vcaEnvAttack;

    /**
     * The voltage controlled amplifier envelope decay.
     */
    private int vcaEnvDecay;

    /**
     * The voltage controlled amplifier envelope sustain.
     */
    private int vcaEnvSustain;

    /**
     * The voltage controlled amplifier envelope release.
     */
    private int vcaEnvRelease;

    /**
     * Whether the ring modulator is on.
     */
    private boolean ringModEnabled;

    /**
     * The ring modulator amount.
     */
    private int ringModAmount;

    /**
     * The portamento time.
     */
    private int portamentoTime;

    /**
     * Construct an instance that describes a Behringer JT-4000 patch.
     */
    public Patch() {
        patchName = "";
        buffer[0] = MidiUtils.SYSEX_INITIAL_BYTE;
        System.arraycopy(PATCH_DUMP_HEADER, 0, buffer, 1, PATCH_DUMP_HEADER.length);
        System.arraycopy(PATCH_DUMP_ID, 0, buffer, PATCH_DUMP_HEADER.length + 1, PATCH_DUMP_ID.length);
        buffer[125] = MidiUtils.SYSEX_TERMINATING_BYTE;
    }

    /**
     * Initialise the voice.
     */
    public void initialise() {
        patchNumber = 0;
        patchName = "";
        osc1Wave = Osc1Wave.TRIANGLE;
        osc1CoarseTune = 0;
        osc1FineTune = 0;
        osc1ModAmount = 0;
        osc2Wave = Osc2Wave.TRIANGLE;
        osc2CoarseTune = 0;
        osc2FineTune = 0;
        lfo1Wave = LfoWave.TRIANGLE;
        lfo1Rate = 0;
        lfo1Amount = 0;
        lfo1Destination = LfoDestination.OSC;
        lfo2Wave = LfoWave.TRIANGLE;
        lfo2Rate = 0;
        lfo2Amount = 0;
        vcfCutoff = 99;
        vcfResonance = 0;
        vcfEnvAmount = 0;
        vcfEnvAttack = 0;
        vcfEnvDecay = 0;
        vcfEnvSustain = 0;
        vcfEnvRelease = 0;
        vcaEnvAttack = 0;
        vcaEnvDecay = 0;
        vcaEnvSustain = 99;
        vcaEnvRelease = 0;
        ringModEnabled = false;
        ringModAmount = 0;
        portamentoTime = 0;
    }

    /**
     * Get the MIDI channel.
     *
     * @return the MIDI channel
     */
    public int getMidiChannel() {
        return midiChannel;
    }

    /**
     * Set the MIDI channel.
     *
     * @param midiChannel the MIDI channel
     */
    public void setMidiChannel(final int midiChannel) {
        this.midiChannel = midiChannel;
    }

    /**
     * Get the patch number.
     *
     * @return the patch number
     */
    public int getPatchNumber() {
        return patchNumber;
    }

    /**
     * Set the patch number.
     *
     * @param patchNumber the patch number
     */
    public void setPatchNumber(final int patchNumber) {
        this.patchNumber = patchNumber;
    }

    /**
     * Get the patch name.
     *
     * @return the patch name
     */
    public String getPatchName() {
        return patchName;
    }

    /**
     * Set the patch name.
     *
     * @param patchName the patch name
     */
    public void setPatchName(final String patchName) {
        this.patchName = patchName;
    }

    /**
     * Get the oscillator 1 wave.
     *
     * @return the voice level
     */
    public Osc1Wave getOsc1Wave() {
        return osc1Wave;
    }

    /**
     * Set the oscillator 1 wave.
     *
     * @param osc1Wave the voice level
     */
    public void setOsc1Wave(final Osc1Wave osc1Wave) {
        this.osc1Wave = osc1Wave;
    }

    /**
     * Get the oscillator 1 coarse tune.
     *
     * @return the oscillator 1 coarse tune
     */
    public int getOsc1CoarseTune() {
        return osc1CoarseTune;
    }

    /**
     * Set the oscillator 1 coarse tune.
     *
     * @param osc1CoarseTune the oscillator 1 coarse tune
     */
    public void setOsc1CoarseTune(final int osc1CoarseTune) {
        this.osc1CoarseTune = osc1CoarseTune;
    }

    /**
     * Get the oscillator 1 fine tune.
     *
     * @return the oscillator 1 fine tune
     */
    public int getOsc1FineTune() {
        return osc1FineTune;
    }

    /**
     * Set the oscillator 1 fine tune.
     *
     * @param osc1FineTune the oscillator 1 fine tune
     */
    public void setOsc1FineTune(final int osc1FineTune) {
        this.osc1FineTune = osc1FineTune;
    }

    /**
     * Get the oscillator 1 modulation amount.
     *
     * @return the oscillator 1 modulation amount
     */
    public int getOsc1ModAmount() {
        return osc1ModAmount;
    }

    /**
     * Set the oscillator 1 modulation amount.
     *
     * @param osc1ModAmount the oscillator 1 modulation amount
     */
    public void setOsc1ModAmount(final int osc1ModAmount) {
        this.osc1ModAmount = osc1ModAmount;
    }

    /**
     * Get the oscillator 2 wave.
     *
     * @return the voice level
     */
    public Osc2Wave getOsc2Wave() {
        return osc2Wave;
    }

    /**
     * Set the oscillator 2 wave.
     *
     * @param osc2Wave the voice level
     */
    public void setOsc2Wave(final Osc2Wave osc2Wave) {
        this.osc2Wave = osc2Wave;
    }

    /**
     * Get the oscillator 2 coarse tune.
     *
     * @return the oscillator 2 coarse tune
     */
    public int getOsc2CoarseTune() {
        return osc2CoarseTune;
    }

    /**
     * Set the oscillator 2 coarse tune.
     *
     * @param osc2CoarseTune the oscillator 2 coarse tune
     */
    public void setOsc2CoarseTune(final int osc2CoarseTune) {
        this.osc2CoarseTune = osc2CoarseTune;
    }

    /**
     * Get the oscillator 2 fine tune.
     *
     * @return the oscillator 2 fine tune
     */
    public int getOsc2FineTune() {
        return osc2FineTune;
    }

    /**
     * Set the oscillator 2 fine tune.
     *
     * @param osc2FineTune the oscillator 2 fine tune
     */
    public void setOsc2FineTune(final int osc2FineTune) {
        this.osc2FineTune = osc2FineTune;
    }

    /**
     * Get the oscillator 2 modulation amount.
     *
     * @return the oscillator 2 modulation amount
     */
    public int getOsc2ModAmount() {
        return osc2ModAmount;
    }

    /**
     * Set the oscillator 2 modulation amount.
     *
     * @param osc2ModAmount the oscillator 2 modulation amount
     */
    public void setOsc2ModAmount(final int osc2ModAmount) {
        this.osc2ModAmount = osc2ModAmount;
    }

    /**
     * Get the low frequency oscillator 1 wave.
     *
     * @return the low frequency oscillator 1 wave
     */
    public LfoWave getLfo1Wave() {
        return lfo1Wave;
    }

    /**
     * Set the low frequency oscillator 1 wave.
     *
     * @param lfo1Wave the low frequency oscillator 1 wave
     */
    public void setLfo1Wave(final LfoWave lfo1Wave) {
        this.lfo1Wave = lfo1Wave;
    }

    /**
     * Get the low frequency oscillator 1 rate.
     *
     * @return the low frequency oscillator 1 rate
     */
    public int getLfo1Rate() {
        return lfo1Rate;
    }

    /**
     * Set the low frequency oscillator 1 rate.
     *
     * @param lfo1Rate the low frequency oscillator 1 rate
     */
    public void setLfo1Rate(final int lfo1Rate) {
        this.lfo1Rate = lfo1Rate;
    }

    /**
     * Get the low frequency oscillator 1 amount.
     *
     * @return the low frequency oscillator 1 amount
     */
    public int getLfo1Amount() {
        return lfo1Amount;
    }

    /**
     * Set the low frequency oscillator 1 amount.
     *
     * @param lfo1Amount the low frequency oscillator 1 amount
     */
    public void setLfo1Amount(final int lfo1Amount) {
        this.lfo1Amount = lfo1Amount;
    }

    /**
     * Get the low frequency oscillator 1 destination.
     *
     * @return the low frequency oscillator 1 destination
     */
    public LfoDestination getLfo1Destination() {
        return lfo1Destination;
    }

    /**
     * Set the low frequency oscillator 1 destination.
     *
     * @param lfo1Destination the low frequency oscillator 1 destination
     */
    public void setLfo1Destination(final LfoDestination lfo1Destination) {
        this.lfo1Destination = lfo1Destination;
    }

    /**
     * Get the low frequency oscillator 2 wave.
     *
     * @return the low frequency oscillator 2 wave
     */
    public LfoWave getLfo2Wave() {
        return lfo2Wave;
    }

    /**
     * Set the low frequency oscillator 2 wave.
     *
     * @param lfo2Wave the low frequency oscillator 2 wave
     */
    public void setLfo2Wave(final LfoWave lfo2Wave) {
        this.lfo2Wave = lfo2Wave;
    }

    /**
     * Get the low frequency oscillator 2 rate.
     *
     * @return the low frequency oscillator 2 rate
     */
    public int getLfo2Rate() {
        return lfo2Rate;
    }

    /**
     * Set the low frequency oscillator 2 rate.
     *
     * @param lfo2Rate the low frequency oscillator 2 rate
     */
    public void setLfo2Rate(final int lfo2Rate) {
        this.lfo2Rate = lfo2Rate;
    }

    /**
     * Get the low frequency oscillator 2 amount.
     *
     * @return the low frequency oscillator 2 amount
     */
    public int getLfo2Amount() {
        return lfo2Amount;
    }

    /**
     * Set the low frequency oscillator 2 amount.
     *
     * @param lfo2Amount the low frequency oscillator 2 amount
     */
    public void setLfo2Amount(final int lfo2Amount) {
        this.lfo2Amount = lfo2Amount;
    }

    /**
     * Get the voltage controlled filter cutoff.
     *
     * @return the voltage controlled filter cutoff
     */
    public int getVcfCutoff() {
        return vcfCutoff;
    }

    /**
     * Set the voltage controlled filter cutoff.
     *
     * @param vcfCutoff the voltage controlled filter cutoff
     */
    public void setVcfCutoff(final int vcfCutoff) {
        this.vcfCutoff = vcfCutoff;
    }

    /**
     * Get the voltage controlled filter resonance.
     *
     * @return the voltage controlled filter resonance
     */
    public int getVcfResonance() {
        return vcfResonance;
    }

    /**
     * Set the voltage controlled filter resonance.
     *
     * @param vcfResonance the voltage controlled filter resonance
     */
    public void setVcfResonance(final int vcfResonance) {
        this.vcfResonance = vcfResonance;
    }

    /**
     * Get the voltage controlled filter envelope amount.
     *
     * @return the voltage controlled filter envelope amount
     */
    public int getVcfEnvAmount() {
        return vcfEnvAmount;
    }

    /**
     * Set the voltage controlled filter envelope amount.
     *
     * @param vcfEnvAmount the voltage controlled filter envelope amount
     */
    public void setVcfEnvAmount(final int vcfEnvAmount) {
        this.vcfEnvAmount = vcfEnvAmount;
    }

    /**
     * Get the voltage controlled filter envelope attack.
     *
     * @return the voltage controlled filter envelope attack
     */
    public int getVcfEnvAttack() {
        return vcfEnvAttack;
    }

    /**
     * Set the voltage controlled filter envelope attack.
     *
     * @param vcfEnvAttack the voltage controlled filter envelope attack
     */
    public void setVcfEnvAttack(final int vcfEnvAttack) {
        this.vcfEnvAttack = vcfEnvAttack;
    }

    /**
     * Get the voltage controlled filter envelope decay.
     *
     * @return the voltage controlled filter envelope decay
     */
    public int getVcfEnvDecay() {
        return vcfEnvDecay;
    }

    /**
     * Set the voltage controlled filter envelope decay.
     *
     * @param vcfEnvDecay the voltage controlled filter envelope decay
     */
    public void setVcfEnvDecay(final int vcfEnvDecay) {
        this.vcfEnvDecay = vcfEnvDecay;
    }

    /**
     * Get the voltage controlled filter envelope sustain.
     *
     * @return the voltage controlled filter envelope sustain
     */
    public int getVcfEnvSustain() {
        return vcfEnvSustain;
    }

    /**
     * Set the voltage controlled filter envelope sustain.
     *
     * @param vcfEnvSustain the voltage controlled filter envelope sustain
     */
    public void setVcfEnvSustain(final int vcfEnvSustain) {
        this.vcfEnvSustain = vcfEnvSustain;
    }

    /**
     * Get the voltage controlled filter envelope release.
     *
     * @return the voltage controlled filter envelope release
     */
    public int getVcfEnvRelease() {
        return vcfEnvRelease;
    }

    /**
     * Set the voltage controlled filter envelope release.
     *
     * @param vcfEnvRelease the voltage controlled filter envelope release
     */
    public void setVcfEnvRelease(final int vcfEnvRelease) {
        this.vcfEnvRelease = vcfEnvRelease;
    }

    /**
     * Get the voltage controlled amplifier envelope attack.
     *
     * @return the voltage controlled amplifier envelope attack
     */
    public int getVcaEnvAttack() {
        return vcaEnvAttack;
    }

    /**
     * Set the voltage controlled amplifier envelope attack.
     *
     * @param vcaEnvAttack the voltage controlled amplifier envelope attack
     */
    public void setVcaEnvAttack(final int vcaEnvAttack) {
        this.vcaEnvAttack = vcaEnvAttack;
    }

    /**
     * Get the voltage controlled amplifier envelope decay.
     *
     * @return the voltage controlled amplifier envelope decay
     */
    public int getVcaEnvDecay() {
        return vcaEnvDecay;
    }

    /**
     * Set the voltage controlled amplifier envelope decay.
     *
     * @param vcaEnvDecay the voltage controlled amplifier envelope decay
     */
    public void setVcaEnvDecay(final int vcaEnvDecay) {
        this.vcaEnvDecay = vcaEnvDecay;
    }

    /**
     * Get the voltage controlled amplifier envelope sustain.
     *
     * @return the voltage controlled amplifier envelope sustain
     */
    public int getVcaEnvSustain() {
        return vcaEnvSustain;
    }

    /**
     * Set the voltage controlled amplifier envelope sustain.
     *
     * @param vcaEnvSustain the voltage controlled amplifier envelope sustain
     */
    public void setVcaEnvSustain(final int vcaEnvSustain) {
        this.vcaEnvSustain = vcaEnvSustain;
    }

    /**
     * Get the voltage controlled amplifier envelope release.
     *
     * @return the voltage controlled amplifier envelope release
     */
    public int getVcaEnvRelease() {
        return vcaEnvRelease;
    }

    /**
     * Set the voltage controlled amplifier envelope release.
     *
     * @param vcaEnvRelease the voltage controlled amplifier envelope release
     */
    public void setVcaEnvRelease(final int vcaEnvRelease) {
        this.vcaEnvRelease = vcaEnvRelease;
    }

    /**
     * Get whether the ring modulator is on.
     *
     * @return whether the ring modulator is on
     */
    public boolean isRingModEnabled() {
        return ringModEnabled;
    }

    /**
     * Set whether the ring modulator is on.
     *
     * @param ringModEnabled whether the ring modulator is on
     */
    public void setRingModEnabled(final boolean ringModEnabled) {
        this.ringModEnabled = ringModEnabled;
    }

    /**
     * Get the ring modulator amount.
     *
     * @return the ring modulator amount
     */
    public int getRingModAmount() {
        return ringModAmount;
    }

    /**
     * Set the ring modulator amount.
     *
     * @param ringModAmount the ring modulator amount
     */
    public void setRingModAmount(final int ringModAmount) {
        this.ringModAmount = ringModAmount;
    }

    /**
     * Get the portamento time.
     *
     * @return the portamento time
     */
    public int getPortamentoTime() {
        return portamentoTime;
    }

    /**
     * Set the portamento time.
     *
     * @param portamentoTime the portamento time
     */
    public void setPortamentoTime(final int portamentoTime) {
        this.portamentoTime = portamentoTime;
    }

    /**
     * Serialise the voice as a System Exclusive voice dump.
     *
     * @return a System Exclusive voice dump
     * @throws InvalidMidiDataException if the voice data is invalid
     */
    public SysexMessage serialise() throws InvalidMidiDataException {
        buffer[30] = (byte) midiChannel;
        buffer[31] = (byte) patchNumber;

        return new SysexMessage(buffer, buffer.length);
    }
}
