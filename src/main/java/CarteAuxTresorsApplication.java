import models.Carte;
import services.CarteAuxTresorFileService;
import services.CarteAuxTresorsPlayService;

import java.io.IOException;

public class CarteAuxTresorsApplication {

    public static void main(String[] args) {
        System.out.println(String.format("Bienvenue dans %s", Carte.getMapName()));
        String filepath = args[0];
        try {
            System.out.println("Debut de la partie");
            Carte carte = CarteAuxTresorFileService.readFile(filepath);
            CarteAuxTresorsPlayService.play(carte);
            CarteAuxTresorFileService.writeFile(carte);
            System.out.println("Fin de la partie");
        }catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
