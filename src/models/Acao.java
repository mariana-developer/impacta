package models;

import exceptions.AcaoLotadaException;
import exceptions.InscricaoDuplicadaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Acao {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final int id;
    private final String titulo;
    private final String descricao;
    private final LocalDateTime data;
    private final int maxParticipantes;
    private final ArrayList<Voluntario> inscritos;

    public Acao(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
        this.inscritos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public List<Voluntario> getInscritos() {
        return Collections.unmodifiableList(inscritos);
    }

    public abstract int calcularPontuacao();

    public abstract String detalhesEspecificos();

    public boolean estaLotada() {
        return inscritos.size() >= maxParticipantes;
    }

    public boolean possuiVoluntario(Voluntario voluntario) {
        for (Voluntario v : inscritos) {
            if (v.getEmail().equalsIgnoreCase(voluntario.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public void inscrever(Voluntario voluntario) {
        if (possuiVoluntario(voluntario)) {
            throw new InscricaoDuplicadaException();
        }
        if (estaLotada()) {
            throw new AcaoLotadaException();
        }
        inscritos.add(voluntario);
    }

    public String exibirDetalhes() {
        StringBuilder texto = new StringBuilder();
        texto.append("Ação ").append(id).append(": ").append(titulo).append("\n");
        texto.append("Descrição: ").append(descricao).append("\n");
        texto.append("Data: ").append(data.format(FORMATO_DATA)).append("\n");
        texto.append(detalhesEspecificos()).append("\n");
        texto.append("Pontuação: ").append(calcularPontuacao()).append("\n");
        texto.append("Vagas: ").append(inscritos.size()).append("/").append(maxParticipantes).append("\n");
        texto.append("Voluntários inscritos:");
        if (inscritos.isEmpty()) {
            texto.append(" nenhum");
        } else {
            for (Voluntario v : inscritos) {
                texto.append("\n- ").append(v.getNome());
            }
        }
        return texto.toString();
    }
}
