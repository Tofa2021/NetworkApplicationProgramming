package org.example.model;

import org.example.Nameable;

public enum TestTools implements Nameable, SelectableEnum<TestTools> {
    POSTMAN("Postman"),
    SELENIUM("Selenium"),
    SOAPUI("SoapUI"),
    DBEAVER("DBeaver"),
    ;

    private final String name;

    TestTools(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
