Para ejecutar los programas se requieren lo siguiente:
 - JDK 11
 - Base de datos mongo (local)

Ejecutar en el siguiente orden
 - Servidor Eureka 
	situarse sobre el directorio ./eureka y ejecutar el comando mvn clean install spring-boot:run 
 - Servidor de Authenticación (Sobre el puerto deseado, ej 13000)
	situarse sobre el directorio ./Authentication y ejecutar el comando mvn clean install spring-boot:run -Dspring-boot.run.arguments=--server.port=13000
 - Servidor de Carrito de Compras (Sobre el puerto deseado, ej 13001)
	situarse sobre el directorio ./ShoppingCart y ejecutar el comando mvn clean install spring-boot:run -Dspring-boot.run.arguments=--server.port=13001

La documentación se encuentra en el directorio ./documentacion
 - en el directorio ./Diagramas se encuentran los diagramas correspondientes de Casos de Uso, Clases, Componentes y Despliegue
 - el archivo DocumentaciónAPI.xlsx contiene la especificación de los endpoints del sistema (incompleto)
 - el archivo MarketPlace.json contiene el schema json de las clases del sistema
 - el archivo PruebasPostman.json contienen ejemplos para las funcionalidades implementadas

Se presenta unicamente las funcionalidades de:
 - servidor de descubrimiento eureka
 - servicio de autenticación (completo)
 - servicio de Carrito de compras (completo)