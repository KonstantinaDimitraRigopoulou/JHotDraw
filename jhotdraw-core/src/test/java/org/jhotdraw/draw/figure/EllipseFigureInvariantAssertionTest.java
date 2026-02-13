package org.jhotdraw.draw.figure;

import org.junit.Test;
import org.junit.Assume;

public class EllipseFigureInvariantAssertionTest {

    private static class BrokenEllipseFigure extends EllipseFigure {
        BrokenEllipseFigure() {
            super(0, 0, 10, 10);
            this.ellipse = null; // break invariant on purpose
        }
    }

    @Test(expected = AssertionError.class)
    public void copy_throwsAssertionError_whenEllipseIsNull() {
        // Skip test if assertions are disabled (no -ea)
        boolean enabled = false;
        assert enabled = true;
        Assume.assumeTrue("Assertions must be enabled with -ea", enabled);

        new BrokenEllipseFigure().copy();
    }
}
