# Rompe Ladrillos

Este es un juego clásico de "Rompe Ladrillos" desarrollado para Android. El objetivo del juego es simple: controla una raqueta para golpear una bola y destruir todos los ladrillos en la pantalla.

## Características

* **Jugabilidad Clásica:** Revive la experiencia del clásico juego de arcade.
* **Controles Táctiles Intuitivos:** Mueve la raqueta deslizando el dedo por la pantalla.
* **Múltiples Vidas:** Tienes varias vidas para completar el juego.
* **Puntuación:** Gana puntos destruyendo ladrillos.
* **Efectos de Sonido:** Disfruta de efectos de sonido que mejoran la experiencia de juego.
* **Pantalla de Fin de Juego:** Muestra tu puntuación final y te permite reiniciar o salir del juego.
* **Animaciones:** Se incluyen animaciones para una experiencia de usuario mas agradable.



## Cómo Jugar

1.  Abre la aplicación en tu dispositivo Android.
2.  Toca la pantalla para mover la raqueta horizontalmente.
3.  Impide que la bola caiga por debajo de la raqueta.
4.  Destruye todos los ladrillos para ganar.
5.  Si la bola cae y no la golpeas con la raqueta, pierdes una vida.
6.  Si pierdes todas las vidas, el juego termina.

## Requisitos

* Dispositivo Android
* Android SDK

## Instalación

1.  Clona este repositorio: `git clone https://github.com/theodelrieu?tab=repositories`
2.  Abre el proyecto en Android Studio.
3.  Conecta tu dispositivo Android o utiliza un emulador.
4.  Ejecuta la aplicación.

## Estructura del Proyecto

* `MainActivity.java`: Clase principal que inicia el juego.
* `VistaJuego.java`: Vista principal del juego que maneja la lógica y el dibujo.
* `Velocidad.java`: Clase que representa la velocidad de la bola.
* `Ladrillo.java`: Clase que representa los ladrillos del juego.
* `FinDelJuego.java`: Actividad que se muestra al finalizar el juego.
* `activity_main.xml`: Diseño de la actividad principal.
* `game_over.xml`: Diseño de la actividad de fin de juego.
* `R.drawable`: Recursos gráficos del juego.
* `R.raw`: Recursos de sonido del juego.
* `anim`: Recursos de animaciones del juego.

## Créditos

* Desarrollador: Álvaro Wau

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.