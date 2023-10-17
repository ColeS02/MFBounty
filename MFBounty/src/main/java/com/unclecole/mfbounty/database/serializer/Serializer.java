package com.unclecole.mfbounty.database.serializer;

import com.unclecole.mfbounty.MFBounty;

public class Serializer {


    /**
     * Saves your class to a .json file.
     */
    public void save(Object instance) {
        MFBounty.getPersist().save(instance);
    }

    /**
     * Loads your class from a json file
     *
   */
    public <T> T load(T def, Class<T> clazz, String name) {
        return MFBounty.getPersist().loadOrSaveDefault(def, clazz, name);
    }



}
