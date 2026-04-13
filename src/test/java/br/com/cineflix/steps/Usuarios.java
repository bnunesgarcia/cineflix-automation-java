package br.com.cineflix.steps;
import java.util.List;

import org.openqa.selenium.WebElement;

import br.com.cineflix.pages.UsuariosPages;
import br.com.cineflix.support.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.*;

public class Usuarios extends Utils {

    UsuariosPages usuarios = new UsuariosPages();

    @Então("devo visualizar os filtros de {string}, {string}, {string} e {string}")
    public void devo_visualizar_os_filtros(String filtro1, String filtro2, String filtro3, String filtro4) {
        List.of(filtro1, filtro2, filtro3, filtro4).forEach(usuarios::visualizarFiltros);
    }

    @E("devo visualizar os botões {string}, {string}, {string} e {string}")
    public void devo_visualizar_os_botoes(String b1, String b2, String b3, String b4) {
        List.of(b1, b2, b3, b4).forEach(usuarios::visualizarBotoes);
    }

    @E("a tabela de listagem deve conter as colunas:")
    public void a_tabela_de_listagem_deve_conter_as_colunas(DataTable dataTable) {
        // Transforma a DataTable em uma List<String>
        List<String> colunasEsperadas = dataTable.asList();
        usuarios.visualizarColunasTabela(colunasEsperadas);
    }

    @Quando("eu clicar no botão {string}")
    public void eu_clicar_no_botao(String botao) {
        usuarios.clicarBotaoUsuarios(botao);
    }

    @Então("o sistema deve exibir o título {string}")
    public void o_sistema_deve_exibir_o_titulo(String titulo) {
        usuarios.visualizarTitulo(titulo);
    }

    @E("a tela de edição deve conter o toggle de status do usuario")
    public void visualizar_toggle_status() {
        usuarios.visualizarToggleStatus();
    }

    @E("os campos {string}, {string} e {string} devem estar preenchidos")
    public void validarCamposEditados(String campo1, String campo2, String campo3) {
        // Valida o primeiro campo
        validarFluxoDeCampo(campo1);
        // Valida o segundo campo
        validarFluxoDeCampo(campo2);
        // Valida o terceiro campo
        validarFluxoDeCampo(campo3);
    }

    @E("as caixa de seleção {string} deve exibir a opção previamente cadastrada")
    public void validarCaixaSelecao(String nomeDaCaixa) {
        WebElement elemento = usuarios.getElementoPorNome(nomeDaCaixa);
        usuarios.esperarEValidarCampoPreenchido(elemento, nomeDaCaixa);
    }

    private void validarFluxoDeCampo(String nomeDoCampoNoGherkin) {
        WebElement elemento = usuarios.getElementoPorNome(nomeDoCampoNoGherkin);
        usuarios.esperarEValidarCampoPreenchido(elemento, nomeDoCampoNoGherkin);
    }

    @E("o sistema deve exibir os campos para preenchimento:")
    public void o_sistema_deve_exibir_os_campos_para_preenchimento(List<String> camposEsperados) {
        usuarios.visualizarCamposPreenchimentoUsuarios(camposEsperados);
    }
    
    // @E("deve exibir a flag de status {string} como um botão slide-toggle")
    // public void exibir_flag_status(String status) {
    //     usuarios.visualizarFlagStatus(status);
    // }

    @E("deve exibir o botão {string}")
    public void exibir_botao(String botao) {
        usuarios.visualizarBotaoSalvar(botao);
    }

    @E("Aguarda 5 segundos")
        public void aguarda_segundos() throws InterruptedException {
        Thread.sleep(5000);
    }
}
