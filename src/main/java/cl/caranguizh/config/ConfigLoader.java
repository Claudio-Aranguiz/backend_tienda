package cl.caranguizh.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class ConfigLoader {

    @Autowired
    private Environment env;

    @Bean
    public Properties databaseProperties() {
        Properties props = new Properties();
        
        // Usar variables de entorno o valores de application.properties
        String dbDriver = System.getenv("DB_DRIVER");
        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");
        
        // Si no hay variables de entorno, usar las de application.properties
        props.setProperty("db.driver", dbDriver != null ? dbDriver : env.getProperty("db.driver"));
        props.setProperty("db.url", dbUrl != null ? dbUrl : env.getProperty("db.url"));
        props.setProperty("db.username", dbUsername != null ? dbUsername : env.getProperty("db.username"));
        props.setProperty("db.password", dbPassword != null ? dbPassword : env.getProperty("db.password"));
        
        // Configuración de Hibernate
        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        
        return props;
    }
    
    @Bean
    public InitializingBean validateConfigurationProperties() {
        return () -> {
            // Verificar que las propiedades requeridas estén configuradas
            if (databaseProperties().getProperty("db.driver") == null ||
                databaseProperties().getProperty("db.url") == null ||
                databaseProperties().getProperty("db.username") == null ||
                databaseProperties().getProperty("db.password") == null) {
                throw new RuntimeException("Faltan propiedades de configuración de la base de datos");
            }
        };
    }
}