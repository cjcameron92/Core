package com.vertmix.core.listener;

import com.vertmix.core.api.packets.PlayerTeleportPacket;
import com.vertmix.packager.api.PacketService;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import me.lucko.helper.utils.Players;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class TeleportListener implements TerminableModule {

    private final PacketService packetService;

    public TeleportListener(PacketService packetService) {
        this.packetService = packetService;
    }

    @Override
    public void setup(@NotNull TerminableConsumer terminableConsumer) {
        packetService.listen(PlayerTeleportPacket.class, packet -> Players.get(packet.getPlayerUuid()).ifPresent(player -> {
            final String serverId = packet.getServerId();
            if (Bukkit.getServer().getName().equalsIgnoreCase(serverId)) {
                player.teleportAsync(packet.getPosition().toLocation());
            }
        }));
    }
}
