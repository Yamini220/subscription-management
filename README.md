# Recurring Revenue & Subscription Lifecycle Management Engine

A full-stack subscription management application built using Spring Boot, PostgreSQL, HTML, CSS and JavaScript.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- HTML
- CSS
- JavaScript
- Maven
- Git & GitHub

## Features

### Admin Login
- Admin login page
- Simple username and password authentication

### Dashboard
- Total customers
- Active subscriptions
- Monthly Recurring Revenue (MRR)
- Expiring subscriptions
- Subscription status
- Revenue overview
- Notifications

### Customer Management
- Add customer
- Edit customer
- Delete customer
- Search customers

### Plan Management
- Add plan
- Edit plan
- Delete plan
- Basic, Pro and Enterprise plans

### Subscription Management
- Create subscription
- Edit subscription
- Renew subscription
- Cancel subscription
- Reactivate subscription
- Delete subscription
- Automatic Active / Upcoming / Expired status
- Automatic expiry detection

## Project Structure

```text
subscription-management
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.klu
│   │   │       ├── controller
│   │   │       ├── repository
│   │   │       ├── Admin.java
│   │   │       ├── Customer.java
│   │   │       ├── Plan.java
│   │   │       ├── Subscription.java
│   │   │       └── SubscriptionManagementApplication.java
│   │   │
│   │   └── resources
│   │       ├── static
│   │       └── application.properties
│
├── pom.xml
├── .gitignore
└── subscription_management.sql