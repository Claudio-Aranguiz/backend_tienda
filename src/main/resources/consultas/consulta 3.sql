-- Consulta 3: Se solicita una lista con las categorías más vendidas y la cantidad total de productos vendidos, ordenados de forma descendente.
SELECT cat.nombre AS categoria, SUM(dc.cantidad) AS total_vendidos
FROM categorias cat
JOIN productos p ON cat.id = p.categoria_id
JOIN detalles_compra dc ON p.id = dc.producto_id
GROUP BY cat.nombre
ORDER BY total_vendidos DESC;