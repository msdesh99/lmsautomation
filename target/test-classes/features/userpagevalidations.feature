#@usersanity
Feature: User Page Validation

Background: Admin should see the ManageUser heading on User Page
Given Admin is on dashboardpage after login
When Admin Clicks user from Navigation Bar
Then Admin Should see a heading Manage User

@tag1E
Scenario: Admin should see the +Add New User popup
Given Admin is on Manage User page
When Admin clicks "Add New User" button
Then Admin should able to see "Add New User" popup

@tag18
Scenario: Validate User Details Popup Window
Given Admin is on Manage User page
When  Admin clicks "Add New User" button
Then Admin should able to see all "Fields"
| Field |
| First name |
| Middle name |
| Last name|
| Location |
| Phone no |
| LinkedIn Url |
| Under Graduate |
| Post Graduate |
| Time Zone |
| User Comments |
| Cancel |
| Submit |
| User Role |
| User Role Status |
| User Visa Status |
| Email address |

@tag19
Scenario: Validate input fields and text boxes in user details form
Given Admin is on Manage User page
When  Admin clicks "Add New User" button
Then Admin should able to see all "TextBoxes"
| Field |
| First name |
| Middle name |
| Last name|
| Location |
| Phone no |
| LinkedIn Url |
| Under Graduate |
| Post Graduate |
| Time Zone |
| User Comments |
| Email address |

#@tag20
#Scenario: Validate input fields and text boxes in user details form
#Given Admin is on Manage User page
#When  Admin clicks "Add New User" button
#Then Admin should able to see all "DropDowns"
#| Field |
#| User Role |
#| User Role Status |
#| Visa Status |

@tag21
Scenario: Check if user is created when only mandatory fields are entered with valid data
Given Admin is on Manage User page
And Admin is on  "Add New User" popup window
When Admin enters "user mandatory fields" in the form and clicks on "Submit" button
Then Admin gets message "User added Successfully"

@tag22
Scenario: Check if user is created when only optional fields are entered with valid data
Given Admin is on Manage User page
And Admin is on  "Add New User" popup window
When Admin enters "skip First name" in the form and clicks on "Submit" button
#When Admin enters "skips all mandatory fields" in the form and clicks on "Submit" button
Then Admin should see error message below the test field and the field will be highlighed in red color

@tag22A
Scenario Outline: Check if user is created when only skip optional fields are entered with valid data
Given Admin is on Manage User page
And Admin is on  "Add New User" popup window
When Admin enters "<run>" in the form and clicks on "Submit" button
Then Admin should see error message below the test field and the field will be highlighed in red color
Examples:
| run |
| skip First name |
| skip Last name |
| skip Middle name |
| skip Location |
| skip Time Zone |
| skip LinkedIn Url |
| skip Under Graduate |
| skip Post Graduate |
| skip User comments |
| skip User Visa Status |
| skip User Role |
| skip User Role Status |
| skips all mandatory fields |
| skip all fields |
@tag24
Scenario: Empty form submission
Given Admin is on Manage User page
And Admin is on  "Add New User" popup window
When Admin enters "without data" in the form and clicks on "Submit" button
Then Admin should see error message below the test field and the field will be highlighed in red color

@tag25
Scenario: Validate Cancel/Close(X) icon on User Details form
Given Admin is on Manage User page
And Admin is on  "Add New User" popup window
When Admin clicks Cancel/Close(X) Icon on User Details form
Then User Details popup window should be closed without saving

@tag26
Scenario: Check if user is created when only mandatory fields are entered with valid data
Given Admin is on Manage User page
And Admin is on  "Add New User" popup window
When Admin enters "user mandatory fields" in the form and clicks on "Cancel" button
Then Admin can see the User details popup disappears without adding any user   

@tag27
Scenario: Check if the user details are added in data table
Given Admin is on Manage User page
And Admin is on  "Add New User" popup window
When Admin enters "all fields" in the form and clicks on "Submit" button
Then Admin gets message "User added Successfully"

@tag28
Scenario: Validate row level edit icon
Given Admin is on Manage User page
When Admin clicks on the edit icon 
Then A new pop up with User details

@tag5
Scenario: Admin should see the +Assign Student button
Given Admin is on Manage User page
When Admin clicks "Assign Student" button
Then Admin should able to see "Assign Student" popup

