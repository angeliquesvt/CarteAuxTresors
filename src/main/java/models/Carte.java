package models;

import exceptions.AventurierOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Carte {
    private final static String name = "La madre de Dios";
    private Aventurier aventurier = new Aventurier();
    private List<Montagne> montagnes = new ArrayList<>();
    private List<Tresor> tresors = new ArrayList<>();
    private int width;
    private int heigth;

    public static String getMapName() {
        return name;
    }

    public Carte(int width, int height) {
        this.width = width;
        this.heigth = height;
    }

    public Aventurier getAventurier() {
        return aventurier;
    }

    public List<Montagne> getMontagnes() {
        return montagnes;
    }

    public List<Tresor> getTresors() {
        return tresors;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setAventurier(Aventurier aventurier) throws AventurierOutOfBoundsException {
        if(!isOutOfBounds(aventurier.getPosition())) {
            this.aventurier = aventurier;
        }else {
            throw new AventurierOutOfBoundsException(String.format("L'aventurier %s est hors des limites de la carte pour les positions x:%d et y:%d",
                    aventurier.getName(), aventurier.getPosition().getX(), aventurier.getPosition().getY()));
        }
    }

    public void setMontagnes(List<Montagne> montagnes) {
        List<Montagne> validMountains = montagnes.stream().filter(montagne -> !isOutOfBounds(montagne.getPosition())).collect(Collectors.toList());
        this.montagnes = validMountains;
    }

    public void setTresors(List<Tresor> tresors) {
        List<Tresor> validTresors = tresors.stream().filter(tresor -> !isOutOfBounds(tresor.getPosition())).collect(Collectors.toList());
        this.tresors = validTresors;
    }

    public boolean isOutOfBounds(Position position) {
        return position.getX() < 0 || position.getX() >= this.width || position.getY() < 0 || position.getY() >= this.heigth;
    }

    @Override
    public String toString() {
        String mountainsListString = montagnes.stream().map(Montagne::toString).collect(Collectors.joining("\n"));
        String tresorsListString = tresors.stream().map(Tresor::toString).collect(Collectors.joining("\n"));
        return String.format("C - %d - %d\n%s\n%s\n%s", getWidth(), getHeigth(), mountainsListString, tresorsListString, aventurier.toString());
    }
}
