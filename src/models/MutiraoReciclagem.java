package models;

import java.time.LocalDateTime;

public class MutiraoReciclagem extends Acao {
    private final int duracaoHoras;

    public MutiraoReciclagem(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    @Override
    public int calcularPontuacao() {
        return 4 * duracaoHoras;
    }

    @Override
    public String detalhesEspecificos() {
        return "Tipo: Mutirão de Reciclagem\nDuração: " + duracaoHoras + " hora(s)";
    }
}
