package SimuladorInvestimentosTest;

import SimuladorInvestimentos.Driver;
import SimuladorInvestimentos.Enums.TipoTempo;
import SimuladorInvestimentos.SimuladorInvestimentos;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SimuladorInvestimentosTest {
    private WebDriver driver;
    private SimuladorInvestimentos simuladorInvestimentos;

    @Before
    public void beforeTest() {
        driver = Driver.iniciarWebDriver();
        driver.get("https://www.sicredi.com.br/html/ferramenta/simulador-investimento-poupanca/");
        simuladorInvestimentos = new SimuladorInvestimentos(driver);
    }

    @After
    public void afterTest() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void realizarSimilacaoCorretamenteMeses() throws InterruptedException {
        Integer quantidadeTempo = 5;
        TipoTempo tipoTempo = TipoTempo.MESES;
        String resultado = simuladorInvestimentos.realizarSimulação("2000", "2500",quantidadeTempo.toString(),tipoTempo);
        Integer quantidadeTempoResult = tipoTempo.equals(TipoTempo.ANOS)?quantidadeTempo*12:quantidadeTempo;
        Assert.assertEquals("Em "+ quantidadeTempoResult.toString() +" meses você terá guardado", resultado);
    }

    @Test
    public void realizarSimilacaoCorretamenteAnos() throws InterruptedException {
        Integer quantidadeTempo = 5;
        TipoTempo tipoTempo = TipoTempo.ANOS;
        String resultado = simuladorInvestimentos.realizarSimulação("2000", "2500",quantidadeTempo.toString(),tipoTempo);
        Integer quantidadeTempoResult = tipoTempo.equals(TipoTempo.ANOS)?quantidadeTempo*12:quantidadeTempo;
        Assert.assertEquals("Em "+ quantidadeTempoResult.toString() +" meses você terá guardado", resultado);
    }

    @Test
    public void realizarSimilacaoAplicacaoInvalida() throws InterruptedException {
        Integer quantidadeTempo = 5;
        TipoTempo tipoTempo = TipoTempo.MESES;
        String resultado = simuladorInvestimentos.realizarSimulação("200", "2500",quantidadeTempo.toString(),tipoTempo);
        Integer quantidadeTempoResult = tipoTempo.equals(TipoTempo.ANOS)?quantidadeTempo*12:quantidadeTempo;
        Assert.assertEquals("Valor mínimo de 20.00", resultado);
    }

    @Test
    public void realizarSimilacaoPupancaInvalida() throws InterruptedException {
        Integer quantidadeTempo = 5;
        TipoTempo tipoTempo = TipoTempo.MESES;
        String resultado = simuladorInvestimentos.realizarSimulação("2000", "200",quantidadeTempo.toString(),tipoTempo);
        Integer quantidadeTempoResult = tipoTempo.equals(TipoTempo.ANOS)?quantidadeTempo*12:quantidadeTempo;
        Assert.assertEquals("Valor mínimo de 20.00", resultado);
    }
}
