package org.jhotdraw.draw.figure;

import org.junit.Test;

import java.awt.geom.Rectangle2D;

import static org.junit.Assert.*;

public class EllipseFigureCopyTest {

    // Best case scenario
    @Test
    public void copy_copiesGeometry_bestCase() {
        EllipseFigure original = new EllipseFigure(10, 20, 30, 40);

        EllipseFigure copy = original.copy();

        assertNotNull(copy);
        assertNotSame(original, copy);

        Rectangle2D.Double b1 = original.getBounds();
        Rectangle2D.Double b2 = copy.getBounds();

        assertEquals(b1.x, b2.x, 0.0001);
        assertEquals(b1.y, b2.y, 0.0001);
        assertEquals(b1.width, b2.width, 0.0001);
        assertEquals(b1.height, b2.height, 0.0001);
    }

    // Boundary case: click without drag => zero size ellipse
    @Test
    public void copy_handlesZeroSize_boundary() {
        EllipseFigure original = new EllipseFigure(5, 5, 0, 0);

        EllipseFigure copy = original.copy();

        Rectangle2D.Double b = copy.getBounds();
        assertEquals(5.0, b.x, 0.0001);
        assertEquals(5.0, b.y, 0.0001);
        assertEquals(0.0, b.width, 0.0001);
        assertEquals(0.0, b.height, 0.0001);
    }
}
