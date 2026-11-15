package models;

import java.time.LocalDateTime;

public class PlantioMudas extends Acao {
    private final int qtdMudas;

    public PlantioMudas(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.qtdMudas = qtdMudas;
    }

    public int getQtdMudas() {
        return qtdMudas;
    }

    @Override
    public int calcularPontuacao() {
        return 5 + 2 * qtdMudas;
    }

    @Override
    public String detalhesEspecificos() {
        return "Tipo: Plantio de Mudas\nMudas estimadas: " + qtdMudas;
    }
}
