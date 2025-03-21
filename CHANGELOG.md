# Registro de Cambios (CHANGELOG)

## [1.0.1] - 2025-03-21

### Correcciones

- **Vistas JSP**: Solucionados problemas de renderización que causaban error 500
  - Corregido nombre de la vista en `CatalogoController` de 'MockUp' a 'mockup' para que coincida con el archivo JSP real
  - Resuelto conflicto de rutas entre `MockUpController` y `CatalogoController` que apuntaban al mismo endpoint '/mock_up'
  - El endpoint de MockUpController se cambió a '/mock_up_v2'

- **Configuración de la aplicación web**:
  - Corregida la ubicación de la página de bienvenida en `web.xml` de '/WEB-INF/index.html' a 'index.html'
  - Creada copia del archivo index.html en la raíz del directorio webapp para que sea accesible directamente
  - Mejorada configuración de recursos estáticos en `WebConfig` con rutas más claras y caché

- **Estandarización de controladores**:
  - Convertido `TestController` de WebServlet a controlador Spring MVC para mantener consistencia
  - Se cambió de usar `HttpServlet` a usar anotaciones `@Controller` y `@GetMapping`

### Mejoras

- **Rendimiento**: Agregada configuración de caché para recursos estáticos (1 hora)
- **Recursos estáticos**: Ampliada configuración para incluir subdirectorios CSS, JS e imágenes

## [1.0.0] - Versión Inicial

- Implementación inicial de la aplicación con Spring MVC
- Sistema de productos y categorías
- Vistas con JSP
- Persistencia con Hibernate
