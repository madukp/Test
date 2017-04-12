Feature: Create Program
@CreateProgram
Scenario: Create Program scenario
Given user authenticate to Incentivio
	And user creates a merchant
And user creates Program
	Then user verify created Program
	And delete the created Program
 



