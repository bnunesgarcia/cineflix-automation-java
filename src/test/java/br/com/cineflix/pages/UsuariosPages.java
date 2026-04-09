package br.com.cineflix.pages;
import java.time.Duration;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.cineflix.support.Utils;

public class UsuariosPages extends Utils {

    public UsuariosPages() {
    PageFactory.initElements(driver, this);

    }

    public void visualizarFiltros(String nomeFiltro) {
        String xpathFiltro = String.format("//input[@placeholder='%s'] | //mat-label[normalize-space()='%s']", nomeFiltro, nomeFiltro);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement filtroElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathFiltro)));
            
            Assert.assertTrue("O filtro '" + nomeFiltro + "' não está visível na tela!", 
                            filtroElement.isDisplayed());
                            
            System.out.println("Filtro validado com sucesso: " + nomeFiltro);
            
        } catch (Exception e) {
            Assert.fail("Não foi possível encontrar o filtro: " + nomeFiltro + " na tela. Erro: " + e.getMessage());
        }
    }

    public void visualizarBotoes(String nomeBotao) {
        String xpathBotao = String.format(
            "//span[normalize-space()='%s'] | //button[@id='extract-xlsx' and 'Exportar'='%s'] | //button[@mattooltip='Extrair página para Excel' and 'Exportar'='%s']", 
            nomeBotao, nomeBotao, nomeBotao
        );

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement btnElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathBotao)));
            Assert.assertTrue("O botão '" + nomeBotao + "' não está visível na tela!", 
                            btnElement.isDisplayed());
                            
            System.out.println("Botão validado com sucesso: " + nomeBotao);
            
        } catch (Exception e) {
            Assert.fail("Não foi possível encontrar o botão: " + nomeBotao + ". Erro: " + e.getMessage());
        }
    }

    public void visualizarColunasTabela(List<String> colunasEsperadas) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (String nomeColuna : colunasEsperadas) {
            String xpathColuna = String.format("//th[normalize-space()='%s'] | //mat-header-cell[normalize-space()='%s']", nomeColuna, nomeColuna);
            
            try {
                WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathColuna)));
                
                Assert.assertTrue("A coluna '" + nomeColuna + "' não está visível na tabela!", 
                                elemento.isDisplayed());
                                
                System.out.println("Coluna validada: " + nomeColuna);
                
            } catch (TimeoutException e) {
                Assert.fail("Erro: A coluna [" + nomeColuna + "] não foi encontrada na tabela de listagem.");
            }
        }
    }

    public void clicarBotaoUsuarios(String nomeBotao) {
        String xpathBotao = String.format(
            "//span[normalize-space()='%s'] | //button[@id='extract-xlsx' and 'Exportar'='%s'] | //button[@mattooltip='Extrair página para Excel' and 'Exportar'='%s']", 
            nomeBotao, nomeBotao, nomeBotao
        );

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement btnElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathBotao)));
            
            try {
                btnElement.click();
                System.out.println("Clicou no botão via Selenium: " + nomeBotao);
            } catch (Exception e) {
                // Se o Selenium falhar (ex: interceptado por Snackbar), tenta via JS
                System.out.println("Clique padrão falhou para " + nomeBotao + ". Tentando via JavaScript...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnElement);
                System.out.println("Clicou no botão via JavaScript: " + nomeBotao);
            }
            
        } catch (Exception e) {
            Assert.fail("Não foi possível localizar ou clicar no botão: " + nomeBotao + ". Erro: " + e.getMessage());
        }
    }

    public void visualizarTitulo(String tituloEsperado) {
        String xpathTitulo = String.format("//h1[normalize-space()='%s'] | //mat-card-title[normalize-space()='%s']", tituloEsperado, tituloEsperado);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement tituloElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathTitulo)));
            
            Assert.assertTrue("O título '" + tituloEsperado + "' não está visível na tela!", 
                            tituloElement.isDisplayed());
                            
            System.out.println("Título validado com sucesso: " + tituloEsperado);
            
        } catch (Exception e) {
            Assert.fail("Não foi possível encontrar o título: " + tituloEsperado + " na tela. Erro: " + e.getMessage());
        }
    }

    public void visualizarCamposPreenchimentoUsuarios(List<String> camposEsperados) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (String nomeCampo : camposEsperados) {
            String xpathCampo = String.format("//input[@placeholder='%s'] | //mat-label[normalize-space()='%s']", nomeCampo, nomeCampo);
            
            try {
                WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathCampo)));
                
                Assert.assertTrue("O campo '" + nomeCampo + "' não está visível para preenchimento!", 
                                elemento.isDisplayed());
                                
                System.out.println("Campo de preenchimento validado: " + nomeCampo);
                
            } catch (TimeoutException e) {
                Assert.fail("Erro: O campo [" + nomeCampo + "] não foi encontrado para preenchimento.");
            }
        }
    }

    public void visualizarBotaoSalvar(String nomeBotao) {
        String xpathBotao = String.format("//span[normalize-space()='%s'] | //button[normalize-space()='%s']", nomeBotao, nomeBotao);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement btnElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathBotao)));
            
            Assert.assertTrue("O botão '" + nomeBotao + "' não está visível na tela!", 
                            btnElement.isDisplayed());
                            
            System.out.println("Botão validado com sucesso: " + nomeBotao);
            
        } catch (Exception e) {
            Assert.fail("Não foi possível encontrar o botão: " + nomeBotao + ". Erro: " + e.getMessage());
        }
    }

}
