package exceptions;

public class EnumUnknownException extends Exception {
    public EnumUnknownException() { super("L'énumération n'existe pas"); }
}
