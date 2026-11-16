# Console Warehouse Simulator

A console-based warehouse and product assembly line simulator built in Java, demonstrating core principles of multithreading and concurrent programming (Java Concurrency).

## Overview

The application models the lifecycle of products inside a warehouse with multiple threads operating concurrently:
- **Suppliers (`SupplierTask`)** — generate new products via a factory (`ProductsFactory`) and deliver them to the warehouse (`Warehouse`), handling overflow or supply errors (`SupplierException`).
- **Packers (`PackerTask`)** — retrieve products from the warehouse and perform assembly/packaging operations.
- **Warehouse (`Warehouse`)** — a thread-safe storage unit that synchronizes concurrent access between suppliers and packers.

## Project Structure

- `Runner.java` — application entry point responsible for initializing and coordinating threads.
- `product/Product.java` — domain model representing a product.
- `products/factory/ProductsFactory.java` — factory class for instantiating product objects.
- `warehouse/Warehouse.java` — storage logic and thread-safe resource synchronization.
- `tasks/supplier/` — supplier worker tasks and custom exception handling (`SupplierTask`, `SupplierException`).
- `tasks/packer/` — packer worker tasks (`PackerTask`).

## Technologies & Concepts

- **Language:** Java
- **Core Concepts:** Multithreading, Thread Synchronization, Producer–Consumer Pattern, Factory Pattern, Custom Exception Handling.

## Getting Started

1. Clone the repository:
   ```bash
   git clone [https://github.com/itPrince1337/ConsoleWarehouseSimulator.git](https://github.com/itPrince1337/ConsoleWarehouseSimulator.git)
