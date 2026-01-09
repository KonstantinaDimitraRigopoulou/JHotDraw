package org.jhotdraw.draw.figure;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingViewPainter;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DrawingViewPainterTest {
    private DrawingViewPainter painter;
    private Graphics2D mockGraphics;
    private Drawing mockDrawing;
    private DefaultDrawingView mockView;
    @Before
    public void setup() {
        painter = new DrawingViewPainter();
        mockGraphics = mock(Graphics2D.class);
        mockDrawing = mock(Drawing.class);
        mockView = mock(DefaultDrawingView.class);
    }

    @Test
    public void testDrawBackgroundDelegation() {
        // Arrange: Setup the mock view to return a specific background color
        when(mockView.getBackground()).thenReturn(Color.BLUE);
        when(mockView.getWidth()).thenReturn(800);
        when(mockView.getHeight()).thenReturn(600);

        // Act: Test the case where drawing is null (should just fill with background color)
        painter.drawBackground(mockGraphics, null, mockView);

        // Assert: Verify that Single Responsibility is working
        // The painter should have called setColor and fillRect on our mock graphics
        verify(mockGraphics).setColor(Color.BLUE);
        verify(mockGraphics).fillRect(0, 0, 800, 600);
    }
}

