# LearnHub

LearnHub is a Spring Boot–based online learning platform that allows instructors to create courses and students to enroll, learn, and interact through comments.
The platform supports JWT-based authentication, role-based access, and RESTful APIs.

---

## Features

- User registration and login  
- Instructors can create courses  
- Instructors can add lessons  
- Students can enroll in courses  
- Users can comment on courses  
- Admin can manage everything  

---

## Roles

### USER
- Can enroll in courses  
- Can view courses & lessons  
- Can comment  

### INSTRUCTOR
- Can create courses  
- Can add lessons  
- Can update and delete own courses  

### ADMIN
- Can delete anything (users, courses, comments)  

---
## Project Structure
│── controller       # Handles HTTP requests and routes them to the service layer
│── service          # Contains business logic for courses, lessons, comments, etc.
│── repository       # Interfaces to interact with the database (Spring Data JPA)
│── dto              # Data Transfer Objects: objects used to transfer data between layers safely
│── mapper           # MapStruct mappers: convert between Entity <-> DTO
│── security         # JWT authentication and security configurations
│── model            # Entity classes representing database tables
└── exception        # Custom exceptions for handling errors (e.g., CourseNotFoundException)

## 🛠 Tech Stack
| Type | Technology |
|-------|------------|
| Language | Java 17+ |
| Framework | Spring Boot |
| Security | JWT & Spring Security |
| Database | MySQL |
| Validation | Jakarta Validation |
| Mapping | MapStruct |
| Build Tool | Maven |

## How to Setup The Project

1. Clone the project  
```bash
git clone <your-repo-url>
cd LearnHub
