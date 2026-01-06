package org.jhotdraw.draw.tool;

import static org.mockito.Mockito.*;

import org.jhotdraw.draw.figure.TextHolderFigure;
import org.mockito.Mockito;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class TextGivenStage extends Stage<TextGivenStage> {

    @ProvidedScenarioState
    TextEditingTool editingTool;

    @ProvidedScenarioState
    TextCreationTool creationTool;

    @ProvidedScenarioState
    TextHolderFigure textFigure;

    // Given a canvas
    public TextGivenStage a_canvas() {
        creationTool = mock(TextCreationTool.class);

        return self();
    }

    // Given a text figure
    public TextGivenStage a_text_figure() {
        editingTool = mock(TextEditingTool.class);
        textFigure = mock(TextHolderFigure.class);

        Mockito.when(textFigure.getText()).thenReturn("Text");
        return self();
    }
}
