package de.mariocst.deviceban;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bans {
    private final DeviceBan plugin;
    private final ConfigSection config;

    private final HashMap<String, List<String>> bans = new HashMap<>();

    public Bans(DeviceBan plugin, ConfigSection config) {
        this.plugin = plugin;
        this.config = config;

        if (this.config.containsKey("bans")) {
            for (String string : this.config.getSection("bans").getKeys()) {
                this.bans.put(string, (List<String>) this.config.getSection("bans").get(string));
            }
        }
    }

    public void addBan(Player player, String reason) {
        List<String> list = new ArrayList<>();

        list.add("name: " + player.getName());
        list.add("reason: " + reason);

        this.bans.put(player.getLoginChainData().getDeviceId(), list);
        this.save();
    }

    public boolean containsDevice(Player player) {
        return this.bans.containsKey(player.getLoginChainData().getDeviceId()) || this.config.getSection("bans").containsKey(player.getLoginChainData().getDeviceId());
    }

    public boolean containsName(Player player) {
        for (String ban : this.bans.keySet()) {
            return this.bans.get(ban).contains("name: " + player.getName());
        }

        for (String ban : this.config.getSection("bans").keySet()) {
            List<String> list = (List<String>) this.config.getSection("bans").get(ban);

            return list.contains("name: " + player.getName());
        }

        return false;
    }

    public String getReason(String deviceId) {
        for (String ban : this.bans.keySet()) {
            for (String string : this.bans.get(ban)) {
                if (string.startsWith("reason: ")) {
                    return string.replaceAll("reason: ", "");
                }
            }
        }

        for (String ban : this.config.getSection("bans").keySet()) {
            List<String> list = (List<String>) this.config.getSection("bans").get(ban);

            for (String s : list) {
                if (s.startsWith("reason: ")) {
                    return s.replaceAll("reason: ", "");
                }
            }
        }

        return "";
    }

    public void removeBan(String deviceId) {
        this.bans.remove(deviceId);
        this.save();
    }

    public void save() {
        if (!this.bans.isEmpty()) this.config.put("bans", this.bans);
        Config c = new Config(this.plugin.getDataFolder() + "/bans.yml", Config.YAML);
        c.setAll(this.config);
        c.save();
    }
}
