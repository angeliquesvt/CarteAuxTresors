package exceptions;

public class NoArgumentException extends Exception {
    public NoArgumentException() {
        super("Il manque des arguments en paramètre du programme");
    }
}
