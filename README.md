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

## How to Setup The Project

1. Clone the project  
```bash
git clone <your-repo-url>
cd LearnHub
