package com.virtualspacex.util.io.database.jdbc;

public class JdbcMetaData {
    public final String driver;
    public final String url;
    public final String username;
    public final String password;

    public JdbcMetaData(String driver, String url, String username, String password){
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
}
