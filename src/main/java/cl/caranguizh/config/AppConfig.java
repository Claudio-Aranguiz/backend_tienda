package cl.caranguizh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Configuración principal de la aplicación.
 * Esta clase importa configuraciones XML para mantener la compatibilidad con componentes antiguos.
 * Permite cargar el contexto de la aplicación a partir del archivo applicationContext.xml.
 */
@Configuration
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {

    // @Bean
    // public DataSource dataSource() {
    //     DriverManagerDataSource dataSource = new DriverManagerDataSource();
    //     dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    //     dataSource.setUrl("jdbc:mysql://localhost:3306/tienda");
    //     dataSource.setUsername("root");
    //     dataSource.setPassword("tu_password");
    //     return dataSource;
    // }

    // @Bean
    // public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    //     return new JdbcTemplate(dataSource);
    // }

    // @Bean
    // public ProductoRepository productoRepository(JdbcTemplate jdbcTemplate) {
    //     return new ProductoRepositoryImpl(jdbcTemplate);
    // }

    // @Bean
    // public CategoriaRepository categoriaRepository(JdbcTemplate jdbcTemplate) {
    //     return new CategoriaRepositoryImpl(jdbcTemplate);
    // }
}