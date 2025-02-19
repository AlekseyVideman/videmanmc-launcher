package ru.videmanmc.launcher.service;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import ru.videmanmc.launcher.model.value.SyncSettings;
import ru.videmanmc.launcher.service.assets.MinecraftCoreService;

/**
 * Service that initializes game launch.
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class GameRunningService {

    private final SyncSettings syncSettings;

    private final ClientService clientService;

    private final MinecraftCoreService minecraftCoreService;

    public void run(String nickname, boolean online) {
        if (online) {
            minecraftCoreService.download(syncSettings.minecraftVersion());
            clientService.synchronizeClient();
        }

        minecraftCoreService.run(syncSettings.minecraftVersion(), nickname);

        System.exit(0);
    }

}
