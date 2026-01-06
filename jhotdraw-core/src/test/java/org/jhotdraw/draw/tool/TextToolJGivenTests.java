package org.jhotdraw.draw.tool;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class TextToolJGivenTests extends ScenarioTest<TextGivenStage, TextWhenStage, TextThenStage> {

    @Test
    public void user_can_create_text() {
        given().a_canvas();
        when().the_user_creates_text();
        then().text_is_created();

    }

    @Test
    public void user_can_edit_text() {
        given().a_text_figure();
        when().the_user_edits_the_text();
        then().text_is_updated();
    }
}
