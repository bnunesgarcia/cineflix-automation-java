package br.com.cineflix.pages;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.cineflix.support.Utils;

public class PerfilDeAcessoPages extends Utils {

    @FindBy(xpath = "//input[@placeholder='Pesquisar']")
    private WebElement campoPesquisaPermissoes;

    @FindBy(xpath = "//input[@placeholder='Título']")
    private WebElement campoTitulo;

    @FindBy(xpath = "//input[@placeholder='Pesquisar']")
    private WebElement campoPesquisarPermissoes;

    @FindBy(xpath = "//textarea[@formcontrolname='description']")
    private WebElement campoDescricao;

    @FindBy(xpath = "//span[contains(@class, 'mdc-button__label') and text()='Salvar']")
    private WebElement botaoSalvar;

    @FindBy(xpath = "//span[@class='mdc-button__label' and text()='Adicionar']")
    private WebElement botaoAddPerfil;

    @FindBy(xpath = "//span[contains(text(), 'Visualizar tudo')]")
    private WebElement linkVisualizarTudo;

    @FindBy(xpath = "//span[contains(text(), 'Listar conteúdos')]")
    private WebElement linkListarConteudos;
    
    @FindBy(xpath = "//span[contains(text(), 'Visualizar, criar ou editar tudo')]")
    private WebElement linkVisualizarCriarEditar;

    @FindBy(xpath = "//span[contains(text(), 'Visualizar conteúdos')]")
    private WebElement linkVisualizarConteudo; 

    @FindBy(xpath = "//span[contains(text(), 'Ler e escrever Administração')]")
    private WebElement linkLerOuEscreverAdm;

    @FindBy(xpath = "//span[contains(@class, 'mdc-button__label') and text()='Adicionar']")
    private WebElement botaoAdicionar;

    public PerfilDeAcessoPages() {
    PageFactory.initElements(driver, this);

    }

    public void campoPesquisaPermissoes() {
        campoPesquisaPermissoes.isDisplayed();
    }

    

    public void clicarNoBotaoAdicionarPerfil() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(botaoAddPerfil));
            
            try {
                botaoAddPerfil.click();
            } catch (Exception e) {
                System.out.println("Botão Adicionar interceptado pelo header. Forçando via JS...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botaoAddPerfil);
            }
        } catch (Exception e) {
            Assert.fail("Não foi possível clicar no botão Adicionar Perfil: " + e.getMessage());
        }
    }

    public void preencherTitulo(String nome) {
        campoTitulo.sendKeys(nome);
    }

    public void preencherDescricao(String descricao) {
        campoDescricao.sendKeys(descricao);
    }

    public void clicarNoBotao() {
        botaoSalvar.click();
    }

    public void selecionarPermissao(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(linkVisualizarTudo)).click();
        System.out.println("Clicou em Visualizar tudo");
        wait.until(ExpectedConditions.elementToBeClickable(linkListarConteudos)).click();
        wait.until(ExpectedConditions.elementToBeClickable(botaoAdicionar)).click();
    }

    public void validarMensagemSucesso() {
        By snackbarTexto = By.cssSelector("div.mat-mdc-snack-bar-label");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(snackbarTexto));

            String textoCapturado = mensagem.getText();
            String mensagemEsperada = "Perfil criado com sucesso.";
            Assert.assertTrue("A mensagem esperada não foi encontrada! Capturado: " + textoCapturado, 
                                    textoCapturado.contains(mensagemEsperada));
            
        } catch (Exception e) {
            Assert.fail("A mensagem de sucesso não apareceu após o cadastro! Erro: " + e.getMessage());
        }
    }

    public void validarCampoFiltroPermissoes(){
        campoPesquisaPermissoes.isDisplayed();
    }

    public void validarListaDePermissoes(){
        linkVisualizarTudo.isDisplayed();
        linkVisualizarCriarEditar.isDisplayed();
        linkLerOuEscreverAdm.isDisplayed();
        linkListarConteudos.isDisplayed();
        linkVisualizarConteudo.isDisplayed();
    }

    public void excluirPerfisDeAcesso() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String xpathBotaoDeletar = "//tr[td[contains(., 'Perfil')]]//mat-icon[text()='delete']/parent::button";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathBotaoDeletar)));
        System.out.println("Perfis de acesso detectados. Iniciando exclusão...");

        while (true) {
            // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathBotaoDeletar)));
            List<WebElement> perfisParaExcluir = driver.findElements(By.xpath(xpathBotaoDeletar));

            // Se não encontrar mais nenhum, encerra o loop
            if (perfisParaExcluir.isEmpty()) {
                System.out.println("Todos os perfis de acesso automatizados foram excluídos desta página.");
                break;
            }

            try {
                WebElement botao = perfisParaExcluir.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(botao)).click();
                DepartamentosPages departamentosPages = new DepartamentosPages();
                departamentosPages.confirmarExclusaoNoModal();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("mat-mdc-snack-bar-container")));
                
            } catch (StaleElementReferenceException e) {
                // Se a página atualizar muito rápido, reinicia a busca da lista
                continue;
            } catch (Exception e) {
                System.out.println("Erro ao excluir: " + e.getMessage());
                break;
            }
        }
    }

}
