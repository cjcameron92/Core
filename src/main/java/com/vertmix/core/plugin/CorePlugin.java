package com.vertmix.core.plugin;

import com.vertmix.core.api.TeleportService;
import com.vertmix.core.command.TeleportCommand;
import com.vertmix.core.listener.ServerListener;
import com.vertmix.core.listener.TeleportListener;
import com.vertmix.core.service.SimpleTeleportService;
import com.vertmix.packager.api.PacketService;
import me.lucko.helper.Services;
import me.lucko.helper.plugin.ExtendedJavaPlugin;

public class CorePlugin extends ExtendedJavaPlugin {

    private PacketService packetService;

    private TeleportService teleportService;

    @Override
    protected void enable() {
        packetService = Services.load(PacketService.class);

        teleportService = new SimpleTeleportService(packetService);

        bindModule(new TeleportCommand(teleportService));

        bindModule(new ServerListener(packetService));
        bindModule(new TeleportListener(packetService));
    }
}
