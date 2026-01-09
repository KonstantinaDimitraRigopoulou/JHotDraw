package org.jhotdraw.draw.action.bdd;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.jhotdraw.draw.figure.Figure;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BringSendToBackBDDTest
        extends ScenarioTest<GivenDrawing, WhenUser, ThenResult> {

    @Test
    public void bring_to_front_scenario() {
        Figure figure = mock(Figure.class);

        GivenDrawing.GivenResult givenResult = given()
                .a_mocked_drawing_with_figures(3);

        when().triggers_bring_to_front(givenResult.view, figure);

        then().drawing = givenResult.drawing;
        then().the_figure_should_be_in_front(figure);
    }

    @Test
    public void send_to_back_scenario() {
        Figure figure = mock(Figure.class);

        GivenDrawing.GivenResult givenResult = given()
                .a_mocked_drawing_with_figures(3);

        when().triggers_send_to_back(givenResult.view, figure);

        then().drawing = givenResult.drawing;
        then().the_figure_should_be_at_back(figure);
    }
}
