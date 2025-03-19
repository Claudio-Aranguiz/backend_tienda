-- 	Consulta 1:	Se solicita mostrar el nombre de cada producto que ha comprado el cliente “Juan Perez” 
SELECT p.nombre
FROM productos p
JOIN detalles_compra dc ON p.id = dc.producto_id
JOIN compras c ON dc.compra_id = c.id
JOIN clientes cl ON c.cliente_id = cl.id
WHERE cl.nombre = 'Juan Perez';