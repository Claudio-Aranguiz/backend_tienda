<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@ include file="/WEB-INF/views/fragments/head.jsp" %>
    
    <body>
        <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>
        <main>
            <div class="container mt-5">
                <h1 class="my-4">Gestión de Productos</h1>
                
                <!-- Formulario para crear/editar productos -->
                <div class="card mb-4">
                    <div class="card-header">
                        <span id="formTitle">Crear Nuevo Producto</span>
                    </div>
                    <div class="card-body">
                        <form id="productoForm" action="${pageContext.request.contextPath}/productos" method="post">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="accion" name="accion" value="crear">
                            
                            <div class="mb-3 row">
                                <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                                </div>
                            </div>
                            
                            <div class="mb-3 row">
                                <label for="precio" class="col-sm-2 col-form-label">Precio</label>
                                <div class="col-sm-10">
                                    <input type="number" class="form-control" id="precio" name="precio" required>
                                </div>
                            </div>
                            
                            <div class="mb-3 row">
                                <label for="categoriaId" class="col-sm-2 col-form-label">Categoría</label>
                                <div class="col-sm-10">
                                    <select class="form-select" id="categoriaId" name="categoriaId" required>
                                        <option value="" selected disabled>Seleccione una categoría</option>
                                        <c:forEach items="${categorias}" var="categoria">
                                            <option value="${categoria.id}">${categoria.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="mb-3 row">
                                <div class="col-sm-10 offset-sm-2">
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                    <button type="button" class="btn btn-secondary" onclick="limpiarFormulario()">Limpiar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                
                <!-- Tabla de productos -->
                <div class="card">
                    <div class="card-header">Listado de Productos</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Categoría</th>
                                    <th>Precio</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${productos}" var="producto">
                                    <tr>
                                        <td>${producto.id}</td>
                                        <td>${producto.nombre}</td>
                                        <td>${producto.categoria.nombre}</td>
                                        <td>$${producto.precio}</td>
                                        <td>
                                            <button class="btn btn-sm btn-warning" onclick="editarProducto(${producto.id}, '${producto.nombre}', ${producto.precio}, ${producto.categoria.id})">Editar</button>
                                            <button class="btn btn-sm btn-danger" onclick="eliminarProducto(${producto.id})">Eliminar</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        
        <%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
        
        <!-- JavaScript para manejar el CRUD -->
        <script>
            function editarProducto(id, nombre, precio, categoriaId) {
                document.getElementById('id').value = id;
                document.getElementById('nombre').value = nombre;
                document.getElementById('precio').value = precio;
                document.getElementById('categoriaId').value = categoriaId;
                document.getElementById('accion').value = 'actualizar';
                document.getElementById('formTitle').textContent = 'Editar Producto';
            }
            
            function eliminarProducto(id) {
                if (confirm('¿Está seguro que desea eliminar este producto?')) {
                    const form = document.createElement('form');
                    form.method = 'POST';
                    form.action = '${pageContext.request.contextPath}/productos';
                    
                    const idInput = document.createElement('input');
                    idInput.type = 'hidden';
                    idInput.name = 'id';
                    idInput.value = id;
                    
                    const accionInput = document.createElement('input');
                    accionInput.type = 'hidden';
                    accionInput.name = 'accion';
                    accionInput.value = 'eliminar';
                    
                    form.appendChild(idInput);
                    form.appendChild(accionInput);
                    document.body.appendChild(form);
                    form.submit();
                }
            }
            
            function limpiarFormulario() {
                document.getElementById('productoForm').reset();
                document.getElementById('id').value = '';
                document.getElementById('accion').value = 'crear';
                document.getElementById('formTitle').textContent = 'Crear Nuevo Producto';
            }
        </script>
    </body>
</html>