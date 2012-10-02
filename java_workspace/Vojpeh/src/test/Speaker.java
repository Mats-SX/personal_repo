package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Speaker extends Thread {
	private MrMonitor mon;
	private SourceDataLine line;
	AudioFormat format;

	public Speaker(MrMonitor monitor, AudioFormat form) {
		mon = monitor;
		format = form;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Can not get sound output line, aborting");
			System.exit(1);
		}
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format);
		} catch (LineUnavailableException ex) {
			System.out.println("Line to speaker unavailable, aborting");
			System.exit(1);
		}
	}

	public void run() {
		line.start();
		while (true) {
			line.write(mon.getMessage(), 0, mon.getBufferSize());
		}

	}

}
