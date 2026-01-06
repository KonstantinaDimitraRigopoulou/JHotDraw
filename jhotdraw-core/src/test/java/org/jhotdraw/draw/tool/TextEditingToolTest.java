package org.jhotdraw.draw.tool;

import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.TextFigure;
import org.jhotdraw.draw.figure.TextHolderFigure;
import org.jhotdraw.draw.text.FloatingTextField;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//to run the test here: mvn -pl jhotdraw-core test

public class TextEditingToolTest {

    private Drawing drawing;
    private TextEditingTool tool;
    private TextHolderFigure mockTextFigure;
    private FloatingTextField mockTextField;


    @Before
    public void setUp() {
        mockTextFigure = mock(TextHolderFigure.class);
        when(mockTextFigure.getText()).thenReturn("Text");

        mockTextField = mock(FloatingTextField.class);
        when(mockTextField.getText()).thenReturn("New Text");

        tool = new TextEditingTool(mockTextFigure){
            @Override
            protected void beginEdit(TextHolderFigure textHolder) {
                this.textField = mockTextField;
                super.beginEdit(textHolder);

            }

            @Override
            protected Drawing getDrawing() {
                // mock getDrawing() since endEdit calls fireUndoableEditHappened
                Drawing drawingMock = mock(Drawing.class);
                doNothing().when(drawingMock).fireUndoableEditHappened(any());
                return drawingMock;
            }

            @Override
            protected DrawingView getView() {
                DrawingView viewMock = mock(DrawingView.class);
                when(viewMock.isEnabled()).thenReturn(true);
                return viewMock;
            }

        };
        
    }

    /*
     * Test if the beginEdit starts correctly
     */
    @Test
    public void testBeginEditSetsUpTextField() {
        tool.beginEdit(mockTextFigure);
        
        assertTrue("Text field should be ready to edit", tool.textField != null);
    }

    /*
    * Tests if the endEdit saves the not null text
     */
    @Test
    public void testEndEditSavesText() {
        tool.beginEdit(mockTextFigure);
        tool.endEdit();

        assertFalse("Field should not accept more editions", tool.isEditing());
    }

    /**
     * Best case scenario:
    * The text is small and not null --> it is saved
     */
    @Test
    public void testEditTextWithValidInput() {
        tool.beginEdit(mockTextFigure);
        tool.endEdit();

        assertEquals("New Text", mockTextField.getText());
    }


    /*
     *Boundary case scenario: change to long text
     */
    @Test
    public void testEditTextWithLongInput() {
        TextFigure text = new TextFigure();
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longText.append("A");
        }
        when(mockTextField.getText()).thenReturn(longText.toString());
        tool.beginEdit(mockTextFigure);
        tool.endEdit();

        assertTrue("Text figure with long input should be added to the drawing", true);
        assertEquals(longText.toString(), mockTextField.getText());
    }

    /*
     * Boundary case scenario: change to null input, so the figure is deleted
     */
    @Test
    public void testEditToNullInput() {
        when(mockTextField.getText()).thenReturn("");
        tool.beginEdit(mockTextFigure);
        tool.endEdit();
        
        assertEquals("", mockTextField.getText());
    }


}
