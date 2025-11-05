# 🏭 WMS Light System (Warehouse Management System)

A lightweight **Warehouse Management System (WMS)** built entirely in **Java**.  
It manages product inventory, supports file-based persistence, and includes both a **command-line interface** and a **Swing GUI**.

---

## 🚀 Features

### Core Functions
- **Add / Remove / Search Products**
- **EXPORT and IMPORT data from CSV file**
- **View Inventory** in a formatted list or GUI table
- **Low-Stock Report** – show items below a user-defined threshold
- **Duplicate Checks** – prevent duplicate article numbers or names
- **Persistent Storage** – products saved and loaded from a CSV file
- **GUI Dashboard** – simple Swing interface for viewing and managing stock

### Technical Highlights
- Object-Oriented Design  
  - `Products` → Data model  
  - `InventoryManager` → Business logic / controller  
  - `Main` → Console interface  
  - `WarehouseGUI` → Graphical interface  
- File I/O with error handling (BufferedReader, PrintWriter)
- Safe CSV parsing with validation for malformed lines
- Uses MVC-style structure (Model–View–Controller)

---

## 🧰 Technologies Used
Java 17 (or any version ≥ 11)
Swing (javax.swing)
Standard Java I/O (java.io)
Collections Framework (ArrayList) 

## GUI Preview: 

<img width="1708" height="989" alt="Screenshot 2025-11-05 at 20 20 10" src="https://github.com/user-attachments/assets/8c80d2f3-e3ef-4d31-91f7-7016a3ee35c3" />
