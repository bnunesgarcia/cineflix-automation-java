package br.com.cineflix.pages;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.cineflix.support.Utils;

public class LogDeAtividadesPages extends Utils {

    @FindBy(xpath = "//*[@id='period-of-insert']")
    private WebElement campoPesquisaPeriodoAtividade;
    @FindBy(xpath = "//*[@id='action']")
    private WebElement campoPesquisaTipoEvento;
    @FindBy(xpath = "//*[@id='user']")
    private WebElement campoPesquisaUsuario;

    public LogDeAtividadesPages() {
    PageFactory.initElements(driver, this);
    }

    public void campoPesquisaPeriodoAtividade() {
        campoPesquisaPeriodoAtividade.isDisplayed();
    }

    public void campoPesquisaTipoEvento() throws InterruptedException {
        campoPesquisaTipoEvento.isDisplayed();
        Thread.sleep(2500);
    }

    public void campoPesquisaUsuario() {
        campoPesquisaUsuario.isDisplayed();
    }

    public void visualizarTipoDeEventos(List<String> opcoesEsperadas) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 1. Abre o dropdown garantindo que o clique ocorra mesmo com overlays na frente
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", campoPesquisaTipoEvento);
            System.out.println("Dropdown aberto.");

            // 2. Aguarda o container de opções do Angular aparecer no DOM global
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdk-overlay-pane")));

            for (String opcao : opcoesEsperadas) {
                // XPath focado na classe específica que vimos no seu print image_544b65
                String xpathOpcao = "//mat-option//span[normalize-space()='" + opcao + "']";
                
                try {
                    // Busca a opção em todo o documento, já que o mat-option fica no final do HTML
                    WebElement opcaoElemento = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOpcao)));
                    
                    // Rola até a opção (caso a lista seja longa) e valida visibilidade
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opcaoElemento);
                    Assert.assertTrue("Opção '" + opcao + "' não está visível!", opcaoElemento.isDisplayed());
                    
                    System.out.println("Opção validada: " + opcao);
                } catch (Exception e) {
                    throw new AssertionError("A opção '" + opcao + "' não foi encontrada no overlay do Angular.");
                }
            }
        } catch (Exception e) {
            Assert.fail("Erro ao interagir com o dropdown: " + e.getMessage());
        } finally {
            // Fecha o dropdown para não quebrar o próximo step
            new Actions(driver).sendKeys(Keys.ESCAPE).perform();
        }
    }

}
