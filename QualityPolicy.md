### Quality Policy
> Describe your Quality Policy in detail for this Sprint (remember what I ask you to do when I talk about the "In your Project" part in the lectures and what is mentioned after each assignment (in due course you will need to fill out all of them, check which ones are needed for each Deliverable). You should keep adding things to this file and adjusting your policy as you go.
> Check in Project: Module Concepts document on Canvas in the Project module for more details 

**GitHub Workflow** (start Sprint 1)
* Master should have the most stable version of the software at all times
* Master must be functional at all times
* Dev branch will only be merge with Master when it it works, it has been tested, and reviewed by 1 team member and Git Master
* User story branch must match the taiga board user story
* User story branch must branch from master
* Tasks must branch from the related user story
* Branches must not be deleted after merge
* Commit messages should be formatted: UserStory# and Task# 
  * e.g.”US1 Task1 Add comments”
* Commits should be concise on what was changed
* “WIP” should be added  if the branch doesn’t if the branch doesn’t work
  * e.g.”US1 Task1 WIP Add comments”


*Git Master Responsibilities*
* Ensures all pull requests are fast forwards
* Merging to master or dev must be the result of a pull request
* Ensure all progress that is completed is uploaded to master by the deadline date of a Sprint
* Ensures pull requests are approved by someone
* Ensures code works upon approval of pull requests to master or dev otherwise revert changes from pull request
* Conflict must be resolved by individual before creating a pull request (No pull request with conflicts)


**Unit Tests Blackbox** (start Sprint 2)
  * Ensure the code is properly tested before merging to dev
  * Everyone one must write at least 4 good unit tests per sprint
  * Commit messages for unit testing need to be formatte correctly
    * e.g. "US1 Task1 Unit Test: Test addBelt"
  * UI code needs to be tested from peers
  * Test new code and code written from previous sprints
  * Black box tests need to cover equivalance partitioning and boundary value analysis
     * try aiming for the minimum amount of each to cover both scenarios
  * Tester include dev and one more memeber of the team (or Reviewer for short)
  * Test code that already exists in the exisiting code and fix major flaws
  * Tests need to be added to test folder in memoranda
  * Ensure Jacoco and Junit can run 
  * Ensure JavaDocs are accompanied by unit tests
  * Each User story should have at least 5 test cases minimum

 **Unit Tests Whitebox** (online: start Sprint 2, campus: start Sprint 3)
  * Ensure the code is properly tested before merging to dev
  * ensure new code and exisitng code from previous sprints are tests to the best ability
  * Aim of 80%-85% percentage code coverage
  * Ensure Jacoco and Junit can run 
  * Tester include dev and one more memeber of the team (or Reviewer for short)
  * Ensure JavaDocs are accompanied by unit tests
  * Each User story should have at least 5 test cases minimum

**Code Review** (online: due start Sprint 2, campus: start Sprint 3)
  * Ensure the code is reviewed by two other people
    * Reviewers should write comments into the Pull request (Checklist filled out and additional comments)
    * Developers should write comments in thier pull request (Checklist filled out and additional comments)
  * No code can be merged into dev or master without a proper code review

## Checklist/questions list which every developer will need to fill out/answer when creating a Pull Request to the Dev branch. 
  - [ ] Code compiles with no Build errors
  - [ ] Unit Tests for BlackBox have been implemented and covers most/all equivalance partitioning and boundary value analysis
  - [ ] Unit Tests for WhiteBox have been implemented and work with proper code coverage (80%-85%)
  - [ ] Ensure checkstyles has less than 25 errors 
  - [ ] Ensure spotbugs has major issues resolved
  - [ ] Failed tests have been fixed and program is stable 
  
## CheckList/question list which every reviewer will need to fill out/anser when conducting a review, this checklist (and the answers of course) need to be put into the Pull Request review.
  - [ ] Code compiles with no Build errors
  - [ ] Confirm Unit Tests for BlackBox have been implemented and covers equivalance partitioning and boundary value analysis
  - [ ] Confirm Unit Tests for WhiteBox have been implemented and work with proper code coverage
  - [ ] Ensure checkstyles has less than 25 errors 
  - [ ] Ensure spotbugs has major issues resolved
  - [ ] Communicate any missing tests to be implemented to developer
  - [ ] Ensure all tests pass 
  
**Static Analysis**  (online: start Sprint 3, campus: start Sprint 3)
  > How: running spotbugs and checkstyles to Statically analyze the code. When: Optionally, Continously though out the creation of a UserStory and required, before merge to dev. Where: Any new addtions to the codebase and optionally any code from existing base that was used.

**Continuous Integration**  (start Sprint 3)
  > Dev and master both have green check marks and any Userstory branch should try to have a green check mark before merging to dev.
