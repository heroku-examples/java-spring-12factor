# Demo 1 - Run
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
# Side Track: /swagger-ui/index.html and self describing services

# Demo 2 - Build, Release, Run
heroku create
heroku addons:create heroku-postgresql:essential-2 && heroku addons:create heroku-redis:mini

# Demo 3 - Test Stateless Services and External Backing Services
git push heroku main 
heroku ps:type web=standard-1x && heroku ps:scale web=2
heroku info
# /api/session/test observe host id value and session counter
heroku open
# open browser console and drag notes and observe host id value

# Demo 4- Build and run Production locally in a Container
pack build java-spring-12factor --builder heroku/builder:24
heroku run env > .env
docker run --rm -t -p 8080:8080 --env-file .env -e PORT=8080 -e SPRING_PROFILES_ACTIVE=prod java-spring-12factor:latest
