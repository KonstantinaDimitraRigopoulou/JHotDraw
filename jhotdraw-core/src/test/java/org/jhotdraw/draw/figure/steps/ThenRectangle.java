package org.jhotdraw.draw.figure.steps;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.figure.RectangleFigure;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.tngtech.jgiven.Stage;

public class ThenRectangle extends Stage<ThenRectangle> {

    public ThenRectangle a_rectangle_should_appear_at_100_100_with_size_100_100(Drawing drawing) {
        // Verify that a figure was actually added to the drawing model
        assertFalse("Drawing should not be empty", drawing.getChildren().isEmpty());
        
        RectangleFigure rect = (RectangleFigure) drawing.getChildren().get(0);
        
        assertEquals(100.0, rect.getBounds().getX(), 0.1);
        assertEquals(100.0, rect.getBounds().getY(), 0.1);
        assertEquals(100.0, rect.getBounds().getWidth(), 0.1);
        assertEquals(100.0, rect.getBounds().getHeight(), 0.1);
        
        return self();
    }
}