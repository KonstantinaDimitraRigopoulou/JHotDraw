package org.jhotdraw.draw.figure.steps;

import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.jhotdraw.draw.tool.CreationTool;

import com.tngtech.jgiven.Stage;

public class GivenRectangle extends Stage<GivenRectangle> {

    public GivenRectangle the_rectangle_tool_is_selected() {
        DefaultDrawingEditor editor = new DefaultDrawingEditor();
        
        CreationTool rectangleTool = new CreationTool(new RectangleFigure());
        editor.setTool(rectangleTool);
        
        return self();
    }
}
