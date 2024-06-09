#!/bin/bash
### Use script to define variables
# Set APP_PROFILE
export APP_PROFILE=prod

# Set DATASOURCE_URL
export DATASOURCE_URL=jdbc:postgres://host:port/database

# Set DATASOURCE_USERNAME
export DATASOURCE_USERNAME=yourDatabaseUser

# Set DATASOURCE_PASSWORD
export DATASOURCE_PASSWORD=yourDatabaseUserPassword

# Set JWT_TOKEN
export JWT_TOKEN=yourToken

# Set SERVER_ADDRESS
export SERVER_ADDRESS=yourServerAddress

echo "Environment variables set:"
echo "APP_PROFILE=$APP_PROFILE"
echo "DATASOURCE_URL=$DATASOURCE_URL"
echo "DATASOURCE_USERNAME=$DATASOURCE_USERNAME"
echo "DATASOURCE_PASSWORD=$DATASOURCE_PASSWORD"
echo "JWT_TOKEN=$JWT_TOKEN"
echo "ADDRESS=$ADDRESS"
