package lab.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Data
@Component//("country")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@FieldDefaults(level = PRIVATE)
public class SimpleCountry implements Country {

    long id;
    String name;
    String codeName;

    @Autowired
    public SimpleCountry(long id, @Qualifier("countryName") String name, String codeName) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
    }

    public SimpleCountry(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }
}
