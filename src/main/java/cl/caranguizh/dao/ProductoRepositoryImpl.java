package cl.caranguizh.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import cl.caranguizh.model.Categoria;
import cl.caranguizh.model.Producto;
import cl.caranguizh.repository.ProductoRepository;

/**
 * Implementación del repositorio para la entidad Producto.
 * Proporciona métodos para acceder y manipular datos de productos en la base de datos.
 */
@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor que inicializa el repositorio con un JdbcTemplate.
     * 
     * @param jdbcTemplate plantilla JDBC para operaciones de base de datos
     */
    @Autowired
    public ProductoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Producto> productoRowMapper = (rs, rowNum) -> {
        Producto producto = new Producto();
        producto.setId(rs.getInt("p.id"));
        producto.setNombre(rs.getString("p.nombre"));
        producto.setPrecio(rs.getInt("p.precio"));
        
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("c.id"));
        categoria.setNombre(rs.getString("c.nombre"));
        
        producto.setCategoria(categoria);
        
        return producto;
    };

    /**
     * Recupera todos los productos existentes en la base de datos.
     * 
     * @return lista de todos los productos
     */
    @Override
    public List<Producto> findAll() {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id";
        return jdbcTemplate.query(sql, productoRowMapper);
    }

    /**
     * Recupera un producto por su identificador.
     * 
     * @param id identificador del producto
     * @return producto con el identificador especificado, o vacío si no existe
     */
    @Override
    public Optional<Producto> findById(Integer id) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE p.id = ?";
        List<Producto> productos = jdbcTemplate.query(sql, productoRowMapper, id);
        return productos.isEmpty() ? Optional.empty() : Optional.of(productos.get(0));
    }

    /**
     * Recupera un producto por su nombre.
     * 
     * @param nombre nombre del producto
     * @return producto con el nombre especificado, o vacío si no existe
     */
    @SuppressWarnings("deprecation")
	@Override
    public List<Producto> findByNombreContaining(String nombre) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE p.nombre LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + nombre + "%"}, productoRowMapper);
    }

    /**
     * Recupera todos los productos de una categoría.
     * 
     * @param categoriaId identificador de la categoría
     * @return lista de productos de la categoría
     */
    @SuppressWarnings("deprecation")
	@Override
    public List<Producto> findByCategoriaId(Integer categoriaId) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE c.id = ?";
        return jdbcTemplate.query(sql, new Object[]{categoriaId}, productoRowMapper);
    }
    
    /**
     * Recupera todos los productos de una categoría por su nombre.
     * 
     * @param categoriaId identificador de la categoría
     * @param nombre nombre del producto
     * @return lista de productos de la categoría con el nombre especificado
     */
    @SuppressWarnings("deprecation")
	@Override
    public List<Producto> findByCategoriaIdAndNombreContaining(Integer categoriaId, String nombre) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE c.id = ? AND p.nombre LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{categoriaId, "%" + nombre + "%"}, productoRowMapper);
    }

    /**
     * Recupera todos los productos cuyo precio esté entre dos valores.
     * 
     * @param min precio mínimo
     * @param max precio máximo
     * @return lista de productos cuyo precio está entre los valores especificados
     */
    @SuppressWarnings("deprecation")
	@Override
    public List<Producto> findByPrecioBetween(Integer min, Integer max) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE p.precio BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{min, max}, productoRowMapper);
    }

    /**
     * Guarda un producto en la base de datos.
     * 
     * @param producto producto a guardar
     */
    @Override
    public void save(Producto producto) {
        if (producto.getId() == null) {
            insert(producto);
        }
        update(producto);
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     * 
     * @param producto producto a insertar
     */
    @SuppressWarnings("null")
    private void insert(Producto producto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO productos (nombre, precio, categoria_id) VALUES (?, ?, ?)";
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getPrecio());
            ps.setInt(3, producto.getCategoria().getId());
            return ps;
        }, keyHolder);
        
        Integer newId = keyHolder.getKey().intValue();
        producto.setId(newId);
    }

    /**
     * Actualiza un producto en la base de datos.
     * 
     * @param producto producto a actualizar
     */
    private void update(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, categoria_id = ? WHERE id = ?";
        jdbcTemplate.update(
            sql, 
            producto.getNombre(), 
            producto.getPrecio(), 
            producto.getCategoria().getId(),
            producto.getId()
        );
    }

    /**
     * Elimina un producto de la base de datos por su identificador.
     * 
     * @param id identificador del producto a eliminar
     */
    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Recupera todos los productos de una categoría por su identificador o nombre.
     * 
     * @param categoriaId identificador de la categoría
     * @param nombre nombre del producto
     * @return lista de productos de la categoría con el identificador o nombre especificado
     */
	@SuppressWarnings("deprecation")
    @Override
    public List<Producto> findByCategoriaIdOrNombreContaining(Integer categoriaId, String nombre) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id as c_id, c.nombre as c_nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE c.id = ? OR p.nombre LIKE ?";
        
        return jdbcTemplate.query(sql, new Object[]{categoriaId, "%" + nombre + "%"}, productoRowMapper);
    }

    // Getters y setters
    public RowMapper<Producto> getProductoRowMapper() {
        return productoRowMapper;
    }

    public void setProductoRowMapper(RowMapper<Producto> productoRowMapper) {
        this.productoRowMapper = productoRowMapper;
    }

}