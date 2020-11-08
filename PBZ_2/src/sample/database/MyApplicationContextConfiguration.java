package sample.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class MyApplicationContextConfiguration {

    @Bean
    @Scope("singleton")
    public Connection connection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/second_lab?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String password = "sasha19062001";
        return DriverManager.getConnection(url, user, password);
    }
}
