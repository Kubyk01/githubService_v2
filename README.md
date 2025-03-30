# GitHub Repositories Integration Service

This project provides a Spring Boot-based service to fetch GitHub user repositories and their branches via GitHub's API. It uses Spring Cloud OpenFeign to communicate with GitHub's API, retrieving repository details and associated branches.

## Features

- Fetch GitHub repositories of a user.
- Fetch branches of each repository.
- Display repository names, owner login, and commit SHA for each branch.
- Provide error handling for non-existing users.

## Prerequisites

- Java 21 or later
- Maven or Gradle for building the project
- Spring Boot and Spring Cloud
- Access to GitHub's API (no authentication is required in this version, but can be added)

## Getting Started

### Clone the repository

git clone https://github.com/yourusername/github-repos-service.git
cd github-repos-service

### Build the application

Using Maven:

./mvnw clean install

Using Gradle:

./gradlew build

### Run the application

To run the application locally, execute:

./mvnw spring-boot:run

Alternatively, if you're using Gradle:

./gradlew bootRun

The application will run on port `8080` by default.

## API Endpoints

### `GET /github/{username}`

Fetches the repositories and branches of a GitHub user.

**Path Parameter:**

- `username` - The GitHub username for which repositories will be fetched.

**Example Response:**

{
"repo_name": "RepositoryName",
"owner_login": "OwnerUsername",
"branches": [
{
"name": "main",
"commit": {
"sha": "abcd1234",
}
}
]
}

### Error Handling

- **404 Not Found**: If the user does not exist or has no repositories, the response will be `404 Not Found`.
- **500 Internal Server Error**: If there's an error communicating with the GitHub API, a `500 Internal Server Error` will be returned.

### Example Request:

GET http://localhost:8080/github/octocat

### Example Response:

[
{
"repo_name": "Hello-World",
"owner_login": "octocat",
"branches": [
{
"name": "main",
"commit": {
"sha": "123abc456def",
}
}
]
}
]

## Running Tests

To run the tests for this project:

./mvnw test

Or for Gradle:

./gradlew test

Tests include integration tests that do not mock the GitHub API, ensuring the real integration is tested. This includes verifying repository and branch data returned from the GitHub API.

## Technologies Used

- Spring Boot
- Spring Cloud OpenFeign
- JUnit 5
- MockMvc
- Jackson (for JSON mapping)
- Maven or Gradle for build management

## Troubleshooting

If you face issues with connecting to the GitHub API, ensure your network can access external APIs and that the GitHub API is up and running. You may also add authentication (like a Personal Access Token) in the future if required.