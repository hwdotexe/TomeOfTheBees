package tech.hadenw.tomeofthebees;

public enum BeePermissions {
    RUN_COMMAND ("bees.use");

    private String node;

    BeePermissions(String n){
        node = n;
    }

    public String getNode(){
        return node;
    }
}
