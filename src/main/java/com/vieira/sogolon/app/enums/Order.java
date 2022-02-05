package com.vieira.sogolon.app.enums;

public enum Order {
    NAME("Name"),
    AGE("Age"),
    RACE("Race"),
    ;

    private String order;

    Order(String order) {
        this.order = order;
    }
}
