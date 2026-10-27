package src.Codegen;

/**
 * The labels a {@code break} or {@code continue} jumps to in a loop.
 */
public final class Loop {
    private final String continueLabel;
    private final String breakLabel;
    private boolean continued;

    Loop(final String continueLabel, final String breakLabel) {
        this.continueLabel = continueLabel;
        this.breakLabel = breakLabel;
    }

    /** @return the label {@code continue} jumps to */
    public String continueLabel() {
        return continueLabel;
    }

    /** @return the label {@code break} jumps to */
    public String breakLabel() {
        return breakLabel;
    }

    /** Records that a {@code continue} jumps to the continue label. */
    public void markContinued() {
        continued = true;
    }

    /** @return whether any {@code continue} jumps to the continue label */
    public boolean isContinued() {
        return continued;
    }
}
