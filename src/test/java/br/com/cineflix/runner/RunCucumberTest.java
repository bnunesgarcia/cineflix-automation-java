package br.com.cineflix.runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:report/test-report.html", "json:report/test-report.json"},
        tags = "@automacao",
        glue = {"br.com.cineflix.steps"}
)
public class RunCucumberTest {

    public static WebDriver driver;
    public static Properties properties = new Properties();

    @BeforeClass
    public static void setup() throws IOException {
        InputStream variables = new FileInputStream("src/test/resources/application.properties");
        properties.load(variables);

        ChromeOptions options = new ChromeOptions();
    
        // Adiciona o argumento para rodar Headless
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu"); // Recomendado para evitar bugs de renderização

        // driver = new FirefoxDriver();
        driver = new ChromeDriver(options);
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
