## APPLICATION SETTINGS

DEFAULT APPLICATION PORT - 9999

## ENVIRONMENT VARIABLES:

### For configuration environment variables:
### Need to change variables in these files first, after that you can run this script in your system

* Use set_env_variables.bat for Windows
* Use set_env_variables.sh for linux

### DEVELOPMENT 
***

* No additional configuration needed

### PRODUCTION
***

* APP_PROFILE-to change profile from dev -> make environment variable APP_PROFILE=prod
* DATASOURCE_URL-datasource url for postgres database -> DATASOURCE_URL=jdbc:postgres://{host}:{port}/{database} -> 
host - your database host, port - your database port, database - your database name
* DATASOURCE_USERNAME-datasource username -> DATASOURCE_USERNAME=yourDatabaseUser
* DATASOURCE_PASSWORD-datasource user password -> DATASOURCE_PASSWORD=yourDatabaseUserPassword
* JWT_TOKEN-secret token to generate jwt tokens for users and validate them -> JWT_TOKEN=yourToken
