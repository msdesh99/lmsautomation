package com.lms.ui.stepdefinitions;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;

import com.lms.ui.pageobjects.UserPage;
import com.lms.ui.utilities.TestContextSetUp;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserSteps {
	public UserPage userPage;
	public TestContextSetUp testContextSetUp;
	
	public UserSteps(TestContextSetUp testContextSetUp) {
		this.testContextSetUp = testContextSetUp;
		userPage = testContextSetUp.pageObjManager.getUserPage();
	}
	
	@Given("Admin is on dashboardpage after login")
	public void admin_is_on_dashboardpage_after_login() throws InterruptedException {
	    String userName = testContextSetUp.base.configs.getUserName();
	    String password = testContextSetUp.base.configs.getPassword();
	    userPage.login(userName, password);
	}
	@When("Admin Clicks user from Navigation Bar")
	public void admin_clicks_user_from_navigation_bar() throws InterruptedException {
		userPage.navigate();	   
	}
	@Then("Admin Should see a heading Manage User")
	public void admin_should_see_a_heading_manage_user() {
		System.out.println(userPage.validate());	   
	}
	
	@Given("Admin is on Manage User page")
	public void admin_is_on_manage_user_page() throws InterruptedException {
		//System.out.println("handle");
		//userPage.getHandle();
	}
	@When("Admin clicks {string} button")
	public void admin_clicks_button(String button) throws InterruptedException {
		userPage.locateButton(button);	    
	}
	@Then("Admin should able to see {string} popup")
	public void admin_should_able_to_see_popup(String validateButton) {
	    
	}
	@Then("Admin should able to see all {string}")
	public void admin_should_able_to_see_all(String listType, DataTable dataTable) {
		List<String> expectedList = dataTable.asList(String.class);
        List<String> actualList = new ArrayList<String>();
		
        if(listType.contains("Fields"))
			 actualList =  userPage.validatePopUp();
		if(listType.contains("TextBoxes"))
			actualList = userPage.validateTextBoxes();			
		
		List<String> absentText = actualList.stream()
  					.filter(s->!expectedList.contains(s))
  					.collect(Collectors.toList());
		for(String absent: absentText)
				System.out.println("absent: "+absent);
		Assert.assertEquals(absentText.size(),0,"Some Fields are missing in popup window");		
	}
	@Given("Admin is on  {string} popup window")
	public void admin_is_on_popup_window(String button) {
		try {
			userPage.locateButton(button);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}	    
	}
	@When("Admin enters {string} in the form and clicks on {string} button")
	public void admin_enters_in_the_form_and_clicks_on_button(String runType, String actionType) throws InterruptedException {
		userPage.addUser(runType,actionType);
	}
	
	@Then("Admin gets message {string}")
	public void admin_gets_message(String expectedmessage) {
	
	}

	@Then("Admin can see the User details popup disappears without adding any user")
	public void admin_can_see_the_user_details_popup_disappears_without_adding_any_user() {
	   //String actualwindow =  
			   userPage.validateWindowUrl();
	}
	@Then("Admin should see error message below the test field and the field will be highlighed in red color")
	public void admin_should_see_error_message_below_the_test_field_and_the_field_will_be_highlighed_in_red_color() {
		userPage.validateTextField();
	}

	@When("Admin clicks Cancel\\/Close\\(X) Icon on User Details form")
	public void admin_clicks_cancel_close_x_icon_on_user_details_form() {
		userPage.clickClose();
	  
	}

	@Then("User Details popup window should be closed without saving")
	public void user_details_popup_window_should_be_closed_without_saving() {
	}

	@When("Admin clicks on the edit icon")
	public void admin_clicks_on_the_edit_icon() {
	    
	}

	@Then("A new pop up with User details")
	public void a_new_pop_up_with_user_details() {
	   
	}


}
