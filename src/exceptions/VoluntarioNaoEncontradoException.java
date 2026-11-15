package exceptions;

public class VoluntarioNaoEncontradoException extends RuntimeException {
    public VoluntarioNaoEncontradoException() {
        super("Voluntário não encontrado.");
    }
}
