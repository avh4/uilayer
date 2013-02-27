package net.avh4.framework.uilayer.designer;

import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.ExternalStorageException;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Designer {

    private final ExternalStorage externalStorage;
    private final ArrayList<Rectangle2D> placeholders = new ArrayList<>();
    private int startX;
    private int startY;

    public Designer(ExternalStorage externalStorage) {
        this.externalStorage = externalStorage;
    }

    public void dragStart(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    public void dragEnd(int x, int y) {
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
            externalStorage.writeFile(filename, output.toString());
        } catch (ExternalStorageException e) {
            e.printStackTrace();
        }
    }
}
