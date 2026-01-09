package org.jhotdraw.draw.action.bdd;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;

import java.util.*;
import static org.mockito.Mockito.*;

public class GivenDrawing {
    public Drawing drawing;
    public DrawingView view;
    public List<Figure> figures;

    public GivenDrawing a_mocked_drawing_with_figures(int count) {
        drawing = mock(Drawing.class);
        view = mock(DrawingView.class);
        figures = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Figure f = mock(Figure.class);
            figures.add(f);
        }

        when(view.getDrawing()).thenReturn(drawing);

        return this;
    }

    public GivenDrawing a_selected_figure(Figure figure) {
        return this;
    }
}
