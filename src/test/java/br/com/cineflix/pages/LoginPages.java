package br.com.cineflix.pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import br.com.cineflix.support.Utils;

public class LoginPages extends Utils {

    @FindBy(css = "img[src*='logo-login.png']")
    private WebElement logoRecord;
    @FindBy(xpath = "//h2[text()='CINEFLIX']")
    private WebElement nomeSistema;
    @FindBy(xpath = "//span[contains(text(), 'Entrar com Login de Rede')]")
    private WebElement botaoLoginRede;
    @FindBy(xpath = "//mat-panel-title[contains(text(), 'Acesso BOT (QA)')]")
    private WebElement botaoLoginAutomacao;
    @FindBy(id = "id-token")
    private WebElement campoIdToken;
    @FindBy(id = "access-token")
    private WebElement campoAccessToken;
    @FindBy(id = "btn-bot-entrar")
    private WebElement botaoEntrarComToken;
    @FindBy(id = "user-menu")
    private WebElement usuarioLogado;
    @FindBy(css = "snack-bar-container, .toast-error, .alert-danger")
    private WebElement mensagemErro;

    public LoginPages() {
        PageFactory.initElements(driver, this);
    }

    public void acessarAplicacao(){
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
    }

    public void validaLogoRecord(){
        logoRecord.isDisplayed();
    }

    public void validaNomeSistema(){
        nomeSistema.isDisplayed();
    }

    public void validaBotaoLoginRede(){
        botaoLoginRede.isDisplayed();
    }

    public void validaBotaoLoginAutomacao(){
        botaoLoginAutomacao.isDisplayed();
    }

    public void validaCamposLoginAutomacao(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(botaoLoginAutomacao)).click();
        wait.until(ExpectedConditions.visibilityOf(campoIdToken));
        // botaoLoginAutomacao.click();
        // campoIdToken.isDisplayed();
        campoAccessToken.isDisplayed();
        botaoEntrarComToken.isDisplayed();
    }

    public void preencherTokensERealizarLogin(String id, String access){
        preencheCampoIdToken(id);
        preencheCampoAccessToken(access);
        clicarBotaoEntrarComToken();
    }

    public void preencheCampoIdToken(String idToken) {
        campoIdToken.clear();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", campoIdToken, idToken);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", campoIdToken);
    }

    public void preencheCampoAccessToken(String accessToken) {
        campoAccessToken.clear();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", campoAccessToken, accessToken);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", campoAccessToken);
    }

    public void clicarBotaoEntrarComToken() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(botaoEntrarComToken)).click();
    }

    public void validarLoginComSucesso() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(usuarioLogado));
        usuarioLogado.isDisplayed();
    }

    // public void realizaLogout(){
    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //     WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(usuarioLogado));
    //     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutBtn);
    //     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutBtn);
    // }
}
