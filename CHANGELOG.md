# ChangeLog File
- Every time a Pull Request is submitted there must be a change log insertion. 
- This helps keep a record of what is happening in the repository and to keep things dated on what is being added

# ChangeLog Template


## Release - _--/--/---_ vX.X.X


   ### <ins>Added:</ins>
   ```diff
   + [name] - Description of information that was added goes here
   ```

   ### <ins>Changed:</ins>
   ```diff
   ! [name] - Description of information that was added goes here
   ```

   ### <ins>Removed:</ins>
   ```diff
   - [name] - Description of information that was added goes here
   ```
---

## Release - 07/26/2020 v0.16.0

   ### <ins>Added:</ins>
   ```diff
   + [sambutler1017] - DqlBuilder class to read querys from acmsql file.
   + [sambutler1017] - JwtTokenUtil class to generate and manage jwt tokens.
   + [kwinborne] - Spec file for User Client.
   + [kwinborne] - Skeleton for User Controller packages
   ```
   
   ### <ins>Changed:</ins>
   ```diff
   ! [sambutler1017] - CodeOwners to include @llengel and @kwinborne
   ! [kwinborne] - Merged UserProfile and UserCredentials into Use
   ```

## Release - 07/27/2020 v0.16.0

   ### <ins>Changed:</ins>
   ```diff
   ! [kwinborne] - Updated Readme.md to include the skeleton for a guide for beginners
   ```

## Release - 07/28/2020 v0.16.0

   ### <ins>Added:</ins>
   ```diff
   + [kwinborne] - Test for UserClient
   + [sambutler1017] - Test for UserService
   ```
   ### <ins>Changed:</ins>
   ```diff
   ! [kwinborne] - Filled in UserService, UserClient, UserController with appropriate methods
   ```

## Release - 07/28/2020 v0.16.0

   ### <ins>Added:</ins>
   ```diff
   + [kwinborne] - Created UserGetRequest class
   ```
   ### <ins>Changed:</ins>
   ```diff
   ! [kwinborne] - Modified user services to accomdate addition
   ! [kwinborne] - Cleaned up javadoc
   ```

## Release - 07/28/2020 v0.16.0
   ### <ins>Added:</ins>
   ```diff
   + [kwinborne] - Created Authentication Service class
   + [kwinborne] - Added dummy classes for JWT model
   + [kwinborne] - Created Authentication Controller class
   ```
   
## Release - 08/01/2020 v0.16.0
   ### <ins>Added:</ins>
   ```diff
   + [shancock] - Created JWT models
   ```

## Release - 08/02/2020 v0.16.0
   ### <ins>Added:</ins>
   ```diff
   + [kwinborne] - Created test for AuthorizationService
   ```
   ### <ins>Changed:</ins>
   ```diff
   ! [kwinborne] - Added password hashing to AuthorizationService
   ```

## Release - 08/02/2020 v0.16.0
   ### <ins>Added:</ins>
   ```diff
   + [sambutler1017] - Wrote test for authentication controller token generation method
   + [kwinborne] - Created tests for AuthenticationController decode method
   ```
   ### <ins>Changed:</ins>
   ```diff
   ! [kwinborne] - Added decode token method to Authentication Controller
   ! [kwinborne] - cleaned up code and javadocs for auth. service and controller.
         ```