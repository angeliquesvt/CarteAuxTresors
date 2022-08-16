package services;

import enumerations.DirectionEnum;
import enumerations.OrientationEnum;
import exceptions.EnumUnknownException;
import exceptions.MapGenerationException;
import models.Carte;
import models.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarteAuxTresorFileServiceIT {

    @TempDir
    File anotherTempDir;

    @Test
    void should_settup_carte() throws IOException, EnumUnknownException, MapGenerationException {
        // GIVEN
        String carteMock = "C - 3 - 4";
        String montagneMock = "M - 1 - 0";
        String tresorMock = "T - 0 - 3 - 2";
        String aventurierMock = "A - Lara - 1 - 1 - S - A";

        File testFile = new File(anotherTempDir, "test.txt");
        List<String> lines = Arrays.asList(carteMock, montagneMock, tresorMock, aventurierMock);
        Files.write(testFile.toPath(), lines);


        Position expectedMountainPosition = new Position(1, 0);
        Position expectedTresorPosition = new Position(0, 3);
        Position expectedAventurierPosition = new Position(1, 1);
        OrientationEnum expectedAventurierOrientation = OrientationEnum.S;
        DirectionEnum expectedAventurierPath = DirectionEnum.A;

        // WHEN
        Carte carte = CarteAuxTresorFileService.readFile(testFile.toPath().toString());

        // THEN
        assertEquals(carte.getWidth(), 3);
        assertEquals(carte.getHeigth(), 4);
        assertEquals(carte.getMontagnes().get(0).getPosition(), expectedMountainPosition);
        assertEquals(carte.getTresors().get(0).getPosition(), expectedTresorPosition);
        assertEquals(carte.getAventurier().getPosition(), expectedAventurierPosition);
        assertEquals(carte.getAventurier().getOrientation(), expectedAventurierOrientation);
        assertEquals(carte.getAventurier().getPaths().get(0), expectedAventurierPath);
    }

    @Test
    void should_throw_FileNotFoundException() throws IOException, EnumUnknownException, MapGenerationException {

        assertThrows(FileNotFoundException.class, () -> {
            CarteAuxTresorFileService.readFile("TEST");
        });
    }
    @Test
    void should_throw_MapGenerationException() throws IOException, EnumUnknownException, MapGenerationException {
        // GIVEN
        CarteAuxTresorFileService.setCarte(new Carte(0, 0));
        String carteMock = "";
        String montagneMock = "M - 1 - 0";
        String tresorMock = "T - 0 - 3 - 2";
        String aventurierMock = "A - Lara - 1 - 1 - S - A";

        File testFile2 = new File(anotherTempDir, "test2.txt");
        List<String> lines = Arrays.asList(carteMock, montagneMock, tresorMock, aventurierMock);
        Files.write(testFile2.toPath(), lines);


        // THEN
        assertThrows(MapGenerationException.class, () -> {
            CarteAuxTresorFileService.readFile(testFile2.toPath().toString());
        });

    }
}
