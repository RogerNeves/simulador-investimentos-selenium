package SimuladorInvestimentos;

import SimuladorInvestimentos.Enums.TipoTempo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SimuladorInvestimentos {
    private WebDriver driver;

    public SimuladorInvestimentos(WebDriver driver) {
        this.driver = driver;
    }

    public String realizarSimulação(String aplicacaoInicial, String poupancaMensal, String qualtidadeDeTempo, TipoTempo tipoTempo) throws InterruptedException {

        WebElement aplicacaoInicialInput = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.id("valorAplicar")));
        aplicacaoInicialInput.click();
        aplicacaoInicialInput.sendKeys(aplicacaoInicial, Keys.TAB);


        if(Integer.parseInt(aplicacaoInicial) < 2000) {
            WebElement valorAplicarError = driver.findElement(By.id("valorAplicar-error"));
            return valorAplicarError.getText();
        }

        WebElement poupancaMensalInput = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.id("valorInvestir")));
        poupancaMensalInput.click();
        poupancaMensalInput.sendKeys(poupancaMensal, Keys.TAB);

        if(Integer.parseInt(poupancaMensal) < 2000) {
            WebElement valorInvestirError = driver.findElement(By.id("valorInvestir-error"));
            return valorInvestirError.getText();
        }

        WebElement qualtidadeDeTempoInput = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.id("tempo")));
        qualtidadeDeTempoInput.sendKeys(qualtidadeDeTempo);

        WebElement selectButton = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.className("btSelect")));

        List<WebElement> selectedButton = driver.findElements(By.cssSelector(".listaSelect a"));

        if(tipoTempo.equals(TipoTempo.ANOS)){
            selectButton.click();
            Thread.sleep(1000);
            selectedButton.get(1).click();
        }

        WebElement simularButton = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.className("btnSimular")));
        simularButton.click();

        WebElement successText = new WebDriverWait(driver, 5)
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".blocoResultadoSimulacao span.texto")));
        return successText.getText();
    }
}
