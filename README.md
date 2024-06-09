## APPLICATION SETTINGS

DEFAULT APPLICATION PORT - 9999
***************
# PRESENTATION MODE
For presentation mode (used dev profile) - you do not need additional configuration.
* Presentation user username = "john_doe"
* Presentation user password = "password"

This user has 10 shortened urls.
* * localhost:9999/urls/abc123
* * localhost:9999/urls/def456
* * localhost:9999/urls/ghi789
* * localhost:9999/urls/jkl012
* * localhost:9999/urls/mno345
* * localhost:9999/urls/pqr678
* * localhost:9999/urls/stu901
* * localhost:9999/urls/vwx234
* * localhost:9999/urls/yzu567
* * localhost:9999/urls/123abc
***************
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
* ADDRESS-your server address for link shorting -> SERVER_ADDRESS=yourServerAddress


# HOW TO MAKE A DOCKER IMAGE AND START WITH COMPOSE
- First configure DOCKER COMPOSE FILES FOR YOUR NEEDS
- EXEC gradle bootJar to make a project jar
- COPY jar to docker dir
- RUN DOCKER-COMPOSE.YML