package org.jhotdraw.draw.tool;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.figure.EllipseFigure;
import org.jhotdraw.draw.figure.Figure;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreationToolCreateFigureTest {

    /**
     * Test subclass so we can inject a mocked editor.
     * This avoids UI/editor wiring and keeps the test a unit test.
     */
    private static class TestableCreationTool extends CreationTool {
        private final DrawingEditor editor;

        TestableCreationTool(Figure prototype, DrawingEditor editor) {
            super(prototype);
            this.editor = editor;
        }

        @Override
        public DrawingEditor getEditor() {
            return editor;
        }
    }

    // Best case scenario: createFigure creates an EllipseFigure and applies default attributes via editor
    @Test
    public void createFigure_createsEllipseAndAppliesDefaultAttributes_bestCase() {
        DrawingEditor editor = mock(DrawingEditor.class);

        // Stub the dependency call so it does nothing
        doNothing().when(editor).applyDefaultAttributesTo(any(Figure.class));

        CreationTool tool = new TestableCreationTool(new EllipseFigure(0, 0, 10, 10), editor);

        Figure created = tool.createFigure();

        assertNotNull(created);
        assertTrue(created instanceof EllipseFigure);

        // Verify the dependency interaction happened
        verify(editor, times(1)).applyDefaultAttributesTo(created);
    }

    // Boundary case: invalid prototype class name -> error path in constructor
    @Test(expected = InternalError.class)
    public void constructor_withInvalidClassName_boundary() {
        new CreationTool("does.not.Exist");
    }
}
