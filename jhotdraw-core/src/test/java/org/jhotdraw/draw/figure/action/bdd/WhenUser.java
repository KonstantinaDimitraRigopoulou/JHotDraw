package org.jhotdraw.draw.action.bdd;

import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.action.BringToFrontAction;
import org.jhotdraw.draw.action.SendToBackAction;

import java.util.Collections;

public class WhenUser {
    public DrawingView view;

    public WhenUser the_user_triggers_bring_to_front(Figure figure) {
        BringToFrontAction.bringToFront(view, Collections.singletonList(figure));
        return this;
    }

    public WhenUser the_user_triggers_send_to_back(Figure figure) {
        SendToBackAction.sendToBack(view, Collections.singletonList(figure));
        return this;
    }
}
