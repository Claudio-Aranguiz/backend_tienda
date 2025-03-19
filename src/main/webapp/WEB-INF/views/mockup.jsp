<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@ include file="/WEB-INF/views/fragments/head.jsp" %>
    
    <body>
        <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>
        <main>
            <div class="container mt-5 text-center">
                <h1 class="my-4">Catálogo de Productos</h1>
                <p>Explore nuestra selección de productos o use los filtros para encontrar lo que busca</p> 
            </div>
            <section>
                <div class="container-xl">
                    <div class="row">
                        <div class="col-1"></div>
                        <div class="col-10">
                            <form action="${pageContext.request.contextPath}/productos" method="get">
                                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                                    <div class="container-fluid">
                                        <!-- Select dinámico de categorías -->
                                        <select name="categoriaId" class="form-select me-1">
                                            <option value="0" ${param.categoriaId == null || param.categoriaId == 0 ? 'selected' : ''}>Todas las Categorías</option>
                                            <c:forEach items="${categorias}" var="categoria">
                                                <option value="${categoria.id}" ${param.categoriaId == categoria.id ? 'selected' : ''}>${categoria.nombre}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="input-group d-flex ms-1">
                                            <input type="text" name="busqueda" class="form-control" placeholder="Buscar producto" 
                                                   value="${param.busqueda}" aria-label="Buscar producto">
                                            <button class="btn btn-outline-secondary" type="submit">Buscar</button>        
                                        </div>
                                    </div>
                                </nav>
                            </form>
                            
                            <!-- Tabla de productos dinámica -->
                            <table class="table table-hover mt-2">
                                <thead>
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Categoría</th>
                                        <th scope="col">Precio</th>
                                        <th scope="col">Acciones</th>                              
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${productos}" var="producto">
                                        <tr>
                                            <th scope="row">${producto.id}</th>
                                            <td>${producto.nombre}</td>
                                            <td>${producto.categoria.nombre}</td>
                                            <td>$${producto.precio}</td>
                                            <td class="text-end">
                                                <div class="btn-group btn-group-sm" role="group">
                                                    <button class="btn btn-success" type="button" onclick="comprarProducto(${producto.id})">
                                                        <i class="bi bi-cart-plus"></i> Comprar
                                                    </button>
                                                    <button class="btn btn-outline-primary" type="button" onclick="verEstadisticas(${producto.id})">
                                                        <i class="bi bi-graph-up"></i> Stats
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    
                                    <!-- Mensaje si no hay productos -->
                                    <c:if test="${empty productos}">
                                        <tr>
                                            <td colspan="5" class="text-center">No se encontraron productos</td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-1"></div>
                    </div>
                </div>
            </section>
        </main>
        <%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
        
        <!-- Scripts de Bootstrap y funcionalidad -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            function comprarProducto(id) {
                alert('Función de compra para el producto ' + id + ' (a implementar)');
            }
            
            function verEstadisticas(id) {
                alert('Estadísticas para el producto ' + id + ' (a implementar)');
            }
        </script>
    </body>
</html>