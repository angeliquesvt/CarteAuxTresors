package services;

import enumerations.DirectionEnum;
import enumerations.OrientationEnum;
import exceptions.AventurierOutOfBoundsException;
import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarteAuxTresorsPlayServiceIT {

    Carte carte;
    Position position;
    List<DirectionEnum> path;

    @BeforeEach
    public void init() {
        carte = new Carte(3, 3);
        position = new Position(1, 1);
        path = new ArrayList<DirectionEnum>();
    }

    @Test
    void should_turn_aventurier_on_south_to_est_and_move_forward() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.G);
        path.add(DirectionEnum.A);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.S, path);
        carte.setAventurier(aventurier);

        Position expectedPosition = new Position(2, 1);
        OrientationEnum expectedOrientation = OrientationEnum.E;

        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getPosition(), expectedPosition);
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
    }

    @Test
    void should_turn_aventurier_on_south_to_ouest_and_move_forward() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.D);
        path.add(DirectionEnum.A);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.S, path);
        carte.setAventurier(aventurier);

        Position expectedPosition = new Position(0, 1);
        OrientationEnum expectedOrientation = OrientationEnum.O;

        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getPosition(), expectedPosition);
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
    }

    @Test
    void should_turn_aventurier_est_to_north_and_move_forward() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.G);
        path.add(DirectionEnum.A);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.E, path);
        carte.setAventurier(aventurier);

        Position expectedPosition = new Position(1, 0);
        OrientationEnum expectedOrientation = OrientationEnum.N;

        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getPosition(), expectedPosition);
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
    }

    @Test
    void should_turn_aventurier_on_est_to_south_and_move_forward() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.D);
        path.add(DirectionEnum.A);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.E, path);
        carte.setAventurier(aventurier);

        Position expectedPosition = new Position(1, 2);
        OrientationEnum expectedOrientation = OrientationEnum.S;

        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getPosition(), expectedPosition);
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
    }

    @Test
    void should_turn_aventurier_on_west_to_north() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.D);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.O, path);
        carte.setAventurier(aventurier);

        OrientationEnum expectedOrientation = OrientationEnum.N;

        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
    }

    @Test
    void should_turn_aventurier_on_west_to_south() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.G);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.O, path);
        carte.setAventurier(aventurier);

        OrientationEnum expectedOrientation = OrientationEnum.S;

        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
    }

    @Test
    void should_turn_aventurier_on_north_to_est() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.D);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.N, path);
        carte.setAventurier(aventurier);

        OrientationEnum expectedOrientation = OrientationEnum.E;

        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
    }

    @Test
    void should_turn_aventurier_on_north_to_west() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.G);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.N, path);
        carte.setAventurier(aventurier);

        OrientationEnum expectedOrientation = OrientationEnum.O;

        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
    }

    @Test
    void should_not_move_forward() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.A);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.S, path);
        carte.setAventurier(aventurier);
        Montagne montagne = new Montagne(new Position(1, 2));
        carte.getMontagnes().add(montagne);

        Position expectedAventurierPosition = new Position(1,1);
        OrientationEnum expectedOrientation = OrientationEnum.S;

        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getPosition(), expectedAventurierPosition);
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
    }

    @Test
    void should_pick_tresor_on_map() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.A);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.S, path);
        carte.setAventurier(aventurier);
        Tresor tresor = new Tresor(new Position(1, 2), 2);
        carte.getTresors().add(tresor);

        Position expectedAventurierPosition = new Position(1,2);
        OrientationEnum expectedOrientation = OrientationEnum.S;


        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getPosition(), expectedAventurierPosition);
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
        assertEquals(carte.getTresors().size(), 1);
        assertEquals(carte.getAventurier().getTresorsWin().size(), 1);
    }

    @Test
    void should_remove_tresor_on_map() throws AventurierOutOfBoundsException {

        // GIVEN
        path.add(DirectionEnum.A);
        Aventurier aventurier = new Aventurier(position, "BearGrylls", OrientationEnum.S, path);
        carte.setAventurier(aventurier);
        Tresor tresor = new Tresor(new Position(1, 2), 1);
        carte.getTresors().add(tresor);

        Position expectedAventurierPosition = new Position(1,2);
        OrientationEnum expectedOrientation = OrientationEnum.S;


        // WHEN
        CarteAuxTresorsPlayService.play(carte);

        // THEN
        assertEquals(carte.getAventurier().getPosition(), expectedAventurierPosition);
        assertEquals(carte.getAventurier().getOrientation(), expectedOrientation);
        assertEquals(carte.getTresors().size(), 0);
        assertEquals(carte.getAventurier().getTresorsWin().size(), 1);
    }

    @Test
    void should_throw_AventurierOutOfBoundsException() {

        path.add(DirectionEnum.A);
        Position positionOutOfBound = new Position(-1, -1);
        Aventurier aventurier = new Aventurier(positionOutOfBound, "BearGrylls", OrientationEnum.S, path);

        // WHEN
        assertThrows(AventurierOutOfBoundsException.class,
                ()->{
                    carte.setAventurier(aventurier);
                });
    }
}
