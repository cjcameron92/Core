package com.vertmix.core.service;

import com.vertmix.core.api.TeleportService;
import com.vertmix.core.api.packets.PlayerSendToServerPacket;
import com.vertmix.core.api.packets.PlayerTeleportPacket;
import com.vertmix.packager.api.PacketService;
import com.vertmix.profiles.api.Profile;
import me.lucko.helper.serialize.Position;
import org.jetbrains.annotations.NotNull;

public class SimpleTeleportService implements TeleportService {

    private final PacketService packetService;

    public SimpleTeleportService(PacketService packetService) {
        this.packetService = packetService;
    }

    @Override
    public void teleport(@NotNull Profile profile, @NotNull String serverId, @NotNull Position position) {
        packetService.send(new PlayerSendToServerPacket(profile.getPlayerUuid(), serverId));
        packetService.send(new PlayerTeleportPacket(profile.getPlayerUuid(), serverId, position));
    }
}
