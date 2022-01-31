package de.mariocst.deviceban.formutils;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindowCustom;
import de.mariocst.deviceban.formutils.custom.CustomForm;

import java.util.HashMap;

public class FormHandler {
    public static HashMap<String, CustomForm> customPending = new HashMap<>();

    public static void handleCustom(Player player, FormWindowCustom form) {
        if (customPending.containsKey(player.getName())) {
            CustomForm cform = customPending.get(player.getName());
            customPending.remove(player.getName());

            if (form.getResponse() == null) {
                cform.setClosed(player);
                return;
            }

            cform.setSubmitted(player, form.getResponse());
        }
    }
}
