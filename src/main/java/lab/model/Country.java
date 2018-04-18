package lab.model;

public interface Country {
    int getId();

    String getName();

    String getCodeName();

    SimpleCountry setId(int id);

    SimpleCountry setName(String name);

    SimpleCountry setCodeName(String codeName);
}
