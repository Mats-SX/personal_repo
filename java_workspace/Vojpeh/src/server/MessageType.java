package server;

/**
 * Describes the various types of signals that can be sent to and be expected in
 * return from the Vojpeh server.
 * 
 */
public enum MessageType {

	// client operations
	REGISTER, // client wants to register
	GET_ADDRESSES, // client wants to see all addresses
	CALL, // client wants to call
	ANSWER_CALL, // called client accepts call
	REJECT_CALL, // called client rejects call
	UNREGISTER, // client wants to unregister

	// server response types
	SERVER_ACK, // operation went fine
	SERVER_NACK, // default for error
	ALREADY_REGISTERED, // client tries to register again
	NEVER_REGISTERED; // client tries to unregister when not registered
}