# Get Started.

## First step.
Clone this repository to your local machine using git or download as ZIP file.

## Second step.
Verify that you have 8080 port free to handle this project, if not change this as you prefer in application-local.yaml.

## Third step.
Go to Neon or use Postgress to get the host, the username and password and set it as the following environment
variables:

DB_PASS = password from database.
DB_HOST = from connection string in Neon (or Postgress directly).
DB_USER = from Neon.
SPRING_PROFILES_ACTIVE=local.

## Fourth step.
Execute boot run task from Gradle.