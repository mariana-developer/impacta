import exceptions.AcaoNaoEncontradaException;
import exceptions.EmailDuplicadoException;
import exceptions.VoluntarioNaoEncontradoException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import models.Acao;
import models.MutiraoReciclagem;
import models.OficinaEcologica;
import models.PlantioMudas;
import models.Voluntario;

public class Impacta {
    private final Map<String, Voluntario> voluntarios;
    private final Map<Integer, Acao> acoes;
    private int proximoIdAcao;

    public Impacta() {
        this.voluntarios = new HashMap<>();
        this.acoes = new HashMap<>();
        this.proximoIdAcao = 1;
    }

    public boolean cadastrarVoluntario(String nome, String email, String matricula) {
        if (textoVazio(nome) || textoVazio(email) || textoVazio(matricula)) {
            return false;
        }
        String chave = chaveEmail(email);
        if (voluntarios.containsKey(chave)) {
            throw new EmailDuplicadoException();
        }
        voluntarios.put(chave, new Voluntario(nome, email, matricula));
        return true;
    }

    public String exibirVoluntario(String email) {
        return buscarVoluntario(email).exibir();
    }

    public String[] listarVoluntarios() {
        ArrayList<Voluntario> ordenados = new ArrayList<>(voluntarios.values());
        Collections.sort(ordenados);
        String[] resultado = new String[ordenados.size()];
        for (int i = 0; i < ordenados.size(); i++) {
            resultado[i] = ordenados.get(i).exibir();
        }
        return resultado;
    }

    public int cadastrarPlantio(String titulo, String descricao, String data, int maxParticipantes, int qtdMudas) {
        LocalDateTime dataConvertida = LocalDateTime.parse(data);
        return registrarAcao(new PlantioMudas(proximoIdAcao, titulo, descricao, dataConvertida, maxParticipantes, qtdMudas));
    }

    public int cadastrarMutirao(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras) {
        LocalDateTime dataConvertida = LocalDateTime.parse(data);
        return registrarAcao(new MutiraoReciclagem(proximoIdAcao, titulo, descricao, dataConvertida, maxParticipantes, duracaoHoras));
    }

    public int cadastrarOficina(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        LocalDateTime dataConvertida = LocalDateTime.parse(data);
        return registrarAcao(new OficinaEcologica(proximoIdAcao, titulo, descricao, dataConvertida, maxParticipantes, duracaoHoras, kitMaterial));
    }

    public boolean inscreverVoluntario(String emailVoluntario, int idAcao) {
        Voluntario voluntario = buscarVoluntario(emailVoluntario);
        Acao acao = buscarAcao(idAcao);
        acao.inscrever(voluntario);
        voluntario.adicionarAcao(acao);
        return true;
    }

    public String exibirDetalhesAcao(int idAcao) {
        return buscarAcao(idAcao).exibirDetalhes();
    }

    private int registrarAcao(Acao acao) {
        acoes.put(acao.getId(), acao);
        proximoIdAcao++;
        return acao.getId();
    }

    private Voluntario buscarVoluntario(String email) {
        Voluntario voluntario = voluntarios.get(chaveEmail(email));
        if (voluntario == null) {
            throw new VoluntarioNaoEncontradoException();
        }
        return voluntario;
    }

    private Acao buscarAcao(int idAcao) {
        Acao acao = acoes.get(idAcao);
        if (acao == null) {
            throw new AcaoNaoEncontradaException();
        }
        return acao;
    }

    private static boolean textoVazio(String texto) {
        return texto == null || texto.isBlank();
    }

    private static String chaveEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.trim().toLowerCase();
    }
}
