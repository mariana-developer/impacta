import static org.junit.jupiter.api.Assertions.assertThrows;

import exceptions.AcaoLotadaException;
import exceptions.EmailDuplicadoException;
import exceptions.InscricaoDuplicadaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExcecoesTest {
    private Impacta impacta;

    @BeforeEach
    void preparar() {
        impacta = new Impacta();
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "001");
        impacta.cadastrarVoluntario("Bruno", "bruno@email.com", "002");
    }

    @Test
    void emailDuplicadoLancaExcecao() {
        assertThrows(EmailDuplicadoException.class, () -> {
            impacta.cadastrarVoluntario("Outra Ana", "ana@email.com", "999");
        });
    }

    @Test
    void acaoLotadaLancaExcecao() {
        int id = impacta.cadastrarPlantio("Plantio", "Praça", "2026-12-01T09:00", 1, 3);
        impacta.inscreverVoluntario("ana@email.com", id);

        assertThrows(AcaoLotadaException.class, () -> {
            impacta.inscreverVoluntario("bruno@email.com", id);
        });
    }

    @Test
    void duplaInscricaoLancaExcecao() {
        int id = impacta.cadastrarMutirao("Mutirão", "Bairro", "2026-12-02T09:00", 5, 2);
        impacta.inscreverVoluntario("ana@email.com", id);

        assertThrows(InscricaoDuplicadaException.class, () -> {
            impacta.inscreverVoluntario("ana@email.com", id);
        });
    }
}
