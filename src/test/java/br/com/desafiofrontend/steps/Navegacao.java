package br.com.desafiofrontend.steps;

import br.com.desafiofrontend.pages.NavegacaoPages;
import br.com.desafiofrontend.support.Utils;
import io.cucumber.java.pt.*;

public class Navegacao extends Utils {

    NavegacaoPages manipulaRegistros = new NavegacaoPages();

    @Dado("que eu acesse a aplicacao demoqa")
    public void acessa_aplicacao_Demoqa() {
        acessarAplicacao();
    }

    @E("o menu {} esta disponivel")
    public void valida_existencia_do_menu(String menu){
        manipulaRegistros.validaExibicaoMenu(menu);
    } 

    @Quando("eu clico no menu {}")
    public void clicar_no_menu(String menu) {
        manipulaRegistros.acessaMenu(menu);
    }

    @Entao("o submenu {} deve estar disponivel")
    public void validar_submenu(String menu) {
        manipulaRegistros.validaSubMenu(menu);
    }

    @E("eu acesso o {}")
    public void clicar_no_submenu(String submenu) {
        manipulaRegistros.acessaSubMenu(submenu);
    }

    @E("acesso a tela de {} registro")
    public void acessar_operacao_de(String operacao) {
        manipulaRegistros.acessarOperacao(operacao);
    }

    @E("{} os dados do registro")
    public void manipular_dados(String acao) throws InterruptedException{
        manipulaRegistros.realizarOperacao(acao);
    }

    @E("a operacao de {} deve ser realizada com sucesso")
    public void validar_acao(String tipoOper){
        manipulaRegistros.validaAcao(tipoOper);
    }

    @E("eu pesquiso o funcionario {}")
    public void pequisar_registro(String registro){
        manipulaRegistros.pesquisaRegistro(registro);
    }
    
    @E("a pesquisa deve ser realizada com sucesso para o registro {}")
    public void pesquisa_com_sucesso(String registro){
        manipulaRegistros.validaPesquisa(registro);
    }
    
    @E("a pesquisa nao deve retornar dados")
    public void pesquisa_negativa(){
        manipulaRegistros.pesquisaNegativaDeRegistros();
    }
}
