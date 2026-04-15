package br.com.cineflix.steps;

import br.com.cineflix.support.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    public void inicializa(Scenario scenario) {
        System.out.println("Iniciando cenário: " + scenario.getName());
        DriverFactory.getDriver(); // Garante que o driver está pronto
    }

    @After
    public void finaliza(Scenario scenario) {
        // Tira print se o teste falhar (MUITO útil para debugar)
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Erro - " + scenario.getName());
        }
        
        DriverFactory.quitDriver(); // Fecha o navegador ao fim de CADA cenário
    }
}
