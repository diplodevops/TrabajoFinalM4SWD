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
        driver.get("http://238e-186-79-186-176.ngrok.io/");
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
        prueba1_sueldo.sendKeys("600000");

        WebElement prueba1_ahorro = driver.findElement(By.xpath("//input[contains(@id,'a')]"));
        prueba1_ahorro.sendKeys("980000");

        WebElement prueba1_boton = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
        JavascriptExecutor prueba1_botonexecutor = (JavascriptExecutor)driver;
        prueba1_botonexecutor.executeScript("arguments[0].click();", prueba1_boton);

        System.out.println("Esperando resultado");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Esperando resultado OK");

        System.out.println("Prueba 1 // Sueldo: $600.000.- Ahorro: $980.000.-");

        //WebElement linkElement11 = driver.findElement(By.xpath("//h6[contains(@id,'resultado_impuesto')]"));
        WebElement linkElement11 = driver.findElement(By.id("resultado_impuesto"));
        if(linkElement11.getText().equals("$ 0"))
        {
            System.out.println("Prueba 1 Impuesto $ 0 // OK");
        }
        else
        {
            System.out.println("Prueba 1 Impuesto // NOT OK");
            System.out.println(linkElement11.getText());
        }

        //WebElement linkElement12 = driver.findElement(By.xpath("//h6[contains(@id,'resultado_impuesto')]"));
        WebElement linkElement12 = driver.findElement(By.id("resultado_saldo"));
        if(linkElement12.getText().equals("$ 0"))
        {
            System.out.println("Prueba 1 Saldo $ 0 // OK");
        }
        else
        {
            System.out.println("Prueba 1 Saldo // NOT OK");
            System.out.println(linkElement12.getText());
        }

        //WebElement linkElement13 = driver.findElement(By.xpath("//h6[contains(@id,'resultado_diez')]"));
        WebElement linkElement13 = driver.findElement(By.id("resultado_diez"));
        if(linkElement13.getText().equals("$ 980000"))
        {
            System.out.println("Prueba 1 10% $ 980.000 // OK");
        }
        else
        {
            System.out.println("Prueba 1 10%// NOT OK");
            System.out.println(linkElement13.getText());
        }



        //Prueba 2

        WebElement prueba2_sueldo = driver.findElement(By.xpath("//input[contains(@id,'s')]"));
        prueba2_sueldo.clear();
        prueba2_sueldo.sendKeys("1500000");

        WebElement prueba2_ahorro = driver.findElement(By.xpath("//input[contains(@id,'a')]"));
        prueba2_ahorro.clear();
        prueba2_ahorro.sendKeys("8000000");

        WebElement prueba2_boton = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
        JavascriptExecutor prueba2_botonexecutor = (JavascriptExecutor)driver;
        prueba2_botonexecutor.executeScript("arguments[0].click();", prueba2_boton);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Prueba 2 // Sueldo: $1.500.000.- Ahorro: $8.000.000.-");

        WebElement linkElement21 = driver.findElement(By.id("resultado_impuesto"));
        if(linkElement21.getText().equals("$ 80000"))
        {
            System.out.println("Prueba 2 Impuesto $ 80000 // OK");
        }
        else
        {
            System.out.println("Prueba 2 Impuesto // NOT OK");
            System.out.println(linkElement21.getText());
        }

        WebElement linkElement22 = driver.findElement(By.id("resultado_saldo"));
        if(linkElement22.getText().equals("$ 7000000"))
        {
            System.out.println("Prueba 2 Saldo// OK");
        }
        else
        {
            System.out.println("Prueba 2 B// NOT OK");
            System.out.println(linkElement22.getText());
        }

        WebElement linkElement23 = driver.findElement(By.id("resultado_diez"));
        if(linkElement23.getText().equals("$ 1000000"))
        {
            System.out.println("Prueba 2 10% $ 1000000// OK");
        }
        else
        {
            System.out.println("Prueba 2 10%// NOT OK");
            System.out.println(linkElement23.getText());
        }



        //Prueba 3

        WebElement prueba3_sueldo = driver.findElement(By.xpath("//input[contains(@id,'s')]"));
        prueba3_sueldo.clear();
        prueba3_sueldo.sendKeys("6000000");

        WebElement prueba3_ahorro = driver.findElement(By.xpath("//input[contains(@id,'a')]"));
        prueba3_ahorro.clear();
        prueba3_ahorro.sendKeys("60000000");

        WebElement prueba3_boton = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
        JavascriptExecutor prueba3_botonexecutor = (JavascriptExecutor)driver;
        prueba3_botonexecutor.executeScript("arguments[0].click();", prueba3_boton);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Prueba 3 // Sueldo: $6.000.000.- Ahorro: $60.000.000.-");

        WebElement linkElement31 = driver.findElement(By.id("resultado_impuesto"));
        if(linkElement31.getText().equals("$ 1665195"))
        {
            System.out.println("Prueba 3 Impuesto $ 1665195 // OK");
        }
        else
        {
            System.out.println("Prueba 3 Impuesto // NOT OK");
            System.out.println(linkElement31.getText());
        }

        WebElement linkElement32 = driver.findElement(By.id("resultado_saldo"));
        if(linkElement32.getText().equals("$ 55242300"))
        {
            System.out.println("Prueba 3 Saldo $ 55242300// OK");
        }
        else
        {
            System.out.println("Prueba 3 B// NOT OK");
            System.out.println(linkElement32.getText());
        }

        WebElement linkElement33 = driver.findElement(By.id("resultado_diez"));
        if(linkElement33.getText().equals("$ 4757700"))
        {
            System.out.println("Prueba 3 10% $ 4757700 // OK");
        }
        else
        {
            System.out.println("Prueba 3 10%// NOT OK");
            System.out.println(linkElement33.getText());
        }
    }
}
