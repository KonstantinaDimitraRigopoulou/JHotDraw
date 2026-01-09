package org.jhotdraw.draw.action;

import org.junit.Test;
import static org.mockito.Mockito.*;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;

import java.util.*;

public class SendToBackActionTest {

    @Test
    public void sendToBack_movesFigureToBottom() {
        DrawingView view = mock(DrawingView.class);
        Drawing drawing = mock(Drawing.class);
        Figure figure = mock(Figure.class);

        when(view.getDrawing()).thenReturn(drawing);
        when(drawing.sort(anyCollection()))
                .thenReturn(Collections.singletonList(figure));

        SendToBackAction.sendToBack(view, Collections.singletonList(figure));

        verify(drawing).sendToBack(figure);
    }

    @Test
    public void sendToBack_withEmptySelection_doesNothing() {
        DrawingView view = mock(DrawingView.class);
        Drawing drawing = mock(Drawing.class);

        when(view.getDrawing()).thenReturn(drawing);
        when(drawing.sort(anyCollection()))
                .thenReturn(Collections.emptyList());

        SendToBackAction.sendToBack(view, Collections.emptyList());

        verify(drawing, never()).sendToBack(any());
    }
}
