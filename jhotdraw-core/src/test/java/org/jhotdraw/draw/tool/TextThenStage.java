package org.jhotdraw.draw.tool;

import org.jhotdraw.draw.figure.TextHolderFigure;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import static org.assertj.core.api.Assertions.assertThat;

public class TextThenStage extends Stage<TextThenStage> {
    @ProvidedScenarioState
    TextCreationTool creationTool;

    @ProvidedScenarioState
    TextEditingTool editingTool;

    @ProvidedScenarioState
    TextHolderFigure figure;

    // Then a text is created
    public TextThenStage text_is_created() {
        assertThat(figure).isNotNull(); // make sure that the figure is not empty
        assertThat(figure.getText()).isNotEmpty();

        return self();
    }

    // Then it lets me change/modify the content
    public TextThenStage text_is_updated() {
        assertThat(figure).isNotNull();
        assertThat(figure.getText()).isNotEmpty();
        assertThat(figure.getText()).isEqualTo("Updated Text");
        return self();
    }
}
