package com.vertmix.core.command;

import com.vertmix.core.api.TeleportService;
import com.vertmix.core.api.packets.PlayerTeleportPacket;
import com.vertmix.packager.api.PacketService;
import com.vertmix.profiles.api.Profile;
import me.lucko.helper.Commands;
import me.lucko.helper.serialize.Position;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand implements TerminableModule {

    private final TeleportService teleportService;

    public TeleportCommand(TeleportService teleportService) {
        this.teleportService = teleportService;
    }

    @Override
    public void setup(@NotNull TerminableConsumer consumer) {
        Commands.create().assertOp().assertUsage("<profile> <server> <world> <x> <y> <z>").handler(context -> {
            final Profile profile = context.arg(0).parseOrFail(Profile.class);
            final String serverId = context.arg(1).parseOrFail(String.class);
            final String world = context.arg(2).parseOrFail(String.class);
            final int x = context.arg(3).parseOrFail(Integer.class);
            final int y = context.arg(4).parseOrFail(Integer.class);
            final int z = context.arg(5).parseOrFail(Integer.class);

            final Position position = Position.of(x, y, z, world);
            teleportService.teleport(profile, serverId, position);
            context.reply("&aSending " + profile.getPlayerName() + " to " + serverId + " world " + world + " x " + x + ", y " + y + ", " + z);
        }).registerAndBind(consumer, "teleport", "tpo");
    }
}
