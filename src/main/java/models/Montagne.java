package models;

public class Montagne extends CarteItem{

    public Montagne(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return String.format("M - %s", this.position.toString());
    }
}
