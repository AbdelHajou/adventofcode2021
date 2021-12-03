package nl.abdel.aoc;

public class AimingSubmarine extends Submarine {

    private int aim;

    public AimingSubmarine() {
        this(0, 0);
    }

    public AimingSubmarine(int initialPosition, int initialDepth) {
        this(initialPosition, initialDepth, 0);
    }

    public AimingSubmarine(int initialPosition, int initialDepth, int initialAim) {
        super(initialPosition, initialDepth);
        this.aim = initialAim;
    }

    @Override
    protected void down(int units) {
        aim += units;
    }

    @Override
    protected void up(int units) {
        aim -= units;
    }

    @Override
    protected void forward(int units) {
        position += units;
        depth += aim * units;
    }

    public int getAim() {
        return aim;
    }
}
