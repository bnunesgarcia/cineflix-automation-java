package br.com.cineflix.steps;
import br.com.cineflix.pages.PerfilDeAcessoPages;
import br.com.cineflix.support.Utils;
import io.cucumber.java.pt.*;

public class PerfilDeAcesso extends Utils {

    PerfilDeAcessoPages perfilDeAcesso = new PerfilDeAcessoPages();

    @E("o sistema deve exibir campo filtro para pesquisa de permissoes")
    public void campo_texto_pesquisa() {
        perfilDeAcesso.campoPesquisaPermissoes();
    }

    @Entao("eu cadastro {string} novos perfis de acesso sequenciais")
    public void cadastrar_perfis_de_acesso_sequenciais(String quantidade) {
        int total = Integer.parseInt(quantidade);
        
        for (int i = 1; i <= total; i++) {
            String nomeTitulo = "Perfil de Acesso " + i;
            String descPerfil = "Descrição automática do perfil " + i;

            perfilDeAcesso.clicarNoBotaoAdicionarPerfil();
            perfilDeAcesso.preencherTitulo(nomeTitulo);
            perfilDeAcesso.preencherDescricao(descPerfil);
            perfilDeAcesso.selecionarPermissao();
            perfilDeAcesso.clicarNoBotao();
            perfilDeAcesso.validarMensagemSucesso(); 
            
            System.out.println("Cadastrado: " + nomeTitulo);
        }
    }

    @E("deve ser exibido campo filtro para pesquisa de permissoes funcionando corretamente")
    public void validar_campo_filtro_permissoes() {
        perfilDeAcesso.validarCampoFiltroPermissoes();
    }

    @E("deve ser exibido permissoes atribuidas ao usuario")
    public void validar_permissoes_atribuidas() {
        perfilDeAcesso.validarListaDePermissoes();
    }

}
