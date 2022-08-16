package models;

public class Tresor extends CarteItem {

    private int numberOfTresor;

    public Tresor(Position position, int numberOfTresor) {
        super(position);
        this.numberOfTresor = numberOfTresor;
    }

    public int getNumberOfTresor() {
        return numberOfTresor;
    }

    public void removeTresor() {
        if(numberOfTresor > 0) {
            this.numberOfTresor--;
        }
    }

    @Override
    public String toString() {
        return String.format("T - %s - %d", this.position.toString(), getNumberOfTresor());
    }
}

