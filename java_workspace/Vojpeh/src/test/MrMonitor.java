package test;

public class MrMonitor {
	private byte[] message = null;
	private int bufferSize;

	public synchronized void setMessage(byte[] m) {
		while (message != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		message = m;

		notifyAll();
	}

	public synchronized void setBufferSize(int bufSize) {
		bufferSize = bufSize;
	}

	public synchronized int getBufferSize() {
		return bufferSize;
	}

	public synchronized byte[] getMessage() {
		while (message == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		byte[] temp = message;

		message = null;

		notifyAll();
		return temp;
	}
}
