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
| **Strategy** | Comportamiento | Encapsula diferentes algoritmos de cálculo de precios, permitiendo aplicar distintas políticas según promociones, temporadas o categorías de huéspedes de forma dinámica. |
| **State** | Comportamiento | Modela los distintos estados de una reserva y de una habitación, permitiendo que su comportamiento y restricciones cambien según el estado actual en el que se encuentren. |
| **Observer** | Comportamiento | Implementa el sistema de notificaciones automáticas para informar a los huéspedes cuando ocurren cambios relevantes en sus reservas o cuando se libera un lugar. |
| **Mediator** | Comportamiento | Centraliza la comunicación entre los componentes del sistema, reduciendo drásticamente el acoplamiento directo entre reservas, habitaciones y huéspedes. |
| **Template Method** | Comportamiento | Define el flujo general de procesos comunes (como el cálculo de facturación o gestión de estadías), permitiendo que ciertos pasos específicos sean personalizados por las subclases. |

## 📈 Distribución de Tareas

Agostina: He evaluado y definido los patrones tanto creacionales, como estructurales y de comportamiento a emplear en el proyecto del sistema hotelero, así como también establecí los requerimientos funcionales y no funcionales con los que debe cumplir. Por otra parte, también he integrado los conocimientos adoptados en POO para detectar los patrones GRASP y principios SOLID que aplican en este caso de estudio. 
A su vez, contribuí con porciones del código que estamos desarrollando para construir el programa; el cual se encuentra en el repositorio de GitHub.

Leandro: He tomado la responsabilidad de realizar el diagrama de clases UML definiendo cuáles son las clases a utilizar y cómo se conectan entre ellas. Además he definido los casos de uso para el sistema y modelado un diagrama de dos de ellos considerados como los más importantes.
De la misma manera realicé un análisis del contexto y alcance del proyecto para definir cuáles son los mejores patrones de diseño que se adaptan a nuestras necesidades y seleccionarlos para que luego los implementemos.

Thiago: Definí y evalué los patrones de diseño a aplicar en el sistema hotelero, abarcando creacionales (Factory, Builder), estructurales (Adapter, Decorator, Facade) y de comportamiento (Observer, State, Strategy, Template Method). También contribuí con porciones del código en el repositorio de GitHub, incluyendo las clases del dominio, los estados de la reserva y la interfaz de estrategia de precio, y participé en las decisiones de arquitectura general del proyecto.

