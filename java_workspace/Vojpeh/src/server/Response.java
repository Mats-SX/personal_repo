package server;

/**
 * Server response model with support for the exported protocol of Vojpeh. A
 * requesting client will always receive an instance of this class.
 * 
 */
public class Response implements Protocol {

	/*
	 * Default generated
	 */
	private static final long serialVersionUID = 2721734143468729955L;

	private MessageType type;
	private String[] users;
	private String data;

	/**
	 * Creates a new Response with the given type.
	 * 
	 * @param type
	 *            the type of the Response.
	 */
	public Response(MessageType type) {
		this.type = type;
	}

	/**
	 * Creates a new Response with the given type and array.
	 * 
	 * @param serverAck
	 *            the type of the Response.
	 * @param users
	 *            array of strings to be sent with the response.
	 */
	public Response(MessageType serverAck, String[] users) {
		this.type = serverAck;
		this.users = users;
	}

	/**
	 * Creates a new Response with the given type and data.
	 * 
	 * @param type
	 *            the type of the Response.
	 * @param data
	 *            the data to be sent with the Response.
	 */
	public Response(MessageType type, String data) {
		this.type = type;
		this.data = data;
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
		return users;
	}

}
