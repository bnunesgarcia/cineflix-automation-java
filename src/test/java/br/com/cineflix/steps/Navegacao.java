package br.com.cineflix.steps;
import java.util.List;
import br.com.cineflix.pages.NavegacaoPages;
import br.com.cineflix.support.Utils;
import io.cucumber.java.pt.*;

public class Navegacao extends Utils {

    NavegacaoPages navegacao = new NavegacaoPages();

    @Entao("o sistema deve conter uma barra de menus fixa no topo da tela")
    public void valida_barra_fixa_no_topo() {
        navegacao.validaBarraFixaNoTopo();
    }

    @E("a barra fixa deve conter Logo da RecordTV junto ao nome do sistema")
    public void valida_logo_e_nome_sistema() {
        navegacao.validaLogoENomeSistema();
    }

    @E("a barra fixa deve conter o botao de alteracao de tema do sistema")
    public void valida_botao_alteracao_tema() {
        navegacao.validaBotaoAlteracaoTema();
    }

    @Quando("eu clicar no botão de alteração de tema {}")
    public void clicarBotaoTema(String tema) {
        navegacao.trocarTemaEValidar(tema); 
    }

    @Entao("o sistema deve aplicar o tema {}")
    public void validarTemaAplicado(String tema) {
        navegacao.validaTema(tema);
    }

    @E("a barra fixa deve conter funcionalidade de Encerrar sessao a direita")
    public void valida_funcionalidade_encerrar_sessao() {
        navegacao.validaFuncionalidadeEncerrarSessao();
    }

    @E("a barra fixa deve conter o botao Buscar")
    public void valida_botao_buscar() {
        navegacao.validaBotaoBuscar();
    }

    @Entao("a barra fixa deve conter o menu {}")
    public void valida_menu(String menu) {
        navegacao.validaMenu(menu);
    }

    @Quando("clicar no menu {string}")
    public void clicar_no_menu(String menu) {
        navegacao.clicarNoMenu(menu);
    }

    @E("clicar na opção {string}")
    public void clicar_na_opcao(String opcao) {
        navegacao.clicarNaOpcao(opcao);
    }

    @Entao("o sistema deve exibir os seguintes submenus:")
    public void validar_lista_submenus(List<String> submenusEsperados) {
        for (String nomeSubmenu : submenusEsperados) {
            navegacao.validarSubmenus(nomeSubmenu);
        }
    }

    @Entao("eu devo ser direcionado para a tela de cadastro de filmes programados para serem exibidos")
    public void validar_tela_cadastro_filmes_programados() {
        navegacao.validarTelaCadastroFilmesProgramados();
    }

    @E("clicar no botao Buscar")
    public void clica_botao_buscar() {
        navegacao.clicaBotaoBuscar();
    }

    @Entao("o sistema deve exibir um campo texto para busca de titulos")
    public void campo_texto_busca_titulos() {
        navegacao.validaCampoTextoBuscaTitulos();
    }

    @E("o sistema deve exibir um campo de busca por filtros")
    public void campo_busca_filtros() {
        navegacao.validaCampoBuscaFiltros();
    }

    @Quando("clicar no campo de busca por filtros")
    public void clicar_campo_busca_filtros() {
        navegacao.clicarNoCampoBuscaFiltros();
    }

    @Entao("a busca avancada atraves do filtro deve conter botao de limpar campos preenchidos ou selecionados")
    public void valida_botao_limpar_filtros() {
        navegacao.validaBotaoLimparFiltros();
    }

    @E("a busca avancada atraves do filtro deve conter botao cancelar para cancelar operacao de filtro")
    public void valida_botao_cancelar_filtros() {
        navegacao.validaBotaoCancelarFiltros();
    }

    @E("a busca avancada atraves do filtro deve conter botao filtrar para executar o filtro")
    public void valida_botao_filtrar_filtros() {
        navegacao.validaBotaoFiltrar();
    }

    @E("o campo de busca deve conter os seguintes filtros:")
    public void valida_filtros(List<String> filtrosEsperados) {
        for (String nomeFiltro : filtrosEsperados) {
            navegacao.validaFiltros(nomeFiltro);
        }
    }

    @E("a busca avancada atraves do filtro deve conter flag {string}")
    public void a_busca_avancada_atraves_do_filtro_deve_conter_flag(String flag) {
        navegacao.validarFlagBuscaAvancada(flag);
    }
}
