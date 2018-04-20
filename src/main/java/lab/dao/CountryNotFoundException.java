package lab.dao;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Throwable cause) {
        super(cause);
    }
}
