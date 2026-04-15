package br.com.cineflix.pages;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.cineflix.support.Utils;

public class RelatoriosPages extends Utils {

    @FindBy(xpath = "//*[@id='period-of-insert']")
    private WebElement campoPesquisaPeriodoAtividade;
    @FindBy(xpath = "//*[@id='action']")
    private WebElement campoPesquisaTipoEvento;
    @FindBy(xpath = "//*[@id='user']")
    private WebElement campoPesquisaUsuario;
    @FindBy(css = "mat-card.report-result")
    private WebElement cardResultadoRelatorio;

    public RelatoriosPages() {
        PageFactory.initElements(driver, this);
    }

    private By getFiltroLocator(String nome) {
        return By.xpath(String.format("//mat-label[normalize-space()='%s'] | //span[normalize-space()='%s']", nome, nome));
    }

    public void validaFiltros(List<String> filtrosEsperados) {
        // Em vez de um loop com Try/Catch, usamos Streams para validar tudo
        filtrosEsperados.forEach(filtro -> {
            Assert.assertTrue("Filtro não encontrado: " + filtro, 
                driver.findElement(getFiltroLocator(filtro)).isDisplayed());
        });
    }

    public void clicarBotao(String nomeBotao) {
        By btn = By.xpath(String.format("//button//span[contains(text(), '%s')]", nomeBotao));
        WebElement elemento = driver.findElement(btn);
        // Rola a tela até o botão ficar no meio da visão
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", elemento);
        waitAndClick(btn);
    }

    public void validaCaixaVisualizacao(){
        cardResultadoRelatorio.isDisplayed();
    }

    public void validaBotaoExport(String nomeBotao) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String iconeEsperado;

        // Tradução do nome do Gherkin para o ícone do Material Design
        switch (nomeBotao.toUpperCase()) {
            case "PDF":
                iconeEsperado = "picture_as_pdf";
                break;
            case "EXCEL":
                iconeEsperado = "summarize";
                break;
            default:
                throw new IllegalArgumentException("Tipo de exportação não mapeado: " + nomeBotao);
        }

        // XPath que busca o botão mat-fab que contém o ícone específico dentro do card de resultados
        String xpathBotao = String.format(
            "//mat-card[contains(@class, 'report-result')]//button[.//mat-icon[text()='%s']]", 
            iconeEsperado
        );

        try {
            WebElement botao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathBotao)));
            Assert.assertTrue("O botão de exportar " + nomeBotao + " não está visível!", botao.isDisplayed());
            System.out.println("Botão de exportação validado: " + nomeBotao);
        } catch (TimeoutException e) {
            Assert.fail("Erro: O botão de exportar [" + nomeBotao + "] não apareceu após o processamento do relatório.");
        }
    }

    public void validaTabelaResultados(List<String> colunasEsperadas) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By localizadorHeaders = By.cssSelector("th.mat-mdc-header-cell");

        try {
            List<WebElement> headers = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(localizadorHeaders));
            List<String> colunasNaTela = headers.stream()
                    .map(el -> (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent.trim();", el))
                    .filter(texto -> !texto.isEmpty())
                    .collect(Collectors.toList());

            System.out.println("Colunas detectadas na tela: " + colunasNaTela);

            colunasEsperadas.forEach(coluna -> 
                Assert.assertTrue("Coluna [" + coluna + "] não encontrada!", colunasNaTela.contains(coluna))
            );

        } catch (TimeoutException e) {
            Assert.fail("A tabela não carregou os cabeçalhos após 20 segundos.");
        }
    }

    @FindBy(tagName = "h1")
    private WebElement tituloPagina;

    public void validaTituloTela(String tituloEsperado) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            // Espera o elemento h1 ficar visível
            wait.until(ExpectedConditions.visibilityOf(tituloPagina));
            
            // Captura o texto atual e remove espaços extras
            String tituloAtual = tituloPagina.getText().trim();
            
            System.out.println("Validando título. Esperado: " + tituloEsperado + " | Encontrado: " + tituloAtual);
            
            Assert.assertEquals("O título da tela não corresponde ao esperado!", 
                tituloEsperado, tituloAtual);
                
        } catch (Exception e) {
            Assert.fail("Erro ao validar o título '" + tituloEsperado + "'. Detalhe: " + e.getMessage());
        }
    }

    

}
