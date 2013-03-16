package net.avh4.demo.uilayer;

import com.google.common.collect.ImmutableList;
import net.avh4.framework.async.Function;
import net.avh4.framework.async.Promise;
import net.avh4.framework.data.File;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;

import java.awt.event.KeyEvent;

public class ChooserDemo implements UI {

    private final UILayerService service;
    private String chosenString;
    private File chosenFile;

    public static void main(String[] args) {
        UILayer.main(new ChooserDemo(UILayer.service));
    }

    public ChooserDemo(UILayerService service) {
        this.service = service;
    }

    @Override
    public void click(int x, int y) {
        if (x < 400) {
            chooseString();
        } else {
            chooseFile();
        }
    }

    private void chooseString() {
        Promise<String> choice = service.showChoices("Choose from among our fine strings",
                ImmutableList.of("fine", "FINE", "F1N3", "Fínè"));
        choice.whenDone(new Function<String, Void>() {
            @Override
            public Void apply(String s) {
                chosenString = s;
                return null;
            }
        });
    }

    private void chooseFile() {
        Promise<File> choice = service.showFileChooser("Choose from among our fine files");
        choice.whenDone(new Function<File, Void>() {
            @Override
            public Void apply(File file) {
                chosenFile = file;
                return null;
            }
        });
    }

    @Override
    public void key(int keyCode, boolean shift) {
        if (keyCode == KeyEvent.VK_S) {
            chooseString();
        }
        if (keyCode == KeyEvent.VK_F) {
            chooseFile();
        }
    }

    @Override
    public Scene getScene() {
        Scene scene = new Scene("Chooser Demo");
        scene.add(new ScenePlaceholder("Choose a string (s)", 5, 5, 390, 290));
        scene.add(new ScenePlaceholder("Choose a file (f)", 405, 5, 390, 290));
        if (chosenString != null) {
            scene.add(new ScenePlaceholder(chosenString, 5, 305, 390, 290));
        }
        if (chosenFile != null) {
            scene.add(new ScenePlaceholder(chosenFile.getName(), 405, 305, 390, 290));
        }
        return scene;
    }
}
