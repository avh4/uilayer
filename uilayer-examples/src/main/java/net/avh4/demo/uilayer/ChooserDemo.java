package net.avh4.demo.uilayer;

import com.google.common.collect.ImmutableList;
import net.avh4.framework.async.Function;
import net.avh4.framework.async.Promise;
import net.avh4.framework.data.File;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.Rect;

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
    public void click(Rect bounds, double x, double y) {
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

    public Scene getScene(Rect bounds) {
        Scene scene = new Scene("Chooser Demo");
        scene.add(bounds.divide(0, 0, .5, .5).inset(5), new ScenePlaceholder("Choose a string (s)"));
        scene.add(bounds.divide(.5, 0, 1, .5).inset(5), new ScenePlaceholder("Choose a file (f)"));
        if (chosenString != null) {
            scene.add(bounds.divide(0, .5, .5, 1).inset(5), new ScenePlaceholder(chosenString));
        }
        if (chosenFile != null) {
            scene.add(bounds.divide(.5, .5, 1, 1).inset(5), new ScenePlaceholder(chosenFile.getName()));
        }
        return scene;
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        getScene(bounds).draw(bounds, g, fm);
    }

    @Deprecated
    @Override
    public Scene getScene() {
        return null;
    }

    @Override
    public UpdateAction time() {
        return NO_UPDATE;
    }
}
