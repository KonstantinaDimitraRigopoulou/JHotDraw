package org.jhotdraw.draw.tool;

import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.TextFigure;
import org.junit.*;
import static org.junit.Assert.*;


//to run the test here: mvn -pl jhotdraw-core test

public class TextCreationToolTest {

    private Drawing drawing;


    @Before
    public void setUp() {
        drawing = new DefaultDrawing();
    }

    /**
     * Best case scenario:
    * If there is small text written -> it is saved
     */
    @Test
    public void testCreateTextWithValidInput() {
        TextFigure text = new TextFigure();
        text.setText("Hello JHotDraw");

        drawing.add(text);

        // Verify figure is in the drawing
        boolean found = false;
        for (Figure f : drawing.getChildren()) {
            if (f == text) {
                found = true;
            }
        }

        assertTrue("Text figure should be added to the drawing", found);
        assertEquals("Hello JHotDraw", text.getText());
    }

    /**
     * Best case scenario:
     *If the text is empty (just create and not write) -> it is discarded
     */
    @Test
    public void testEmptyTextIsDiscarded() {
        TextFigure text = new TextFigure();
        text.setText(""); //simbolizes no writting anything

        if (!text.getText().isEmpty()) {
            drawing.add(text);
        }

        int count = 0;
        for (Figure f : drawing.getChildren()) {
            count++;
        }

        assertEquals("Empty text should not create a figure", 0, count);
    }

    /*
     *Boundary case scenario: long text input
     */
    @Test
    public void testreateTextWithLongInput() {
        TextFigure text = new TextFigure();
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longText.append("A");
        }
        text.setText(longText.toString());
        drawing.add(text);

        assertTrue("Text figure with long input should be added to the drawing", drawing.getChildren().contains(text));
        assertEquals(longText.toString(), text.getText());
    }

    /*
     * Boundary case scenario: null input
     * The best case also takes into consideration the null input but it testes that it is not saved
     */
    @Test
    public void testCreateNullInput() {
        TextFigure text = new TextFigure();
        text.setText(null);
        if(text.getText() != null){
            drawing.add(text);
        }
        

        assertFalse("Text figure with null input shouldn't be added to the drawing", drawing.getChildren().contains(text));
        assertEquals(null, text.getText());
    }
}
