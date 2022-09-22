package com.vertmix.core.listener;

import com.vertmix.core.api.packets.PlayerSendToServerPacket;
import com.vertmix.packager.api.PacketService;
import me.lucko.helper.Services;
import me.lucko.helper.messaging.bungee.BungeeCord;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import me.lucko.helper.utils.Players;
import org.jetbrains.annotations.NotNull;

public class ServerListener implements TerminableModule {

    private final PacketService packetService;

    public ServerListener(PacketService packetService) {
        this.packetService = packetService;
    }

    @Override
    public void setup(@NotNull TerminableConsumer terminableConsumer) {
        packetService.listen(PlayerSendToServerPacket.class, packet -> Players.get(packet.getPlayerUuid()).ifPresent(player -> Services.load(BungeeCord.class).connect(player, packet.getServerId())));
    }
}
