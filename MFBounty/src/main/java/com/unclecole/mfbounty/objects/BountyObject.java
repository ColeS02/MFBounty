package com.unclecole.mfbounty.objects;

import org.bukkit.entity.Player;

import java.util.UUID;

public class BountyObject {
    private UUID owner;
    private UUID uuid;
    private long bounty;
    private long time;

    public BountyObject(UUID owner, UUID uuid, long bounty, long time) {
        this.owner = owner;
        this.uuid = uuid;
        this.bounty = bounty;
        this.time = time;
    }

    public long getBounty() {
        return bounty;
    }

    public UUID getUUID() {
        return uuid;
    }

    public UUID getOwnerUUID() { return owner; }
}
