package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Test;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    private WebDriver driver;

    @Before
    public void setUp(){
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://bb1c-200-9-100-75.ngrok.io");
        driver.manage().window().maximize();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println("Iniciando Pruebas...");

        //Prueba 1

        WebElement prueba1_sueldo = driver.findElement(By.xpath("//input[contains(@id,'s')]"));
        prueba1_sueldo.sendKeys("1000000");

        WebElement prueba1_ahorro = driver.findElement(By.xpath("//input[contains(@id,'a')]"));
        prueba1_ahorro.sendKeys("80000000");

        WebElement prueba1_boton = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
        JavascriptExecutor prueba1_botonexecutor = (JavascriptExecutor)driver;
        prueba1_botonexecutor.executeScript("arguments[0].click();", prueba1_boton);

        try {
            Thread.sleep(260000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Prueba 1 // Sueldo: $1.000.000.- Ahorro: $8.000.000.-");
        System.out.println("Prueba 1 // OK");
        System.out.println("Prueba 1 // NOT OK");
    }
}
