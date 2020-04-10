package BasicFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Scanner;

public class Login {
	public static void login(WebDriver dr){
		dr.get("https://staging.payroo.com.au/login");

		Scanner sc = new Scanner(System.in);
		WebElement inputBoxEmail = dr.findElement(By.xpath("//form[@class='ui form login-form']//div[2]//div[1]//div[1]//input[1]"));
		WebElement inputBoxPassword = dr.findElement(By.xpath("//form[@class='ui form login-form']//div[2]//div[4]//div[1]//input[1]"));
		WebElement submitButton = dr.findElement(By.xpath("//button[@type='submit']"));
		System.out.println("Enter user ID: ");
		String userID = sc.nextLine();
		System.out.println("Enter password");
		String password = sc.nextLine();

		Actions a = new Actions(dr);
		a.moveToElement(inputBoxEmail).click().sendKeys(userID).moveToElement(inputBoxPassword).click().sendKeys(password).moveToElement(submitButton).click().build().perform();
	}
}
