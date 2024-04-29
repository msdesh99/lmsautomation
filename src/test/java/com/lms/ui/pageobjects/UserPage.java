package com.lms.ui.pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
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
    	if(button.contentEquals("Add New User"))
    		driver.findElement(addUserButton).click();
    	if(button.contentEquals("Assign Student"))
    		driver.findElement(assignStudent).click();
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
            
    	    //popupTextBox.add(driver.findElement(closeIcon).getText());
    	    return popupTextBox;
    	
    }	
    public void addIntoList(List<WebElement> listPopup) {
    	 for(WebElement textbox: listPopup) {
 	    	popupTextBox.add(textbox.getText());
 	    	System.out.println("add: "+textbox.getText());
 	    }    	 
    }
    public List<String> validateTextBoxes(){
    	driver.switchTo().window(driver.getWindowHandle());
        List<WebElement> textBoxList = driver.findElements(textBoxes);
        popupTextBox = new ArrayList<String>();

        for(WebElement box: textBoxList) {
 	    	popupTextBox.add(box.getAttribute("data-placeholder"));
 	    	System.out.println("Is textBox: "+box.getAttribute("data-placeholder")+" : "+box.getTagName());
        }
        return popupTextBox;
    }
    public void clickClose() {
    	driver.findElement(closeIcon).click();   	
    }
    public void addUser(String runType, String actionType) throws InterruptedException {
    	
       if(runType.contains("skips all mandatory fields") ||
    		   runType.contains("without data")) 	{
    		clickSubmit(actionType);
    		validateAllTextFieldForError();
    		//validateTextField(dataMap);
       }		
 
       else if(runType.contains("user mandatory fields")) {
    	   
    	   String fileName = System.getProperty("user.dir")+"/src/test/resources/testdata/usertestdata.xlsx";
    	   String sheetName ="UserSheet";
    	   dataMap = FilloExcel.getSingleData(fileName,sheetName,runType);
    	   
    	   sendDataToTextBoxes(dataMap);  
    	   Thread.sleep(1000);
    	   
    	  // sendDataToDropDown(dataMap);
    	   sendDropDown(dataMap);

    	   Thread.sleep(1000);
    	   
    	   clickSubmit(actionType);
       }
       else if(runType.contains("invalid data")) {
    	   
    	   String fileName = System.getProperty("user.dir")+"/src/test/resources/testdata/usertestdata.xlsx";
    	   String sheetName ="UserSheet";
    	   dataMap = FilloExcel.getSingleData(fileName,sheetName,"user mandatory fields");
    	   dataMap.put("User Comments","111");
    	   sendDataToTextBoxes(dataMap);  
    	   Thread.sleep(1000);
    	   
    	  // sendDataToDropDown(dataMap);
    	   sendDropDown(dataMap);
    	   Thread.sleep(1000);
    	   
    	  clickSubmit(actionType);
    	  Thread.sleep(2000);
    	  WebElement activeElement = driver.switchTo().activeElement();
    	  System.out.println("Error Message: "+ activeElement.getAriaRole() );
    	  System.out.println(activeElement.getTagName()+ "text: "+ activeElement.getText());
    	  System.out.println("att: "+activeElement.getAttribute("value"));

       }
       else if(runType.contains("skip one field")){
    	    String fileName = System.getProperty("user.dir")+"/src/test/resources/testdata/usertestdata.xlsx";
       		String sheetName ="UserSheet";
       		dataMap = FilloExcel.getSingleData(fileName,sheetName,"user mandatory fields");
       		
       		//readDataFromDataMap(dataMap);
       }
       else if(runType.contains("skip text%")){
       	String fileName = System.getProperty("user.dir")+"/src/test/resources/testdata/usertestdata.xlsx";
          	String sheetName ="UserSheet";  	
          	dataMapMulti = new HashMap<String,String>();
          	dataMapList = FilloExcel.getData(fileName,sheetName,runType);
  
    	System.out.println("countList: "+dataMapList.size());
    	for(Map<String,String> dataMapMulti: dataMapList) {
    	
    		sendDataToTextBoxes(dataMapMulti);  
            Thread.sleep(1000);
    		clickSubmit("Submit");
     		Thread.sleep(1000);
            validateTextField(dataMapMulti);

     		clickClose();
    		driver.findElement(addUserButton).click();
    	}    
       }
     }
  /*  
    public void sendDataFromMap(Map<String,String> dataMap, String blankField) throws InterruptedException {
    	List<WebElement> textBoxList = driver.findElements(textBoxes);
    	for(WebElement inputField: textBoxList) {
    		inputField.clear();
    		String placeHolderField = inputField.getAttribute("data-placeholder");
    	 if(!(placeHolderField.contains(blankField))) {
    		inputField.sendKeys(dataMap.get(placeHolderField));
    	 }	
    	}   
		//sendDataToDropDown(dataMap);
 		clickSubmit("Submit");
 		Thread.sleep(3000);
 		clickClose();
		driver.findElement(addUserButton).click();
    } 
    public void readDataFromDataMap(Map<String,String> dataMap) throws InterruptedException {
    	for(Map.Entry<String, String> map: dataMap.entrySet()) {
    	  if(!(map.getKey().contains("run") || 
    		   map.getKey().contains("User Role") ||
    		   map.getKey().contains("User Role Status") ||
    		   map.getKey().contains("User Visa Status") ||
    		   map.getKey().contains("message"))) 
    			  {
      		System.out.println("key: "+map.getKey()+ "Valu: "+map.getValue());
      		Thread.sleep(2000);
           	sendDataFromMap(dataMap, map.getKey());  

           	Thread.sleep(2000);
    	}
    	}
    }*/
    public void sendDataToTextBoxes(Map<String,String> dataMap) {
    	List<WebElement> textBoxList = driver.findElements(textBoxes);
    	for(WebElement inputField: textBoxList) {
    		inputField.clear();
    		String placeHolderField = inputField.getAttribute("data-placeholder");
    	 if(!(dataMap.get(placeHolderField)==null)) {
    		inputField.sendKeys(dataMap.get(placeHolderField));
    	 }	
    	}    	
    }
    public void sendDropDown(Map<String,String> dataMap) throws InterruptedException {
    	String dropdownType="";
    	List<WebElement> allDropdownList = driver.findElements(allDropDown);
           for(WebElement dropText: allDropdownList) {
        	    dropdownType =  dropText.getText();
                driver.findElement(By.xpath("//div//label[text()='"+dropdownType+"']//..//div[@role='button']")).click();
               List<WebElement> menuList = driver.findElements(By.xpath("//ul[@role='listbox']//span"));
               List<String> menuTextList = new ArrayList<String>();
               for(int i=0;i<menuList.size();i++) {
            	   menuTextList.add(menuList.get(i).getText());           	   
               }
               
                for(String subText: menuTextList) {  
             		if(subText.contains(dataMap.get(dropdownType))) {
             			driver.findElement(By.xpath("//ul[@role='listbox']"
             					+ "//span[contains(text(),'"+subText+"')]")).click();
                  		Thread.sleep(1000);
              		}          		
            	}
                Thread.sleep(1000);
           }
      }
 /*  
    public void sendDataToDropDown(Map<String,String> dataMap) throws InterruptedException {
    	//private By roleClick =  By.xpath("//div//label[text()='User Role']//..//div[@role='button']");
    	String dropdownType="";
    	List<WebElement> allDropdownList = driver.findElements(allDropDown);
           for(WebElement dropText: allDropdownList) {
        	    dropdownType =  dropText.getText();
                driver.findElement(By.xpath("//div//label[text()='"+dropdownType+"']//..//div[@role='button']")).click();
               List<WebElement> menuList = driver.findElements(By.xpath("//ul[@role='listbox']//span"));
               
               // By locSpan = By.xpath("//ul[@role='listbox']//span");
                //List<WebElement> menuList  = 
                		new WebDriverWait(driver,Duration.ofSeconds(4))
                       .until(ExpectedConditions.visibilityOfAllElements(menuList));
                    		   //visibilityOfAllElementsLocatedBy((locSpan));
                for(WebElement dropdown: menuList) {  
             		if(dropdown.getText().contains(dataMap.get(dropdownType))) {
              		 By locator	= By.xpath("//ul[@role='listbox']//span[contains(text(),'"
              				 +dataMap.get(dropdownType)+"')]");
              		 new WebDriverWait(driver,Duration.ofSeconds(5))
              		     .until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
                  		Thread.sleep(3000);
            			dropdown.click();
              		}          		
            	}
                Thread.sleep(1000);
           }
      }
    */
    public void clickSubmit(String actionType) {
    	List<WebElement> popupButton = driver.findElements(popUpButton);
    	for(WebElement buttonElement: popupButton) {
    		if(buttonElement.getText().contains(actionType))
    			buttonElement.click();
    	}
    }
 
    public void validateAllTextFieldForError() {
    	List<WebElement> errorList = driver.findElements(errorMessage);
    	for(WebElement errorEle: errorList)
    	   System.out.println("Actual Error: "+errorEle.getText());
    	System.out.println("Expected Error: "+dataMap.get("message"));
    } 
    public void validateTextField(Map<String,String> dataMap) {
    	WebElement error = driver.findElement(errorMessage);
    	String color = error.getCssValue("color").trim();
    	
    	System.out.println("Expected Error: "+dataMap.get("message")+ " Actual Error: "+error.getText());
    	System.out.println("Expected Text Color: "+dataMap.get("text color") +" Actual Color: "+ Color.fromString(color).asHex());

    }
    
   
    public WebElement callDriverWait(By locator) {
    	return new WebDriverWait(driver, Duration.ofSeconds(6))
    			.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
}
   
