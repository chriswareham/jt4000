# Behringer JT-4000 Editor

Cross platform editor for the Behringer JT-4000 synthesiser.

![Screenshot](/screenshot.png)

## Requirements

To build, the following are required:

* Java JDK, version 17 or later
* Apache Maven, version 3.9 or later

The editor can be built with the following command:

```
mvn package
```

To run, the following are required:

* Java JRE, version 17 or later

Once built, the editor can be run with the following command:

```
java -jar target/jt4000-*.jar
```

## Control Change

As of the latest firmware, released on 2023-12-07, the JT-4000 supports the
following control change parameters:

CC Number | Parameter                            | Range
--------- | -------------------------------------|------
`24`      | OSC1 Wave                            | 0(off)/18(triangle)/36(square)/54(PWM)/72(saw)/90(supersaw)/108(FM)/126(noise)
`115`     | OSC1 Coarse Tune                     | 0-24
`111`     | OSC1 Fine Tune                       | 0-99
`113`     | OSC1 PWM/Supersaw Detune/FM Feedback | 0-99
`25`      | OSC2 Wave                            | 0(off)/21(triangle)/42(square)/63(PWM)/84(saw)/105(noise)
`116`     | OSC2 Coarse Tune                     | 0-24
`112`     | OSC2 Fine Tune                       | 0-99
`114`     | OSC2 PWM                             | 0-99
`54`      | LFO1 Waveform                        | 0(triangle)/64(square)/127(saw)
`72`      | LFO1 Rate                            | 0-99
`70`      | LFO1 Amount                          | 0-99
`56`      | LFO1 Destination                     | 0(VCF)/64(OSC)
`55`      | LFO2 Waveform                        | 0(triangle)/64(square)/127(saw)
`73`      | LFO2 Rate                            | 0-99
`28`      | LFO2 Amount                          | 0-99
`74`      | VCF Cutoff                           | 0-99
`71`      | VCF Resonance                        | 0-99
`47`      | VCF Env Amount                       | 0-99
`85`      | VCF Attack                           | 0-99
`86`      | VCF Decay                            | 0-99
`87`      | VCF Sustain                          | 0-99
`88`      | VCF Release                          | 0-99
`81`      | VCA Attack                           | 0-99
`82`      | VCA Decay                            | 0-99
`83`      | VCA Sustain                          | 0-99
`84`      | VCA Release                          | 0-99
`96`      | Ring Mod Off/On                      | 0(off)/65(on)
`95`      | Ring Mod Amount                      | 0-99
`5`       | Portamento Time                      | 0-99
`?`       | Portamento Mode                      | ?

## Example Control Change

```
amidi -p hw:4,0,0 -S B02F40
```

Byte | Description
---- | -----------
`B0` | # CC message (MIDI channel 1)
`2F` | # CC number (filter env amount)
`40` | # CC value (49)

## Control Change Value Mapping 0-24

Parameter Value | CC Value
--------------- | -----------
00              | 0x00 (0)
01              | 0x06 (6)
02              | 0x0B (11)
03              | 0x10 (16)
04              | 0x16 (22)
05              | 0x1B (27)
06              | 0x20 (32)
07              | 0x26 (38)
08              | 0x2B (43)
09              | 0x30 (48)
10              | 0x36 (54)
11              | 0x3B (59)
12              | 0x40 (64)
13              | 0x46 (70)
14              | 0x4B (75)
15              | 0x50 (80)
16              | 0x56 (86)
17              | 0x5B (91)
18              | 0x60 (96)
19              | 0x66 (102)
20              | 0x6B (107)
21              | 0x70 (112)
22              | 0x76 (118)
23              | 0x7B (123)
24              | 0x7F (127)

## Control Change Value Mapping 0-99

Parameter Value | CC Value
--------------- | -----------
00              | 0x00,0x01
01              | 0x02
02              | 0x03
03              | 0x04,0x05
04              | 0x06
05              | 0x07
06              | 0x08
07              | 0x09,0x0A
08              | 0x0B
09              | 0x0C
10              | 0x0D,0x0E
11              | 0x0F
12              | 0x10
13              | 0x11
14              | 0x12,0x13
15              | 0x14
16              | 0x16
17              | 0x16,0x17
18              | 0x18
19              | 0x19
20              | 0x1A
21              | 0x1B,0x1C
22              | 0x1D
23              | 0x1E
24              | 0x1F,0x10
25              | 0x21
26              | 0x22
27              | 0x23
28              | 0x24,0x25
29              | 0x26
30              | 0x27
31              | 0x28,0x29
32              | 0x2A
33              | 0x2B
34              | 0x2C
35              | 0x2D,0x2E
36              | 0x2F
37              | 0x30
38              | 0x31,0x32
39              | 0x33
40              | 0x34
41              | 0x35
42              | 0x36,0x37
43              | 0x38
44              | 0x39
45              | 0x3A,0x3B
46              | 0x3C
47              | 0x3D
48              | 0x3E
49              | 0x3F,0x40
50              | 0x41
51              | 0x42
52              | 0x43
53              | 0x44,0x45
54              | 0x46
55              | 0x47
56              | 0x48,0x49
57              | 0x4A
58              | 0x4B
59              | 0x4C
60              | 0x4D,0x4E
61              | 0x4F
62              | 0x50
63              | 0x51,0x52
64              | 0x53
65              | 0x54
66              | 0x55
67              | 0x56,0x57
68              | 0x58
69              | 0x59
70              | 0x5A,0x5B
71              | 0x5C
72              | 0x5D
73              | 0x5E
74              | 0x5F,0x60
75              | 0x61
76              | 0x62
77              | 0x63,0x64
78              | 0x65
79              | 0x66
80              | 0x67
81              | 0x68,0x69
82              | 0x6A
83              | 0x6B
84              | 0x6C,0x6D
85              | 0x6E
86              | 0x6F
87              | 0x70
88              | 0x71,0x72
89              | 0x73
90              | 0x74
91              | 0x75,0x76
92              | 0x77
93              | 0x78
94              | 0x79
95              | 0x7A,0x7B
96              | 0x7C
97              | 0x7D
98              | 0x7E
99              | 0x7F

# Author

Chris Wareham chris@chriswareham.net
