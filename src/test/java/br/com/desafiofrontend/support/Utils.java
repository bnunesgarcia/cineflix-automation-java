package br.com.desafiofrontend.support;

import br.com.desafiofrontend.runner.RunCucumberTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Utils extends RunCucumberTest {

    public void acessarAplicacao(){
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
    }

    public void esperarElementoEstarVisivel(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void esperarDadoEstarVisivel(By element, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.textToBe(element, text));
    }

}
