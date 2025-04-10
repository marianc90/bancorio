# HomeBaking Application

HomeBaking is a Java-based application designed to manage banking operations such as account management, transactions, and card operations. The project follows Object-Oriented Programming (OOP) principles to ensure modularity, maintainability, and scalability.
This project is only for academic pourposes.

## Features

- **Account Management**: Create, update, delete, and list bank accounts.
- **Transaction Management**: Perform various types of transactions, including transfers, payments, and adjustments.
- **Card Management**: Manage credit and debit cards.
- **User Interface**: A graphical user interface (GUI) for managing operations.
- **Database Integration**: Uses H2 database for data persistence.

## Technologies Used

- **Programming Language**: Java
- **Database**: H2 Database
- **Frameworks**: Swing for GUI
- **Build Tool**: Maven
- **IDE**: IntelliJ IDEA

## Project Structure
src/ ├── homebaking/ │ ├── dao/ # Data Access Object interfaces │ ├── exceptions/ # Custom exception classes │ ├── h2Impl/ # H2 database implementations │ ├── model/ # Domain models (e.g., Cuenta, Movimiento, Tarjeta) │ ├── service/ # Business logic services │ └── ui/ # User interface components └── resources/ └── DBManager.java # Database connection manager

## Key OOP Principles Applied

1. **Encapsulation**: Business logic is encapsulated in service classes like `MovimientoService`, `CuentaService`, and `TarjetaService`.
2. **Abstraction**: Abstract classes and interfaces, such as `AbstractPantallaAltaPanel` and `MovimientoDao`, provide a clear contract for implementation.
3. **Inheritance**: Class hierarchies are used to reuse and extend functionality.
4. **Polymorphism**: Interfaces and their implementations allow flexibility and extensibility.
5. **Single Responsibility Principle**: Each class has a well-defined responsibility.

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/homebaking.git

   Open the project in IntelliJ IDEA.
1-Build the project using Maven.
2-Run the application from the main class.
3-Database Setup

The application uses an H2 database. Ensure the database is properly configured in the DBManager class.  
Example Usage

Create an Account: Use the GUI to input account details and save them to the database.
Perform a Transaction: Select the transaction type, fill in the required fields, and execute the operation.
View Accounts: List all accounts or filter by user.

Contributing
Contributions are welcome! Please follow these steps:  

Fork the repository.
Create a new branch for your feature or bug fix.
Commit your changes and push them to your fork.
Submit a pull request.

License
This project is licensed under the MIT License. See the LICENSE file for details.  
