# README - Proyecto Front MVC

## Descripción

Front MVC es una aplicación web desarrollada con Spring MVC que implementa un sistema de tienda con gestión de productos y categorías. Esta aplicación sirve como ejemplo de arquitectura MVC con persistencia en base de datos utilizando Hibernate y Spring.

## Estructura del proyecto

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── cl/caranguizh/
│   │   │       ├── config/           # Configuración de Spring
│   │   │       ├── controller/       # Controladores MVC
│   │   │       ├── dao/              # Capa de acceso a datos
│   │   │       ├── model/            # Entidades y modelos de datos
│   │   │       └── repository/       # Interfaces de repositorio  
│   │   ├── resources/
│   │   │   ├── application.properties.template  # Plantilla de configuración
│   │   │   ├── applicationContext.xml           # Contexto de Spring
│   │   │   └── log4j2.xml                       # Configuración de logging
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── views/            # Vistas JSP
│   │       │   └── web.xml           # Descriptor de despliegue
│   │       └── resources/            # Recursos estáticos (CSS, JS, imágenes)
│   └── test/                         # Tests unitarios e integración
└── pom.xml                           # Configuración de Maven
```

## Requisitos

* Java 17
* Maven 3.x
* MySQL Server 8.0
* Apache Tomcat 9

## Configuración

1. El proyecto utiliza Spring MVC para el manejo de peticiones web
2. Las vistas se implementan usando JSP y están ubicadas en `/WEB-INF/views/`
3. Se requiere configurar un ViewResolver para que las vistas funcionen correctamente
4. El context path del proyecto es `/front_mvc`

## Configuración del entorno

### 1. Base de datos

1. Crea una base de datos MySQL:

   **CREATE** **DATABASE** tienda_db;
3. Configura tu usuario y permisos:

   **CREATE** **USER** **'tu_usuario'**@**'localhost'** **IDENTIFIED** **BY** **'tu_password'**;

   **GRANT** **ALL** **PRIVILEGES** **ON** tienda_db.***** **TO** **'tu_usuario'**@**'localhost'**;

   **FLUSH** **PRIVILEGES**;

### 2. Variables de entorno

Para proteger las credenciales de la base de datos y otras configuraciones sensibles, el proyecto soporta variables de entorno:

1. Copia el archivo `.env-front_mvc` a `.env` (para desarrollo local)

   **DB_DRIVER=com.mysql.cj.jdbc.Driver**

   **DB_URL=jdbc:mysql://localhost:3306/tienda_db?useSS**L=false&serverTimezone=UTC

   **DB_USERNAME=tu_usuario**

   **DB_PASSWORD=tu_password**
2. Copia [application.properties.template](vscode-file://vscode-app/c:/Users/claud/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) a [application.properties](vscode-file://vscode-app/c:/Users/claud/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html)
3. Configura las variables específicas de tu entorno

### 3. Configuración alternativa

Si prefieres no usar variables de entorno, puedes editar directamente el archivo `application.properties`:

**db.driver**=com.mysql.cj.jdbc.Driver

**db.url**=jdbc:mysql://localhost:3306/tienda_db?**useSSL**=false&**serverTimezone**=UTC

**db.username**=tu_usuario

**db.password**=tu_password

**# Configuración de Hibernate**

**hibernate.dialect**=org.hibernate.dialect.MySQL8Dialect

**hibernate.show_sql**=true

**hibernate.format_sql**=true

**hibernate.hbm2ddl.auto**=update

## Instalación y despliegue

### Método 1: Maven + Tomcat (desarrollo)

1. Clona el repositorio:

   **git clone **https://github.com/tu-usuario/front_mvc.git

   **cd front_mvc**
2. Compila el proyecto:

   **mvn clean package**
3. Despliega el WAR generado en Tomcat:

   * Copia `target/front_mvc.war` al directorio `webapps` de Tomcat
   * O configura Tomcat en tu IDE y despliega desde allí

### Método 2: Despliegue en servidor de producción

1. Genera el WAR:

   **mvn clean package**
2. Despliega el archivo `target/front_mvc.war` en tu servidor
3. Configura las variables de entorno en el servidor:

   **export DB_DRIVER=com.mysql.cj.jdbc.Driver**

   **export DB_URL=jdbc:mysql://host:3306/tienda_db?use**SSL=false

   **export DB_USERNAME=usuario_prod**

   **export DB_PASSWORD=contraseña_segura**

## Uso de la aplicación

Una vez desplegada, accede a través de un navegador:

* Página principal: `http://localhost:8080/front_mvc/`
* Catálogo de productos: `http://localhost:8080/front_mvc/mock_up`
* Administración de productos: `http://localhost:8080/front_mvc/productos`
* Login: `http://localhost:8080/front_mvc/login`

## Problemas conocidos

1. **Error 404 en vistas JSP** : Las vistas de productos y categorías pueden presentar errores 404

* Solución: Verificar la existencia de los archivos JSP en la ruta correcta y la configuración del ViewResolver

1. **Problemas con rutas URL** : Los enlaces deben incluir el contexto de la aplicación `/front_mvc`

* Solución: Usar rutas completas como `/front_mvc/productos` en lugar de `/productos`

## Funcionamiento

La aplicación permite:

* Ver una página de inicio con enlaces a las diferentes secciones
* Filtrar productos por nombre o características
* Filtrar productos por categoría
* Mostrar información detallada de productos seleccionados

## Enlaces principales

* Página principal: `/front_mvc/`
* Filtro por productos: `/front_mvc/productos`
* Filtro por categorías: `/front_mvc/categorias`

## Tecnologías utilizadas

* Java 17 (Compatible con Apache Tomcat 9)
* Spring MVC 5.3.x
* Hibernate 5.6.x
* MySQL 8.0
* Apache Tomcat 9
* Maven 3.x
* JSP/JSTL para vistas
* Bootstrap 5
* HTML5/CSS3


## Contacto

Para cualquier consulta o sugerencia, contacta a [caranguizh@outlook.com](vscode-file://vscode-app/c:/Users/claud/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html)
