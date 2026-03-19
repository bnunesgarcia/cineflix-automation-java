package br.com.desafiofrontend.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;
import java.time.Duration;
import java.util.NoSuchElementException;
import br.com.desafiofrontend.support.Utils;

public class LoginPages extends Utils {

    @FindBy(id = "newUser")
    private WebElement botaoTelaNewUser;
    @FindBy(id = "firstname")
    private WebElement campoFirstName;
    @FindBy(id = "lastname")
    private WebElement campoLastName;
    @FindBy(id = "userName")
    private WebElement campoUserName;
    @FindBy(id = "password")
    private WebElement campoPassword;
    @FindBy(id = "register")
    private WebElement botaoRegistrar;
    @FindBy(id = "login")
    private WebElement botaoLogin;
    @FindBy(id = "submit")
    private WebElement botaoLogout;
    @FindBy(id = "name")
    private WebElement containerMensagem;
    @FindBy(id = "userName-value")
    private WebElement containerNomeUser;

    public LoginPages() {
        PageFactory.initElements(driver, this);
    }

    public void acessaTelaNovoUsuario(){
        botaoTelaNewUser.click();
    }

    public void cadastraNovoUsuario() throws InterruptedException{
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userName = "Teste" + faker.number().digits(3);
        String password = "Teste@" + faker.number().digits(4);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Tenta ocultar o reCAPTCHA iframe se ele existir
        try {
            WebElement recaptchaFrame = driver.findElement(By.xpath("//iframe[contains(@src, 'recaptcha')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", recaptchaFrame);
        } catch (NoSuchElementException e) {
            // reCAPTCHA pode não estar presente em todos os casos
        }
        
        // Preenche os campos do formulário
        campoFirstName.sendKeys(firstName);
        campoLastName.sendKeys(lastName);
        campoUserName.sendKeys(userName);
        campoPassword.sendKeys(password);

        // Remove ads se existirem (usando seletor mais genérico)
        try {
            WebElement adIframe = driver.findElement(By.xpath("//iframe[contains(@id, 'google_ads')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", adIframe);
        } catch (NoSuchElementException e) {
            // Ad iframe pode não estar presente
            System.out.println("Google Ads iframe nao encontrado.");
        }
        
        // Registra o novo usuário via JavaScript
        WebElement registerButton = botaoRegistrar;
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);
    }

    public void realizarLogin(){
        campoUserName.isDisplayed();
        campoUserName.sendKeys("TesteBru");
        campoPassword.sendKeys("Teste@1000");
        botaoLogin.click();
        WebElement nomeUsuarioLogado = containerNomeUser;
        String elemento = nomeUsuarioLogado.getText();
        Assert.assertEquals(elemento, "TesteBru");
    }

    public void realizaLogout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(botaoLogout));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutBtn);
    }

    public void realizarLoginNegativo(String motivo_erro) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            wait.until(ExpectedConditions.visibilityOf(campoUserName));
            if (motivo_erro.equals("senha invalida")) {
                campoUserName.sendKeys("TesteBru");
                campoPassword.sendKeys("teste");
            } else if (motivo_erro.equals("usuario invalido")) {
                campoUserName.sendKeys("userInv");
                campoPassword.sendKeys("Teste@2000");
            }
        } catch (Exception e) {
            System.out.println("Campo userName nao encontrado, tentando localizacao alternativa");
        }
        botaoLogin.click();
    }

    public void validaFalhaNoLogin(String motivo_erro, String mensagem){
        if(motivo_erro.equals("senha invalida")){
            WebElement mensagemContainer = containerMensagem;
            String elemento = mensagemContainer.getText();
            Assert.assertEquals(elemento, mensagem);
        } else if(motivo_erro.equals("usuario invalido")){
            WebElement mensagemContainer = containerMensagem;
            String elemento = mensagemContainer.getText();
            Assert.assertEquals(elemento, mensagem);
        }
    }
}
