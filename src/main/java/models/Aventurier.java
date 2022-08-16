package models;

import enumerations.DirectionEnum;
import enumerations.OrientationEnum;

import java.util.ArrayList;
import java.util.List;

public class Aventurier extends CarteItem {
    private String name = "";
    private OrientationEnum orientation = OrientationEnum.S;
    private List<DirectionEnum> paths = new ArrayList<>();
    private List<Tresor> tresorsWin = new ArrayList<>();

    public List<DirectionEnum> getPaths() {
        return paths;
    }

    public Aventurier() {
        super(new Position(0,0));
    }

    public Aventurier(Position position, String name, OrientationEnum orientation, List<DirectionEnum> paths) {
        super(position);
        this.name = name;
        this.orientation = orientation;
        this.paths = paths;
    }

    public String getName() {
        return name;
    }

    public OrientationEnum getOrientation() {
        return orientation;
    }

    public List<Tresor> getTresorsWin() {
        return tresorsWin;
    }

    public void setOrientation(OrientationEnum orientation) {
        this.orientation = orientation;
    }

    public void addTresor(Tresor tresor) {
        tresorsWin.add(tresor);
    }

    @Override
    public String toString() {
        return String.format("A - %s - %s - %s - %d",getName(), position.toString(), orientation.toString(), tresorsWin.size());
    }
}
