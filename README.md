
# Class Diagram

```mermaid
classDiagram
    class User {
        <<Aggregate Root>>
        -UUID id
        -String name
        -String lastname
        -String email
        -String encryptedPassword
        -List~UUID~ roleIds
    }

    class Role {
        <<Aggregate Root>>
        -UUID id
        -String name
        -List~String~ permissions
    }

    class Client {
        <<Aggregate Root>>
        -UUID id
        -UUID createdByUserId
        -String cnpj
        -String companyName
        -Phone phone
        -String email
        -Address address
    }

    class Order {
        <<Aggregate Root>>
        -UUID id
        -UUID clientId
        -UUID userId
        -OrderStatus status
        -Instant orderDate
        -List~Event~ events
    }

    class Event {
        <<Entity>>
        -UUID serviceId
        -Address executionAddress
        -EventStatus status
        -Instant eventDate
        -BigDecimal chargedPrice
    }

    class Service {
        <<Aggregate Root>>
        -UUID id
        -String name
        -BigDecimal price
        -String description
        -ServiceStatus status
    }

    class Address {
        <<Value Object>>
        -String street
        -String neighborhood
        -Character UF
        -String zipCode
    }

    class Phone {
        <<Value Object>>
        -String number
    }

    User "1" --> "*" Role : has (via roleIds)
    Client "*" --> "1" User : created by
    Order "*" --> "1" User : created by
    Order "*" --> "1" Client : belongs to
    Order "1" *-- "*" Event : contains
    Event "*" --> "1" Service : references
```
