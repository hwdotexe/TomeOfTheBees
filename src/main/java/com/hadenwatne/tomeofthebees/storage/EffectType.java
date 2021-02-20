package com.hadenwatne.tomeofthebees.storage;

public enum EffectType {
    ALLERGY,
    HONEY_SMELL,
    QUEEN_BEE;

    public static boolean containsValue(String value){
        for(EffectType t : values()){
            if(t.name().equalsIgnoreCase(value)){
                return true;
            }
        }

        return false;
    }
}
