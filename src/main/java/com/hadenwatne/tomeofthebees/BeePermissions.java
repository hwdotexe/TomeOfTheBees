package com.hadenwatne.tomeofthebees;

public enum BeePermissions {
    RUN_COMMAND ("bees.use"),
    BEES_JAR ("bees.jar"),
    BEES_STATUS ("bees.status");

    private String node;

    BeePermissions(String n){
        node = n;
    }

    public String getNode(){
        return node;
    }
}
