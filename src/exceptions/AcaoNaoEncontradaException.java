package exceptions;

public class AcaoNaoEncontradaException extends RuntimeException {
    public AcaoNaoEncontradaException() {
        super("Ação não encontrada.");
    }
}
