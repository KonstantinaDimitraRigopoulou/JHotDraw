package org.jhotdraw.draw.figure;

import java.awt.geom.Point2D;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import org.jhotdraw.draw.Drawing;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class FigureRefactoringTest {
    private RectangleFigure rectangleFigure;
    private Drawing mockDrawing;

    @Before
    public void setup() {
        
        mockDrawing = mock(Drawing.class);
        rectangleFigure = new RectangleFigure();
    }
    @Test
    public void verifyInformationExpertEnforcesMinimumDelta() {
   
        Point2D.Double anchor = new Point2D.Double(100, 100);
        Point2D.Double lead = new Point2D.Double(100, 100);

      
        rectangleFigure.setBounds(anchor, lead);

        
        assertThat(rectangleFigure.getBounds().width)
            .as("Width must be normalized to the framework's 0.1 minimum")
            .isCloseTo(0.1, within(0.001));

        assertThat(rectangleFigure.getBounds().height)
            .as("Height must be normalized to the framework's 0.1 minimum")
            .isCloseTo(0.1, within(0.001));
    }

}
