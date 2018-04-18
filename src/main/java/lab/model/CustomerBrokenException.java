package lab.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerBrokenException extends RuntimeException {
	CustomerBrokenException(String message) {
		super(message);
	}
}
