package com.odogwudozilla.designpatterns.commandpattern.remotecontrol;

import com.odogwudozilla.designpatterns.commandpattern.remotecontrol.garagedoor.GarageDoor;
import com.odogwudozilla.designpatterns.commandpattern.remotecontrol.garagedoor.GarageDoorOpenCommand;
import com.odogwudozilla.designpatterns.commandpattern.remotecontrol.light.Light;
import com.odogwudozilla.designpatterns.commandpattern.remotecontrol.light.LightOnCommand;

public class RemoteControlTest {
	public static void main(String[] args) {
		SimpleRemoteControl remote = new SimpleRemoteControl();

		Light light = new Light();
		GarageDoor garageDoor = new GarageDoor();

		LightOnCommand lightOn = new LightOnCommand(light);
		GarageDoorOpenCommand garageDoorOpen = new GarageDoorOpenCommand(garageDoor);

		remote.setCommand(lightOn);
		remote.buttonWasPressed();
		remote.setCommand(garageDoorOpen);
		remote.buttonWasPressed();
	}
}
