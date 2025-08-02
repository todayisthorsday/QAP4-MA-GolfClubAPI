# QAP4-MA-GolfClubAPI

A RESTful API for managing golf club members and tournaments. Easily add, update, and search members and tournaments — all wrapped up with Docker support for smooth setup and deployment.

## Supported Search APIs

### Member Search Endpoints

| Endpoint                              | Description                                  | Query Parameters           |
|-------------------------------------|----------------------------------------------|---------------------------|
| `GET /api/members/search/by-name`          | Search members by partial name (case-insensitive) | `name` (string)           |
| `GET /api/members/search/by-membership`    | Search members by membership type (case-insensitive) | `membershipType` (string) |
| `GET /api/members/search/by-phone`         | Search members by phone number                       | `phone` (string)          |
| `GET /api/members/search/by-start-date`    | Search members by start date (`yyyy-MM-dd`)           | `date` (string)           |
<img width="1277" height="841" alt="Screen Shot 2025-08-01 at 9 17 52 PM" src="https://github.com/user-attachments/assets/65e242f2-e0f3-401d-9807-2339f79feb9c" />
<img width="1276" height="1044" alt="Screen Shot 2025-08-01 at 9 20 07 PM" src="https://github.com/user-attachments/assets/82bd43f8-31df-4634-a3ce-96044628dc42" />
<img width="1281" height="738" alt="Screen Shot 2025-08-01 at 9 20 26 PM" src="https://github.com/user-attachments/assets/0f220956-02e2-47b5-9405-41615c6006ba" />



---

### Tournament Search Endpoints

| Endpoint                                        | Description                                   | Query Parameters             |
|------------------------------------------------|-----------------------------------------------|-----------------------------|
| `GET /api/tournaments/search/by-start-date`    | Search tournaments by start date (`yyyy-MM-dd`) | `date` (string)             |
| `GET /api/tournaments/search/by-location`      | Search tournaments by location (partial match, case-insensitive) | `location` (string)         |
| `GET /api/tournaments/{id}/members`             | Get all members participating in a tournament       | `id` (tournament ID, long)  |
<img width="1279" height="760" alt="Screen Shot 2025-08-01 at 9 21 14 PM" src="https://github.com/user-attachments/assets/4632ca37-8ca9-4ab1-90af-c33e44a667c5" />

---

## Running the Project in Docker

1. **Build the Docker image:**

```bash
docker build -t golfclub-api .
```
2. **Run MySQL container (if you don’t have one running):**
```
docker run --name golfclub-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=golfclubdb -p 3306:3306 -d mysql:8
```
3. **Run the API container and link to MySQL container:**
```
docker run --name golfclub-api-container -p 8080:8080 --link golfclub-mysql:mysql -d golfclub-api
```
4. **Access API at:**
```
http://localhost:8080/api
```
**Notes**
- Make sure your application.properties or application.yml points to the MySQL container host (mysql if using Docker linking).
- Use the exposed ports (8080 for API, 3306 for MySQL) to connect from outside Docker.
- 
<img width="1478" height="1100" alt="Screen Shot 2025-08-01 at 8 25 51 PM" src="https://github.com/user-attachments/assets/51d83e94-f66f-4c3c-96eb-008f0a923f70" />
