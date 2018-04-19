package lab.dao;

public class DaoConstants {
    public static final String CREATE_COUNTRY_TABLE_SQL = "create table country(id identity, name varchar (255), code_name varchar (255))";
    public static final String DROP_COUNTRY_TABLE_SQL = "drop table country";

    private DaoConstants() {
    }
}
