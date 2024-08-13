# URL Shortener API

This project is a URL Shortener service built with Spring Boot and Java 17. It provides endpoints for generating shortened URLs and redirecting users to the original URLs.

## Features

- Shorten URLs with an optional expiration date.
- Allow users to specify a custom short URL (up to 20 characters).
- Automatically generate a unique short URL if no custom URL is provided.
- Redirect users to the original URL when they visit the shortened link.

## Prerequisites

- Java 17
- Maven
- MySQL Database

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/PX201/url-shortener-api.git
cd url-shortener-api
```

### 2. Configure the Database

Update the `application.properties` file with your MySQL database configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your-database
spring.datasource.username=your-username
spring.datasource.password=your-password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Build and Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

### 1. Shorten a URL

**Endpoint**: `/shorten`

**Method**: `POST`

**Request Body**: JSON

```json
{
  "originUrl": "https://www.example.com",
  "expDate": "2024-12-31",
  "customShortUrl": "mycustomurl"
}
```

**Parameters**:
- `originUrl` (required): The original URL to be shortened.
- `expDate` (optional): Expiration date for the short URL. The default expiration date is two days after the creation.
- `ShortUrl` (optional): Custom short URL (up to 20 characters). If not provided, a unique short URL will be generated.

**Response**: JSON

```json
{
  "id": 101,
  "shortUrl": "http://localhost:8080/r/mycustomurl",
  "originUrl": "https://www.example.com",
  "expirationDate": "2024-12-31",
  "createdAt": "2024-12-30",
  "clickCount": 0

}
```

### 2. Redirect to the Original URL

**Endpoint**: `/r/{shortlink}`

**Method**: `GET`

**Description**: Redirects to the original URL associated with the short link.

## Dependencies

The project uses the following dependencies:

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- MySQL Connector/J
- Spring Boot Starter Test
- Spring Boot Starter Validation
- Apache Commons Codec

## License

This project is licensed under the MIT License.
