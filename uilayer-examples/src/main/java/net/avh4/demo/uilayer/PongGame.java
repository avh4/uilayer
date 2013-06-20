package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.AspectRatioContext;
import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneOval;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.framework.uilayer.scene.SceneRect;
import net.avh4.math.geometry.Rect;

import java.awt.event.KeyEvent;

public class PongGame implements UI {

    private final SceneRect paddle1 = new SceneRect(Color.fromHSL(25, .6, .25));
    private final SceneRect paddle2 = new SceneRect(Color.fromHSL(25, .6, .25));
    private final SceneOval ball = new SceneOval(Color.fromHSL(50, 0, 1));
    private double paddle1Y = 300;
    private double paddle2Y = 300;
    private double lastTapX;
    private double lastTapY;
    private double ballX = 400;
    private double ballY = 300;

    public static void main(String[] args) {
        UILayer.main(new PongGame());
    }

    @Deprecated
    @Override
    public Scene getScene() {
        return null;
    }

    @Override
    public void click(Rect bounds, double x, double y) {
        click2(bounds.divide(0, 0, .5, 1), x, y);
        click2(bounds.divide(.5, 0, 1, .5), x, y);
        click2(bounds.divide(.5, .5, 1, 1), x, y);
    }

    private void click2(Rect bounds, double x, double y) {
        Rect tap = Rect.fromCenter(x, y, 0, 0);
        Rect tinyGame = bounds.aspectRatio(800, 600);
        if (tinyGame.contains(tap)) {
            Rect tapInReferenceSpace = tap.translate(Rect.ofSize(0, 0), tinyGame).scale(tinyGame.size(), Rect.ofSize(800, 600));
            click(tapInReferenceSpace.midX(), tapInReferenceSpace.midY());
        }
    }

    private void click(double referenceX, double referenceY) {
        lastTapX = referenceX;
        lastTapY = referenceY;

        // clicking the ball stops it
        // clicking a paddle changes color
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawRect(bounds, Color.BLACK);

        draw2(bounds.divide(0, 0, .5, 1), Color.fromHSL(205, .5, .5), g, fm);
        draw2(bounds.divide(.5, 0, 1, .5), Color.fromHSL(350, .3, .5), g, fm);
        draw2(bounds.divide(.5, .5, 1, 1), Color.fromHSL(350, .3, .4), g, fm);
    }

    private void draw2(Rect divide, int backgroundColor, GraphicsOperations g, FontMetricsService fm) {
        g.drawRect(divide, backgroundColor);
        AspectRatioContext context = new AspectRatioContext(800, 600, divide, g, fm);
        draw(context);
    }

    private void draw(AspectRatioContext context) {
        context.draw(Rect.fromTopLeft(0, 0, 800, 600), new SceneRect(Color.fromHSL(120, .40, .25)));
        context.draw(Rect.fromTopLeft(0, 0, 800, 600).inset(5), new ScenePlaceholder("(Grass Texture)"));
        context.draw(Rect.fromCenter(ballX, ballY, 25, 25), ball);
        context.draw(Rect.fromCenter(30, paddle1Y, 10, 105), paddle1);
        context.draw(Rect.fromCenter(800 - 30, paddle2Y, 10, 105), paddle2);

        context.draw(Rect.fromCenter(lastTapX, lastTapY, 20, 20), new SceneOval(Color.fromHSL(345, .4, .7)));
    }

    @Override
    public void key(int keyCode, boolean shift) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                paddle2Y -= 5;
                break;
            case KeyEvent.VK_DOWN:
                paddle2Y += 5;
                break;
            case KeyEvent.VK_W:
                paddle1Y -= 5;
                break;
            case KeyEvent.VK_S:
                paddle1Y += 5;
                break;
        }
    }

    @Override
    public UpdateAction time() {
        ballX += 3;
        return NEEDS_REDRAW;
    }
}
