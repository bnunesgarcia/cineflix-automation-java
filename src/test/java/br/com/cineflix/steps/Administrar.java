package br.com.cineflix.steps;
import br.com.cineflix.pages.AdministrarPages;
import br.com.cineflix.support.Utils;
import io.cucumber.java.pt.*;

public class Administrar extends Utils {

    AdministrarPages administrar = new AdministrarPages();

    @Entao("devo ser redirecionado para a tela de gerenciamento de {string}")
    public void acessa_submenu_administrar(String submenu) {
        administrar.acessaSubmenuAdministrar(submenu);
    }
}
