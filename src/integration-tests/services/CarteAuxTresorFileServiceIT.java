package services;

import enumerations.DirectionEnum;
import enumerations.OrientationEnum;
import exceptions.AventurierOutOfBoundsException;
import exceptions.EnumUnknownException;
import exceptions.MapGenerationException;
import models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
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

    @Test
    void should_writeFile() throws IOException, AventurierOutOfBoundsException {
        // GIVEN
        Carte carte = new Carte(3, 3);
        List<DirectionEnum> path = new ArrayList<>();
        List<Montagne> montagnes = new ArrayList<>();
        List<Tresor> tresors = new ArrayList<>();
        Aventurier aventurier = new Aventurier(new Position(1,1), "BearGrylls", OrientationEnum.S, path);
        Montagne montagne = new Montagne(new Position(1, 2));
        Tresor tresor = new Tresor(new Position(1, 2), 2);
        path.add(DirectionEnum.A);
        montagnes.add(montagne);
        tresors.add(tresor);
        carte.setAventurier(aventurier);
        carte.setMontagnes(montagnes);
        carte.setTresors(tresors);
        String expectedOutPutData =
                "C - 3 - 3\n" +
                "M - 1 - 2\n" +
                "T - 1 - 2 - 2\n" +
                "A - BearGrylls - 1 - 1 - S - 0";

        // WHEN
        CarteAuxTresorFileService.writeFile(carte, anotherTempDir.toPath().toString());

        // THEN
        String content = Files.readString(anotherTempDir.toPath().resolve("GameResult.txt"));
        assertEquals(content, expectedOutPutData);
    }
}
