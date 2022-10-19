# jpa-client

To run this project locally, it is required:
- JDK 17
- Docker

Clone the repo:
````
cd /directory
git clone https://github.com/rodrigoorf/jpa-client.git
cd /jpa-client
````

Execute the following command:
````
docker-compose up -d
````
It will start a PostgreSQL database inside a container at port 5432. It creates the tables and inserts some information inside the database.
After the container starts, then run:

````
./gradlew bootRun
````

Wait for the application to start. Then, navigate to http://localhost:8080/swagger-ui/index.html. Enjoy!