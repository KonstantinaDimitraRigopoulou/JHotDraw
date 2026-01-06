package org.jhotdraw.draw.tool;

import static org.mockito.Mockito.*;

import org.jhotdraw.draw.figure.TextHolderFigure;
import org.mockito.Mockito;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class TextWhenStage extends Stage<TextWhenStage> {
    @ProvidedScenarioState
    TextCreationTool creationTool;

    @ProvidedScenarioState
    TextEditingTool editingTool;

    @ProvidedScenarioState
    TextHolderFigure figure;

    // When I click anywhere to create text
    public TextWhenStage the_user_creates_text() {
        creationTool.mousePressed(null); // simulate click
        figure = mock(TextHolderFigure.class);
        creationTool.beginEdit(figure);
        Mockito.when(figure.getText()).thenReturn("User input"); // simualte writing

        return self();
    }

    // When I edit the text
    public TextWhenStage the_user_edits_the_text() {
        editingTool.beginEdit(figure);
        Mockito.when(figure.getText()).thenReturn("Updated Text");
        editingTool.endEdit();
        return self();
    }
}
