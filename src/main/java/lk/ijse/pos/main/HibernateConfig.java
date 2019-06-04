package lk.ijse.pos.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@PropertySource("file:C:\\Users\\DELL\\Desktop\\New folder (7)\\POSJPASpring\\POSJPASpring-1\\resourse\\application.properties")
public class HibernateConfig {

    @Autowired
    private Environment env;

@Bean
public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource ds,JpaVendorAdapter jpaVendorAdapter){
    LocalContainerEntityManagerFactoryBean lcmef = new LocalContainerEntityManagerFactoryBean();
    lcmef.setDataSource(ds);
    lcmef.setJpaVendorAdapter(jpaVendorAdapter);
    lcmef.setPackagesToScan("lk.ijse.pos.entity");
return lcmef;

}

@Bean
public DataSource dataSource(){
    DriverManagerDataSource ds =new DriverManagerDataSource();
    ds.setDriverClassName(env.getRequiredProperty("javax.persistence.jdbc.driver"));
    ds.setUrl(env.getRequiredProperty("javax.persistence.jdbc.url"));
    ds.setUsername(env.getRequiredProperty("javax.persistence.jdbc.user"));
    ds.setPassword(env.getRequiredProperty("javax.persistence.jdbc.password"));
    return ds;

}
@Bean
public JpaVendorAdapter jpaVendorAdapter (){
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabase(Database.MYSQL);
    adapter.setDatabasePlatform(env.getRequiredProperty("hibernate.dialect"));
    adapter.setShowSql(Boolean.getBoolean(env.getRequiredProperty("hibernate.show_sql")));
    adapter.setGenerateDdl(env.getRequiredProperty("hibernate.hbm2ddl.auto").equalsIgnoreCase("update")?true:false);
    return adapter;
}
@Bean
public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
   return new JpaTransactionManager(emf);
}

}
