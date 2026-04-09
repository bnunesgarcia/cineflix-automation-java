package br.com.cineflix.steps;
import java.util.List;
import br.com.cineflix.pages.DepartamentosPages;
import br.com.cineflix.support.Utils;
import io.cucumber.java.pt.*;

public class Departamentos extends Utils {

    DepartamentosPages departamentos = new DepartamentosPages();

    @Então("devo visualizar o campo de texto livre para pesquisa")
    public void campo_texto_pesquisa() {
        departamentos.campoTextoPesquisa();
    }

    @E("devo visualizar os botões {string}, {string} e {string}")
    public void devo_visualizar_os_botoes(String b1, String b2, String b3) {
        List.of(b1, b2, b3).forEach(departamentos::visualizarBotoes);
    }

    @Entao("eu cadastro {string} novos departamentos sequenciais")
    public void cadastrar_departamentos_sequenciais(String quantidade) {
        int total = Integer.parseInt(quantidade);
        
        for (int i = 1; i <= total; i++) {
            String nomeDepto = "Departamento " + i;
            String descDepto = "Descrição automática do depto " + i;

            departamentos.clicarNoBotaoAdicionarDepto();
            departamentos.preencherNome(nomeDepto);
            departamentos.preencherDescricao(descDepto);
            departamentos.clicarNoBotao();
            departamentos.validarMensagemSucesso(); 
            
            System.out.println("Cadastrado: " + nomeDepto);
        }
    }


    @Quando("eu clicar no botao de editar {string}")
    public void eu_clicar_no_botao_de_editar(String tipo) {
        departamentos.clicarNoPrimeiroBotaoEditar(tipo);
    }

    @E("os campos {string} e {string} devem vir preenchidos")
    public void validarCamposEditados(String campo1, String campo2) throws InterruptedException {
        departamentos.validarCampoPreenchido(campo1, campo2);
    }

    @Então("eu excluo {string}")
    public void excluir_departamentos(String tipo) {
        departamentos.exclusao(tipo);
    }
}
