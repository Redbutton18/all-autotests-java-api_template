package com.wd3.automated.api.properties;

public enum PropertySource {
    USER("user.properties"),
    DB("db.properties");

    public String sourceFile;

    public PropertySource[] getOptions(){
        return PropertySource.values();
    }

    PropertySource(String sourceFile) {
        this.sourceFile = sourceFile;
    }

}

