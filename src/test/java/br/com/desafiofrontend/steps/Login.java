package br.com.desafiofrontend.steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import br.com.desafiofrontend.pages.LoginPages;

public class Login {

    LoginPages loginPages = new LoginPages();

    @E("acesso a tela de New User")
    public void acessar_tela_novo_usuario(){
        loginPages.acessaTelaNovoUsuario();
    }

    @Entao("cadastro um novo usuario com sucesso")
    public void cadastrar_novo_usuario() throws InterruptedException{
        loginPages.cadastraNovoUsuario();
    }

    @Entao("realizo login com sucesso")
    public void realizar_login(){
        loginPages.realizarLogin();
    }

    @E("eu realizo o logout com sucesso")
    public void realiza_logout(){
        loginPages.realizaLogout();
    }

    @Entao("realizo login inserindo {}")
    public void realizar_login_negativo(String motivo_erro){
        loginPages.realizarLoginNegativo(motivo_erro);
    }

    @E("o {} deve gerar a mensagem de erro {}")
    public void falha_no_login(String motivo_erro, String mensagem){
        loginPages.validaFalhaNoLogin(motivo_erro, mensagem);
    }
    
}
