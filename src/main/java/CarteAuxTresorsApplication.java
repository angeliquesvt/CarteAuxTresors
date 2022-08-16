import exceptions.NoArgumentException;
import models.Carte;
import services.CarteAuxTresorFileService;
import services.CarteAuxTresorsPlayService;

public class CarteAuxTresorsApplication {

    public static void main(String[] args) throws NoArgumentException {
        if(args.length == 2) {
            System.out.println(String.format("Bienvenue dans %s", Carte.getMapName()));
            String filepath = args[0];
            String resultpath = args[1];
            try {
                System.out.println("Debut de la partie");
                Carte carte = CarteAuxTresorFileService.readFile(filepath);
                CarteAuxTresorsPlayService.play(carte);
                CarteAuxTresorFileService.writeFile(carte, resultpath);
                System.out.println("Fin de la partie");
            }catch (Exception e ) {
                e.printStackTrace();
            }
        } else {
            throw new NoArgumentException();
        }
    }
}
