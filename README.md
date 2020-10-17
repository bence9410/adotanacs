# Website for Erzsébet Németh tax expert.

## Available at: adotanacs.com

### Requirements:

- OpenJDK-11
- Maven 3
- npm 6

### Download dependencies:

- run 'npm install' in tax-vue-frontend to download frontend dependencies
- run 'mvn clean package -DskipTests' in tax-spring-backend to download backend dependencies

### Development:

- install nginx with 'sudo apt-get install nginx' and disable virtual host with 'sudo unlink /etc/nginx/sites-enabled/default' for our script to work.
- run 'devRunAll.sh' to start the frontend, the backend and the nginx proxy in local, the proxy will listen on port 80, and will forward '/api/' to the backend and other requests to the frontend.

### Production:

- The user and password for sending e-mail is missing from the backend's application.yml, do not commit it to the repository!
- The script 'prodBuild.sh' builds the frontend, then copies the files from the frontend's dist folder to the backend's resources/public folder and builds the backend. The standalone runnable jar will be in the backend's target folder.
