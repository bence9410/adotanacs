#email user and pass is missing from application.yml, do not commit it to public repository!
cd tax-vue-frontend; npm run build; cd ..;
rm -r -f tax-spring-backend/src/main/resources/public/*;
mkdir tax-spring-backend/src/main/resources/public/
cp -r tax-vue-frontend/dist/* tax-spring-backend/src/main/resources/public/;
cd tax-spring-backend; mvn clean package -DskipTests;
