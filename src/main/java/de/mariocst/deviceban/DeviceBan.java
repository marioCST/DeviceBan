package de.mariocst.deviceban;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import lombok.Getter;
import lombok.Setter;

public class DeviceBan extends PluginBase {
    @Getter
    @Setter
    private String prefix;

    @Getter
    private Bans bans;

    private DeviceBanConfig deviceBanConfig;

    @Override
    public void onEnable() {
        this.loadConfigs();

        this.getServer().getCommandMap().register("deviceban", new DeviceBanCommand(this));
        this.getServer().getPluginManager().registerEvents(new JoinListener(this), this);

        this.log("DeviceBan enabled!");
    }

    @Override
    public void onDisable() {
        this.saveConfigs();

        this.log("DeviceBan enabled!");
    }

    public void loadConfigs() {
        Config c = new Config(this.getDataFolder() + "/config.yml", Config.YAML);
        this.deviceBanConfig = new DeviceBanConfig(this, c.getRootSection());

        this.bans = new Bans(this, this.getDataFolder());
    }

    public void saveConfigs() {
        this.deviceBanConfig.save();
        this.bans.save();
    }

    public void log(String msg) {
        this.getLogger().info(this.prefix + msg);
    }
}
