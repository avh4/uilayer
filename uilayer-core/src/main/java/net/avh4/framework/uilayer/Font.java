package net.avh4.framework.uilayer;

public class Font {

    /**
     * This should only be set directly for testing
     */
    public static UILayerService service = UILayer.service;

    public static final Font PFENNIG = new Font("Pfennig.ttf");

    private final String resourceName;
    private final int size;

    public Font(String fontResourceName) {
        this(fontResourceName, 12);
    }

    public Font(String resourceName, int fontSize) {
        this.resourceName = resourceName;
        this.size = fontSize;
    }

    public int measureText(String text) {
        return service.measureText(this, text);
    }

    public int getLineHeight() {
        return service.getFontHeight(this);
    }

    public String getResourceName() {
        return resourceName;
    }

    public Font size(int fontSize) {
        return new Font(resourceName, fontSize);
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Font font = (Font) o;

        if (size != font.size) return false;
        if (resourceName != null ? !resourceName.equals(font.resourceName) : font.resourceName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resourceName != null ? resourceName.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        return "Font{" +
                "'" + resourceName + '\'' +
                " (" + size +
                ")}";
    }
}
