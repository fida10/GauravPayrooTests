import BasicFunctions.Login;
import EmployeeTab.EmployeeTabOpenAndNew;
import EmployeeTab.NewEmployeePersonalDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException{

		System.setProperty("webdriver.chrome.driver", "//Users//fida10//selenium//drivers//chrome//chromedriver");
		WebDriver dr = new ChromeDriver();
		dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		dr.manage().window().maximize();

		Login.login(dr);
		Actions a = new Actions(dr);
		EmployeeTabOpenAndNew.newEmployeeMenu(dr, a);
		NewEmployeePersonalDetails emplOne = new NewEmployeePersonalDetails();
		emplOne.newEmployeePersonalDetails(dr, "Mr", "Fida", "Eshad", a, "Male", "March", "1936", "22");
	}
}
//issue: It is clicking on a date one day before the actual desired date. This is not the code; I tried manually testing it and when I click, say, June 14th, the actual date displayed is June 13th