package de.mariocst.deviceban;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final DeviceBan plugin;

    public JoinListener(DeviceBan plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        this.plugin.log(player.getName() + " DeviceID: " + player.getLoginChainData().getDeviceId() + " OS ID: " + player.getLoginChainData().getDeviceOS());

        if (this.plugin.getBans().containsDevice(player)) {
            if (this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()).equals("")) {
                player.kick("Kicked by admin", false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
                if (!this.plugin.getBans().containsName(player) && !this.plugin.getBans().containsUUID(player) && !this.plugin.getBans().containsXUID(player)) this.plugin.getBans().addBan(player, "");
            }
            else {
                player.kick(this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()), false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
                if (!this.plugin.getBans().containsName(player) && !this.plugin.getBans().containsUUID(player) && !this.plugin.getBans().containsXUID(player)) this.plugin.getBans().addBan(player, this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()));
            }
        }

        if (this.plugin.getBans().containsName(player)) {
            if (this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()).equals("")) {
                player.kick("Kicked by admin", false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
                if (!this.plugin.getBans().containsDevice(player) && !this.plugin.getBans().containsUUID(player) && !this.plugin.getBans().containsXUID(player)) this.plugin.getBans().addBan(player, "");
            }
            else {
                player.kick(this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()), false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
                if (!this.plugin.getBans().containsDevice(player) && !this.plugin.getBans().containsUUID(player) && !this.plugin.getBans().containsXUID(player)) this.plugin.getBans().addBan(player, this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()));
            }
        }

        if (this.plugin.getBans().containsUUID(player)) {
            if (this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()).equals("")) {
                player.kick("Kicked by admin", false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
                if (!this.plugin.getBans().containsDevice(player) && !this.plugin.getBans().containsName(player) && !this.plugin.getBans().containsXUID(player)) this.plugin.getBans().addBan(player, "");
            }
            else {
                player.kick(this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()), false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
                if (!this.plugin.getBans().containsDevice(player) && !this.plugin.getBans().containsName(player) && !this.plugin.getBans().containsXUID(player)) this.plugin.getBans().addBan(player, this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()));
            }
        }

        if (this.plugin.getBans().containsXUID(player)) {
            if (this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()).equals("")) {
                player.kick("Kicked by admin", false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
                if (!this.plugin.getBans().containsDevice(player) && !this.plugin.getBans().containsName(player) && !this.plugin.getBans().containsUUID(player)) this.plugin.getBans().addBan(player, "");
            }
            else {
                player.kick(this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()), false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
                if (!this.plugin.getBans().containsDevice(player) && !this.plugin.getBans().containsName(player) && !this.plugin.getBans().containsUUID(player)) this.plugin.getBans().addBan(player, this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()));
            }
        }
    }
}
