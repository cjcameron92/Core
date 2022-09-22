package com.vertmix.core.api.packets;

import com.vertmix.packager.api.Packet;

import java.util.UUID;

public class PlayerSendToServerPacket extends Packet {

    private UUID playerUuid;
    private String serverId;

    public PlayerSendToServerPacket() {
        super("packet-send-to-server");
    }

    public PlayerSendToServerPacket(UUID playerUuid, String serverId) {
        this.playerUuid = playerUuid;
        this.serverId = serverId;
    }

    public UUID getPlayerUuid() {
        return playerUuid;
    }

    public String getServerId() {
        return serverId;
    }
}
