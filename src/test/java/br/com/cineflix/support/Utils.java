package br.com.cineflix.support;

import br.com.cineflix.pages.DepartamentosPages;
import br.com.cineflix.pages.PerfilDeAcessoPages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Utils {

    protected WebDriver driver;
    protected static Properties properties;

    public Utils(){
        this.driver = DriverFactory.getDriver();
        if (properties == null) {
            carregarProperties();
        }
    }

    private void carregarProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/application.properties");
            properties.load(file);
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo application.properties: " + e.getMessage());
        }
    }

    public void esperarElementoEstarVisivel(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void esperarDadoEstarVisivel(By element, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.textToBe(element, text));
    }

    public void validarVisibilidadePorXpath(String xpath, String mensagemErro) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            
            Assert.assertTrue(mensagemErro, elemento.isDisplayed());
            System.out.println("Elemento validado com sucesso: " + xpath);
            
        } catch (Exception e) {
            Assert.fail(mensagemErro + " | Erro técnico: " + e.getMessage());
        }
    }

    public void validarBotaoDinamico(String nomeBotao) {
        String xpathBotao = String.format(
            "//span[normalize-space()='%s'] | //button[@mattooltip='Extrair página para Excel' and '%s'='Exportar']", 
            nomeBotao, nomeBotao
        );

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement btnElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathBotao)));
            
            Assert.assertTrue("O botão '" + nomeBotao + "' não está visível!", btnElement.isDisplayed());
            System.out.println("Botão validado com sucesso: " + nomeBotao);
            
        } catch (Exception e) {
            Assert.fail("Erro ao localizar o botão '" + nomeBotao + "'. Certifique-se de que o elemento está na tela. Erro: " + e.getMessage());
        }
    }

    public void exclusao(String tipo){
        if(tipo.equals("departamentos")) {
            DepartamentosPages departamentosPages = new DepartamentosPages();
            departamentosPages.excluirDepartamentos();
        } else if(tipo.equals("perfis de acesso")) {
            PerfilDeAcessoPages perfilDeAcessoPages = new PerfilDeAcessoPages();
            perfilDeAcessoPages.excluirPerfisDeAcesso();
        } else {
            Assert.fail("Tipo de exclusão desconhecido: " + tipo);
        }
    }

    public void waitAndClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        
        try {
            // Tenta o clique normal primeiro
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            // Se falhar (interceptado), força via JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

}
