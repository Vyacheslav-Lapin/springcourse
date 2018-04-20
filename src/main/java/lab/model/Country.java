package lab.model;

public interface Country {
    long getId();

    String getName();

    String getCodeName();

    SimpleCountry setId(long id);

    SimpleCountry setName(String name);

    SimpleCountry setCodeName(String codeName);
}
