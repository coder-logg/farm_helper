package isbd.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DBTest {
	static Connection connection;
	static private String pass ="postgres";
	static private String url = "jdbc:postgresql://localhost:5432/postgres";
	static private String user = "postgres";

	@BeforeAll
	static void init() throws IOException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		PGSimpleDataSource ds = new PGSimpleDataSource();
		ds.setURL(url);
		ds.setPassword(pass);
		ds.setUser(user);
		connection = ds.getConnection();
//		execSQLFile("src/test/resources/sql/import.sql");
	};

//	@Test
//	void triggersTest(){
//		execSQLFile("src/test/resources/sql/triggers.sql");
//	}
//
//	@Test
//	void filling(){
//		execSQLFile("src/test/resources/sql/filling.sql");
//	}

	@AfterAll
	static void finish() throws SQLException {
//		execSQLFile("src/test/resources/sql/delete_tables.sql");
		connection.close();
	}

	static void execSQLFile(String path) {
		try {
			Scanner scanner = new Scanner(new File(path));
			scanner.useDelimiter(";");
			while (scanner.hasNext()) {
				String statement = scanner.next();
				try {
					connection.createStatement().execute(statement);
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage() + "\n\t" + statement, e);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
