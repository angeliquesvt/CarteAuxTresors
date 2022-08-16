package models;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position addPositions(Position position1, Position position2) {
        return new Position(position1.getX()+ position2.getX(), position1.getY()+ position2.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Position)) {
            return false;
        }

        Position position = (Position) o;

        return position.getX() == x && position.getY() == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("%d - %d", this.getX(), this.getY());
    }

}
