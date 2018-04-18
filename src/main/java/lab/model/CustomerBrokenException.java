package lab.model;


public class CustomerBrokenException extends RuntimeException {
	public CustomerBrokenException(String message) {
		super(message);
	}

	public CustomerBrokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerBrokenException(Throwable cause) {
		super(cause);
	}
}
