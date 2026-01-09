package org.jhotdraw.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static org.jhotdraw.draw.AttributeKeys.CANVAS_FILL_COLOR;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_FILL_OPACITY;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_HEIGHT;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_WIDTH;

public class DrawingViewPainter {
    private BufferedImage backgroundTile;

    public void drawBackground(Graphics2D g, Drawing drawing, DefaultDrawingView view) {
        if (drawing == null) {
            g.setColor(view.getBackground());
            g.fillRect(0, 0, view.getWidth(), view.getHeight());
        } else if (drawing.get(CANVAS_WIDTH) == null || drawing.get(CANVAS_HEIGHT) == null) {
            Color canvasColor = drawing.get(CANVAS_FILL_COLOR);
            double canvasOpacity = drawing.get(CANVAS_FILL_OPACITY);
            if (canvasColor != null) {
                if (canvasOpacity == 1) {
                    g.setColor(new Color(canvasColor.getRGB()));
                    g.fillRect(0, 0, view.getWidth(), view.getHeight());
                } else {
                    Point r = view.drawingToView(new Point2D.Double(0, 0));
                    g.setPaint(getBackgroundPaint(r.x, r.y));
                    g.fillRect(0, 0, view.getWidth(), view.getHeight());
                    g.setColor(new Color(canvasColor.getRGB() & 0xfffff | ((int) (canvasOpacity * 256) << 24), true));
                    g.fillRect(0, 0, view.getWidth(), view.getHeight());
                }
            } else {
                Point r = view.drawingToView(new Point2D.Double(0, 0));
                g.setPaint(getBackgroundPaint(r.x, r.y));
                g.fillRect(0, 0, view.getWidth(), view.getHeight());
            }
        } else {
            g.setColor(view.getBackground());
            g.fillRect(0, 0, view.getWidth(), view.getHeight());
            Rectangle r = view.drawingToView(new Rectangle2D.Double(0, 0, drawing.get(CANVAS_WIDTH), drawing.get(CANVAS_HEIGHT)));
            g.setPaint(getBackgroundPaint(r.x, r.y));
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }
    protected Paint getBackgroundPaint(
            int x, int y) {
        if (backgroundTile == null) {
            backgroundTile = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = backgroundTile.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, 16, 16);
            g.setColor(new Color(0xdfdfdf));
            g.fillRect(0, 0, 8, 8);
            g.fillRect(8, 8, 8, 8);
            g.dispose();
        }
        return new TexturePaint(backgroundTile,
                new Rectangle(x, y, backgroundTile.getWidth(), backgroundTile.getHeight()));
    }

}