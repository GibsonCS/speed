```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#ffcc00', 'edgeColor': '#ffcc00', 'tertiaryColor': '#ffffff', 'borderRadius': '5', 'fontFamily': 'Arial'}}}%%
classDiagram
    class User {
        +int id
        +string name
        +string email
    }
    class Product {
        +int id
        +string title
        +float price
    }
    class Order {
        +int id
        +date orderDate
    }
    User "1" -- "*" Order : places
    Order "*" -- "*" Product : contains
```  

# Detailed Table Descriptions 

| Table Name | Description | Column Names | Column Descriptions |
|------------|-------------|--------------|---------------------|
| User       | Stores user information | id, name, email | id: Unique identifier for the user; name: User's name; email: User's email address |
| Product    | Stores product information | id, title, price | id: Unique identifier for the product; title: Name of the product; price: Product price |
| Order      | Stores order information | id, orderDate | id: Unique identifier for the order; orderDate: Date the order was placed |

## Logical Data Model

This diagram reflects the logical data model of the application, showing the relationship between users, products, and orders.