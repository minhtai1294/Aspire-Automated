package tools.helpers;

public class TestStep {
    private int step;
    private int subStep;

    public int start() {
        this.step = 1;
        return step;
    }

    public int next() {
        this.step++;
        this.subStep = 0;
        return step;
    }

    public String subStep() {
        this.subStep++;
        return this.subStep == 0 ? String.valueOf(step) : String.format("%d.%d", this.step, this.subStep);
    }

    @Override
    public String toString() {
        return this.subStep == 0 ? String.valueOf(step) : String.format("%d.%d", this.step, this.subStep);
    }
}
