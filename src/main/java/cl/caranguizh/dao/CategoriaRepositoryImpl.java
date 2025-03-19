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

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final JdbcTemplate jdbcTemplate;

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

    @Override
    public List<Categoria> findAll() {
        String sql = "SELECT id, nombre FROM categorias";
        return jdbcTemplate.query(sql, categoriaRowMapper);
    }

    @SuppressWarnings("deprecation")
	@Override
    public Optional<Categoria> findById(Integer id) {
        String sql = "SELECT id, nombre FROM categorias WHERE id = ?";
        List<Categoria> categorias = jdbcTemplate.query(sql, new Object[]{id}, categoriaRowMapper);
        return categorias.isEmpty() ? Optional.empty() : Optional.of(categorias.get(0));
    }

    @SuppressWarnings("deprecation")
	@Override
    public Optional<Categoria> findByNombre(String nombre) {
        String sql = "SELECT id, nombre FROM categorias WHERE nombre = ?";
        List<Categoria> categorias = jdbcTemplate.query(sql, new Object[]{nombre}, categoriaRowMapper);
        return categorias.isEmpty() ? Optional.empty() : Optional.of(categorias.get(0));
    }

    @Override
    public Categoria save(Categoria categoria) {
        if (categoria.getId() == null) {
            return insert(categoria);
        }
        return update(categoria);
    }

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

    private Categoria update(Categoria categoria) {
        String sql = "UPDATE categorias SET nombre = ? WHERE id = ?";
        jdbcTemplate.update(sql, categoria.getNombre(), categoria.getId());
        return categoria;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

	@SuppressWarnings("deprecation")
    @Override
    public List<Categoria> findByNombreContaining(String nombre) {
        String sql = "SELECT id, nombre FROM categorias WHERE nombre LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + nombre + "%"}, categoriaRowMapper);
    }

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
