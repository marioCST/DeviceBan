package de.mariocst.deviceban;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;

public class DeviceBanConfig {
    private final DeviceBan plugin;
    private final ConfigSection config;

    private final String prefix;

    public DeviceBanConfig(DeviceBan plugin, ConfigSection config) {
        this.plugin = plugin;
        this.config = config;

        if (this.config.containsKey("prefix")) {
            this.prefix = this.config.getString("prefix");
        }
        else {
            this.prefix = "§8[§cDevice§4Ban§8] §f";
        }

        this.plugin.setPrefix(this.prefix);
    }

    public void save() {
        this.config.put("prefix", this.prefix);
        Config c = new Config(this.plugin.getDataFolder() + "/config.yml", Config.YAML);
        c.setAll(this.config);
        c.save();
    }
}
