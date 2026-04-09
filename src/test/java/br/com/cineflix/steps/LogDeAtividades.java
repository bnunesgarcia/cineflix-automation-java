package br.com.cineflix.steps;
import java.util.List;

import br.com.cineflix.pages.LogDeAtividadesPages;
import br.com.cineflix.support.Utils;
import io.cucumber.java.pt.*;

public class LogDeAtividades extends Utils {

    LogDeAtividadesPages logDeAtividades = new LogDeAtividadesPages();

    @Entao("devo visualizar o campo de pesquisa por Período de atividade")
    public void campo_pesquisa_periodo_atividade() {
        logDeAtividades.campoPesquisaPeriodoAtividade();
    }

    @E("devo visualizar o campo de pesquisa por tipo de Evento")
    public void campo_pesquisa_tipo_evento() throws InterruptedException {
        logDeAtividades.campoPesquisaTipoEvento();
    }

    @E("o campo de filtro por tipo de Evento deve conter as opções:")
    public void tipo_de_eventos(List<String> opcoesEsperadas) {
        logDeAtividades.visualizarTipoDeEventos(opcoesEsperadas);
    }

    @E("devo visualizar o campo de pesquisa por usuário")
    public void campo_pesquisa_usuario() {
        logDeAtividades.campoPesquisaUsuario();
    }

}
