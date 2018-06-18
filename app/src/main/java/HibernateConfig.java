import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class HibernateConfig {

  @Bean
  public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("org.postgresql.Driver");
      dataSource.setUrl("jdbc:postgresql://localhost:5432/personneldb");
      dataSource.setUsername("postgres");
      dataSource.setPassword("ex1stgl0bal");
      return dataSource;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setHibernateProperties(hibernateProperties());
    sessionFactory.setPackagesToScan(new String[] {"com.exist.ecc.model"});
    return sessionFactory;
  }

  Properties hibernateProperties() {
    return new Properties() {
       {
          setProperty("hibernate.dialect",
            "org.hibernate.dialect.PostgreSQLDialect");
          setProperty("hibernate.show_sql",
            "false");
          setProperty("hibernate.cache.region.factory_class",
            "org.hibernate.cache.ehcache.EhCacheRegionFactory");
          setProperty("hibernate.cache.use_second_level_cache",
            "true");
          setProperty("hibernate.cache.use_query_cache",
            "true");  
       }
    };
  }
}