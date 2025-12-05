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
### Security 🔐
- JWT Authentication  
- Password hashing using BCrypt  
- Role-based access control  

## 🔑 Default Roles

| Role | Permissions |
|-------|-------------|
| ADMIN | Can delete anything |
| INSTRUCTOR | Create courses, add lessons, manage their courses |
| USER | Enroll, comment, view courses |
---
## Project Structure
│── controller       
│── service          
│── repository       
│── dto             
│── mapper           
│── security         
│── model           
└── exception       

## 🛠 Tech Stack
| Type | Technology |
|-------|------------|
| Language | Java  |
| Framework | Spring Boot |
| Security | JWT & Spring Security |
| Database | PostgreSQL |
| Validation | Jakarta Validation |
| Mapping | MapStruct |
| Build Tool | Maven |
## 🔑 Default Roles

| Role | Permissions |
|-------|-------------|
| STUDENT | Enroll, comment, view courses |
| INSTRUCTOR | Create courses, add lessons, manage their courses |

## 📌 API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register user |
| POST | /auth/login | Login and receive JWT |

### Courses
| Method | Endpoint | Access |
|--------|----------|---------|
| POST | /courses | Instructor Only |
| GET | /courses | Public |
| GET | /courses/{id} | Public |

### Lessons
| Method | Endpoint | Access |
|--------|----------|---------|
| POST | /courses/{id}/lessons | Instructor Only |
| GET | /courses/{id}/lessons | Enrolled users |

### Comments
| Method | Endpoint |
|--------|----------|
| POST | /courses/{id}/comments |
| GET | /courses/{id}/comments |

