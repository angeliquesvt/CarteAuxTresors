package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTests {

    @Test
    void should_sum_two_positions() {
        // GIVEN
        Position position  = new Position(2, 3);
        Position position2 = new Position(4, 1);

        Position expectedResult = new Position(6, 4);

        // WHEN
        Position resultPosition = Position.addPositions(position, position2);

        // THEN
        assertEquals(resultPosition, expectedResult);
    }
}
