package models;

import java.time.LocalDateTime;

public class OficinaEcologica extends Acao {
    private final int duracaoHoras;
    private final boolean kitMaterial;

    public OficinaEcologica(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public boolean temKitMaterial() {
        return kitMaterial;
    }

    @Override
    public int calcularPontuacao() {
        int pontos = 3 * duracaoHoras;
        if (kitMaterial) {
            pontos = pontos + 10;
        }
        return pontos;
    }

    @Override
    public String detalhesEspecificos() {
        String kit = "não";
        if (kitMaterial) {
            kit = "sim";
        }
        return "Tipo: Oficina Ecológica\nDuração: " + duracaoHoras + " hora(s)\nKit de material: " + kit;
    }
}
