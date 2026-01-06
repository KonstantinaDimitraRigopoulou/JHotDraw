package org.jhotdraw.draw.tool;

import static org.assertj.core.api.Assertions.*;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

public class TextToolGUITest extends AssertJSwingJUnitTestCase {

    // Testing of the clicking to create figure
    // Tetsting of clicking to edit figure

    private TextToolTestFrame frame;
    private FrameFixture window;

    @Override
    protected void onSetUp() throws Exception {
        frame = GuiActionRunner.execute(() -> new TextToolTestFrame());
        window = new FrameFixture(robot(), frame);
        window.show();
    }

    @Test
    public void testCreateTextFigure() {
        window.button("createTextButton").click();
        window.panel("drawingPanel").click();

        frame.getFigure().setText("User input");

        assertThat(frame.getFigure().getText()).isEqualTo("User input");
    }

    @Test
    public void testEditTextFigure() {
        window.panel("drawingPanel").click();
        window.button("EditTextButton").click();

        frame.getFigure().setText("Updated User input");

        assertThat(frame.getFigure().getText()).isEqualTo("Updated User input");// the text has been upadted
    }

}
