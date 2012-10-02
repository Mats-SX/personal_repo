package client;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Reads data from an inputstream and plays the data as sound on your speakers.
 * 
 * @author Apteryx
 * 
 */
public class CallInput extends Thread {
	private InputStream is;
	private volatile boolean lineOpen;
	private CallManager man;
	SourceDataLine line;

	/**
	 * Creates a CallInput that opens a line to the speakers with the given
	 * AudioFormat. The InputStream and CallManager is saved for future use when
	 * the thread is started.
	 * 
	 * @param format
	 *            the sound-format to be used for playback to the speakers.
	 * @param inputStream
	 *            the stream that sound is read from.
	 * @param man
	 *            manages the call
	 */
	public CallInput(AudioFormat format, InputStream inputStream,
			CallManager man) {
		is = inputStream;
		this.man = man;
		lineOpen = true;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Can not get sound output line, aborting");
			man.closeCall();
			lineOpen = false;
		}
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format);
		} catch (LineUnavailableException ex) {
			System.out.println("Line to speaker unavailable, aborting");
			man.closeCall();
			lineOpen = false;
		}
	}

	/**
	 * Responsible for reading sound from the InputStream and playing it to the
	 * speakers. Run until the stream closes or endCall() is invoked.
	 */
	public void run() {

		byte[] b = new byte[1024];
		line.start();
		while (lineOpen) {
			try {
				int bytesRead = is.read(b, 0, 1024);
				if (bytesRead == -1) {
					break;
				}
				line.write(b, 0, bytesRead);
			} catch (IOException e) {
				break;
			}
		}
		line.close();
		man.closeCall();
	}

	/**
	 * Used to abort a call.
	 */
	public void endCall() {
		lineOpen = false;
	}

}
