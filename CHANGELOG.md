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
---

## Release - 08/13/2020 v1.1.0

   ### <ins>Changed:</ins>
   ```diff
   ! [sambutler1017] - How the filtering of the user is done when getting a list of users and sending an email.
   ```
   
## Release - 08/08/2020 v1.1.0

   ### <ins>Changed:</ins>
   ```diff
   + [kwinborne] - Created Update User endpoint 
   + [kwinborne] - Implemented email functionality to create user
   ```

   ### <ins>Changed:</ins>
   ```diff
   + [kwinborne] - Added Github package skeleton
   + [kwinborne] - Added Application package skeleton
   + [kwinborne] - Added Calendar package skeleton
   ```
   
## Release - 08/07/2020 v1.1.0

   ### <ins>Changed:</ins>
   ```diff
   + [jvandyke] - Updated getUserCredentials query and UserCredentialMapper to include first and last name.
   ```

## Release - 08/05/2020 v1.1.0

   ### <ins>Added:</ins>
   ```diff
   + [kwinborne] - Create User functionality to User Controller, with test
   ```
   
## Release - 08/04/2020 v1.1.0

   ### <ins>Added:</ins>
   ```diff
   + [sambutler1017] - Email functionality to service.
   ```
   
## Release - 08/02/2020 v0.16.0

   ### <ins>Added:</ins>
   ```diff
   + [sambutler1017] - Test for authentication controller token generation method.
   + [kwinborne] - Test for AuthorizationService.
   + [kwinborne] - Tests for AuthenticationController decode method
   ```
   
   ### <ins>Changed:</ins>
   ```diff
   ! [kwinborne] - Decode token method to Authentication Controller.
   ! [kwinborne] - Password hashing to AuthorizationService
   ! [kwinborne] - cleaned up code and javadocs for auth. service and controller.
   ```

## Release - 08/01/2020 v0.16.0

   ### <ins>Added:</ins>
   ```diff
   + [shancock] - JWT models.
   ```
   
## Release - 07/28/2020 v0.16.0

   ### <ins>Added:</ins>
   ```diff
   + [kwinborne] - Authentication Service class.
   + [kwinborne] - Dummy classes for JWT model.
   + [kwinborne] - Authentication Controller class.
   + [kwinborne] - Test for UserClient.
   + [kwinborne] - UserGetRequest class.
   + [sambutler1017] - Test for UserService.
   ```
   
   ### <ins>Changed:</ins>
   ```diff
   ! [kwinborne] - Filled in UserService, UserClient, UserController with appropriate methods.
   ! [kwinborne] - Modified user services to accomdate addition.
   ! [kwinborne] - Cleaned up javadoc.
   ```

## Release - 07/27/2020 v0.16.0

   ### <ins>Changed:</ins>
   ```diff
   ! [kwinborne] - Readme.md to include the skeleton for a guide for beginners.
   ```
   
## Release - 07/26/2020 v0.16.0

   ### <ins>Added:</ins>
   ```diff
   + [sambutler1017] - DqlBuilder class to read querys from acmsql file.
   + [sambutler1017] - JwtTokenUtil class to generate and manage jwt tokens.
   + [kwinborne] - Spec file for User Client.
   + [kwinborne] - Skeleton for User Controller packages.
   ```
   
   ### <ins>Changed:</ins>
   ```diff
   ! [sambutler1017] - CodeOwners to include @llengel and @kwinborne.
   ! [kwinborne] - Merged UserProfile and UserCredentials into Use.
   ```
