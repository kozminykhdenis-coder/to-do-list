# To-Do List API

REST API для управления задачами на Spring Boot + PostgreSQL.

## Стек
- Java 21, Spring Boot 4.1.1
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Docker

## Функционал
- `GET /tasks` — все задачи (фильтр `?completed=true|false`)
- `GET /tasks/{id}` — задача по id
- `POST /tasks` — создать
- `PUT /tasks/{id}` — обновить
- `DELETE /tasks/{id}` — удалить

## Запуск

### 1. Postgres в Docker
docker run --name my-postgress -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_DB=tododb -p 5432:5432 -d postgres:18

### 2. Переменные окружения
Перед запуском задай:
DB_USER=postgres
DB_PASSWORD=mysecretpassword

### 3. Запуск приложенияg
./mvnw spring-boot:run
Приложение: http://localhost:8080

## Пример запроса
curl -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d "{"title":"Купить хлеб","description":"И молоко","completed":false}"

## Тесты
- Unit-тесты на `TaskService` (Mockito)
- Интеграционные тесты на `TaskContoller` (`@WebMvcTest` + `MockMvc`)

Запуск
./mvnw test
