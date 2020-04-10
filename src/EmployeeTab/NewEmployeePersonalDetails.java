package EmployeeTab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class NewEmployeePersonalDetails {
	public void newEmployeePersonalDetails(WebDriver dr, String titleNoPeriod, String firstName, String lastName, Actions a, String genderFirstLetterCapital, String monthFirstLetterCapital, String yearOfBirth, String numericDateOfBirth){
		titleSelect(dr, titleNoPeriod);
		firstAndLastName(dr, firstName, lastName, a);
		genderBox(dr, genderFirstLetterCapital);
		dateOfBirthOpenCal(dr, a);
		dateOfBirthMonth(dr, monthFirstLetterCapital);
		dateOfBirthYear(dr, a, yearOfBirth);
		dateofBirthDate(dr, numericDateOfBirth, monthFirstLetterCapital);
	}
	private static void titleSelect(WebDriver dr, String titleNoPeriod){
		Select titleDropDown = new Select(dr.findElement(By.xpath("//form[@id='employeeForm']//div[2]//div[1]//select[1]")));
		titleDropDown.selectByVisibleText(titleNoPeriod);
	}
	private static void firstAndLastName(WebDriver dr, String firstName, String lastName, Actions a){
		WebElement firstNameBox = dr.findElement(By.xpath("//form[@id='employeeForm']//div[2]//div[2]//div[1]//input[1]"));
		WebElement lastNameBox = dr.findElement(By.xpath("//form[@id='employeeForm']//div[2]//div[3]//div[1]//input[1]"));
		a.moveToElement(firstNameBox).click().sendKeys(firstName).build().perform();
		a.moveToElement(lastNameBox).click().sendKeys(lastName).build().perform();
	}
	private static void genderBox(WebDriver dr, String genderFirstLetterCapital){
		WebElement genderDropDownBox = dr.findElement(By.xpath("//select[@placeholder='Select gender...']"));
		Select genderDropDown = new Select(genderDropDownBox);
		genderDropDown.selectByVisibleText(genderFirstLetterCapital);
	}
	private static void dateOfBirthOpenCal(WebDriver dr, Actions a){
		WebElement calendarBox = dr.findElement(By.xpath("//form[@id='employeeForm']//div[3]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]"));
		a.moveToElement(calendarBox).click().build().perform();
	}
	private static void dateOfBirthMonth(WebDriver dr, String monthFirstLetterCapital){
		String monthAllCaps = monthGetter(monthFirstLetterCapital).toUpperCase();
		WebElement arrowKeyMonth = dr.findElement(By.xpath("//button[@aria-label='Previous Month']"));
		while(true){
			arrowKeyMonth.click();
			String currentMonth = dr.findElement(By.xpath("//div[@class='react-datepicker__current-month react-datepicker__current-month--hasYearDropdown']")).getText();
			System.out.println(currentMonth);
			if (currentMonth.contains(monthAllCaps)){
				System.out.println("Reached desired month: " + currentMonth);
				break;
			}
		}
	}
	private static void dateOfBirthYear(WebDriver dr, Actions a, String yearOfBirth){
		a.moveToElement(dr.findElement(By.xpath("//span[@class='react-datepicker__year-read-view--selected-year']"))).click().build().perform(); //clicks on the year to open the dynamic dropdown containing the years
		WebElement yearArrowKeyDown = dr.findElement(By.xpath("//a[@class='react-datepicker__navigation react-datepicker__navigation--years react-datepicker__navigation--years-previous']"));

		String firstYear = dr.findElement(By.xpath("//div[@class='react-datepicker__year-dropdown']//div[1]")).getText();
		System.out.println(firstYear);
		int yearOfBirthInInt = Integer.parseInt(yearOfBirth);

		if(yearOfBirth.equals(firstYear)){ //if the birth year is for some reason the current year on top, 2020
			System.out.println("Current selected year is: " + firstYear);
			dr.findElement(By.xpath("//div[@class='react-datepicker__year-dropdown']//div[1]")).click();
		}
		while (yearOfBirth.equals("1899")){ //if the birth year is 1899.
			yearArrowKeyDown.click();
			String year = dr.findElement(By.xpath("//div[@class='react-datepicker__year-dropdown']//div[7]")).getText();
			System.out.println(year);
			if(year.contains(yearOfBirth)) {
				dr.findElement(By.xpath("//div[@class='react-datepicker__year-dropdown']//div[7]")).click();

				System.out.println("Current selected year is: " + year);
				break;
			}
		}
		while (yearOfBirthInInt < 2020 && yearOfBirthInInt > 1904) { //for years between 2019 and 1905
			yearArrowKeyDown.click();
			String year = dr.findElement(By.xpath("//div[@class='react-datepicker__year-dropdown']//div[2]")).getText();
			if(year.contains(yearOfBirth)) {
				dr.findElement(By.xpath("//div[@class='react-datepicker__year-dropdown']//div[2]")).click();
				System.out.println("Current selected year is: " + year);
				break;
			}
			/*if(yearOfBirthInInt < 1905 && yearOfBirthInInt > 1898){
				//search for the year, click the element that matches.
				String yearOfBirthBackToString = Integer.toString(yearOfBirthInInt);
				dr.findElement(By.xpath(String.format("//div[text()='%s']", yearOfBirthBackToString))).click();
				System.out.println("Current selected year is: " + yearOfBirthBackToString);
				break;
			} else {
				System.out.println("Invalid year entry");
				//An attempt to cover the years between 1905 and 1898
			}*/
		}
		while (yearOfBirthInInt < 1905 && yearOfBirthInInt > 1899){ //for years between 1904 and 1900
			yearArrowKeyDown.click();
			String year = dr.findElement(By.xpath("//div[@class='react-datepicker__year-dropdown']//div[6]")).getText();
			if(year.contains(yearOfBirth)) {
				dr.findElement(By.xpath("//div[@class='react-datepicker__year-dropdown']//div[6]")).click(); //note that for some reason the displayed years changed from 8 to 7 elements, resulting in the div[7] going to div[6]. Adjust accordingly if it changes in size again.
				System.out.println("Current selected year is: " + year);
				break;
			}
		}
	}
	private static void dateofBirthDate(WebDriver dr, String numericDateOfBirth, String monthFirstLetterCapital){
		String monthFirstLetterCapitalGot = monthGetter(monthFirstLetterCapital);
		String dateAndMonth = monthFirstLetterCapitalGot + " " + numericDateOfBirth;
		WebElement theNumericDate = dr.findElement(By.xpath(String.format("//div[contains(@aria-label, '%s')]", dateAndMonth)));
		theNumericDate.click();
	}
	private static String monthGetter(String monthFirstLetterCapital){
		return monthFirstLetterCapital;
	}
}
