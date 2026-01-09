package org.jhotdraw.draw.figure;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RectangleFigureUnitTest {

    @Test
    public void testInformationExpertBoundsNormalization() {
        // Arrange
        RectangleFigure figure = new RectangleFigure();
        Point2D.Double anchor = new Point2D.Double(100, 100);
        Point2D.Double lead = new Point2D.Double(100, 100);

        /**Act
        *This calls your refactored logic in AbstractFigure/RectangleFigure*/
        figure.setBounds(anchor, lead);

        /**Assert
        *We verify the "Information Expert" correctly enforced the 0.1 delta
        *instead of leaving the width at 0.0*/
        double expectedDelta = 0.1;
        assertEquals("Width should be normalized to 0.1", expectedDelta, figure.getBounds().width, 0.001);
        assertEquals("Height should be normalized to 0.1", expectedDelta, figure.getBounds().height, 0.001);
    }
}