package org.jhotdraw.draw.figure.steps;

import java.awt.Point;

import org.assertj.swing.fixture.FrameFixture;

import com.tngtech.jgiven.Stage;

public class WhenRectangle extends Stage<WhenRectangle> {
    
    private FrameFixture window;

    public WhenRectangle the_user_drags_the_mouse_from_100_100_to_200_200() {
        // We use the robot directly from the window fixture to simulate the precise movement
        window.robot().click(window.target(), new Point(100, 100));
        window.robot().moveMouse(window.target(), new Point(200, 200));
        window.robot().releaseMouseButtons();
        
        return self();
    }
}