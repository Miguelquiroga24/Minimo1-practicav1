# Minimo1-practicav1

# Proyecto Gestión de Flota de Drones

## Descripción
Este proyecto implementa una aplicación REST para gestionar una flota de drones, pilotos y reservas de vuelos. Se han desarrollado:
- Un componente de negocio (fachada) que gestiona drones, pilotos, reservas y mantenimiento.
- Un servicio REST basado en JAX-RS (Jersey) que expone las siguientes operaciones:
  - **Añadir un dron:** Permite registrar un nuevo dron.
  - **Añadir un piloto:** Permite registrar un nuevo piloto.
  - **Listar drones ordenados:** Devuelve los drones ordenados descendentemente por horas de vuelo.
  - **Listar pilotos ordenados:** Devuelve los pilotos ordenados descendentemente por horas de vuelo.
- Se han implementado tests unitarios con JUnit y se utilizan las dependencias únicamente a través de Maven.
- Se ha integrado Swagger para documentar la API.

## Estado Actual
- **Funcionalidades Implementadas y Funcionando:**
  - Gestión de drones, pilotos y reservas mediante el componente de negocio.
  - Servicio REST que expone operaciones de creación y consulta.
  - Documentación automática con Swagger.
  - Tests unitarios que validan las operaciones principales.
- **Pendientes de Desarrollo:**
  - Integrar operaciones adicionales (p.ej., modificar o eliminar registros).
  - Mejorar la gestión de excepciones y validaciones.
  
## Errores/Problemas Encontrados
- No se han encontrado errores críticos durante el desarrollo.
- Se han identificado posibles mejoras en la validación de datos y manejo de solapamientos en reservas.

## Instrucciones de Ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/nombre-repositorio.git
