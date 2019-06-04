package lk.ijse.pos.main;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = "lk.ijse.pos")
@Import(HibernateConfig.class)
public class AppConfig {
}
