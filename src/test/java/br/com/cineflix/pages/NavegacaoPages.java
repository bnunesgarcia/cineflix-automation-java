package br.com.cineflix.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.com.cineflix.support.Utils;
import java.time.Duration;
import org.junit.Assert;

public class NavegacaoPages extends Utils {

    public NavegacaoPages() {
    PageFactory.initElements(driver, this);
    }

    @FindBy(id = "navbar-principal")
    private WebElement barraNavegacaoSuperior;
    @FindBy(id = "toolbar-logo")
    private WebElement logoSuperior;
    @FindBy(id = "header-title")
    private WebElement nomeSistema;
    @FindBy(id = "toggle-theme")
    private WebElement botaoAlteracaoTema;
    @FindBy(css = "#toggle-theme mat-icon")
    private WebElement iconeTema;
    @FindBy(id = "user-menu")
    private WebElement botaoUsuario;
    @FindBy(id = "logout")
    private WebElement botaoLogout;
    @FindBy(xpath = "//mat-label[normalize-space()='Buscar']")
    private WebElement botaoBuscar;
    @FindBy(xpath = "//span[contains(text(), 'Administrar ')]")
    private WebElement menuAdmnistrar;
    @FindBy(xpath = "//span[contains(text(), 'Relatório ')]")
    private WebElement menuRelatorios;
    @FindBy(xpath = "//span[contains(text(), ' Cadastro ')]")
    private WebElement menuCadastro;
    @FindBy(xpath = "//span[contains(text(), ' Visualizar Programação ')]")
    private WebElement menuVisualizarProgramacao;
    @FindBy(xpath = "//button[.//mat-icon[text()='search'] and .//span[contains(text(), 'Buscar')]]")
    private WebElement btnBuscar;
    @FindBy(css = "input.mat-mdc-input-element.mat-mdc-form-field-input-control")
    private WebElement campoTextoBuscaFiltro;
    @FindBy(xpath = "//button[contains(@class, 'advanced-filter')]//span[contains(text(), 'Filtros')]")
    private WebElement btnFiltros;
    @FindBy(xpath = "//span[normalize-space()='Limpar']")
    private WebElement btnLimparFiltros;
    @FindBy(xpath = "//button[@color='warn' and @mattooltip='Campos selecionados serão perdidos']")
    private WebElement btnCancelarFiltros;
    @FindBy(xpath = "//span[normalize-space()='Filtrar']")
    private WebElement btnFiltrar;

    public void validaBarraFixaNoTopo() {
        Assert.assertTrue("A barra de menus fixa no topo da tela não está presente!", 
        barraNavegacaoSuperior.isDisplayed());
    }

    public void validaLogoENomeSistema() {        
        Assert.assertTrue("O logo da RecordTV não está presente na barra de navegação!", logoSuperior.isDisplayed());
        Assert.assertTrue("O nome do sistema não está presente na barra de navegação!", nomeSistema.isDisplayed());
        String textoAtual = nomeSistema.getText();
        Assert.assertEquals("O nome do sistema no header está incorreto!", "CINEFLIX", textoAtual);
    }

    public void validaBotaoAlteracaoTema(){
        Assert.assertTrue("O botão de alteração de tema não está presente na barra de navegação!", botaoAlteracaoTema.isDisplayed());
    }

    public void validaFuncionalidadeEncerrarSessao() {
        Assert.assertTrue("O nome do usuario nao esta presente", botaoUsuario.isDisplayed());
        botaoUsuario.click();
        Assert.assertTrue("A opção de encerrar sessão não está presente no menu do usuário!", botaoLogout.isDisplayed());
    }

    public void validaBotaoBuscar() {
        Assert.assertTrue("O botão de busca não está presente na barra de navegação!", botaoBuscar.isDisplayed());
    }

    public void validaMenu(String menu) {
        if (menu.equals("Administrar")) {
            Assert.assertTrue("O menu 'Administrar' não está presente na barra de navegação!", 
            menuAdmnistrar.isDisplayed());
        } else if (menu.equals("Relatorios")) {
            Assert.assertTrue("O menu 'Relatorios' não está presente na barra de navegação!", 
            menuRelatorios.isDisplayed());
        } else if (menu.equals("Cadastro")) {
            Assert.assertTrue("O menu 'Cadastro' não está presente na barra de navegação!", 
            menuCadastro.isDisplayed());
        } else if (menu.equals("Visualizar Programacao")) {
            Assert.assertTrue("O menu 'Visualizar Programacao' não está presente na barra de navegação!", 
            menuVisualizarProgramacao.isDisplayed());
        }
    }

    public void validaTema(String tema) {
        WebElement body = driver.findElement(By.tagName("body"));
        String classesDoBody = body.getAttribute("class");
        String colorScheme = body.getAttribute("style");

        if (tema.equalsIgnoreCase("Light")) {
            Assert.assertTrue("O tema esperado era Light, mas a classe 'light-theme' não foi encontrada!", 
                classesDoBody.contains("light-theme"));
            Assert.assertTrue("O style 'color-scheme' não está como light!", 
                colorScheme.contains("color-scheme: light"));

        } else if (tema.equalsIgnoreCase("Dark")) {
            Assert.assertTrue("O tema esperado era Dark, mas a classe 'dark-theme' não foi encontrada!", 
                classesDoBody.contains("dark-theme"));
            Assert.assertTrue("O style 'color-scheme' não está como dark!", 
                colorScheme.contains("color-scheme: dark"));
        }
    }

    public void trocarTemaEValidar(String temaAlvo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toggle-theme")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botaoAlteracaoTema);
            System.out.println("Solicitada alteração de tema para: " + temaAlvo);
            String classeEsperada = temaAlvo.toLowerCase() + "-theme";
            wait.until(ExpectedConditions.attributeContains(By.tagName("body"), "class", classeEsperada));
            validaTema(temaAlvo);
            
        } catch (Exception e) {
            Assert.fail("Não foi possível trocar o tema para " + temaAlvo + ". Erro: " + e.getMessage());
        }
    }

    public void clicarNoMenu(String nomeMenu) {
        String xpathMenu = String.format("//button[.//span[contains(@class, 'mdc-button__label') and normalize-space()='%s']]", nomeMenu);
        
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement menuElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathMenu)));
            
            menuElement.click();
            System.out.println("Menu clicado: " + nomeMenu);
            
        } catch (Exception e) {
            Assert.fail("Não foi possível clicar no menu: " + nomeMenu + ". Erro: " + e.getMessage());
        }
    }

    public void clicarNaOpcao(String nomeOpcao) {
        String xpathOpcao = String.format("//button[@role='menuitem'][normalize-space()='%s'] | //button[@role='menuitem']//span[normalize-space()='%s']", nomeOpcao, nomeOpcao);
        
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement opcaoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOpcao)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opcaoElement);

            try {
                opcaoElement.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opcaoElement);
            }
            
            System.out.println("Opção clicada com sucesso: " + nomeOpcao);
    
        } catch (Exception e) {
            Assert.fail("Não foi possível localizar ou clicar na opção: " + nomeOpcao + ". Verifique se o menu está aberto. Erro: " + e.getMessage());
        }
    }

    public void validarSubmenus(String nomeSubmenu) {
        String xpathDinâmico = String.format("//button[@role='menuitem']//span[contains(text(), '%s')]", nomeSubmenu);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500)); 
        
        try {
            WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathDinâmico)));
            Assert.assertTrue("Submenu não exibido: " + nomeSubmenu, elemento.isDisplayed());
        } catch (TimeoutException e) {
            Assert.fail("O submenu [" + nomeSubmenu + "] não foi encontrado na lista aberta.");
        }
    }

    public void validarTelaCadastroFilmesProgramados() {
        String xpathTab = "//span[contains(@class, 'mdc-tab__text-label') and contains(text(), 'Programação Anual')]";
    
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement labelProgramacao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathTab)));
            Assert.assertTrue("A tela de Programação Anual não foi exibida corretamente!", 
                labelProgramacao.isDisplayed());
            WebElement tabPai = labelProgramacao.findElement(By.xpath("./ancestor::div[@role='tab']"));
            String estaSelecionada = tabPai.getAttribute("aria-selected");
            
            Assert.assertEquals("A aba 'Programação Anual' foi encontrada, mas não está selecionada!", 
                "true", estaSelecionada);

            System.out.println("Tela 'Programação Anual' validada com sucesso.");

        } catch (TimeoutException e) {
            Assert.fail("Timeout: Não foi possível carregar a tela de Programação Anual.");
        }
    }

    public void clicaBotaoBuscar() {
        botaoBuscar.click();
    }

    public void validaCampoTextoBuscaTitulos() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement campoVisible = wait.until(ExpectedConditions.visibilityOf(campoTextoBuscaFiltro));
            Assert.assertTrue("O campo de texto para busca de títulos não foi exibido!", 
                campoVisible.isDisplayed());
                
            System.out.println("Campo de texto de busca validado com sucesso.");

        } catch (TimeoutException e) {
            Assert.fail("Timeout: O campo de texto de busca não apareceu após 5 segundos.");
        }
    }

    public void validaCampoBuscaFiltros() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement filtroVisible = wait.until(ExpectedConditions.visibilityOf(btnFiltros));
            Assert.assertTrue("O botão 'Filtros' não foi exibido na tela de busca!", 
                filtroVisible.isDisplayed());
                
            System.out.println("Botão 'Filtros' validado com sucesso.");

        } catch (TimeoutException e) {
            Assert.fail("Timeout: O botão 'Filtros' não apareceu após o acionamento da busca.");
        }
    }

    public void clicarNoCampoBuscaFiltros() {
        btnFiltros.click();
    }

    public void validaBotaoLimparFiltros(){
        btnLimparFiltros.isDisplayed();
    }

    public void validaBotaoCancelarFiltros(){
        btnCancelarFiltros.isDisplayed();
    }

    public void validaBotaoFiltrar(){
        btnFiltrar.isDisplayed();
    }

    public void validaFiltros(String nomeFiltro) {
        String xpathFiltro = String.format("//mat-label[normalize-space()='%s'] | //span[normalize-space()='%s']", nomeFiltro, nomeFiltro);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement elementoFiltro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathFiltro)));
            
            Assert.assertTrue("O filtro [" + nomeFiltro + "] não está visível na tela!", 
                elementoFiltro.isDisplayed());
                
            System.out.println("Filtro validado: " + nomeFiltro);

        } catch (TimeoutException e) {
            Assert.fail("Erro: O filtro [" + nomeFiltro + "] não foi encontrado após 5 segundos. Verifique se o nome na feature está idêntico ao da tela.");
        }
    }

    public void validarFlagBuscaAvancada(String nomeFlag) {
        String xpathFlag = String.format("//mat-slide-toggle[.//label[normalize-space()='%s']]", nomeFlag);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement flagElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathFlag)));
            Assert.assertTrue("A flag [" + nomeFlag + "] não foi encontrada ou não está visível!", 
                flagElement.isDisplayed());
            WebElement toggleButton = flagElement.findElement(By.cssSelector("button[role='switch']"));
            Assert.assertNotNull("O botão de alternância da flag " + nomeFlag + " não foi localizado.", toggleButton);

            System.out.println("Flag validada com sucesso: " + nomeFlag);

        } catch (TimeoutException e) {
            Assert.fail("Erro: A flag [" + nomeFlag + "] não apareceu no modal de busca avançada.");
        }
    }

}
