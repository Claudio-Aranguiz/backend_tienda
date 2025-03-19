-- Consulta 4: Se solicita una lista que muestre los productos ( nombre y precio), de aquellos que no han sido vendidos nunca, ordenados por precio de forma ascendente. 
SELECT p.nombre, p.precio
FROM productos p
LEFT JOIN detalles_compra dc ON p.id = dc.producto_id
WHERE dc.producto_id IS NULL
ORDER BY p.precio ASC;