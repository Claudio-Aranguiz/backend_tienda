-- Consulta 2: Se solicita una lista del Nombre y el total gastado de todos los clientes que han realizado compras superiores a $1000. 
SELECT cl.nombre, c.total AS total_gastado
FROM clientes cl
JOIN compras c ON cl.id = c.cliente_id
WHERE c.total > 1000;