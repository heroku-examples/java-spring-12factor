# Demo 1 - Run
./mvnw spring-boot:run

# Demo 2 - Test Stateless
heroku create
heroku addons:create heroku-postgresql:essential-0
heroku addons:create heroku-redis:mini
git push heroku main 
heroku ps:type web=standard-1x && heroku ps:scale web=2
# /api/session/test observe host id value and session counter
# open browser console and drag notes and observe host id value

# Demo 3- Build and run in a Container
pack build java-spring-12factor --builder heroku/builder:24
heroku run env > .env
docker run --rm -t -p 8080:8080 --env-file .env -e PORT=8080 -e SPRING_PROFILES_ACTIVE=prod java-spring-12factor:latest
