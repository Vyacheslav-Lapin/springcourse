package lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component//("country")
public class SimpleCountry implements Country {

    private int id;
    private String name;
    private String codeName;

    @Autowired
    public SimpleCountry(int id, @Qualifier("countryName") String name, String codeName) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
    }

    public SimpleCountry(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }
}
