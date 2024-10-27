package ru.videmanmc.launcher;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.videmanmc.launcher.configuration.DIConfiguration;
import ru.videmanmc.launcher.gui.component.MainScreen;
import ru.videmanmc.launcher.repository.SettingsRepository;

import java.io.IOException;

public class Launcher extends Application {

    private SettingsRepository settingsRepository;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        var diProvider = new DIConfiguration().initDependencies();
        this.settingsRepository = diProvider.get(SettingsRepository.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        settingsRepository.load();

        stage.setTitle("VidemanMC Launcher %s".formatted(
                "0.0.1" //todo externalize
        ));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));

        new MainScreen().show(stage);
    }

    @Override
    public void stop() throws IOException {
        settingsRepository.unload();
    }
}
