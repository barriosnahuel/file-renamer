fileRenamer
===========

*FileRenamer* es una aplicación Java para ejecutar desde la línea de comandos que permite al usuario renombrar un set de archivos desde un patrón conocido a otro patrón distinto.

Ejemplo de uso:
Paso 1: Generar .jar ejecutable con Eclipse.
Paso 2: Desde la línea de comandos ejecutar:

> java -jar FileRenamer.jar unDirectorio/otroDirectorio "%a - %t" "%t - %a"