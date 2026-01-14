Online Food Delivery System (Java)

A **console-based Java application** that simulates an **online food delivery system**, supporting **Admin** and **Customer** roles.
The system allows managing restaurants, food items, delivery personnel, orders, and order statuses using clean **object-oriented design**.

---

Features

###  Admin Module

* Secure admin login
* Add new restaurants
* Add food items to restaurants
* Add delivery persons
* View all orders
* Update order status

###  Customer Module

* Customer registration & login
* View available restaurants and food items
* Place food orders
* Make payments
* View order details and status

---

Project Structure

```
OnlineFoodDeliverySystem
│
├── src
│   ├── com.fooddelivery.features
│   │   ├── Cart.java
│   │   ├── DeliveryPerson.java
│   │   ├── DeliveryService.java
│   │   └── FeaturesOrder.java
│   │
│   ├── com.fooddelivery.model
│   │   ├── Cart.java
│   │   ├── Customer.java
│   │   ├── DeliveryPerson.java
│   │   ├── FoodItem.java
│   │   ├── Order.java
│   │   ├── PaymentMethod.java
│   │   ├── PaymentStatus.java
│   │   ├── Restaurant.java
│   │   └── User.java
│   │
│   ├── com.fooddelivery.service
│   │   └── Main.java
│   │
│   └── module-info.java
│
├── JRE System Library [JavaSE-21]
└── README.md
```

---

Technologies Used

* **Java (JDK 21)**
* **Eclipse IDE**
* **Object-Oriented Programming (OOP)**
* **Collections Framework**
* **Scanner (Console Input)**

---

How to Run the Project

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/online-food-delivery-system.git
   ```

2. **Open in Eclipse**

   * File → Import → Existing Projects into Workspace
   * Select the cloned folder

3. **Run the application**

   * Navigate to:
     `src → com.fooddelivery.service → Main.java`
   * Right-click → **Run As → Java Application**

---

Default Admin Credentials

```
Username: Admin
Password: admin123
```

*(You can modify this in `Main.java`)*

---

Sample Workflow

1. Admin logs in
2. Admin adds restaurant & food items
3. Customer registers/logs in
4. Customer browses menu
5. Customer places an order
6. Admin updates order status
7. Customer views order progress

---

Learning Outcomes

* Java OOP principles
* Package-based project structure
* Menu-driven console applications
* Real-world application modeling
* Clean code and modular design

---

Future Enhancements

* File or database persistence (MySQL)
* Spring Boot REST API
* Web or mobile frontend
* Role-based authentication
* Order tracking system

---

Author

**Abdul Mateen**
Final Year B.Tech (Information Technology)
Aspiring Java / Backend Developer

---

License

This project is for **learning and educational purposes**.
You are free to use and modify it.

---
