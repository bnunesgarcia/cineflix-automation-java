package br.com.desafiofrontend.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:report/test-report.html", "json:report/test-report.json"},
        // tags = "@teste_registros",
        glue = {"br.com.desafiofrontend.steps"}
)
public class RunCucumberTest {

    public static WebDriver driver;
    public static Properties properties = new Properties();

    @BeforeClass
    public static void setup() throws IOException {
        InputStream variables = new FileInputStream("src/test/resources/application.properties");
        properties.load(variables);
        // driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
