package net.avh4.framework.uilayer.designer;

import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.ExternalStorageException;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;

import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Designer implements UI {

    private final ExternalStorage externalStorage;
    private final ArrayList<Rectangle2D> placeholders = new ArrayList<>();
    private double startX;
    private double startY;

    public static void main(String[] args) {
        ExternalStorage externalStorage = UILayer.getExternalStorage();
        Designer designer = new Designer(externalStorage);
        UILayer.main(designer);
    }

    public Designer(ExternalStorage externalStorage) {
        this.externalStorage = externalStorage;
    }

    public void dragStart(double x, double y) {
        this.startX = x;
        this.startY = y;
    }

    public void dragEnd(double x, double y) {
        this.placeholders.add(new Rectangle2D.Double(startX, startY, x - startX, y - startY));
    }

    public void chooseMenuItem(String menuItemName) {
        String filename = "MyScene.java";
        StringBuilder output = new StringBuilder();
        output.append("" +
                "import net.avh4.framework.uilayer.SceneCreator;\n" +
                "import net.avh4.framework.uilayer.scene.Scene;\n");
        if (placeholders.size() > 0) {
            output.append("" +
                    "import net.avh4.framework.uilayer.scene.ScenePlaceholder;\n");
        }
        output.append("" +
                "\n" +
                "public class MyScene implements SceneCreator {\n" +
                "\n" +
                "    @Override\n" +
                "    public Scene getScene() {\n" +
                "        Scene scene = new Scene();\n");
        for (Rectangle2D placeholder : placeholders) {
            output
                    .append("        scene.add(new ScenePlaceholder(\"\", ")
                    .append((int) placeholder.getMinX()).append(", ")
                    .append((int) placeholder.getMinY()).append(", ")
                    .append((int) placeholder.getWidth()).append(", ")
                    .append((int) placeholder.getHeight())
                    .append("));\n");
        }
        output.append("" +
                "        return scene;\n" +
                "    }\n" +
                "}");
        try {
            externalStorage.getFile(filename).writeContents(output.toString());
        } catch (ExternalStorageException e) {
            e.printStackTrace();
        }
    }

    private boolean isDragging = false;

    @Override
    public void click(double x, double y) {
        // TODO: not tested
        if (!isDragging) {
            this.dragStart(x, y);
        } else {
            this.dragEnd(x, y);
        }
        isDragging = !isDragging;
    }

    @Override
    public void key(int keyCode, boolean shift) {
        // TODO: not tested
        if (keyCode == KeyEvent.VK_S) {
            this.chooseMenuItem("Save");
        }
    }

    @Override
    public Scene getScene() {
        // TODO: not tested
        Scene scene = new Scene();
        int i = 0;
        for (Rectangle2D placeholder : placeholders) {
            scene.add(new ScenePlaceholder("" + i));
            i++;
        }
        return scene;
    }
}
