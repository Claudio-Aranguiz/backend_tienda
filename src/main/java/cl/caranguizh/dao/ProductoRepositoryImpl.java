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

@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    private final JdbcTemplate jdbcTemplate;

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

    @Override
    public List<Producto> findAll() {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id";
        return jdbcTemplate.query(sql, productoRowMapper);
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE p.id = ?";
        List<Producto> productos = jdbcTemplate.query(sql, productoRowMapper, id);
        return productos.isEmpty() ? Optional.empty() : Optional.of(productos.get(0));
    }

    @SuppressWarnings("deprecation")
	@Override
    public List<Producto> findByNombreContaining(String nombre) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE p.nombre LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + nombre + "%"}, productoRowMapper);
    }

    @SuppressWarnings("deprecation")
	@Override
    public List<Producto> findByCategoriaId(Integer categoriaId) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE c.id = ?";
        return jdbcTemplate.query(sql, new Object[]{categoriaId}, productoRowMapper);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public List<Producto> findByCategoriaIdAndNombreContaining(Integer categoriaId, String nombre) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE c.id = ? AND p.nombre LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{categoriaId, "%" + nombre + "%"}, productoRowMapper);
    }

    @SuppressWarnings("deprecation")
	@Override
    public List<Producto> findByPrecioBetween(Integer min, Integer max) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id, c.nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE p.precio BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{min, max}, productoRowMapper);
    }

    @Override
    public void save(Producto producto) {
        if (producto.getId() == null) {
            insert(producto);
        }
        update(producto);
    }

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

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

	@SuppressWarnings("deprecation")
    @Override
    public List<Producto> findByCategoriaIdOrNombreContaining(Integer categoriaId, String nombre) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.id as c_id, c.nombre as c_nombre " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE c.id = ? OR p.nombre LIKE ?";
        
        return jdbcTemplate.query(sql, new Object[]{categoriaId, "%" + nombre + "%"}, productoRowMapper);
    }

    public RowMapper<Producto> getProductoRowMapper() {
        return productoRowMapper;
    }

    public void setProductoRowMapper(RowMapper<Producto> productoRowMapper) {
        this.productoRowMapper = productoRowMapper;
    }

}