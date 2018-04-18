package lab.model;

import lombok.Value;

@Value
//@Component("contact")
public class SimpleContact implements Contact {
    String type;
    String value;
}
