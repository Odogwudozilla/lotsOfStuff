package com.odogwudozilla.designpatterns.commandpattern.remotecontrol.light;

import com.odogwudozilla.designpatterns.commandpattern.remotecontrol.Command;

public class LightOnCommand implements Command {
	Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}
	public void execute() {
		light.on();
	}
}
