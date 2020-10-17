gnome-terminal --maximize \
--tab -e "bash -c 'cd tax-spring-backend; mvn clean spring-boot:run; exec bash'" \
--tab -e "bash -c 'cd tax-vue-frontend; npm run serve; exec bash'";
cd nginx-proxy; sh applyConfigAndRestart.sh;
