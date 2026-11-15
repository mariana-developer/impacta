import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import models.Acao;
import models.MutiraoReciclagem;
import models.OficinaEcologica;
import models.PlantioMudas;
import org.junit.jupiter.api.Test;

class PontuacaoTest {
    private LocalDateTime data = LocalDateTime.of(2026, 12, 1, 9, 0);

    @Test
    void pontuacaoPlantioMudas() {
        PlantioMudas plantio = new PlantioMudas(1, "Plantio", "Praça central", data, 10, 3);
        assertEquals(11, plantio.calcularPontuacao());
    }

    @Test
    void pontuacaoMutiraoReciclagem() {
        MutiraoReciclagem mutirao = new MutiraoReciclagem(2, "Mutirão", "Bairro", data, 10, 2);
        assertEquals(8, mutirao.calcularPontuacao());
    }

    @Test
    void pontuacaoOficinaComKit() {
        OficinaEcologica oficina = new OficinaEcologica(3, "Oficina", "Escola", data, 10, 2, true);
        assertEquals(16, oficina.calcularPontuacao());
    }

    @Test
    void pontuacaoOficinaSemKit() {
        OficinaEcologica oficina = new OficinaEcologica(4, "Oficina", "Escola", data, 10, 2, false);
        assertEquals(6, oficina.calcularPontuacao());
    }

    @Test
    void pontuacaoPolimorficaComListaDeAcoes() {
        ArrayList<Acao> acoes = new ArrayList<>();
        acoes.add(new PlantioMudas(1, "Plantio", "Praça", data, 10, 3));
        acoes.add(new MutiraoReciclagem(2, "Mutirão", "Bairro", data, 10, 2));
        acoes.add(new OficinaEcologica(3, "Oficina", "Escola", data, 10, 2, true));

        assertEquals(11, acoes.get(0).calcularPontuacao());
        assertEquals(8, acoes.get(1).calcularPontuacao());
        assertEquals(16, acoes.get(2).calcularPontuacao());
    }
}
