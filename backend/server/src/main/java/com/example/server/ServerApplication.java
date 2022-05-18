package com.example.server;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication @Component
public class ServerApplication implements ApplicationRunner {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	//@Autowired
	@org.springframework.beans.factory.annotation.Autowired(required=true)
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	@Modifying
	public void run(ApplicationArguments args) throws Exception {
		try (Connection connection = dataSource.getConnection()){
			System.out.println(dataSource.getClass());
			System.out.println(connection.getMetaData().getURL());
			System.out.println(connection.getMetaData().getUserName());

			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE t_user(user_no INTEGER NOT NULL, user_name VARCHAR(255), PRIMARY KEY (user_no))";
			statement.executeUpdate(sql);
//			sql = "SELECT * FROM user";
//			statement.executeUpdate(sql);
		}
		jdbcTemplate.execute("INSERT INTO t_user VALUES (1, '홍명주')");
	}
}