package com.odogwudozilla.designpatterns.commandpattern.remotecontrol.garagedoor;

public class GarageDoor {
	void up() {
		System.out.println("Garage Door is opening");
	}
	void down() {
		System.out.println("Garage Door is closing");
	}
	void stop() {
		System.out.println("Garage Door is stoping");
	}
	void lightOn() {
		System.out.println("Garage Door light is on");
	}
	void lightOff() {
		System.out.println("Garage Door light is off");
	}

}
