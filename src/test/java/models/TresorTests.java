package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TresorTests {

    @Test
    void should_remove_Tresor() {
        // GIVEN
        Position position  = new Position(1, 1);
        Tresor tresor = new Tresor(position, 1);

        // WHEN
        tresor.removeTresor();

        // THEN
        assertEquals(tresor.getNumberOfTresor(), 0);
    }

    @Test
    void should_not_remove_Tresor() {
        // GIVEN
        Position position  = new Position(1, 1);
        Tresor tresor = new Tresor(position, 0);

        // WHEN
        tresor.removeTresor();

        // THEN
        assertEquals(tresor.getNumberOfTresor(), 0);
    }
}
