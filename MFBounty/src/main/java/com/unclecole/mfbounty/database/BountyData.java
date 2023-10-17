package com.unclecole.mfbounty.database;

import com.unclecole.mfbounty.MFBounty;
import com.unclecole.mfbounty.database.serializer.Serializer;
import com.unclecole.mfbounty.objects.BountyObject;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class BountyData {

    public static transient BountyData instance = new BountyData();

    public static HashMap<UUID, BountyObject> bountyData = new HashMap<>();

    public static void save() {
        new Serializer().save(instance);
    }

    public static void load() {
        new Serializer().load(instance, BountyData.class, "bountydata");
    }
}
