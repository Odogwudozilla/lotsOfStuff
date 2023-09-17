package com.odogwudozilla.adventOfCode.event2022.day10;

public enum SignalInstructions {

    ADD_X("addx", 2),
    NOOP("noop", 1);

    public final String instruction;
    public final int cycleTime;

    SignalInstructions( String instruction, int cycleTime) {
        this.instruction = instruction;
        this.cycleTime = cycleTime;
    }
}
