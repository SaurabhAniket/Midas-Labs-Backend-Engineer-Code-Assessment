# Midas-Labs-Backend-Engineer-Code-Assessment
## Setup

### Pre-requisities

To run the application you would require:

- [Java](https://www.azul.com/downloads/#zulu)
- [Temporal](https://docs.temporal.io/cli#install)
- [Docker](https://docs.docker.com/get-docker/)
- [Stripe API Keys](https://stripe.com/docs/keys)

### On macOS:

First, you need to install Java 21 or later. You can download it from [Azul](https://www.azul.com/downloads/#zulu) or
use [SDKMAN](https://sdkman.io/).

```sh
brew install --cask zulu21
```

You can install Temporal using Homebrew

```sh
brew install temporal
```

or visit [Temporal Installation](https://docs.temporal.io/cli#install) for more information.

You can install Docker using Homebrew

```sh
brew install docker
```

or visit [Docker Installation](https://docs.docker.com/get-docker/) for more information.

### Other platforms

Please check the official documentation for the installation of Java, Temporal, and Docker for your platform.

### Stripe API Keys

Sign up for a Stripe account and get your API keys from the [Stripe Dashboard](https://dashboard.stripe.com/apikeys).
Then in `application.properties` file add the following line with your secret key.

```properties
stripe.api-key=sk_test_51J3j
```

## Run

You are required to first start the temporal server using the following command

```sh
temporal server start-dev
```

and then run the application using the following command or using your IDE.

```sh
./gradlew bootRun
```

### Other commands

#### Lint
To run lint checks, use the following command

```sh
./gradlew sonarlintMain
```

#### Code Formatting
To format the code, use the following command

```sh
./gradlew spotlessApply
```

## Steps to Set Up the Project
1)Clone the Repository: Clone the project repository from GitHub:
```sh
git clone <repository_url>
```
2)Navigate to Project Directory: Open a terminal and navigate to the project directory:
```sh
cd <project_directory>
```
3)Configure Stripe API Credentials:
-Open the application.properties file located in src/main/resources.
-Add your Stripe API credentials:
```sh
stripe.api.key=YOUR_STRIPE_API_KEY
```
4)Build the Project: Use Gradle to build the project:
```sh
./gradlew build
```

### How to Run Tests
# Unit Tests
To run unit tests, execute the following command:
```sh
./gradlew test
```
This command will run all unit tests in the project.

# Integration Tests
To run integration tests, execute the following command:
```sh
./gradlew integrationTest
```
Integration tests verify the interaction between different components of the system.

### Implementation Approach and Assumptions
## Implementation Approach
-Modular Structure: The project follows a modular structure with separate components for controllers, services, repositories, models, and providers.
-Testing Strategy: Unit tests are written using JUnit and Mockito to test individual components in isolation. Integration tests cover interactions between different components, such as controllers and services.
-Gradle for Build Automation: Gradle is used as the build automation tool to compile, test, and package the project.

## Assumptions
-Stripe API Credentials: It is assumed that valid Stripe API credentials are provided in the application.properties file for integration tests to run successfully.
-Signup Process: The signup process involves creating a new account with basic details such as first name, last name, and email. Additional validation and error handling may be required based on specific business requirements.
-GET /accounts Endpoint: The GET /accounts endpoint returns a list of user accounts, including newly created accounts. This assumes that accounts are persisted successfully in the database.

