# ISSUE TRACKER

## Build and run the project

* To build back-end project. Enter the root folder of the project and run
* ./gradlew bootRun

* To build and run the ui. Enter root folder of the ui project and run 

* npm start

## Access URLs

* To access UI go to <br /> http://localhost:3000/

* Swagger documentation is added to the back-end project. You can see the available list of APIS by accessing URL <br /> http://localhost:8080/penguine/swagger-ui.html

## Technical Choices

* UI is developer using React <br />

    Its easy to prototype applications using react because of the less learning curve. React performance is comparatively high because of the virtual DOM rendering. React hooks are developer-friendly while dealing with dynamic rendering

* Spring boot is used for backend <br />

    No boilerplate code is needed to create a new project. we can easily create a project using spring initializer and configure the project. Embedded tomcat helps develop and deploy the application fastly.

* Spring data JPA <br />

    We don't want to worry about the underlying database. we can switch to any database quickly

* Gradle <br />

    High performance. No need to install it in the local machine like maven. better readability. 

* Docker <br />

    Docker is used to containerizing the application.  Easiness of build and ship the application.

## Problems Completing the Project

* I tried to build the application using docker and docker-compose but got an error while trying to build the application using docker-compose.

## Problems Faced

* While developing the algorithm my main concern was how to distribute the tasks equally among the developers. No developer must not be over-utilized or underutilized while planning the story. So I decided to sort the developers after each story assignment. After sorting developer with high bandwidth will come on the top and the next task will assign to him.

## Part of project leave out

* UI to manage developers and edit, delete of story is not done<br/>
  Reason: Story planning was the most important part to me. So I created UI to add and plan tory

* Test case only added for plan story and only one scenario covered </br>
 
   Reason: plan story contains more logic and time constraint to cover the other scenario

* Docker part is not completed because I was stuck in an error

* Backend and front validation is left out due to time constraints

* Project is developed under the assumption that story point for a story is less than or equal to 10 and all developers are in same team <br/>

    Reason: It will add more complexity. Want to keep the solution simple for the first time and we can build on top of that.

## What will I resolve If I have more time

* The Algorithm is not efficient when the number of developers is high. Need to find a more optimized algorithm.

* Backend and front validations need to be completed.

* UI to manage users and stories.

* Deploy the application using Docker.

* I assumed that all developers are in the same team even though I added a field teamId in developer table. In the future, we can pass the team Id and plan the task for different teams