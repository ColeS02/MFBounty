package com.unclecole.mfbounty.listeners;

import com.unclecole.mfbounty.MFBounty;
import com.unclecole.mfbounty.database.BountyData;
import com.unclecole.mfbounty.objects.BountyObject;
import com.unclecole.mfbounty.utils.C;
import com.unclecole.mfbounty.utils.PlaceHolder;
import com.unclecole.mfbounty.utils.TL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        if(!BountyData.bountyData.containsKey(player)) return;

        Player killer = event.getEntity().getKiller();

        BountyObject object = BountyData.bountyData.get(player);
        if(object.getOwnerUUID().equals(killer.getUniqueId())) return;

        MFBounty.getEcon().depositPlayer(killer, object.getBounty());

        TL.BOUNTY_COLLECTED.broadcast(
                new PlaceHolder("%player%", killer.getName()),
                new PlaceHolder("%bountyOwner%", Bukkit.getPlayer(object.getOwnerUUID()).getName()),
                new PlaceHolder("%bounty%", player.getName()),
                new PlaceHolder("%amount%", C.getFormattedMoney(object.getBounty())));

        BountyData.bountyData.remove(player);
    }
}
