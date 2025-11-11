# 🧩 Spring Boot Product Management (JWT + Thymeleaf UI)

Project ini adalah aplikasi **Product Management sederhana** menggunakan **Spring Boot**, **Spring Security JWT**, **JPA (PostgreSQL/H2)**, dan **Thymeleaf UI (Bootstrap)**.  
Aplikasi ini memiliki dua bagian utama:

1. **REST API Backend** — untuk register, login, dan CRUD produk.
2. **Thymeleaf UI Frontend** — halaman sederhana untuk login/register dan manajemen produk.

---

## 🚀 Tech Stack

**Backend:**
- Java 17
- Spring Boot 3.x
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- PostgreSQL / H2 Database
- Maven

**Frontend (UI):**
- Thymeleaf Template Engine
- Bootstrap 5
- Vanilla JavaScript (Fetch API)

---

### 1 Clone Project
```bash
git https://github.com/elvinhndrwn/product-service.git
cd product-service
```

### 2 Clone Project
```bash
mvn clean install
```

### 3 Run Application
```bash
mvn spring-boot:run
```

### 4 Access UI
- 🔑 Register Page → http://localhost:8080/register
- 🔑 Login Page → http://localhost:8080/login
- 🔑 Products Page → http://localhost:8080/products-ui