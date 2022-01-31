package de.mariocst.deviceban;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import de.mariocst.deviceban.formutils.custom.CustomForm;

import java.util.ArrayList;

public class BanForm {
    public void openBanForm(DeviceBan plugin, Player player) {
        ArrayList<String> players = new ArrayList<>();

        players.add("DeviceID");

        for (Player player1 : plugin.getServer().getOnlinePlayers().values()) {
            if (player1 != player) players.add(player1.getName());
        }

        CustomForm form = new CustomForm.Builder(plugin, "§cBan")
                .addElement(new ElementDropdown("§6Players", players, players.indexOf("DeviceID")))
                .addElement(new ElementInput("§6Reason", "Hacking"))
                .addElement(new ElementInput("§6DeviceID (set Players to DeviceID)", player.getLoginChainData().getDeviceId()))
                .onSubmit((e, r) -> {
                    if (r.getDropdownResponse(0).getElementContent().equals("DeviceID")) {
                        if (r.getInputResponse(1).isEmpty()) {
                            player.sendMessage(plugin.getPrefix() + "§cPlease enter a device id!");
                            return;
                        }

                        plugin.getBans().addDeviceId(r.getInputResponse(1), r.getInputResponse(0).isEmpty() ? "" : r.getInputResponse(0));
                        if (r.getInputResponse(0).isEmpty()) {
                            player.sendMessage(plugin.getPrefix() + "§cThe device id §6" + r.getInputResponse(1) + " §cgot banned!");
                        }
                        else {
                            player.sendMessage(plugin.getPrefix() + "§cThe device id §6" + r.getInputResponse(1) + " §cgot banned for §6" + r.getInputResponse(0) + "§c!");
                        }
                        return;
                    }

                    plugin.getBans().addBan(plugin.getServer().getPlayer(r.getDropdownResponse(0).getElementContent()), r.getInputResponse(0).isEmpty() ? "" : r.getInputResponse(0));
                    if (r.getInputResponse(0).isEmpty()) {
                        player.sendMessage(plugin.getPrefix() + "§cThe player §6" + r.getDropdownResponse(0).getElementContent() + " §cgot banned!");
                    }
                    else {
                        player.sendMessage(plugin.getPrefix() + "§cThe player §6" + r.getDropdownResponse(0).getElementContent() + " §cgot banned for §6" + r.getInputResponse(0) + "§c!");
                    }
                })
                .build();
        form.send(player);
    }

    public void openUnbanForm(DeviceBan plugin, Player player) {
        CustomForm form = new CustomForm.Builder(plugin, "§cUnban")
                .addElement(new ElementDropdown("§6Device IDs", plugin.getBans().getDeviceIds()))
                .onSubmit((e, r) -> {
                    plugin.getBans().removeBan(r.getDropdownResponse(0).getElementContent());
                    player.sendMessage(plugin.getPrefix() + "§aThe device id §6" + r.getDropdownResponse(0).getElementContent() + " §agot unbanned!");
                })
                .build();
        form.send(player);
    }
}
