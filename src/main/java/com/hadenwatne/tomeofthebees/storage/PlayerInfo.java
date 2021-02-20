package com.hadenwatne.tomeofthebees.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Serialized class to record data for this player.
 */
public class PlayerInfo {
    private String _uuid;
    private List<EffectType> _effects;

    public PlayerInfo(String UUID){
        _uuid = UUID;
        _effects = new ArrayList<EffectType>();
    }

    public String getUUID(){
        return _uuid;
    }

    public List<EffectType> getEffects(){
        return _effects;
    }

    public boolean hasEffect(EffectType t){
        for(EffectType et : _effects){
            if(et == t){
                return true;
            }
        }

        return false;
    }

    public void addEffect(EffectType t){
        _effects.add(t);
    }

    public void removeEffect(EffectType t){
        _effects.remove(t);
    }

    public void removeEffects(){
        _effects.clear();
    }
}
