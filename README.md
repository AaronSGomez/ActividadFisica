# 🏋️ Actividad Física & Sensor Game

Una aplicación Android nativa desarrollada en **Kotlin** que combina el registro de actividades deportivas con una experiencia interactiva utilizando los sensores del dispositivo.

## 📱 Descripción del Proyecto

Este proyecto tiene dos funcionalidades principales:
1.  **Gestor de Actividades:** Un formulario para registrar entrenamientos deportivos, visualizarlos en una lista y clasificarlos por tipo.
2.  **Shake Game (Juego del Meneo):** Un módulo interactivo que utiliza el **acelerómetro** del móvil para medir la intensidad de la agitación y responder visualmente en tiempo real.

## 🚀 Funcionalidades

### 1. Registro de Actividades (Main Activity)
* **Formulario de entrada:** Campos para nombre de la actividad y duración (con validación de tipos).
* **Selector de Categoría:** `Spinner` desplegable con opciones predefinidas (Cardio, Fuerza, Yoga, Natación, etc.).
* **Listado Dinámico:** Uso de `RecyclerView` para mostrar los registros.
* **Iconos Inteligentes:** La app asigna automáticamente un icono vectorial (`Vector Asset`) específico según el deporte seleccionado.
* **Gestión de Fechas:** Captura automática de la fecha y hora actual usando `LocalDateTime` y formateo con `DateTimeFormatter`.

### 2. Sesión en Tiempo Real (Sensor Activity)
* **Uso de Sensores:** Implementación de `SensorManager` y `SensorEventListener` para acceder al acelerómetro.
* **Lógica Matemática:** Cálculo de la fuerza G y la intensidad del movimiento (`sqrt(x² + y² + z²) - gravedad`).
* **Feedback Visual Dinámico:** La pantalla cambia de color y texto en tiempo real según la intensidad:
    * 🔵 **Azul (Quieto):** Sin movimiento.
    * 🐬 **Cyan (Suave):** Movimiento leve (Texto negro para contraste).
    * 🟣 **Magenta (Medio):** Actividad moderada.
    * 🔴 **Rojo (Depravado):** Alta intensidad (Texto blanco para contraste).

## 🛠️ Stack Tecnológico & Conceptos Aprendidos

* **Lenguaje:** Kotlin.
* **UI/UX:** XML Layouts, LinearLayout, ConstraintLayout.
* **Componentes Android:**
    * `RecyclerView` & Custom Adapter.
    * `Spinner` & `ArrayAdapter`.
    * `Intent` (Navegación entre actividades).
    * `Activity Lifecycle` (Manejo de onResume/onPause para sensores).
* **Lógica:**
    * Manejo de Listas Mutables (`MutableList`).
    * Estructuras de control (`when` expression).
    * Manipulación programática de propiedades de vista (`setBackgroundColor`, `setTextColor`).

## 📸 Capturas de Pantalla 

<img width="303" height="auto" alt="Captura de pantalla 2025-12-28 132023" src="https://github.com/user-attachments/assets/f4aaf1d1-5cff-4807-8f36-66decd4276ee" />

<img width="303" height="auto" alt="Captura de pantalla 2025-12-28 134757" src="https://github.com/user-attachments/assets/c7cc8174-085f-4842-889d-8fe3e78baae4" />

## 🔜 Próximos Pasos (Roadmap)

* [ ] **Persistencia de Datos:** Guardar la lista de actividades en memoria local usando `SharedPreferences` y `Gson` (JSON).
* [ ] **Borrado:** Implementar "Swipe to delete" en el RecyclerView.
* [ ] **Gamificación:** Guardar los resultados del "Juego del Meneo" como una actividad física más.

## 👤 AaronSGomez

Desarrollado como parte de un proyecto de aprendizaje intensivo de Android y Kotlin.
