Informe Técnico de Incidencia: BuggyWebApp
1. Descripción del problema
   El cliente reporta un fallo de seguridad y lógica en el formulario de contacto de la aplicación. El sistema permite el envío y procesamiento de formularios incluso cuando los campos obligatorios (nombre y email) se encuentran totalmente vacíos.

2. Pasos para la reproducción
   Para reproducir el error, se han seguido estos pasos:

Ejecutar la clase principal MainApp.java.

Llamar al método submitContactForm("", "") enviando cadenas de texto vacías.

Observar la consola de salida.

Resultado actual: La consola muestra [LOG] Formulario procesado, confirmando que el sistema no detiene la ejecución ante datos inválidos.

3. Análisis técnico
   Tras utilizar el Debugger de IntelliJ IDEA, se ha analizado el flujo interno en la clase ContactController.java:

Se colocó un breakpoint en el método submitContactForm.

En el panel de variables, se confirmó que los parámetros name y email llegan con el valor "" (String vacío).

El flujo de ejecución no encuentra ninguna estructura de control (if) que verifique el contenido de estas variables antes de instanciar el objeto ContactForm.

4. Causa raíz
   La causa del error es la ausencia de lógica de validación en la capa del controlador. El código original confía ciegamente en los datos de entrada y procede a llamar al servicio de procesamiento sin comprobar si los requisitos mínimos de información se cumplen.

5. Propuesta de solución
   Se propone implementar una validación mediante una estructura condicional en la clase ContactController:

Utilizar el método .trim().isEmpty() para detectar campos vacíos o que solo contengan espacios.

En caso de detectar campos vacíos, se debe registrar un mensaje de error en el Logger y finalizar la ejecución del método mediante un return para evitar que el formulario llegue a procesarse.