# Ecommerce Store

A full-stack e-commerce web application built with **Spring Boot 4**, **Thymeleaf**, and **PostgreSQL**. Users can browse products, leave reviews, and manage their accounts. Authenticated users can also create and manage products and categories.

---

## Tech Stack

| Layer        | Technology                                      |
|--------------|-------------------------------------------------|
| Backend      | Java 21, Spring Boot 4, Spring MVC, Spring Security |
| Persistence  | Spring Data JPA, Hibernate, PostgreSQL          |
| Frontend     | Thymeleaf, Bootstrap 5                          |
| Build        | Maven 3.9                                       |
| DevOps       | Docker, Docker Compose                          |
| Utilities    | Lombok                                          |

---

## Features

- **Product catalog** — paginated list of products with sorting (by price, name, etc.) in ascending or descending order
- **Search** — case-insensitive product search by name
- **Product detail page** — full product information with user reviews
- **Product management** — create, update, and delete products (authenticated users only)
- **Category management** — add product categories
- **Review system** — submit star-rated reviews (1–5 stars) with comments on any product
- **User authentication** — registration, login/logout via Spring Security with BCrypt password hashing
- **Auto-login after registration** — users are automatically signed in after creating an account
- **Seed data** — 4 categories and 16 sample products with reviews are pre-loaded on startup

---

## Project Structure

```
src/main/java/by/nurbolat/ecommerce/
├── config/
│   └── SecurityConfig.java          # Spring Security filter chain, BCrypt bean
├── controller/
│   ├── ProductController.java        # CRUD + paginated listing with search & sort
│   ├── CategoryController.java       # Add new categories
│   ├── ReviewController.java         # Add product reviews
│   └── UserController.java           # Registration and login pages
├── db/
│   ├── entity/
│   │   ├── Product.java              # Product entity (name, price, imageUrl, category, reviews)
│   │   ├── Category.java             # Category entity (name, createdAt)
│   │   ├── Review.java               # Review entity (rating enum, comment, reviewOwner)
│   │   ├── Rating.java               # Enum: ONE_STAR … FIVE_STAR
│   │   ├── User.java                 # User entity (name, email, password, role)
│   │   └── UserRoles.java            # Enum: USER, ADMIN (with Spring authority mapping)
│   └── repository/
│       ├── ProductRepository.java    # JPA repo + ILIKE search query
│       ├── CategoryRepository.java
│       ├── ReviewRepository.java
│       └── UserRepository.java       # findUserByEmailIgnoreCase
├── dto/
│   ├── UserCreateDto.java            # Record: name, email, password
│   └── UserReadDto.java              # Record: name, email
├── exception/
│   ├── ProductNotFoundException.java
│   ├── CategoryNotFoundException.java
│   └── ReviewNotFoundException.java
├── mapper/
│   ├── Mapper.java                   # Generic mapper interface
│   └── UserMapper.java               # Maps UserCreateDto → User entity
└── service/
    ├── ProductService.java           # + paginated/sorted/searched listing, page numbers list
    ├── CategoryService.java
    ├── ReviewService.java
    ├── UserService.java
    └── impl/
        ├── ProductServiceImpl.java
        ├── CategoryServiceImpl.java
        ├── ReviewServiceImpl.java
        └── UserServiceImpl.java      # Implements UserDetailsService for Spring Security

src/main/resources/
├── application.yaml                  # DB config (Docker profile active by default)
├── data.sql                          # Seed data for categories, products, reviews
└── templates/
    ├── index.html                    # Product listing with pagination, search, sort
    ├── card-details.html             # Single product view with reviews
    ├── add-product.html              # New product form
    ├── update-product.html           # Edit product form
    ├── add-category.html             # New category form
    ├── add-review.html               # New review form
    ├── login.html                    # Login page
    ├── registration.html             # Registration page
    ├── navbar.html                   # Shared navigation fragment
    ├── pagination.html               # Pagination fragment
    ├── sort.html                     # Sort controls fragment
    └── links.html                    # CSS/JS link fragment
```

---

## Data Model

```
Category
  id          BIGINT PK
  name        VARCHAR
  created_at  TIMESTAMP

Product
  id            BIGINT PK
  name          VARCHAR
  image_url     VARCHAR
  price         FLOAT
  categories_id BIGINT FK → Category
  created_at    TIMESTAMP

Review
  id            BIGINT PK
  rating        ENUM (ONE_STAR … FIVE_STAR)
  review_owner  VARCHAR
  comment       VARCHAR
  created_at    DATE
  products_id   BIGINT FK → Product

User
  id        BIGINT PK
  name      VARCHAR(20)
  email     VARCHAR(30) UNIQUE NOT NULL
  password  VARCHAR(90) NOT NULL
  roles     ENUM (USER, ADMIN)
```

---

## Security

| URL pattern               | Access             |
|---------------------------|--------------------|
| `GET /products`           | Public             |
| `GET /registration`       | Public             |
| `GET /login`              | Public             |
| All other routes          | Authenticated only |

- Passwords are hashed using **BCrypt**.
- Login uses the user's **email** as the username parameter.
- After successful registration, the user is automatically logged in via `SecurityContextHolder`.
- CSRF protection is **disabled** (suitable for development; enable in production).

---

## Running with Docker Compose (Recommended)

**Prerequisites:** Docker and Docker Compose installed.

1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd Ecommerce-store
   ```

2. Create a `.env` file from the example:
   ```bash
   cp .env.example .env
   ```

3. Edit `.env` with your credentials:
   ```env
   DB_PASSWORD=your_db_password
   DB_USER=your_db_user
   DB_NAME=postgres
   ```

4. Build and start all services:
   ```bash
   docker compose up --build
   ```

5. The application will be available at **http://localhost:8080**.

The backend waits for the PostgreSQL health check to pass before starting, so the database is guaranteed to be ready on first boot. The schema is created and seed data is inserted automatically.

---

## Running Locally (Without Docker)

**Prerequisites:** Java 21, Maven 3.9+, PostgreSQL running locally.

1. Create a PostgreSQL database:
   ```sql
   CREATE DATABASE ecommerce;
   ```

2. Update `src/main/resources/application.yaml` — uncomment the local profile block and update credentials:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/ecommerce
       username: postgres
       password: your_password
   ```

3. Build and run:
   ```bash
   mvn clean spring-boot:run
   ```

4. Open **http://localhost:8080/products**.

---

## API / Routes Reference

| Method | URL                              | Description                             | Auth required |
|--------|----------------------------------|-----------------------------------------|---------------|
| GET    | `/products`                      | Product listing (search, sort, paginate) | No           |
| GET    | `/products/{id}`                 | Product detail with reviews             | No            |
| GET    | `/products/add`                  | Add product form                        | Yes           |
| POST   | `/products/add`                  | Submit new product                      | Yes           |
| GET    | `/products/update/{id}`          | Edit product form                       | Yes           |
| POST   | `/products/update`               | Submit product update                   | Yes           |
| POST   | `/products/delete/{id}`          | Delete product                          | Yes           |
| GET    | `/products/{id}/add-review`      | Add review form                         | Yes           |
| POST   | `/products/{id}/add-review`      | Submit review                           | Yes           |
| GET    | `/categories/add`                | Add category form                       | Yes           |
| POST   | `/categories/add`                | Submit new category                     | Yes           |
| GET    | `/registration`                  | Registration page                       | No            |
| POST   | `/registration`                  | Register new user                       | No            |
| GET    | `/login`                         | Login page                              | No            |
| POST   | `/logout`                        | Logout                                  | Yes           |

**Query parameters for `GET /products`:**

| Param        | Default | Description                              |
|--------------|---------|------------------------------------------|
| `search`     | —       | Filter products by name (case-insensitive) |
| `page`       | `0`     | Page number (zero-indexed)               |
| `size`       | `3`     | Products per page                        |
| `sort_by`    | `price` | Field to sort by (`price`, `name`, etc.) |
| `sort_order` | `asc`   | Sort direction: `asc` or `desc`          |

---

## Seed Data

On startup (`ddl-auto: create-drop`), the database is populated with:

- **4 categories:** Electronics, Clothing, Books, Home
- **16 products:** 4 per category with real image URLs
- **8 reviews** across selected products

> ⚠️ `ddl-auto: create-drop` drops and recreates the schema on every restart. For a persistent database, switch to `update` and keep the local profile configuration.

---

## Environment Variables (Docker)

| Variable                      | Description              |
|-------------------------------|--------------------------|
| `DB_PASSWORD`                 | PostgreSQL password       |
| `DB_USER`                     | PostgreSQL username       |
| `DB_NAME`                     | PostgreSQL database name  |
| `SPRING_DATASOURCE_URL`       | Set automatically by Compose |
| `SPRING_DATASOURCE_USERNAME`  | Set automatically by Compose |
| `SPRING_DATASOURCE_PASSWORD`  | Set automatically by Compose |

---

## Author

Developed by **Nurbolat**.
