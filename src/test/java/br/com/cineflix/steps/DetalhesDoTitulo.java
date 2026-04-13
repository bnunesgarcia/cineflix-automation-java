package br.com.cineflix.steps;
import java.util.List;
import br.com.cineflix.pages.DetalhesDoTituloPages;
import br.com.cineflix.support.Utils;
import io.cucumber.java.pt.*;

public class DetalhesDoTitulo extends Utils {

    DetalhesDoTituloPages detalhes = new DetalhesDoTituloPages();

    @E("clicar em um banner pelo título {string} na tela inicial")
    public void clicarEmUmBannerPeloTituloNaTelaInicial(String titulo) throws InterruptedException {
        detalhes.clicarNoBannerPeloTitulo(titulo);
        System.out.println("Aguardando 10 segundos para observação visual...");
        Thread.sleep(5000);
    }

    @E("deve exibir os botões de ação:")
    public void deve_exibir_os_botoes_de_acao(List<String> botoes) {
        detalhes.validarBotoesAcao(botoes);
    }

    @Entao("ao lado direito da tela sistema deve exibir os seguintes detalhes:")
    public void deve_exibir_os_detalhes(List<String> detalhesTitulo) {
        detalhes.validarDetalhesTitulo(detalhesTitulo);
    }

    @E("deve conter uma tabela com Detalhes de Exibições")
    public void deve_conter_uma_tabela_com_detalhes_de_exibicoes() {
        detalhes.validaTabelaDetalhesExibicao();
    }

    @E("deve exibir as listas de cards para {string}")
    public void deve_exibir_as_listas_de_cards_com_foto_e_nome(String categoria) {
        detalhes.validarCardsNaSecao(categoria);
    }

    @Então("o sistema deve abrir uma caixa de diálogo")
    public void valida_caixa_de_dialogo(){
        detalhes.validaCaixaDeDialogo();
    }

    @E("deve exibir a tabela de {string} com as colunas:")
    public void valida_tabela_com_colunas(String nomeTabela, List<String> colunas) {
        detalhes.validarColunasTabela(nomeTabela, colunas);
    }

    @Então("o sistema deve exibir os detalhes técnicos:")
    public void valida_detalhes_tecnicos(List<String> detalhesTecnicos) {
        detalhes.validarPresencaDetalhesTecnicos(detalhesTecnicos);
    }

    @Então("deve abrir caixa de diálogo contendo o trailer cadastrado pronto para executar")
    public void valida_trailer() {
        detalhes.validarTrailer();
    }

    @E("o sistema deve exibir o titulo do filme")
    public void valida_titulo_do_filme() {
        detalhes.validarExistenciaTituloDoFilme();
    }
}
