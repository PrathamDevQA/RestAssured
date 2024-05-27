#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#@tag
Feature: Validate the Plaeces APIs
@Regression
@AddPlace 
  Scenario Outline: Verify if the Places is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User call "addPlaceAPI" with "POST" http request
    Then the API call got success with status 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name  | language | address     |
      | Staah | English  | Pal, Adajan |
   #   | Markettime | Hindi    | Vesu, Piplod 395008 |
  @Regression    
  @DeletePlace 
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When User call "deletePlaceAPI" with "POST" http request
    Then the API call got success with status 200
    And "status" in response body is "OK"
