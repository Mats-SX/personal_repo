package server;

import java.io.Serializable;

/**
 * The Vojpeh exported protocol. Implementing classes of this type are sent
 * across the streams used by the Vojpeh system.
 * 
 */
public interface Protocol extends Serializable {

	/**
	 * Returns the type of the Protocol.
	 * 
	 * @return the type of the Protocol.
	 */
	public MessageType getType();

	/**
	 * Returns the data that was sent in the Protocol.
	 * 
	 * @return the data that was sent in the Protocol.
	 */
	public String getData();

	/**
	 * Returns an array of user names that was sent in the Protocol.
	 * 
	 * @return an array of user names that was sent in the Protocol.
	 */
	public String[] getUserNames();

}