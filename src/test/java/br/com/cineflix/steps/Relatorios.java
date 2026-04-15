package br.com.cineflix.steps;
import java.util.List;
import br.com.cineflix.pages.RelatoriosPages;
import br.com.cineflix.support.Utils;
import io.cucumber.java.pt.*;

public class Relatorios extends Utils {

    RelatoriosPages relatorios = new RelatoriosPages();

    @Então("o sistema deve exibir a tela de relatório {string} contendo os seguintes filtros:")
    public void valida_filtros(String nomeRelatorio, List<String> filtros) {
        relatorios.validaFiltros(filtros);
    }

    @Quando("clicar no botao {string}")
    public void clicar_botao(String nomeBotao) {
        relatorios.clicarBotao(nomeBotao);
    }

    @Então("o sistema deve exibir o titulo {string}")
    public void valida_titulo(String titulo) {
        relatorios.validaTituloTela(titulo);
    }


    @Então("deve ser exibido a caixa de visualização de total de itens encontrados")
    public void valida_caixa_visualizacao() {
        relatorios.validaCaixaVisualizacao();
    }

    @E("o botao de exportar {string} deve estar disponível")
    public void valida_botao_exportar(String nomeBotao) {
        relatorios.validaBotaoExport(nomeBotao);
    }

    @Então("deve ser exibida a tabela de exibição de resultados contendo as seguintes colunas:")
    public void valida_tabela_resultados(List<String> colunas) {
        relatorios.validaTabelaResultados(colunas);
    }

}
