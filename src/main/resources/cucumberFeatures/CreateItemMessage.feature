Feature: Create Messsage
@CreateMessage
Scenario: Create Message verify and delete scenario
Given user authenticate to Incentivio
	And user creates a merchant
	And user creates Program
	And user creates Campaign	
Then user creates message
And user authenticate to IncentivioMobileAPI
And user verify created message in Mobile 

#Scenario: Delete Message scenario
#Given user authenticate to Incentivio
#	And delete the created message

