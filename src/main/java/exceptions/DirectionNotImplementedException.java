package exceptions;

public class DirectionNotImplementedException extends Exception {
    public DirectionNotImplementedException() {
        super("La direction n'a pas encore été implémentée");
    }
}
