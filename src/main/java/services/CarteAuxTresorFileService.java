package services;

import enumerations.DirectionEnum;
import enumerations.OrientationEnum;
import exceptions.*;
import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarteAuxTresorFileService {

    private static List<Montagne> montagnes = new ArrayList<>();
    private static List<Tresor> tresors = new ArrayList<>();
    private static Aventurier aventurier;
    private static Carte carte;

    // TODO : A modifier car utiliser seulement pour les tests d'inte... (car snn conserve les data de carte)
    public static void setCarte(Carte carte) {
        CarteAuxTresorFileService.carte = carte;
    }

    public static Carte readFile(String file) throws EnumUnknownException, FileNotFoundException, MapGenerationException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] lineData = line.replaceAll("\\s", "").split("-");
                switch (lineData[0]) {
                    case "C":
                        carte = new Carte(Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]));
                        break;
                    case "M":
                        montagnes.add(new Montagne(new Position(Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]))));
                        break;
                    case "T":
                        Position tresorPosition = new Position(Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]));
                        int numberOfTresors = Integer.parseInt(lineData[3]);
                        tresors.add(new Tresor(tresorPosition, numberOfTresors));
                        break;
                    case "A":
                        OrientationEnum orientation = OrientationEnum.valueOf(lineData[4]);
                        Position aventurierPosition = new Position(Integer.parseInt(lineData[2]), Integer.parseInt(lineData[3]));
                        String name = lineData[1];
                        List<DirectionEnum> movements = new ArrayList<>();
                        for(String path: lineData[5].split("")) {
                            movements.add(DirectionEnum.valueOf(path));
                        }
                        aventurier = new Aventurier(aventurierPosition, name, orientation, movements);
                        break;
                    default:
                        break;
                }
            }
            checkCarteAndComputeCarteItems();
        } catch (MapGenerationException e) {
            throw new MapGenerationException("Probleme génération de carte");
        } catch (IllegalArgumentException e) {
            throw new EnumUnknownException();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }

        return carte;
    }

    private static void checkCarteAndComputeCarteItems() throws MapGenerationException {
        if (carte != null) {
            carte.setTresors(tresors);
            carte.setMontagnes(montagnes);
            carte.setAventurier(aventurier);
        } else {
            throw new NullCarteException("Il n'existe pas de donnée pour gérérer la carte de jeu");
        }
    }

    public static void writeFile(Carte carte) {
        String fileName= "GameResult.txt";
        try(FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(carte.toString());
            System.out.println(String.format("Vous pourrez consulter les résultats de la partie dans %s", fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
