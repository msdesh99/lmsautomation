package com.lms.ui.pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lms.ui.utilities.FilloExcel;

public class UserPage {
	private WebDriver driver;
	private By userModule = By.id("user");
	private By addUserButton = By.xpath("//span[contains(text(),'Add New User')]");
	private By assignStudent = By.xpath("//span[contains(text(),'Assign Student')]");
	private By addUserPopupfields = By.xpath("//label[contains(@class,'inserted')]//span");
	private By textBoxes = By.xpath("//input[contains(@class,'mat-input')]");
	private By popUpButton =  By.xpath("//*[contains(@class,'mat-card')]//span[contains(@class,'button-wrapper')]");
	private By popupDropdown = By.xpath("//label[contains(text(),'User')]");
	private By emailField = By.xpath("//input[@placeholder='Email address']");
	
	private By allDropDown = By.xpath("//div//label[contains(text(),'User')]");
    private By errorMessage = By.xpath("//div[contains(@class,'transitionMessages')]//mat-error");
	private By closeIcon = By.xpath("//div[contains(@class,'header-icons')]//span");
	private List<String> popupTextBox ;
	List<Map<String,String>> dataMapList;
	Map<String,String> dataMap = new HashMap<String,String>();
	Map<String,String> dataMapMulti;


	public UserPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login(String userName, String password) throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login")).click();
	}
    public void navigate() throws InterruptedException {
    	driver.findElement(userModule).click();
    }
    public void locateButton(String button) throws InterruptedException {
    	//userWindowHandleId = driver.getWindowHandle();
    	if(button.contentEquals("Add New User"))
    		driver.findElement(addUserButton).click();
    	if(button.contentEquals("Assign Student"))
    		driver.findElement(assignStudent).click();
    	//System.out.println("Window count: "+ driver.getWindowHandles().size());

    	//userWindowHandleId = driver.getWindowHandle();
    	//System.out.println("userWindow: "+ userWindowHandleId);

    }
    public String validate() {
    	return driver.findElement(By.className("box")).getText();
    }
    public List<String> validatePopUp() {
        driver.switchTo().window(driver.getWindowHandle());
    
    	    List<WebElement> popupList = driver.findElements(addUserPopupfields);
    	    List<WebElement> popupBtn = driver.findElements(popUpButton);
    	    List<WebElement> popupDrop = driver.findElements(popupDropdown);
    	    
            popupTextBox = new ArrayList<String>();
            
            addIntoList(popupList);
    	    addIntoList(popupDrop);
    	    
    	    popupTextBox.add(driver.findElement(emailField).getAttribute("placeholder"));
    	    addIntoList(popupBtn);

    	    return popupTextBox;
    	
    }	
    public void addIntoList(List<WebElement> listPopup) {
    	 for(WebElement textbox: listPopup) {
 	    	popupTextBox.add(textbox.getText());
 	    }    	 
    }
    public List<String> validateTextBoxes(){
    	driver.switchTo().window(driver.getWindowHandle());
    	//userWindowHandleId = driver.getWindowHandle();
    	//System.out.println("userWindow: "+ userWindowHandleId);

        List<WebElement> textBoxList = driver.findElements(textBoxes);
        popupTextBox = new ArrayList<String>();

        for(WebElement box: textBoxList) {
 	    	popupTextBox.add(box.getAttribute("data-placeholder"));
 	    	System.out.println("Is textBox: "+box.getAttribute("data-placeholder")+" : "+box.getTagName());
        }
        return popupTextBox;
    }
    public void getHandle() {
    	//mainWindowHandleId = driver.getWindowHandle();
    	//System.out.println("mainWindow: "+ mainWindowHandleId);
    }
    public void clickClose() {
    	driver.findElement(closeIcon).click();   	
    }
    public void addUser(String runType, String actionType) throws InterruptedException {
       if(runType.contains("without data")) 	
    			clickSubmit(actionType);
       else {
    	String fileName = System.getProperty("user.dir")+"/src/test/resources/testdata/usertestdata.xlsx";
    	String sheetName ="UserSheet";
    	dataMap = FilloExcel.getSingleData(fileName,sheetName,runType);
    	sendDataToTextBoxes(dataMap);  
        Thread.sleep(1000);
		sendDataToDropDown(dataMap);
        Thread.sleep(1000);

		clickSubmit(actionType);
       }
     
        /*  
    	
    	//dataMapList = new ArrayList<Map<String,String>>();
    	dataMapMulti = new HashMap<String,String>();
    	dataMapList = FilloExcel.getData(fileName,sheetName,runType);
  
    	System.out.println("countList: "+dataMapList.size());
    	for(Map<String,String> dataMapMulti: dataMapList) {
    	
    		sendDataToTextBoxes(dataMapMulti);  
            Thread.sleep(1000);
    		sendDataToDropDown(dataMapMulti);
            //Thread.sleep(1000);
    		clickSubmit(actionType);
            //Thread.sleep(1000);
            //validateTextField(dataMapMulti);
    	}    
       }*/	
     }
    public void sendDataToTextBoxes(Map<String,String> dataMap) {
    	List<WebElement> textBoxList = driver.findElements(textBoxes);
    	for(WebElement inputField: textBoxList) {
    		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(inputField)));*/
    		inputField.clear();
    		String placeHolderField = inputField.getAttribute("data-placeholder");
    			//System.out.println(dataMap.get(placeHolderField)+ " data-placeholder: "+inputField.getAttribute("data-placeholder"));
    	//System.out.println("emai: "+dataMap.get("Email address"));
    	//for(Map.Entry entry: dataMap.entrySet()) 
    		//System.out.println("Key: "+entry.getKey()+" value: "+entry.getValue());
    	
    	 if(!(dataMap.get(placeHolderField)==null)) {
    		/* new WebDriverWait(driver, Duration.ofSeconds(4))
     		.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(inputField)));*/
    		inputField.sendKeys(dataMap.get(placeHolderField));
    	 }	
    	}    	
    }
    public void sendDataToDropDown(Map<String,String> dataMap) throws InterruptedException {
    	//private By roleClick =  By.xpath("//div//label[text()='User Role']//..//div[@role='button']");
    	String dropdownType="";
    	List<WebElement> allDropdownList = driver.findElements(allDropDown);
           for(WebElement dropText: allDropdownList) {
        	     dropdownType =  dropText.getText();
                driver.findElement(By.xpath("//div//label[text()='"+dropdownType+"']//..//div[@role='button']")).click();
                List<WebElement> menuList = driver.findElements(By.xpath("//ul[@role='listbox']//span"));
                for(WebElement dropdown: menuList) {
                	
                   // Thread.sleep(1000);
              		if(dropdown.getText().contains(dataMap.get(dropdownType))) {
              		//	By locator= By.xpath("//ul[@role='listbox']//span"
              			//		+ "[contains(text(),'"+dataMap.get(dropdownType)+"')]");
              			//callDriverWait(locator).click();
                        Thread.sleep(1000);

            			dropdown.click();

              		}
              		
            	}
                Thread.sleep(1000);
           }
      }
    
    public void clickSubmit(String actionType) {
    	List<WebElement> popupButton = driver.findElements(popUpButton);
    	for(WebElement buttonElement: popupButton) {
    		System.out.println("button :" + buttonElement.getText() +" act: "+ actionType);
    		if(buttonElement.getText().contains(actionType))
    			buttonElement.click();
    	}
    }
    public void validateWindowUrl() {
    	//System.out.println("user: " + userWindowHandleId);
    	//System.out.println("main: " + mainWindowHandleId);

    	//driver.switchTo().window(driver.getWindowHandle());
    	//System.out.println("Current: "+driver.getWindowHandle());
    }
    public void validateTextField() {
    	WebElement error = driver.findElement(errorMessage);
    	System.out.println(error.getText());
    	System.out.println("Expected Error: "+dataMap.get("message"));
    }
    public void validateTextField(Map<String,String> dataMap) {
    	WebElement error = driver.findElement(errorMessage);
    	System.out.println(error.getText());
    	System.out.println("Expected Error: "+dataMap.get("message"));
    }
    
   
    public WebElement callDriverWait(By locator) {
    	return new WebDriverWait(driver, Duration.ofSeconds(6))
    			.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
}
   
