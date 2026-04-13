package br.com.cineflix.pages;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.cineflix.support.Utils;

public class DetalhesDoTituloPages extends Utils {

    @FindBy(xpath = "//input[@placeholder='Pesquisar']")
    private WebElement campoPesquisaPermissoes;
    @FindBy(xpath = "//mat-panel-title[normalize-space()='Detalhes de Exibições']")
    private WebElement tabelaDetalhesExibicao;
    @FindBy(xpath = "//h3[normalize-space()='Direção']")
    private WebElement secaoDirecao;
    @FindBy(xpath = "//h3[normalize-space()='Elenco']")
    private WebElement secaoElenco;
    @FindBy(xpath = "//h2[text()='Contratos']")
    private WebElement modalContratos;
    @FindBy(css = ".video-dialog iframe.player")
    private WebElement playerVideo;

    public DetalhesDoTituloPages() {
    PageFactory.initElements(driver, this);

    }

    public void validarCardsNaSecao(String categoria) {
        if(categoria.equalsIgnoreCase("Direção")) {
            Assert.assertTrue("A seção de Direção não está visível!", secaoDirecao.isDisplayed());
            System.out.println("Seção validada: " + categoria);
        } else if(categoria.equalsIgnoreCase("Elenco")) {
            Assert.assertTrue("A seção de Elenco não está visível!", secaoElenco.isDisplayed());
            System.out.println("Seção validada: " + categoria);
        } else {
            Assert.fail("Categoria desconhecida: " + categoria);
        }
    }

    public void clicarNoBannerPeloTitulo(String tituloFilme) {
        String xpathBanner = String.format("//img[contains(@alt, '%s')]", tituloFilme);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement banner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathBanner)));
            wait.until(driver -> banner.getAttribute("src").contains("api/images/content"));
            new Actions(driver).moveToElement(banner).click().perform();
            
            System.out.println("Sucesso: Banner '" + tituloFilme + "' carregado e clicado.");
            
        } catch (Exception e) {
            Assert.fail("O banner do filme '" + tituloFilme + "' não carregou a tempo. Erro: " + e.getMessage());
        }
    }

    public void validarBotoesAcao(List<String> botoesEsperados) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (String nomeBotao : botoesEsperados) {
            String xpathBotao;
            if (nomeBotao.equalsIgnoreCase("Voltar")) {
                xpathBotao = "//button[.//mat-icon[text()='arrow_back']] | //button[@mattooltip='Voltar para Home']";
            } else {
                xpathBotao = String.format("//span[@class='mdc-button__label' and contains(normalize-space(), '%s')]", nomeBotao);
            }

            try {
                WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathBotao)));
                Assert.assertTrue("O botão '" + nomeBotao + "' não está visível!", btn.isDisplayed());
                System.out.println("Botão validado: " + nomeBotao);
            } catch (Exception e) {
                Assert.fail("Não foi possível encontrar o botão de ação: " + nomeBotao);
            }
        }
    }

    public void validarDetalhesTitulo(List<String> detalhesEsperados) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (String detalhe : detalhesEsperados) {
            String xpathDetalhe = String.format("//div[contains(@class, 'details')]//span[contains(normalize-space(), '%s')]", detalhe);

            try {
                WebElement elementoDetalhe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathDetalhe)));
                Assert.assertTrue("O detalhe '" + detalhe + "' não está visível!", elementoDetalhe.isDisplayed());
                System.out.println("Detalhe validado: " + detalhe);
            } catch (Exception e) {
                Assert.fail("Não foi possível encontrar o detalhe: " + detalhe + ". Verifique se o texto no BDD está idêntico ao da tela.");
            }
        }
    }

    public void validaTabelaDetalhesExibicao(){
        tabelaDetalhesExibicao.isDisplayed();
    }

    public void validaCaixaDeDialogo(){
        modalContratos.isDisplayed();
    }

    public void validarColunasTabela(String nomeTabela, List<String> colunasEsperadas) {
        String seletorTabela = nomeTabela.equalsIgnoreCase("vigencia") ? ".contratos-table" : ".direitos-table";
        List<WebElement> headersEncontrados = driver.findElements(By.cssSelector(seletorTabela + " th"));
        
        List<String> colunasCapturadas = headersEncontrados.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(texto -> !texto.isEmpty())
                .collect(Collectors.toList());

        Assert.assertEquals("As colunas da tabela " + nomeTabela + " não conferem!", colunasEsperadas, colunasCapturadas);
    }

    public void validarPresencaDetalhesTecnicos(List<String> labelsEsperados) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fields-grid")));
        List<WebElement> labelsElementos = driver.findElements(By.cssSelector(".field-card .label"));
        
        List<String> labelsNaTela = labelsElementos.stream()
                .map(el -> el.getText().replace(":", "").trim())
                .collect(Collectors.toList());

        for (String labelEsperado : labelsEsperados) {
            boolean encontrou = labelsNaTela.stream()
                    .anyMatch(l -> l.equalsIgnoreCase(labelEsperado.trim()));
            
            Assert.assertTrue("O campo '" + labelEsperado + "' não foi encontrado! Campos na tela: " + labelsNaTela, encontrou);
            String xpathValor = String.format("//span[contains(.,'%s')]/following-sibling::strong", labelEsperado);
            
            WebElement valorElemento = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathValor)));
            
            String valorTexto = valorElemento.getText().trim();
            Assert.assertFalse("O campo '" + labelEsperado + "' está vazio!", valorTexto.isEmpty());
            
            System.out.println("Sucesso: " + labelEsperado + " encontrado com valor: " + valorTexto);
        }
    }

    public void validarTrailer() {
        playerVideo.isDisplayed();
    }

    @FindBy(css = "h3.content-name")
    private WebElement tituloDoFilme;

    public void validarExistenciaTituloDoFilme(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(tituloDoFilme));
        String texto = tituloDoFilme.getText().trim();
        
        Assert.assertTrue("O título do filme não está sendo exibido!", tituloDoFilme.isDisplayed());
        Assert.assertFalse("O título do filme está vazio no HTML!", texto.isEmpty());
        
        System.out.println("Título validado com sucesso: " + texto);
    }
}
