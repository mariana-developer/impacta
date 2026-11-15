package exceptions;

public class AcaoLotadaException extends RuntimeException {
    public AcaoLotadaException() {
        super("Ação lotada.");
    }
}
