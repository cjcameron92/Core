package com.vertmix.core.api;

import com.vertmix.profiles.api.Profile;
import me.lucko.helper.serialize.Position;
import org.jetbrains.annotations.NotNull;

public interface TeleportService {

    void teleport(@NotNull Profile profile, @NotNull String serverId, @NotNull Position position);
}
