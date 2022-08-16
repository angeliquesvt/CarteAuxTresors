package models;

import enumerations.OrientationEnum;
import exceptions.AventurierOutOfBoundsException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarteTests {
    @Test
    void should_test_return_true_if_Xposition_greater_than_bounds() {
        // GIVEN
        Position position  = new Position(2, 0);
        Carte carte = new Carte(1,2);

        // WHEN
        boolean result = carte.isOutOfBounds(position);
        // THEN
        assertTrue(result);
    }

    @Test
    void should_test_return_true_if_Xposition_lower_than_bounds() {
        // GIVEN
        Position position  = new Position(-2, 0);
        Carte carte = new Carte(1,2);

        // WHEN
        boolean result = carte.isOutOfBounds(position);

        // THEN
        assertTrue(result);
    }

    @Test
    void should_test_return_true_if_Yposition_greater_than_bounds() {
        // GIVEN
        Position position  = new Position(0, 3);
        Carte carte = new Carte(1,2);

        // WHEN
        boolean result = carte.isOutOfBounds(position);

        // THEN
        assertTrue(result);
    }

    @Test
    void should_test_return_true_if_Yposition_lower_than_bounds() {
        // GIVEN
        Position position  = new Position(0, -3);
        Carte carte = new Carte(1,2);

        // WHEN
        boolean result = carte.isOutOfBounds(position);

        // THEN
        assertTrue(result);
    }

    @Test
    void should_test_return_false_if_Xposition_and_Yposition_in_bounds() {
        // GIVEN
        Position position  = new Position(0, 0);
        Carte carte = new Carte(1,2);

        // WHEN
        boolean result = carte.isOutOfBounds(position);

        // THEN
        assertFalse(result);
    }

    @Test
    void should_throw_aventuerOutOfBoundsException() throws AventurierOutOfBoundsException {
        // GIVEN
        Position position  = new Position(-10, -10);

        Aventurier aventurier = new Aventurier(position, "test", OrientationEnum.N, new ArrayList<>());
        Carte carte = new Carte(1,2);

        // WHEN
        assertThrows(AventurierOutOfBoundsException.class,
                ()->{
                    carte.setAventurier(aventurier);
                });
    }
}
