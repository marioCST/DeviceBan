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

        this.plugin.log(player.getName() + " " + player.getLoginChainData().getDeviceId());

        if (this.plugin.getBans().containsDevice(player) || this.plugin.getBans().containsName(player)) {
            if (this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()).equals("")) {
                player.kick("Kicked by admin", false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
            }
            else {
                player.kick(this.plugin.getBans().getReason(player.getLoginChainData().getDeviceId()), false);
                this.plugin.log("The player " + player.getName() + " tried to connect while being device banned!");
            }
        }
    }
}
