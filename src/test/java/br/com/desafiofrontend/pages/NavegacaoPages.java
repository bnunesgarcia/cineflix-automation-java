package br.com.desafiofrontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.com.desafiofrontend.support.Utils;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

public class NavegacaoPages extends Utils {

    public NavegacaoPages() {
    PageFactory.initElements(driver, this);
    }

    List<String> getSubmenus(String menu) {
        switch (menu.toLowerCase()) {
            case "elements":
                return Arrays.asList("Text Box", "Check Box", "Radio Button", "Web Tables", "Buttons", "Links", "Broken Links - Images", "Upload and Download", "Dynamic Properties");
            case "forms":
                return Arrays.asList("Practice Form");
            case "alerts, frame & windows":
                return Arrays.asList("Browser Windows", "Alerts", "Frames", "Nested Frames", "Modal Dialogs");
            case "widgets":
                return Arrays.asList("Accordian", "Auto Complete", "Date Picker", "Slider", "Progress Bar", "Tabs", "Tool Tips", "Menu", "Select Menu");
            case "interactions":
                return Arrays.asList("Sortable", "Selectable", "Resizable", "Droppable", "Dragabble");
            case "book store application":
                return Arrays.asList("Login", "Book Store", "Profile", "Book Store API");
            default:
                return Arrays.asList();
        }
    }

    String[][] REGISTROS = {
        {"Vega", "Cierra", "Vega", "39", "cierra@example.com", "10000", "Insurance"},
        {"Cantrell", "Alden", "Cantrell", "45", "alden@example.com", "12000", "Compliance"},
        {"Gentry", "Kierra", "Gentry", "29", "kierra@example.com", "2000", "Legal"}
    };

    @FindBy(css = "a[href='/forms']")
    private WebElement menuForms;
    @FindBy(css = "a[href='/alertsWindows']")
    private WebElement menuAlerts;
    @FindBy(css = "a[href='/elements']")
    private WebElement menuElements;
    @FindBy(css = "a[href='/interaction']")
    private WebElement menuInteractions;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div/a[6]")    
    private WebElement menuBookStoreApplication;
    @FindBy(css = "a[href='/widgets']")
    private WebElement menuWidgets;
    @FindBy(xpath = "//span[text()='Web Tables']")
    private WebElement subMenuWebElements;
    @FindBy(xpath = "//span[text()='Sortable']")
    private WebElement subMenuSortable;
    @FindBy(xpath = "//div[2]/div/div/div/div[1]/div/div/div[2]/div/ul")
    private WebElement subMenuPracticeFroms;
    @FindBy(css = ".show > ul:nth-child(1) > li:nth-child(1) > span:nth-child(2)")
    private WebElement subMenuBrowserWindows;
    @FindBy(css = ".show > ul:nth-child(1) > li:nth-child(1)")
    private WebElement subMenuLogin;
    @FindBy(id = "searchBox")
    private WebElement campoPesquisa;
    @FindBy(id = "addNewRecordButton")
    private WebElement botaoAddNovoRegistro;
    @FindBy(id = "edit-record-1")
    private WebElement botaoEditaRegistro;
    @FindBy(id = "delete-record-1")
    private WebElement botaoDeletaRegistro;
    @FindBy(id = "firstName")
    private WebElement campoNome;
    @FindBy(id = "lastName")
    private WebElement campoSobrenome;
    @FindBy(id = "userEmail")
    private WebElement campoEmail;
    @FindBy(id = "age")
    private WebElement campoIdade;
    @FindBy(id = "salary")
    private WebElement campoSalario;
    @FindBy(id = "department")
    private WebElement campoDepto;
    @FindBy(id = "submit")
    private WebElement botaoSubmitFormulario;

    public void validaExibicaoMenu(String menu) {
        if (menu.equals("forms")) {
            menuForms.isDisplayed();
        } else if (menu.equals("alerts, frame & windows")) {
            menuAlerts.isDisplayed();
        } else if (menu.equals("elements")) {
            menuElements.isDisplayed();
        } else if (menu.equals("interactions")) {
            menuInteractions.isDisplayed();
        } else if (menu.equals("widgets")) {
            menuWidgets.isDisplayed();
        } else if (menu.equals("book store application")) {
            menuBookStoreApplication.isDisplayed();
        }
    }

    public void acessaMenu(String menu) {
        WebElement elementoMenu = null;
        
        if (menu.equals("forms")) {
            elementoMenu = menuForms;
        } else if (menu.equals("alerts, frame & windows")) {
            elementoMenu = menuAlerts;
        } else if (menu.equals("elements")) {
            elementoMenu = menuElements;
        } else if (menu.equals("interactions")) {
            elementoMenu = menuInteractions;
        } else if (menu.equals("widgets")) {
            elementoMenu = menuWidgets;
        } else if (menu.equals("book store application")) {
            elementoMenu = menuBookStoreApplication;
        }
        
        if (elementoMenu != null) {
            // Espera o elemento estar clicável
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(elementoMenu));
            
            // Faz scroll até o elemento
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementoMenu);
            
            // Clica usando JavaScript para evitar interceptação
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementoMenu);
        }
    }

    public void validaSubMenu(String menu) {
        List<String> submenus = getSubmenus(menu);
        for (String submenu : submenus) {
            String submenuXpath = "//span[@class='text' and text()='" + submenu + "']";
            if (driver.findElement(By.xpath(submenuXpath)).isDisplayed()) {
                System.out.println("Submenu " + submenu + " OK!");
            }
        }
    }

    public void acessaSubMenu(String submenu) {
        // Para submenus específicos que exigem tratativa especial
        // Tenta com a busca case-insensitive (normalize-space para remover espaços extras)
        String xpathDinamico = "//span[@class='text' and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" 
                              + submenu.toLowerCase() + "')]";
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathDinamico)));
            
            // Scroll até o elemento
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            
            // Small delay para garantir que o elemento está pronto
            Thread.sleep(500);
            
            // Clica usando JavaScript para evitar obstruções
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Erro ao acessar submenu: " + submenu + " - " + e.getMessage());
            throw new RuntimeException("Submenu '" + submenu + "' não encontrado ou não clicável");
        }
    }

    public void acessarOperacao(String operacao) {
        WebElement botao = null;
        if (operacao.equals("adicionar")) {
            botao = botaoAddNovoRegistro;
        } else if (operacao.equals("editar")) {
            botao = botaoEditaRegistro;
        } else if (operacao.equals("deletar")) {
            botao = botaoDeletaRegistro;
        }

        if (botao != null) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement el = wait.until(ExpectedConditions.visibilityOf(botao));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            // Clica via JS para evitar interceptações de elementos sobrepostos
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            try {
                Thread.sleep(300);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void realizarOperacao(String acao) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        if (acao.equals("preencho")) {
            wait.until(ExpectedConditions.visibilityOf(campoNome));
            campoNome.sendKeys("Bruno");
            campoSobrenome.sendKeys("Teste");
            campoEmail.sendKeys("teste@teste.com.br");
            campoIdade.sendKeys("36");
            campoSalario.sendKeys("5000");
            campoDepto.sendKeys("TI");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botaoSubmitFormulario);
        } else if (acao.equals("altero")) {
            wait.until(ExpectedConditions.visibilityOf(campoNome));
            campoNome.clear();
            campoNome.sendKeys("Alteracao");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botaoSubmitFormulario);
        } else if (acao.equals("excluo")) {
            int safetyCount = 0;
            while (true) {
                List<WebElement> deleteButtons = driver.findElements(By.cssSelector("[id^='delete-record-']"));
                if (deleteButtons.isEmpty() || safetyCount++ > 20) {
                    break;
                }
                WebElement btn = deleteButtons.get(0);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    public void validaAcao(String tipoOper) {
        switch (tipoOper.toLowerCase()) {
            case "criacao":
                validarElemento(".rt-tr-group:nth-of-type(4) .rt-td:first-of-type", "Bruno");
                break;
            case "alteracao":
                validarElemento(".rt-td[role=\"gridcell\"]:nth-of-type(1)", "Alteracao");
                break;
            case "exclusao":
                pesquisaNegativaDeRegistros();
                break;
            default:
                throw new IllegalArgumentException("Tipo de operação não suportado: " + tipoOper);
        }
    }

    public void pesquisaRegistro(String registro){
        campoPesquisa.clear();
        campoPesquisa.sendKeys(registro);
    }

    public void validaPesquisa(String registro){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.rt-tbody div.rt-tr-group")),
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'No rows found')]")
        )));

        for (String[] valores : REGISTROS) {
            if (registro.equals(valores[0])) {
                validarElementos(valores);
                return;
            }
        }
        Assert.fail("Registro esperado nao encontrado na lista de dados: " + registro);
    }       

    public void pesquisaNegativaDeRegistros(){
        try {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(), 'No rows found')]") );
            String elementoNegativo = element.getText();
            Assert.assertTrue(elementoNegativo.contains("No rows found"));
        } catch (Exception e) {
            Assert.fail("Nao foi exibida a mensagem de nenhum registro encontrado");
        }
    }

    private void validarElementos(String[] expectedValues) {
        List<WebElement> rows = driver.findElements(By.cssSelector("div.rt-tbody div.rt-tr-group"));
        if (rows.isEmpty()) {
            Assert.fail("Nenhum registro encontrado na tabela para validação");
            return;
        }

        WebElement matchingRow = null;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("div.rt-td"));
            if (!cells.isEmpty() && cells.get(0).getText().equals(expectedValues[0])) {
                matchingRow = row;
                break;
            }
        }

        if (matchingRow == null) {
            Assert.fail("Registro não encontrado na tabela: " + expectedValues[0]);
            return;
        }

        List<WebElement> cells = matchingRow.findElements(By.cssSelector("div.rt-td"));
        for (int i = 1; i < expectedValues.length && i < cells.size(); i++) {
            String actualText = cells.get(i).getText();
            if (!actualText.equals(expectedValues[i])) {
                Assert.fail(String.format("Valor incorreto na coluna %d: esperado='%s' atual='%s'", i, expectedValues[i], actualText));
            }
        }
    }

    private void validarElemento(String cssSelector, String valorEsperado) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
            String elemento = element.getText();
            Assert.assertEquals(elemento, valorEsperado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
