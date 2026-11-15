package models;

import java.util.ArrayList;

public class Voluntario implements Comparable<Voluntario> {
    private final String nome;
    private final String email;
    private final String matricula;
    private final ArrayList<Acao> acoes;

    public Voluntario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.acoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void adicionarAcao(Acao acao) {
        acoes.add(acao);
    }

    public int getQuantidadeAcoes() {
        return acoes.size();
    }

    public int getPontuacao() {
        int total = 0;
        for (Acao acao : acoes) {
            total = total + acao.calcularPontuacao();
        }
        return total;
    }

    public String exibir() {
        return "Nome: " + nome + "\nAções: " + getQuantidadeAcoes() + "\nPontuação: " + getPontuacao();
    }

    @Override
    public int compareTo(Voluntario outro) {
        int minhaPontuacao = this.getPontuacao();
        int outraPontuacao = outro.getPontuacao();
        if (minhaPontuacao != outraPontuacao) {
            return Integer.compare(outraPontuacao, minhaPontuacao);
        }
        return this.nome.compareToIgnoreCase(outro.nome);
    }
}
