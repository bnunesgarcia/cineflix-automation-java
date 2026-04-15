package br.com.cineflix.pages;
import org.openqa.selenium.support.PageFactory;
import br.com.cineflix.support.Utils;

public class AdministrarPages extends Utils {

    public AdministrarPages() {
    PageFactory.initElements(driver, this);

    }

    public void acessaSubmenuAdministrar(String submenu) {
        if(submenu.equals("Usuários") || submenu.equals("Departamentos") || submenu.equals("Perfis de Acesso")){
            String tituloEsperado = "Administrar " + submenu;
            // String xpathTitulo = String.format("//h1[normalize-space()='%s']", tituloEsperado);
            String xpath = String.format("//h1[contains(text(), '%s')]", tituloEsperado);
            validarVisibilidadePorXpath(xpath, "O título da página de detalhes não apareceu! Esperado: " + tituloEsperado);
        } else {
            String tituloEsperado = submenu;
            // String xpathTitulo = String.format("//h1[normalize-space()='%s']", tituloEsperado);
            String xpath = String.format("//h1[contains(text(), '%s')]", tituloEsperado);
            validarVisibilidadePorXpath(xpath, "O título da página de detalhes não apareceu! Esperado: " + tituloEsperado);
        }
    }
}
