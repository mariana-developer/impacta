import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RankingTest {
    private Impacta impacta;
    private int idPlantio;
    private int idMutirao;
    private int idOficina;

    @BeforeEach
    void preparar() {
        impacta = new Impacta();
        idPlantio = impacta.cadastrarPlantio("Plantio", "Praça", "2026-12-01T09:00", 10, 3);
        idMutirao = impacta.cadastrarMutirao("Mutirão", "Bairro", "2026-12-02T09:00", 10, 2);
        idOficina = impacta.cadastrarOficina("Oficina", "Escola", "2026-12-03T09:00", 10, 2, true);
    }

    @Test
    void rankingOrdenadoPorPontuacaoDecrescente() {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "001");
        impacta.cadastrarVoluntario("Bruno", "bruno@email.com", "002");
        impacta.cadastrarVoluntario("Carla", "carla@email.com", "003");

        impacta.inscreverVoluntario("ana@email.com", idMutirao);
        impacta.inscreverVoluntario("bruno@email.com", idOficina);
        impacta.inscreverVoluntario("carla@email.com", idPlantio);

        String[] esperado = {
            "Nome: Bruno\nAções: 1\nPontuação: 16",
            "Nome: Carla\nAções: 1\nPontuação: 11",
            "Nome: Ana\nAções: 1\nPontuação: 8"
        };
        assertArrayEquals(esperado, impacta.listarVoluntarios());
    }

    @Test
    void empateDePontuacaoOrdenaPorNome() {
        impacta.cadastrarVoluntario("Daniel", "daniel@email.com", "004");
        impacta.cadastrarVoluntario("Carla", "carla@email.com", "003");

        impacta.inscreverVoluntario("daniel@email.com", idPlantio);
        impacta.inscreverVoluntario("carla@email.com", idPlantio);

        String[] esperado = {
            "Nome: Carla\nAções: 1\nPontuação: 11",
            "Nome: Daniel\nAções: 1\nPontuação: 11"
        };
        assertArrayEquals(esperado, impacta.listarVoluntarios());
    }

    @Test
    void voluntarioSemAcoesFicaComZeroPontos() {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "001");

        String[] esperado = {"Nome: Ana\nAções: 0\nPontuação: 0"};
        assertArrayEquals(esperado, impacta.listarVoluntarios());
    }
}
