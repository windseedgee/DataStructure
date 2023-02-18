package com.zhaipz.study.datastructure.enums;

import java.util.Arrays;

public enum Source {
    CARRIER("APP", "test"),
    UNKNOWN_TEST("unknown", "test-unknown");


    private final String type;

    private final String aname;

    Source(String type, String aname) {
        this.type = type;
        this.aname = aname;
    }

    public String getType() {
        return type;
    }

    public String getaname() {
        return aname;
    }

    public String getanameByType(String type) {
        Source targetSource = Arrays.stream(Source.values())
            .filter(source -> source.type.equals(type))
            .findFirst()
            .orElse(UNKNOWN_TEST);
        return targetSource.getaname();
    }
}
