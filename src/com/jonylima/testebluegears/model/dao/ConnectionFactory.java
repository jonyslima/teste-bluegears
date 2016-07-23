package com.jonylima.testebluegears.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite: bluegears.db");
			createTable(connection);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao conectar com o banco de dados!");
		}
		return connection;
	}

	public static void createTable(Connection conn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS itens( ");
		sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append(" foto VARCHAR(255) NOT NULL, ");
		sql.append(" nome VARCHAR(255) NOT NULL, ");
		sql.append(" descricao TEXT NOT NULL, ");
		sql.append(" selecionado INT NOT NULL DEFAULT '0', ");
		sql.append(" dt_selecao DATETIME ) ");

		conn.createStatement().execute(sql.toString());
	}

}
