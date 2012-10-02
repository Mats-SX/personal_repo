package client;

public class UserListUpdater extends Thread {
	private int sleepPeriod;
	private GUI gui;
	private boolean gogo;

	/**
	 * Creates a UserListUpdater that runs updateUserList() in the GUI at the
	 * period sleepPeriod.
	 * 
	 * @param sleepPeriod
	 *            the time to wait before calling updateUserList().
	 * @param gui
	 *            the GUI to update.
	 */
	public UserListUpdater(int sleepPeriod, GUI gui) {
		this.sleepPeriod = sleepPeriod;
		this.gui = gui;
		gogo = true;
	}

	/**
	 * Stops the thread from updating.
	 */
	public synchronized void stopUpdating() {
		gogo = false;
	}

	/**
	 * Responsible for updating the GUI with updateUserList(). Sleeps
	 * sleepPeriod between invocations.
	 */
	public void run() {
		while (gogo) {
			try {
				gui.updateUserList();
				Thread.sleep(sleepPeriod);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}