package org.jhotdraw.draw.figure;

import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DrawingEditor; 
import org.jhotdraw.draw.figure.steps.GivenRectangle;
import org.jhotdraw.draw.figure.steps.ThenRectangle;
import org.jhotdraw.draw.figure.steps.WhenRectangle;
import org.junit.Before;
import org.junit.Test;

import com.tngtech.jgiven.junit.ScenarioTest;

public class RectangleDrawingTest extends ScenarioTest<GivenRectangle, WhenRectangle, ThenRectangle> {
    
    
    private DrawingEditor editor;

    @Before
    public void setup() {
        editor = new DefaultDrawingEditor();
    }

    @Test
    public void user_can_draw_a_rectangle() {
        given().the_rectangle_tool_is_selected();
        when().the_user_drags_the_mouse_from_100_100_to_200_200();
        
then().a_rectangle_should_appear_at_100_100_with_size_100_100(editor.getActiveView().getDrawing());    }
}