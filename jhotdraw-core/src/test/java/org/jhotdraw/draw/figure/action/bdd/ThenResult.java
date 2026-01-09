package org.jhotdraw.draw.action.bdd;

import org.assertj.core.api.Assertions;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.figure.Figure;

import static org.mockito.Mockito.verify;

public class ThenResult {

    public Drawing drawing;

    public ThenResult the_figure_should_be_in_front(Figure figure) {
        verify(drawing).bringToFront(figure);
        return this;
    }

    public ThenResult the_figure_should_be_at_back(Figure figure) {
        verify(drawing).sendToBack(figure);
        return this;
    }
}
