package org.jhotdraw.draw.figure;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import org.jhotdraw.draw.AttributeKeys;
import static org.jhotdraw.draw.AttributeKeys.*;
import org.jhotdraw.draw.connector.ChopRectangleConnector;
import org.jhotdraw.draw.connector.Connector;
import org.jhotdraw.geom.Dimension2DDouble;
import org.jhotdraw.geom.Geom;
import org.jhotdraw.io.Base64;
import org.jhotdraw.util.*;
import org.jhotdraw.xml.*;

/**
 * A default implementation of {@link ImageHolderFigure} which can hold a buffered image.
 */
public class ImageFigure extends AbstractAttributedDecoratedFigure
        implements ImageHolderFigure {

    private static final long serialVersionUID = 1L;

    // Fix 1: extracted constant
    private static final String IMAGE_DATA_TAG = "imageData";

    private Rectangle2D.Double rectangle;
    private byte[] imageData;
    private transient BufferedImage bufferedImage;

    public ImageFigure() {
        this(0, 0, 0, 0);
    }

    public ImageFigure(double x, double y, double width, double height) {
        rectangle = new Rectangle2D.Double(x, y, width, height);
    }

    // Fix 3: copy constructor (instead of clone())
    public ImageFigure(ImageFigure other) {
        this.rectangle = (Rectangle2D.Double) other.rectangle.clone();
        this.imageData = other.imageData != null ? other.imageData.clone() : null;
        this.bufferedImage = other.bufferedImage;
    }


    // DRAWING
    @Override
    protected void drawFigure(Graphics2D g) {
        if (get(FILL_COLOR) != null) {
            g.setColor(get(FILL_COLOR));
            drawFill(g);
        }
        drawImage(g);
        if (get(STROKE_COLOR) != null && get(STROKE_WIDTH) > 0d) {
            g.setStroke(AttributeKeys.getStroke(this, AttributeKeys.getScaleFactorFromGraphics(g)));
            g.setColor(get(STROKE_COLOR));
            drawStroke(g);
        }
        if (get(TEXT_COLOR) != null) {
            if (get(TEXT_SHADOW_COLOR) != null
                    && get(TEXT_SHADOW_OFFSET) != null) {
                Dimension2DDouble d = get(TEXT_SHADOW_OFFSET);
                g.translate(d.width, d.height);
                g.setColor(get(TEXT_SHADOW_COLOR));
                drawText(g);
                g.translate(-d.width, -d.height);
            }
            g.setColor(get(TEXT_COLOR));
            drawText(g);
        }
    }

    @Override
    protected void drawFill(Graphics2D g) {
        Rectangle2D.Double r = (Rectangle2D.Double) rectangle.clone();
        double grow = AttributeKeys.getPerpendicularFillGrowth(this, AttributeKeys.getScaleFactorFromGraphics(g));
        Geom.grow(r, grow, grow);
        g.fill(r);
    }

    protected void drawImage(Graphics2D g) {
        BufferedImage image = getBufferedImage();
        if (image != null) {
            g.drawImage(image, (int) rectangle.x, (int) rectangle.y, (int) rectangle.width, (int) rectangle.height, null);
        } else {
            g.setStroke(new BasicStroke());
            g.setColor(Color.red);
            g.draw(rectangle);
            g.draw(new Line2D.Double(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height));
            g.draw(new Line2D.Double(rectangle.x + rectangle.width, rectangle.y, rectangle.x, rectangle.y + rectangle.height));
        }
    }

    @Override
    protected void drawStroke(Graphics2D g) {
        Rectangle2D.Double r = (Rectangle2D.Double) rectangle.clone();
        double grow = AttributeKeys.getPerpendicularDrawGrowth(this, AttributeKeys.getScaleFactorFromGraphics(g));
        Geom.grow(r, grow, grow);
        g.draw(r);
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return (Rectangle2D.Double) rectangle.clone();
    }

    @Override
    public Rectangle2D.Double getFigureDrawingArea() {
        Rectangle2D.Double r = (Rectangle2D.Double) rectangle.clone();
        double grow = AttributeKeys.getPerpendicularHitGrowth(this, 1.0);
        Geom.grow(r, grow, grow);
        return r;
    }

    @Override
    public boolean figureContains(Point2D.Double p) {
        Rectangle2D.Double r = (Rectangle2D.Double) rectangle.clone();
        double grow = AttributeKeys.getPerpendicularHitGrowth(this, 1.0) + 1d;
        Geom.grow(r, grow, grow);
        return r.contains(p);
    }

    @Override
    public void setBounds(Point2D.Double anchor, Point2D.Double lead) {
        rectangle.x = Math.min(anchor.x, lead.x);
        rectangle.y = Math.min(anchor.y, lead.y);
        rectangle.width = Math.max(0.1, Math.abs(lead.x - anchor.x));
        rectangle.height = Math.max(0.1, Math.abs(lead.y - anchor.y));
    }

    @Override
    public void transform(AffineTransform tx) {
        Point2D.Double anchor = getStartPoint();
        Point2D.Double lead = getEndPoint();
        setBounds(
                (Point2D.Double) tx.transform(anchor, anchor),
                (Point2D.Double) tx.transform(lead, lead));
    }

    @Override
    public void restoreTransformTo(Object geometry) {
        rectangle.setRect((Rectangle2D.Double) geometry);
    }

    @Override
    public Object getTransformRestoreData() {
        return rectangle.clone();
    }

    @Override
    public Collection<Action> getActions(Point2D.Double p) {
        return new LinkedList<>();
    }

    @Override
    public Connector findConnector(Point2D.Double p, ConnectionFigure prototype) {
        return new ChopRectangleConnector(this);
    }

    @Override
    public Connector findCompatibleConnector(Connector c, boolean isStartConnector) {
        return new ChopRectangleConnector(this);
    }

    // clone() removed

    @Override
    public void read(DOMInput in) throws IOException {
        super.read(in);
        if (in.getElementCount(IMAGE_DATA_TAG) > 0) {
            in.openElement(IMAGE_DATA_TAG);
            String base64Data = in.getText();
            if (base64Data != null) {
                setImageData(Base64.decode(base64Data));
            }
            in.closeElement();
        }
    }

    @Override
    public void write(DOMOutput out) throws IOException {
        super.write(out);
        if (getImageData() != null) {
            out.openElement(IMAGE_DATA_TAG);
            out.addText(Base64.encodeBytes(getImageData()));
            out.closeElement();
        }
    }

    @Override
    public void setImage(byte[] imageData, BufferedImage bufferedImage) {
        willChange();
        this.imageData = imageData;
        this.bufferedImage = bufferedImage;
        changed();
    }

    public void setImageData(byte[] imageData) {
        willChange();
        this.imageData = imageData;
        this.bufferedImage = null;
        changed();
    }

    @Override
    public void setBufferedImage(BufferedImage image) {
        willChange();
        this.imageData = null;
        this.bufferedImage = image;
        changed();
    }

    @Override
    public BufferedImage getBufferedImage() {
        if (bufferedImage == null && imageData != null) {
            try {
                bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
            } catch (IOException e) {
                e.printStackTrace();
                imageData = null;
            }
        }
        return bufferedImage;
    }

    @Override
    public byte[] getImageData() {
        if (bufferedImage != null && imageData == null) {
            try (ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
                ImageIO.write(bufferedImage, "PNG", bout);
                imageData = bout.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                bufferedImage = null;
            }
        }
        return imageData;
    }

    @Override
    public void loadImage(File file) throws IOException {
        try (InputStream in = new FileInputStream(file)) {
            loadImage(in);
        } catch (Exception e) { // Fix 4
            ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
            IOException ioException = new IOException(labels.getFormatted("file.failedToLoadImage.message", file.getName()));
            ioException.initCause(e);
            throw ioException;
        }
    }

    @Override
    public void loadImage(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[512];
        int bytesRead;
        while ((bytesRead = in.read(buf)) > 0) {
            baos.write(buf, 0, bytesRead);
        }
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
        if (img == null) {
            ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
            throw new IOException(labels.getFormatted("file.failedToLoadImage.message", in.toString()));
        }
        imageData = baos.toByteArray();
        bufferedImage = img;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        getImageData();
        out.defaultWriteObject();
    }
}
