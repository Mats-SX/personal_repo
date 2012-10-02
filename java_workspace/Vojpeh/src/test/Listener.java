package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class Listener extends Thread {
	private TargetDataLine line;
	private MrMonitor mon;
	private byte[] data;
	AudioFormat format;

	public Listener(MrMonitor monitor, AudioFormat form) {
		mon = monitor;
		format = form;
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Can not get sound input line, aborting");
			System.exit(1);
		}

		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
		} catch (LineUnavailableException ex) {
			System.out.println("Line to microphone unavailable, aborting");
			System.exit(1);
		}
		data = new byte[line.getBufferSize() / 5];
		mon.setBufferSize(line.getBufferSize() / 5);
	}

	public void run() {
		int numBytesRead;
		line.start();
		while (true) {
			numBytesRead = line.read(data, 0, data.length);
			System.out.println("bytes read: " + numBytesRead);
			mon.setMessage(data);
		}
	}
}
