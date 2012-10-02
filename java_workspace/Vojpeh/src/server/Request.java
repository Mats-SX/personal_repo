package server;

/**
 * Client request model with support for the Vojpeh exported protocol. 
 * 
 */
public class Request implements Protocol {

	/*
	 * Default generated
	 */
	private static final long serialVersionUID = 1543129818844632167L;

	private MessageType type;
	private String data;

	/**
	 * Creates a new Request with the given type.
	 * 
	 * @param type
	 *            the type of the Request.
	 */
	public Request(MessageType type) {
		this.type = type;
	}

	/**
	 * Creates a new Request with the given type and name.
	 * 
	 * @param type
	 *            the type of the Request
	 * @param name
	 *            The name of the user that sent the Request.
	 */
	public Request(MessageType type, String name) {
		this.type = type;
		this.data = name;
	}

	@Override
	public MessageType getType() {
		return type;
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public String[] getUserNames() {
		return null;
	}
}