Feature: Create Store
@CreateStore
Scenario: Create Program scenario
	Given user authenticate to Incentivio
	And user creates a merchant
	And user creates a store
	Then user verify created store
	And delete the created store
 



