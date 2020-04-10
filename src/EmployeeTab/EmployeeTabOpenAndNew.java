package EmployeeTab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class EmployeeTabOpenAndNew {
	public static void switchToEmployeeTab(WebDriver dr, Actions a) throws InterruptedException{
		Thread.sleep(2500);
		WebElement employeeButton = dr.findElement(By.xpath("//header[@class='Header-container ']//nav[1]//ul[1]//a[3]"));
		a.moveToElement(employeeButton).click().build().perform();
	}
	public static void newEmployeeMenu(WebDriver dr, Actions a) throws InterruptedException{
		switchToEmployeeTab(dr, a);
		dr.findElement(By.xpath("//button[@class='ui secondary button']")).click();
	}
}
