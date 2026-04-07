# Database Schema Documentation

## Tables

### Users
- **id**: Primary key
- **username**: Unique username
- **email**: User email address
- **created_at**: Timestamp when the user was created
- **updated_at**: Timestamp when the user was last updated

### Products
- **id**: Primary key
- **name**: Product name
- **description**: Brief description of the product
- **price**: Price of the product
- **created_at**: Timestamp when the product was added
- **updated_at**: Timestamp when the product was last updated

### Orders
- **id**: Primary key
- **user_id**: Foreign key referencing Users
- **product_id**: Foreign key referencing Products
- **quantity**: Quantity of the product ordered
- **created_at**: Timestamp when the order was created
- **updated_at**: Timestamp when the order was last updated

## Relationships
- **Users** to **Orders**: One-to-Many (One user can have multiple orders)
- **Products** to **Orders**: One-to-Many (One product can be in multiple orders)

## Constraints
- **Users.username**: Unique constraint
- **Orders.user_id**: Foreign key constraint that references `Users.id`
- **Orders.product_id**: Foreign key constraint that references `Products.id`

## Indexes
- Index on `Users.email` for faster lookups by email
- Index on `Orders.user_id` for faster query of orders by user
- Index on `Orders.product_id` for faster query of orders by product

---

This document outlines the database schema for the Speed repository, detailing the structure of tables, their relationships, constraints, and indexing to ensure data integrity and optimization.