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
import cl.caranguizh.repository.CategoriaRepository;

/**
 * Implementación del repositorio para la entidad Categoria.
 * Proporciona métodos para acceder y manipular datos de categorías en la base de datos.
 */
@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor que inicializa el repositorio con un JdbcTemplate.
     * 
     * @param jdbcTemplate plantilla JDBC para operaciones de base de datos
     */
    @Autowired
    public CategoriaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Categoria> categoriaRowMapper = (rs, rowNum) -> {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("id"));
        categoria.setNombre(rs.getString("nombre"));
        return categoria;
    };

    /**
     * Recupera todas las categorías existentes en la base de datos.
     * 
     * @return lista de todas las categorías
     */
    @Override
    public List<Categoria> findAll() {
        String sql = "SELECT id, nombre FROM categorias";
        return jdbcTemplate.query(sql, categoriaRowMapper);
    }

    /**
     * Recupera una categoría por su identificador.
     * 
     * @param id identificador de la categoría
     * @return categoría con el identificador especificado, o vacío si no existe
     */
    @SuppressWarnings("deprecation")
	@Override
    public Optional<Categoria> findById(Integer id) {
        String sql = "SELECT id, nombre FROM categorias WHERE id = ?";
        List<Categoria> categorias = jdbcTemplate.query(sql, new Object[]{id}, categoriaRowMapper);
        return categorias.isEmpty() ? Optional.empty() : Optional.of(categorias.get(0));
    }

    /**
     * Recupera una categoría por su nombre.
     * 
     * @param nombre nombre de la categoría
     * @return categoría con el nombre especificado, o vacío si no existe
     */
    @SuppressWarnings("deprecation")
	@Override
    public Optional<Categoria> findByNombre(String nombre) {
        String sql = "SELECT id, nombre FROM categorias WHERE nombre = ?";
        List<Categoria> categorias = jdbcTemplate.query(sql, new Object[]{nombre}, categoriaRowMapper);
        return categorias.isEmpty() ? Optional.empty() : Optional.of(categorias.get(0));
    }

    /**
     * Guarda una categoría en la base de datos.
     * 
     * @param categoria categoría a guardar
     * @return categoría guardada
     */
    @Override
    public Categoria save(Categoria categoria) {
        if (categoria.getId() == null) {
            return insert(categoria);
        }
        return update(categoria);
    }

    /**
     * Inserta una nueva categoría en la base de datos.
     * 
     * @param categoria categoría a insertar
     * @return categoría insertada
     */
    private Categoria insert(Categoria categoria) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, categoria.getNombre());
            return ps;
        }, keyHolder);
        
        @SuppressWarnings("null")
		Integer newId = keyHolder.getKey().intValue();
        categoria.setId(newId);
        return categoria;
    }

    /**
     * Actualiza una categoría en la base de datos.
     * 
     * @param categoria categoría a actualizar
     * @return categoría actualizada
     */
    private Categoria update(Categoria categoria) {
        String sql = "UPDATE categorias SET nombre = ? WHERE id = ?";
        jdbcTemplate.update(sql, categoria.getNombre(), categoria.getId());
        return categoria;
    }

    /**
     * Elimina una categoría de la base de datos por su identificador.
     * 
     * @param id identificador de la categoría a eliminar
     */
    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Recupera la categoría de la base de datos por el nombre.
     */
	@SuppressWarnings("deprecation")
    @Override
    public List<Categoria> findByNombreContaining(String nombre) {
        String sql = "SELECT id, nombre FROM categorias WHERE nombre LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + nombre + "%"}, categoriaRowMapper);
    }

    /**
     * Recupera todas los de productos que pertenecen a una categoría.
     * 
     * @param id identificador de la categoría
     * @return lista de categorías de productos
     */
	@SuppressWarnings("deprecation")
    @Override
    public List<Categoria> findProductosByCategoriaId(Integer id) {
        String sql = "SELECT c.id, c.nombre FROM categorias c " +
                    "JOIN productos p ON p.categoria_id = c.id " +
                    "WHERE p.categoria_id = ? " +
                    "GROUP BY c.id, c.nombre";
        return jdbcTemplate.query(sql, new Object[]{id}, categoriaRowMapper);
    }
}
