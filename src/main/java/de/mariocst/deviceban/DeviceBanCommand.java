package de.mariocst.deviceban;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class DeviceBanCommand extends Command {
    private final DeviceBan plugin;

    public DeviceBanCommand(DeviceBan plugin) {
        super("deviceban", "DeviceBan command", "deviceban", new String[]{"db"});
        this.setPermission("deviceban.deviceban");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length >= 2) {
                switch (args[0].toLowerCase()) {
                    case "config": {
                        switch (args[1].toLowerCase()) {
                            case "save": {
                                this.plugin.saveConfigs();
                                sender.sendMessage(this.plugin.getPrefix() + "Configs saved!");
                                break;
                            }
                            case "reload": {
                                this.plugin.loadConfigs();
                                sender.sendMessage(this.plugin.getPrefix() + "Configs reloaded!");
                                break;
                            }
                            default: {
                                sender.sendMessage(this.plugin.getPrefix() + "/db config <save|reload>");
                            }
                        }
                        break;
                    }
                    case "ban": {
                        Player t = this.plugin.getServer().getPlayer(args[1]);

                        if (t == null) {
                            sender.sendMessage(this.plugin.getPrefix() + "The player " + args[1] + " doesn't exist!");
                            return false;
                        }

                        if (args.length >= 3) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 2; i < args.length; i++) {
                                stringBuilder.append(args[i]).append(" ");
                            }

                            this.plugin.getBans().addBan(t, stringBuilder.toString().replaceAll("&", "§"));
                            t.kick(stringBuilder.toString().replaceAll("&", "§"), false);
                            sender.sendMessage(this.plugin.getPrefix() + "The player " + t.getName() + " got banned for " + stringBuilder.toString().replaceAll("&", "§") + "§f!");
                        }
                        else {
                            this.plugin.getBans().addBan(t, "");
                            t.kick("Kicked by admin", false);
                            sender.sendMessage(this.plugin.getPrefix() + "The player " + t.getName() + " got banned!");
                        }
                        break;
                    }
                    case "unban": {
                        this.plugin.getBans().removeBan(args[1]);
                        sender.sendMessage(this.plugin.getPrefix() + "The DeviceID " + args[1] + " got unbanned!");
                        break;
                    }
                    default: sender.sendMessage(this.plugin.getPrefix() + "/db <config|ban|unban>");
                }
            }
            else {
                sender.sendMessage(this.plugin.getPrefix() + "/db <config|ban|unban>");
            }
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission("deviceban.deviceban") || player.hasPermission("deviceban.*") || player.hasPermission("*") || player.isOp()) {
            if (args.length >= 2) {
                switch (args[0].toLowerCase()) {
                    case "config": {
                        switch (args[1].toLowerCase()) {
                            case "save": {
                                this.plugin.saveConfigs();
                                player.sendMessage(this.plugin.getPrefix() + "Configs saved!");
                                break;
                            }
                            case "reload": {
                                this.plugin.loadConfigs();
                                player.sendMessage(this.plugin.getPrefix() + "Configs reloaded!");
                                break;
                            }
                            default: {
                                player.sendMessage(this.plugin.getPrefix() + "/db config <save|reload>");
                            }
                        }
                        break;
                    }
                    case "ban": {
                        Player t = this.plugin.getServer().getPlayer(args[1]);

                        if (t == null) {
                            player.sendMessage(this.plugin.getPrefix() + "The player " + args[1] + " doesn't exist!");
                            return false;
                        }

                        if (args.length >= 3) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 2; i < args.length; i++) {
                                stringBuilder.append(args[i]).append(" ");
                            }

                            this.plugin.getBans().addBan(t, stringBuilder.toString().replaceAll("&", "§"));
                            t.kick(stringBuilder.toString().replaceAll("&", "§"), false);
                            player.sendMessage(this.plugin.getPrefix() + "The player " + t.getName() + " got banned for " + stringBuilder.toString().replaceAll("&", "§") + "§f!");
                        }
                        else {
                            this.plugin.getBans().addBan(t, "");
                            t.kick("Kicked by admin", false);
                            player.sendMessage(this.plugin.getPrefix() + "The player " + t.getName() + " got banned!");
                        }
                        break;
                    }
                    case "unban": {
                        this.plugin.getBans().removeBan(args[1]);
                        player.sendMessage(this.plugin.getPrefix() + "The DeviceID " + args[1] + " got unbanned!");
                        break;
                    }
                    default: player.sendMessage(this.plugin.getPrefix() + "/db <config|ban|unban>");
                }
            }
            else {
                player.sendMessage(this.plugin.getPrefix() + "/db <config|ban|unban>");
            }
        }
        else {
            player.sendMessage(this.plugin.getPrefix() + "§cNo permission");
        }
        return false;
    }
}
