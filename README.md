# 💻 Sistema de Ticketing de Incidencias LAB-LIS

Este proyecto es una implementación en consola de un sistema de gestión de incidencias técnicas para el **Laboratorio de Cómputo (LAB-LIS)**. Desarrollado en Java bajo el paradigma de **Programación Orientada a Objetos (POO)**.

El objetivo principal es registrar, rastrear y gestionar las fallas de hardware, software y red de los equipos, facilitando la comunicación entre usuarios y técnicos.

## 📋 Características Principales

El sistema permite gestionar el ciclo de vida completo de una incidencia, desde su reporte hasta su resolución.

### Funcionalidades por Rol:
* **Alumnos y Personal Académico:**
    * Reportar nuevas incidencias especificando el equipo y el tipo de falla (Hardware, Software, Red).
* **Técnicos:**
    * Consultar historial de incidencias asignadas.
    * Asignar prioridad a las incidencias (Urgente, Alta, Media, Baja).
    * Actualizar estado de la incidencia (Pendiente -> En Proceso -> Terminado).
* **Sistema (Automático):**
    * **Asignación Inteligente:** Asigna automáticamente la incidencia al técnico con menor carga de trabajo.
    * **Control de Inventario:** Cambia el estado del equipo (Activo, Mantenimiento, Inactivo) según las incidencias vivas.
    * **Reportes:** Genera un resumen del estado actual del laboratorio.
