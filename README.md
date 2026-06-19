# 🏨 Hotel Pattern Masters

## 👥 Integrantes del Grupo
* **Agostina Sol Lopez**
* **Leandro Corti**
* **Thiago Minian**

---

## 📌 Descripción del Proyecto
Gestionar un hotel implica coordinar muchas piezas al mismo tiempo: saber qué habitaciones están libres, qué huéspedes tienen reservas, qué servicios pidieron, cuánto deben pagar y cómo avisarles si algo cambia. 

Cuando todo eso se maneja de forma manual o con herramientas separadas, los errores son inevitables: se asigna una habitación que ya estaba ocupada, un huésped no se entera de que se liberó el lugar que estaba esperando, o el costo final se calcula mal porque no se tuvieron en cuenta los descuentos. 

El problema central no es solo la falta de herramientas, sino la **falta de integración**. Cada área trabaja con su propia información, y cuando ocurre un cambio en una parte, las demás no se enteran a tiempo. Este sistema resuelve dicha problemática mediante una arquitectura robusta, integrada y basada en patrones de diseño.

---

## Instrucciones para Ejecutar el Proyecto

Para levantar el servidor backend, ejecutá los siguientes comandos en tu terminal:

cd C:\Users\thiag\OneDrive\Escritorio\Documentos\GitHub\Pattern-Masters-The-Rebirth\hotel-app\backend
node server.js

Deberías ver el siguiente mensaje:

◇ injected env (6) from .env // tip: ⌘ multiple files { path: ['.env.local', '.env'] }
🏨 Hotel API corriendo en http://localhost:3001
✅ Conectado a MySQL/MariaDB


| Patrón | Tipo | Aplicación en el Proyecto |
| :--- | :--- | :--- |
| **Factory Method** | Creacional | Se utiliza para crear distintos tipos de habitaciones (*Standard*, *Suite* y *Premium*) sin depender de clases concretas, permitiendo extensiones futuras sin modificar el código core. |
| **Builder** | Creacional | Se emplea para construir objetos complejos como una `Reserva`, configurando paso a paso sus diferentes atributos (huésped, habitación, fechas, servicios adicionales y promociones). |
| **Decorator** | Estructural | Añade servicios adicionales a una estadía de manera dinámica (desayuno, spa, cochera) sin modificar la estructura de las clases base. |
| **Facade** | Estructural | Proporciona una interfaz simplificada a los distintos subsistemas del hotel, facilitando operaciones complejas como la gestión integrada de reservas, habitaciones y facturación. |
|
