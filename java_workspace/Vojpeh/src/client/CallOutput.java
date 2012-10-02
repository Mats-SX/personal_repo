package client;

import java.io.IOException;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 * Reads data from your mic and writes it to and outputstream.
 * 
 * @author Apteryx
 * 
 */
public class CallOutput extends Thread {
	private OutputStream os;
	private volatile boolean lineOpen;
	private volatile boolean muted;
	private CallManager man;
	TargetDataLine line;

	/**
	 * Creates a CallOutput that opens a line to the microphone with the given
	 * AudioFormat. The OutputStream and CallManager is saved for future use
	 * when the thread is started.
	 * 
	 * @param format
	 *            the sound format to be used to record wound from the
	 *            microphone.
	 * @param outputStream
	 *            the stream that sound is written to.
	 * @param man
	 *            manages the call.
	 */
	public CallOutput(AudioFormat format, OutputStream outputStream,
			CallManager man) {
		os = outputStream;
		this.man = man;
		lineOpen = true;
		muted = false;
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Can not get sound input line, aborting");
			man.closeCall();
			lineOpen = false;
		}

		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
		} catch (LineUnavailableException ex) {
			System.out.println("Line to microphone unavailable, aborting");
			man.closeCall();
			lineOpen = false;
		}
	}

	/**
	 * Responsible for reading sound from the microphone and writing it to the
	 * outputstream. Run until the stream closes or endCall() is invoked.
	 */
	public void run() {
		byte[] b = new byte[1024];
		line.start();
		while (lineOpen) {
			// Read sound from mic and write to outputstream
			int bytesRead = line.read(b, 0, 1024);
			if (!muted) {
				try {
					os.write(b, 0, bytesRead);
				} catch (IOException e) {
					break;
				}
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

	/**
	 * Used to mute the local users microphone.
	 */
	public void mute() {
		muted = true;
	}

	/**
	 * Used to unmute the local users microphone.
	 */
	public void unMute() {
		muted = false;
	}
}
