-- Consulta 5: Se solicita una lista que muestre el monto que ha gastado cada cliente en la categoría “Electrodomésticos”. 
SELECT cl.nombre, SUM(p.precio * dc.cantidad) AS total_electrodomesticos
FROM clientes cl
JOIN compras c ON cl.id = c.cliente_id
JOIN detalles_compra dc ON c.id = dc.compra_id
JOIN productos p ON dc.producto_id = p.id
JOIN categorias cat ON p.categoria_id = cat.id
WHERE cat.nombre = 'Electrodomésticos'
GROUP BY cl.nombre;