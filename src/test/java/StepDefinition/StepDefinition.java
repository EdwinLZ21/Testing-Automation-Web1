package StepDefinition;

import io.cucumber.java.es.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class StepDefinition {

    WebDriver driver;


    @Dado("que la web demo QA esta disponible")
    public void que_la_web_demo_qa_esta_disponible() {
        // Establecemos la propiedad del sistema para indicar la ubicación del controlador de Chrome. Esto es necesario para ejecutar pruebas en Chrome.
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        //Creamos una instancia
        driver = new ChromeDriver();
        //Maximizamos el navegador
        driver.manage().window().maximize();
        //Navegeamos a la url proporcionada
        driver.navigate().to("https://demoqa.com/text-box");
        //Selenium esperará hasta 20 segundos para encontrar un elemento antes de arrojar un error de "ElementNotVisibleException" o "NoSuchElementException
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
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("submit")));
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



    public void metodosdeprueba(){
        WebElement element = driver.findElement(By.id("id-element"));
        element.click(); // Hacer click sobre el elemento
        String text = element.getText(); // Devuelve el elemento contenido dentro del elemento
        element.clear(); // Limpiar el contenido de un input. Si el contenido no es un input no afectará en nada
        element.sendKeys("Escribe un valor para el input"); // Completa un input con el contenido pasado por parámetro
        boolean isDisplayed = element.isDisplayed(); // El elemento es visible en el DOM
        boolean isEnabled = element.isEnabled(); // El elemento está habilitado
        element.submit(); // Si el elemento es un formulario lo enviará
        String value = element.getAttribute("value"); // Obtener el valor del atributo pasado por parametro
    }

}
