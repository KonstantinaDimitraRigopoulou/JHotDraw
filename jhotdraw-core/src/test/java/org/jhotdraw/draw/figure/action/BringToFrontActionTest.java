package org.jhotdraw.draw.action;

import org.junit.Test;
import static org.mockito.Mockito.*;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;

import java.util.*;

public class BringToFrontActionTest {

    @Test
    public void bringToFront_movesFigureToTop() {
        DrawingView view = mock(DrawingView.class);
        Drawing drawing = mock(Drawing.class);
        Figure figure = mock(Figure.class);

        when(view.getDrawing()).thenReturn(drawing);
        when(drawing.sort(anyCollection()))
                .thenReturn(Collections.singletonList(figure));

        BringToFrontAction.bringToFront(view,
                Collections.singletonList(figure));

        verify(drawing).bringToFront(figure);
    }

    @Test
    public void bringToFront_withEmptySelection_doesNothing() {
        DrawingView view = mock(DrawingView.class);
        Drawing drawing = mock(Drawing.class);

        when(view.getDrawing()).thenReturn(drawing);
        when(drawing.sort(anyCollection()))
                .thenReturn(Collections.emptyList());

        BringToFrontAction.bringToFront(view,
                Collections.emptyList());

        verify(drawing, never()).bringToFront(any());
    }
}
