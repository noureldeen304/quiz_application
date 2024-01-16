# Quiz Application Backend

Welcome to the Quiz Application Backend repository! This backend application is built with Java, PostgreSQL, Hibernate, REST API, and Spring Security. It offers a feature-rich quiz management system with secure user authentication and a powerful RESTful API.

## Project Overview

- **Technology Stack:**
  - Java
  - PostgreSQL
  - Hibernate
  - REST API
  - Spring Security

- **Database:**
  - PostgreSQL for efficient data management.

- **ORM (Object-Relational Mapping):**
  - Leveraged Hibernate for seamless object-relational mapping.

- **Security:**
  - Implemented security measures using Spring Security.

## Key Features

- **Quiz Management:**
  - Developed a feature-rich quiz management system.

- **User Authentication:**
  - Ensured secure user authentication using Spring Security.

- **RESTful API:**
  - Designed and implemented a RESTful API for seamless communication.

- **Dynamic Queries:**
  - Used Hibernate to execute dynamic queries for optimized database interactions.

## RESTful API Endpoints

Explore the project's RESTful API endpoints:

## Admin Endpoints

### Quizzes Management

- **Retrieve All Quizzes:**
  - [GET /api/v1/admin/quizzes](#) - Retrieve all quizzes.

- **Retrieve Admin's Quizzes:**
  - [GET /api/v1/admin/quizzes/my-quizzes](#) - Retrieve quizzes created by the admin.

- **Retrieve Quiz by ID:**
  - [GET /api/v1/admin/quizzes/id/{id}](#) - Retrieve a specific quiz by ID.

- **Retrieve Quizzes by Category:**
  - [GET /api/v1/admin/quizzes/category/{category}](#) - Retrieve quizzes by category.

- **Retrieve Quizzes by Category and Version:**
  - [GET /api/v1/admin/quizzes/category/{category}/version/{version}](#) - Retrieve quizzes by category and version.

### Quiz Modification

- **Create Quiz with Random Questions:**
  - [POST /api/v1/admin/quizzes/create-quiz-with-random-questions](#) - Create a quiz with random questions.

- **Create Quiz with Selected Questions:**
  - [POST /api/v1/admin/quizzes/create-quiz-with-selected-questions](#) - Create a quiz with selected questions.

- **Update Quiz Version:**
  - [PUT /api/v1/admin/quizzes/update-quiz/{id}/update-version](#) - Update the version of a specific quiz.

- **Add Random Questions to Quiz:**
  - [PUT /api/v1/admin/quizzes/update-quiz/{id}/add-random-questions](#) - Add random questions to a quiz.

- **Add Selected Questions to Quiz:**
  - [PUT /api/v1/admin/quizzes/update-quiz/{id}/add-selected-questions](#) - Add selected questions to a quiz.

- **Remove Random Questions from Quiz:**
  - [PUT /api/v1/admin/quizzes/update-quiz/{id}/remove-random-questions](#) - Remove random questions from a quiz.

- **Remove Selected Questions from Quiz:**
  - [PUT /api/v1/admin/quizzes/update-quiz/{id}/remove-selected-questions](#) - Remove selected questions from a quiz.

- **Remove All Questions from Quiz:**
  - [PUT /api/v1/admin/quizzes/update-quiz/{id}/remove-all-questions](#) - Remove all questions from a quiz.

- **Remove Quiz by ID:**
  - [DELETE /api/v1/admin/quizzes/remove-quiz/{id}](#) - Remove a specific quiz by ID.

### Quiz Analysis

- **Get Model Answers for Quiz:**
  - [GET /api/v1/admin/quizzes/id/{id}/get-model-answers](#) - Retrieve model answers for a specific quiz.

- **Get Students' Scores for Quiz:**
  - [GET /api/v1/admin/quizzes/id/{id}/get-students-scores](#) - Retrieve students' scores for a specific quiz.

## Student Endpoints

### Quiz Access

- **Get All Quizzes:**
  - [GET /api/v1/student/quizzes](#) - Retrieve all quizzes available to students.

- **Get Quiz by Category and Version:**
  - [GET /api/v1/student/quiz](#) - Retrieve a specific quiz by category and version.

### Quiz Submission

- **Submit Answers:**
  - [POST /api/v1/student/quiz/submit-answers](#) - Submit answers for a quiz.

### Quiz Results

- **Show Results:**
  - [GET /api/v1/student/quiz/show-results](#) - Retrieve results and correct answers for a quiz.
