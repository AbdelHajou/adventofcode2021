package nl.abdel.aoc;

public class Submarine {

    protected int position;
    protected int depth;

    public Submarine() {
        this(0, 0);
    }

    public Submarine(int initialPosition, int initialDepth) {
        this.position = initialPosition;
        this.depth = initialDepth;
    }

    public int getPosition() {
        return position;
    }

    public int getDepth() {
        return depth;
    }

    public void command(String command) {
        if (command == null) {
            throw new IllegalArgumentException("Command cannot be null.");
        }

        String[] commandParts = command.split(" ");
        String direction = commandParts[0];
        int units = Integer.parseInt(commandParts[1]);

        switch (direction) {
            case "forward":
                forward(units);
                break;
            case "down":
                down(units);
                break;
            case "up":
                up(units);
                break;
            default:
                break;
        }
    }

    protected void forward(int units) {
        position += units;
    }

    protected void down(int units) {
        depth += units;
    }

    protected void up(int units) {
        depth -= units;
    }
}
