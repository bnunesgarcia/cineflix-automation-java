package br.com.cineflix.pages;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.cineflix.support.Utils;

public class DepartamentosPages extends Utils {

    @FindBy(xpath = "//input[@placeholder='Pesquisar']")
    private WebElement campoPesquisaLivre;

    @FindBy(xpath = "//input[@placeholder='Nome']")
    private WebElement campoNome;

    @FindBy(xpath = "//input[@placeholder='Título']")
    private WebElement campoTitulo;

    @FindBy(xpath = "//textarea[@formcontrolname='description']")
    private WebElement campoDescricao;

    @FindBy(xpath = "//span[contains(@class, 'mdc-button__label') and text()='Salvar']")
    private WebElement botaoSalvar;

    @FindBy(xpath = "//span[contains(@class, 'mdc-button__label') and contains(text(), 'Adicionar')]")
    private WebElement botaoAddDepto;

    @FindBy(xpath = "(//tbody/tr//button//mat-icon[text()='edit']/parent::button)[1]")
    private WebElement primeiroBotaoEditarPerfilDeAcesso;

    @FindBy(css = "tbody tr:first-child button .mat-icon[data-mat-icon-type='font']")
    private WebElement primeiroBotaoEditarUsuario;

    @FindBy(xpath = "(//button[.//mat-icon[text()='edit']])[1]")
    private WebElement primeiroBotaoEditarDepartamento;

    @FindBy(xpath = "//app-decision-dialog//button//span[text()='Confirmar']")
    private WebElement botaoConfirmarExclusao;

    public DepartamentosPages() {
    PageFactory.initElements(driver, this);

    }

    public WebElement getElementoPorNome(String nomeCampo) {
        switch (nomeCampo.toLowerCase()) {
            case "nome": return campoNome;
            case "descrição":
            case "descricao": return campoDescricao;
            case "título":
            case "titulo": return campoTitulo;
            default: throw new IllegalArgumentException("Campo não mapeado: " + nomeCampo);
        }
    }

    public void campoTextoPesquisa() {
        campoPesquisaLivre.isDisplayed();
    }

    public void visualizarBotoes(String nomeBotao) {
        validarBotaoDinamico(nomeBotao);
    }

    public void clicarNoBotaoAdicionarDepto(){
        botaoAddDepto.click();
    }

    public void preencherNome(String nome) {
        campoNome.sendKeys(nome);
    }

    public void preencherDescricao(String descricao) {
        campoDescricao.sendKeys(descricao);
    }

    public void clicarNoBotao() {
        botaoSalvar.click();
    }

    public void validarMensagemSucesso() {
        By snackbarTexto = By.cssSelector("div.mat-mdc-snack-bar-label");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(snackbarTexto));

            String textoCapturado = mensagem.getText();
            String mensagemEsperada = "Departamento criado com sucesso.";
            Assert.assertTrue("A mensagem esperada não foi encontrada! Capturado: " + textoCapturado, 
                                    textoCapturado.contains(mensagemEsperada));
            
        } catch (Exception e) {
            Assert.fail("A mensagem de sucesso não apareceu após o cadastro! Erro: " + e.getMessage());
        }
    }

    public void clicarNoPrimeiroBotaoEditar(String tipo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // 1. SINCRONISMO GLOBAL: Espera a primeira linha da tabela aparecer
        // Isso garante que os dados carregaram, idependente do tipo (departamento ou perfil)
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]")));
        } catch (TimeoutException e) {
            System.out.println("A tabela não carregou dados a tempo.");
        }

        // 2. SELEÇÃO DO ELEMENTO
        if (tipo.equalsIgnoreCase("perfil de acesso")) {
            executarCliqueSeguro(primeiroBotaoEditarPerfilDeAcesso);
        } else if (tipo.equalsIgnoreCase("departamento")) {
            executarCliqueSeguro(primeiroBotaoEditarDepartamento);
        } else if (tipo.equalsIgnoreCase("usuario")){
            executarCliqueSeguro(primeiroBotaoEditarUsuario);
        }
    }

    private void executarCliqueSeguro(WebElement elemento) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elemento)).click();
        } catch (Exception e) {
            System.out.println("Clique padrão falhou ou foi interceptado. Forçando via JS...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
        }
    }

    public void esperarEValidarCampoPreenchido(WebElement elemento, String nomeCampo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Espera até que o atributo 'value' não esteja mais vazio ou nulo
            wait.until(driver -> {
                String valor = elemento.getAttribute("value");
                return valor != null && !valor.trim().isEmpty();
            });
        } catch (TimeoutException e) {
            Assert.fail("O campo '" + nomeCampo + "' não foi preenchido após 10 segundos.");
        }

        // Validação final por segurança
        String valorFinal = elemento.getAttribute("value");
        Assert.assertFalse("O campo '" + nomeCampo + "' deveria estar preenchido!", valorFinal.isEmpty());
    }

    public void confirmarExclusaoNoModal(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(botaoConfirmarExclusao));
            btn.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("app-decision-dialog")));
            System.out.println("Exclusão confirmada no modal.");
        } catch (Exception e) {
            Assert.fail("Não foi possível confirmar a exclusão no modal: " + e.getMessage());
        }
    }

    public void excluirDepartamentos() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String xpathBotaoDeletar = "//tr[td[contains(., 'Departamento')]]//mat-icon[text()='delete']/parent::button";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathBotaoDeletar)));
        System.out.println("Departamentos detectados. Iniciando exclusão...");

        while (true) {
            // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathBotaoDeletar)));
            List<WebElement> departamentosParaExcluir = driver.findElements(By.xpath(xpathBotaoDeletar));

            // Se não encontrar mais nenhum, encerra o loop
            if (departamentosParaExcluir.isEmpty()) {
                System.out.println("Todos os departamentos automatizados foram excluídos desta página.");
                break;
            }

            try {
                WebElement botao = departamentosParaExcluir.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(botao)).click();
                confirmarExclusaoNoModal();
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
