package exceptions;

public class InscricaoDuplicadaException extends RuntimeException {
    public InscricaoDuplicadaException() {
        super("Voluntário já está inscrito na ação.");
    }
}
