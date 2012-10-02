package test;

import javax.sound.sampled.AudioFormat;

public class Majneh {

	public static void main(String[] args) {
		MrMonitor mon = new MrMonitor();
		AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
		Listener list = new Listener(mon, format);
		Speaker speak = new Speaker(mon, format);
		list.start();
		speak.start();

	}
}
