@echo off

rem Set APP_PROFILE
set "APP_PROFILE=prod"

rem Set DATASOURCE_URL
set "DATASOURCE_URL=jdbc:postgres://{host}:{port}/{database}"

rem Set DATASOURCE_USERNAME
set "DATASOURCE_USERNAME=yourDatabaseUser"

rem Set DATASOURCE_PASSWORD
set "DATASOURCE_PASSWORD=yourDatabaseUserPassword"

rem Set JWT_TOKEN
set "JWT_TOKEN=yourToken"

rem Set SERVER_ADDRESS
set "SERVER_ADDRESS=yourServerAddress"

echo Environment variables set:
echo APP_PROFILE=%APP_PROFILE%
echo DATASOURCE_URL=%DATASOURCE_URL%
echo DATASOURCE_USERNAME=%DATASOURCE_USERNAME%
echo DATASOURCE_PASSWORD=%DATASOURCE_PASSWORD%
echo JWT_TOKEN=%JWT_TOKEN%
echo ADDRESS=%ADDRESS%

