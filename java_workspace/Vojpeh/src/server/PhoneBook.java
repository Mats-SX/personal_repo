package server;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Monitor for the server part of this program. Here are addresses and names
 * which are registered to the Vojpeh service stored and handled. The monitor
 * guarantees full thread safety with synchronization and defensive copying of
 * mutable inner fields. Lookups and reverse lookups are supported.
 * 
 */
public class PhoneBook {
	private HashMap<String, String> phoneBook; // address, name

	/**
	 * Creates a new empty PhoneBook.
	 */
	public PhoneBook() {
		phoneBook = new HashMap<String, String>();
	}

	/**
	 * Returns the user names of users that are connected.
	 * 
	 * @return the user names of users that are connected.
	 */
	public synchronized String[] getUserNames() {
		String[] users = new String[phoneBook.size()];
		if (!phoneBook.isEmpty()) {
			int i = 0;
			for (String s : phoneBook.keySet()) {
				users[i] = phoneBook.get(s);
				i++;
			}
		}
		return users;
	}

	/**
	 * Registers a user with the user name name and IP-address address to be
	 * online.
	 * 
	 * @param name
	 *            the name of the user.
	 * @param address
	 *            the IP-address of the user.
	 * @return false if user name or address already in PhoneBook, else true.
	 */
	public synchronized boolean register(String name, String address) {
		address = trimAddress(address);
		System.out.println("Adding: " + name + " - " + address);
		if (name == null || address == null) {
			System.err.println("Illegal argument value: null");
			throw new NullPointerException();
		}
		if (phoneBook.containsValue(name)) {
			if(getAddress(name).equals(address)){
				return true;
			}
			return false;
		}
		return phoneBook.put(address, name) == null;
	}

	/**
	 * Removes a user with the IP-address address from the PhoneBook.
	 * 
	 * @param address
	 *            the IP-address of the user.
	 * @return true if the address existed and was removed, else false
	 */
	public synchronized boolean unregister(String address) {
		address = trimAddress(address);
		if (address == null) {
			System.err.println("Illegal argument value: null");
			throw new NullPointerException();
		}
		return phoneBook.remove(address) != null;
	}

	/**
	 * Returns the name associated with the given address.
	 * 
	 * @param address
	 *            the address to be used to find the user name.
	 * @return the name of the user associated with the address, null if the
	 *         user does not exist in the PhoneBook.
	 */
	public synchronized String getName(String address) {
		address = trimAddress(address);
		return phoneBook.get(address);
	}

	/**
	 * Returns the IP-address associated with the given user name.
	 * 
	 * @param name
	 *            the user name to be used to find the IP-address.
	 * @return the IP-address of the user with the user name name, null if the
	 *         user does not exist in the PhoneBook.
	 */
	public synchronized String getAddress(String name) {
		Set<Entry<String, String>> set = phoneBook.entrySet();
		for (Entry<String, String> e : set) {
			if (e.getValue().equals(name)) {
				if (e.getKey().startsWith("/")) { // some addresses get a '/'
													// char appended at index 0
					return e.getKey().substring(1);
				} else {
					return e.getKey();
				}
			}
		}
		return null;
	}
	
	
	private String trimAddress(String address){
		if(address.startsWith("/")){
			address = address.substring(1);
		}
		return address;
	}
}