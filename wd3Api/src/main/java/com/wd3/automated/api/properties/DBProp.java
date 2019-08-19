package com.wd3.automated.api.properties;

import java.time.ZoneId;

public class DBProp {

    public static final String DB_HOST = System.getProperty("db.host");
    public static final String DB_REMOTE_HOST = System.getProperty("db.host.remote");
    public static final String DB_MAX_ACTIVE = System.getProperty("db.maxActive");
    public static final String DB_NAME = System.getProperty("db.name");
    public static final String DB_PORT = System.getProperty("db.port");
    public static final String DB_USERNAME = System.getProperty("db.username");
    public static final String DB_PASSWORD = System.getProperty("db.password");
    public static final String HOST_TIMEZONE = System.getProperty("host.timezone");
    public static final ZoneId ZONE_ID = ZoneId.of(HOST_TIMEZONE);
}
