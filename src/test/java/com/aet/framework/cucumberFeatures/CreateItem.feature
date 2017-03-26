@tag
Feature: Create Item
	I want to use this template for my feature file

@CreateItem
Scenario: Create Item scenario
Given user authenticate to Incentivio
	And user creates a merchant
Then user creates Item
	And user verify created Item
	And delete the created Item
 



