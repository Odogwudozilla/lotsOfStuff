package com.odogwudozilla.designpatterns.commandpattern.remotecontrol.garagedoor;

import com.odogwudozilla.designpatterns.commandpattern.remotecontrol.Command;

public class GarageDoorOpenCommand implements Command {
	GarageDoor garageDoor;

	public GarageDoorOpenCommand(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}

	@Override
	public void execute() {
		garageDoor.up();
	}
}