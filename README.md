# acm-app-microservice

## Contents
 1. Introduction
 
 2. Current Developers
 
 3. CodeOwners
 
 4. Common Information
 
 5. How to Help
 
 6. Git
 
 7. Gradle
 
 8. Java 8 JDK
 
 9. Java IDE
 
 10. VScode
 
 11. SQL
 
 12. Other Tools
 
 13. Further Discussion
 
 14. Rules of thumb
 
 15. Versioning
 
## Section 1
### Introduction

This repository contains the back-end code of the UPRC ACM chapter website.

## Section 2
### Current developers and emails (Insite-Dev):

Josue Van Dyke - josuemvd@gmail.com

Sam Butler - sambutler1017@icloud.com

Kiyle Winborne - kiyleawinborne@gmail.com
## Section 3
### CodeOwners and Emails:

#### Josue Van Dyke
Chairman 

Email: josuemvd@gmail.com

#### Sam Butler
Chief Software Engineer

Email: sambutler1017@icloud.com

#### Luke Lengel 
Treasurer/Secretary

Email: steven.lengel@rockets.utoledo.edu

#### Kiyle Winborne
Chief Hardware Engineer

Email: kiyleawinborne@gmail.com

## Section 4
### Common Information/Commands

* To test your branch you can run gradle test, which will run all the test in the repo


* To build your project you can run gradle clean build


* To contribute to this repo you will always extend from the development branch


* The branch naming style will be as follows:
<TICKET_LABEL>/ACMAPP-<TICKET_NUMBER>-<TICKET_DESCRIPTION>
## Section 5
### How to Help

To help develop and maintain this repository, you will need a few tools to create the development environment. This section will briefly describe the necessary tools, with more detailed descriptions, with a short introduction to each tool included in subsequent sections. 



#### Tools Needed
 * Git (Section 6)
 * Gradle (Section 7)
 * JDK 8 (Section 8)
 * Eclipse or equivalent IDE for Java development (Section 9)
 * Vscode or equivalent IDE for Angular development (Section 10) 
 * MySQL workbench (Section 11)
 * Other recommended tools and options (Section 12)
 
 ### Languages Used
  * Java - Used for Back End systems
  * Typescript - Used for Front End systems
  * Angular - Used for Front End systems
  * SQL - Used for database
  
  ### Where to begin?
  
  Once your development environment is correctly set up, the best place to look for a task is the Projects board on the github page for this repository. For reference, this is:
  [https://github.com/cse-uprc/acm-app-microservice]
  
  ## Section 6
  ### Git
  
  To get started with this repository, you will need git. To download Git, please refer to this website:
  [https://git-scm.com/downloads]
  
  Click the download link corresponding to your operating system.
  
  For Mac OS X users, you must also install homebrew and run the command `$ brew install git`.
  
  For linux users, run `sudo apt update` then `sudo apt upgrade`. After these commands have completed their tasks, run `sudo apt-get install git`. This will install git on your machine.
  
  ## Section 7
  ### Gradle
  Installing Gradle can be done by building from source, or manually including the binaries in your environment variables, specifically path.
  
  Detailed instructions can be found at [https://gradle.org/install/]
  
  Alternatively, you can install Node.js, which will install gradle alongside other packages. This can be downloaded at [https://nodejs.org/en/]
  
  ## Section 8
  ### Java
  
  Java 8 is used in this repository. Download links can be found at [https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html].
  Note, that an oracle account is required to download from oracle.com.
  
  ## Section 9
  ### Java Development Tools
  
  Eclipse is the recommended IDE for java development. To download, navigate to [https://www.eclipse.org/downloads/packages/] and find Eclipse for Java Developers.
  
  ## Section 10
  ### Angular Development
  
  VScode is used for angular development. Download link is [https://code.visualstudio.com/].
  
  ## Section 11
  ### SQL
  
  MySQL workbench is used here, download link is [https://dev.mysql.com/downloads/workbench/].
  
  ## Section 12
  ### Recommended Tools and Optional Installations
   * Node.js : [https://nodejs.org/en/]. Installs gradle automatically.
  ## Section 13
  ### Further Discussion
   This readme file is subject to change, and indeed will be updated when new features are added. New additions will be above the sections related to getting started, and will document features. Revisions to the preceding sections will be added to the appropriate sections of the changelog as "Section (Section Number here) - Brief Description)." An example: "Section 13 - Added discussion on readme changes."
   
   Please refer to the appropriate section that was changed for the most up to date information on a feature.
   
  ## Section 14
  ### Rules of thumb and Etiquette
  
  * After approval, a pull request should be posted in slack under the channel #merge-request.
  * Every commit should include a matching update to the changelog.md file, describing what is new, what has changed. 
  * Changelog additions should be formatted as follows: [yourname] - A short description of the change.
  
  ## Section 15
  ### Versioning
  Versioning should be as follows: 
  * Major versions will be incremented when "breaking changes" occur, which include migration to a different server, or changing frameworks.
  
  * Minor versions will be incremented when new functions are added, such as creating a new way to log in, while remaining compatible with the existing code base.
  
  * Patch versions will be incremented when a pull request is merged to production outside of normal release schedule. An example is clearing a bug.
  
  * Remember: v1.0.0 is not a version number, while version 1.0.0 is. Documentation, tags, and release should conform to semantic versioning, according to [semver.org]
  
  * Version numbers should be assigned as a git tag, and only changed if the preceding rules have been followed, and only during releases.
  
  * Releases should be released as X.Y.Z-rc.A until proven stable, then this will change to X.Y.Z .
  
  * Because versioning was not started from the beginning of this repository, the versions prior to 1.0.0-rc.1 have minor versions corresponding to merged pull requests during development.
 
  

