package org.jhotdraw.draw.tool;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jhotdraw.draw.figure.TextFigure;
import org.w3c.dom.Text;

public class TextToolTestFrame extends JFrame {
    // Frame for GUI testing

    private JPanel canvasPanel;
    private JButton createTextButton;
    private JButton editTextButton;
    private TextFigure figure;
    private TextCreationTool creationTool;
    private TextEditingTool editingTool;

    public TextToolTestFrame() {
        setTitle("Text Tool Test Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvasPanel = new JPanel();
        canvasPanel.setName("drawingPanel");
        add(canvasPanel);

        createTextButton = new JButton("Create Text");
        createTextButton.setName("createTextButton");
        add(createTextButton);

        editTextButton = new JButton("Edit Text");
        editTextButton.setName("EditTextButton");
        add(editTextButton);

        figure = new TextFigure();
        creationTool = new TextCreationTool(figure);
        editingTool = new TextEditingTool(figure);
    }

    public TextFigure getFigure() {
        return figure;
    }
}
