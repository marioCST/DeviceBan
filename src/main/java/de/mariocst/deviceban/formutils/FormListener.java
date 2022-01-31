package de.mariocst.deviceban.formutils;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;

public class FormListener implements Listener {
    @EventHandler
    public void onResponse(PlayerFormRespondedEvent event) {
        if (event.getWindow() instanceof FormWindowCustom) FormHandler.handleCustom(event.getPlayer(), (FormWindowCustom) event.getWindow());
    }
}
