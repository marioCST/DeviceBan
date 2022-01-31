package de.mariocst.deviceban;

import cn.nukkit.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Bans {
    private final DeviceBan plugin;

    private final File file;

    private final List<String> bans = new ArrayList<>();

    public Bans(DeviceBan plugin, File path) {
        this.plugin = plugin;

        if (!path.exists()) path.mkdirs();

        this.file = new File(path + "/bans.txt");

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            }
            catch (IOException ignored) { }
        }

        try {
            this.bans.addAll(Files.readAllLines(this.file.toPath()));
        }
        catch (IOException ignored) { }
    }

    public void addBan(Player player, String reason) {
        this.bans.add(player.getLoginChainData().getDeviceId() + ":" + player.getName() + ":" + reason + ":" + player.getUniqueId().toString() + ":" + player.getLoginChainData().getXUID());
        this.save();
    }

    public void addDeviceId(String id, String reason) {
        this.bans.add(id + ":Unknown:" + reason + ":Unknown:Unknown");
        this.save();
    }

    public boolean containsDevice(Player player) {
        for (String string : this.bans) {
            String[] strings = string.split(":");

            if (strings[0].equals(player.getLoginChainData().getDeviceId())) return true;
        }
        return false;
    }

    public boolean containsName(Player player) {
        for (String string : this.bans) {
            String[] strings = string.split(":");

            if (strings[1].equals(player.getLoginChainData().getDeviceId())) return true;
        }
        return false;
    }

    public boolean containsUUID(Player player) {
        for (String string : this.bans) {
            String[] strings = string.split(":");

            if (strings[3].equals(player.getUniqueId().toString())) return true;
        }
        return false;
    }

    public boolean containsXUID(Player player) {
        for (String string : this.bans) {
            String[] strings = string.split(":");

            if (strings[4].equals(player.getLoginChainData().getXUID())) return true;
        }
        return false;
    }

    public ArrayList<String> getDeviceIds() {
        ArrayList<String> list = new ArrayList<>();

        for (String string : this.bans) {
            String[] strings = string.split(":");
            list.add(strings[0]);
        }

        return list;
    }

    public String getReason(String deviceId) {
        for (String string : this.bans) {
            String[] strings = string.split(":");

            if (strings[0].equals(deviceId)) {
                if (!strings[2].equals("")) return strings[2];
            }
        }
        return "";
    }

    public void removeBan(String deviceId) {
        this.bans.removeIf(string -> string.startsWith(deviceId));
        this.save();
    }

    public void save() {
        try {
            Files.write(this.file.toPath(), this.bans);
        }
        catch (IOException e) {
            e.printStackTrace();
            this.plugin.log("Couldn't write to ban file!");
        }
    }
}
