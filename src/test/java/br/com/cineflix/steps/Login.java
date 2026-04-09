package br.com.cineflix.steps;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import br.com.cineflix.pages.LoginPages;
import br.com.cineflix.support.Utils;

public class Login extends Utils{

    LoginPages loginPages = new LoginPages();

    @Dado("que eu acesse a aplicacao cineflix")
    public void acessa_aplicacao_cineflix() {
        loginPages.acessarAplicacao();
    }

    @Entao("a tela deve conter o logo da recordTv")
    public void valida_logo(){
        loginPages.validaLogoRecord();
    }

    @E("a tela deve conter o nome do sistema")
    public void valida_nome_sistema(){
        loginPages.validaNomeSistema();
    }

    @E("a tela deve conter o botao para acesso ao sistema com login de rede")
    public void valida_botao_login_rede(){
        loginPages.validaBotaoLoginRede();
    }

    @E("a tela deve conter o botao para acesso via bot ao sistema")
    public void valida_botao_login_automacao(){
        loginPages.validaBotaoLoginAutomacao();
    }

    @E("apos clique no botao de acesso via bot deve ser disponiblizado os campos para preenchimento das credenciais")
    public void valida_campos_login_automacao(){
        loginPages.validaCamposLoginAutomacao();
    }

    @Entao("o acesso deve ser realizado com sucesso")
    public void valida_acesso_realizado_com_sucesso(){
        loginPages.validarLoginComSucesso();
    }

    @Quando("realizar o login na aplicacao com sucesso")
    public void realizar_login_sucesso() {
        String idToken = properties.getProperty("token.id_valido");
        String accessToken = properties.getProperty("token.access_valido");
        loginPages.validaCamposLoginAutomacao();;
        loginPages.preencherTokensERealizarLogin(idToken, accessToken);
        loginPages.validarLoginComSucesso(); 
    }

}
