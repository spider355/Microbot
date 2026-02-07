package net.runelite.client.plugins.microbot.tutorialisland;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameLoop;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
        name = "Tutorial Island Bot",
        description = "Automatically completes Tutorial Island with character creation",
        tags = {"tutorial", "island", "automation", "microbot", "ironman"}
)
@Slf4j
public class TutorialIslandPlugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private TutorialIslandConfig config;

    @Inject
    private TutorialIslandScript script;

    @Inject
    private TutorialIslandOverlay overlay;

    @Inject
    private OverlayManager overlayManager;

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(overlay);
        script.run(config);
        log.info("Tutorial Island Bot started");
    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(overlay);
        script.stop();
        log.info("Tutorial Island Bot stopped");
    }

    @Subscribe
    public void onGameLoop(GameLoop event) {
        // Game loop handling if needed
    }

    @Provides
    TutorialIslandConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(TutorialIslandConfig.class);
    }
}
