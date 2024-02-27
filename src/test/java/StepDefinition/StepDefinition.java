package StepDefinition;


import io.cucumber.java.es.*;
import io.cucumber.java.lv.Ja;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class StepDefinition {

    WebDriver driver;


    @Dado("que la web demo QA esta disponible")
    public void que_la_web_demo_qa_esta_disponible() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/text-box");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @Y("doy click en aceptar")
    public void doyClickEnAceptar() {
       esperar(2);
        driver.findElement(By.xpath("//button[@aria-label='Consent']")).click();

    }

    @Y("registro mis datos")
    public void registroMisDatos() {
      esperar(2);
        driver.findElement(By.id("userName")).sendKeys("Edwin Lopez");
        driver.findElement(By.id("userEmail")).sendKeys("edwin21@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("mi direccion de prueba");
        driver.findElement(By.id("permanentAddress")).sendKeys("o tal vez sea estaaaaaaaaaaaaaaaaa");
        // Crea una instancia de JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Haz scroll hacia abajo en la página
        js.executeScript("window.scrollBy(0, 500);");
      esperar(2);
    }

    @Cuando("doy click en registrar")
    public void doyClickEnRegistrar() {
        //driver.findElement(By.id("submit")).click();
        //driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        JavascriptExecutor executor=(JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",driver.findElement(By.id("submit")));
    }
    @Entonces("se muestra mis datos en la pantalla")
    public void se_muestra_mis_datos_en_la_pantalla() {

        esperar(2);

        System.out.println(driver.findElement(By.id("name")).getText());
        System.out.println(driver.findElement(By.id("email")).getText());
        driver.findElement(By.id("currentAddress")).isDisplayed();
        driver.findElement(By.id("permanentAddress")).isDisplayed();

        esperar(2);
        //Limpiar Cookies
        driver.manage().deleteAllCookies();
        driver.close();

    }


    //Metodos adicionales
    public void esperar(int segundos) {
        try {
            // Convierte los segundos a milisegundos
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
