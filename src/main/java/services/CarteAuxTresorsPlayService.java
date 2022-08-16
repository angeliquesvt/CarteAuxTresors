package services;

import enumerations.DirectionEnum;
import enumerations.OrientationEnum;
import exceptions.DirectionNotImplementedException;
import exceptions.OrientationNotImplementedException;
import models.Carte;
import models.Montagne;
import models.Position;
import models.Tresor;

import java.util.Optional;

public class CarteAuxTresorsPlayService {

    public static void play(Carte carte) {
        carte.getAventurier().getPaths().forEach(path -> {
            try {
                moveAventurierOnMap(carte, path);
            } catch (DirectionNotImplementedException | OrientationNotImplementedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void moveAventurierOnMap(Carte carte, DirectionEnum direction) throws DirectionNotImplementedException, OrientationNotImplementedException {
        switch (direction) {
            case A:
                moveAventurierForwardOnMap(carte);
                break;
            case D:
                carte.getAventurier().setOrientation(changeOrientationToRight(carte.getAventurier().getOrientation()));
                break;
            case G:
                carte.getAventurier().setOrientation(changeOrientationToLeft(carte.getAventurier().getOrientation()));
                break;
            default:
                throw new DirectionNotImplementedException();
        }
    }

    private static OrientationEnum changeOrientationToRight(OrientationEnum currentOrientation) throws OrientationNotImplementedException {
        switch (currentOrientation) {
            case N:
                return OrientationEnum.E;
            case S:
                return OrientationEnum.O;
            case O:
                return OrientationEnum.N;
            case E:
                return OrientationEnum.S;
            default:
                throw new OrientationNotImplementedException();
        }
    }

    private static OrientationEnum changeOrientationToLeft(OrientationEnum currentOrientation) throws OrientationNotImplementedException {
        switch (currentOrientation) {
            case N:
                return OrientationEnum.O;
            case S:
                return OrientationEnum.E;
            case O:
                return OrientationEnum.S;
            case E:
                return OrientationEnum.N;
            default:
                throw new OrientationNotImplementedException();
        }
    }

    private static Position positionByOrientationHandler(OrientationEnum orientation) throws OrientationNotImplementedException {
        switch (orientation) {
            case N:
                return new Position(0, -1);
            case S:
                return new Position(0, 1);
            case O:
                return new Position(-1, 0);
            case E:
                return new Position(1, 0);
            default:
                throw new OrientationNotImplementedException();
        }
    }

    private static boolean isLegalMove(Carte carte, Position positionToCheck) {
        Optional<Montagne> optionalMontagne = carte.getMontagnes().stream().filter(montagne -> montagne.getPosition().equals(positionToCheck)).findFirst();

        return optionalMontagne.isEmpty() && !carte.isOutOfBounds(positionToCheck);
    }

    private static void removeTresorOnMap(Carte carte, Tresor tresorToRemove) {
        if(tresorToRemove.getNumberOfTresor() > 1) {
            tresorToRemove.removeTresor();
        }else {
            carte.getTresors().remove(tresorToRemove);
        }
    }

    private static void tresorsHandler(Carte carte, Position positionToCheck) {
        Optional<Tresor> optionalTresor = carte.getTresors().stream().filter(tresor -> tresor.getPosition().equals(positionToCheck)).findFirst();
        if(!optionalTresor.isEmpty() && optionalTresor.get().getNumberOfTresor() > 0) {
            carte.getAventurier().addTresor(optionalTresor.get());
            removeTresorOnMap(carte, optionalTresor.get());
        }
    }

    private static void moveAventurierForwardOnMap(Carte carte) throws OrientationNotImplementedException {
        Position nextPosition = Position.addPositions(carte.getAventurier().getPosition(), positionByOrientationHandler(carte.getAventurier().getOrientation()));
        if(isLegalMove(carte, nextPosition)) {
            carte.getAventurier().setPosition(nextPosition);
            tresorsHandler(carte, nextPosition);
        }
    }
}
