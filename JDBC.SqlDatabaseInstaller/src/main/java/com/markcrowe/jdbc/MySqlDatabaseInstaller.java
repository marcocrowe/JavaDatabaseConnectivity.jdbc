/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.markcrowe.jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 *
 * @author Conchraidh
 */
public class MySqlDatabaseInstaller
{
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException
	{
		ConnectionStringBuilder connectionStringBuilder = new ConnectionStringBuilder();
		String application = "mysql";
		connectionStringBuilder.setApplication(application);
		String password = "";
		connectionStringBuilder.setPassword(password);
		String server = "localhost";
		connectionStringBuilder.setServer(server);
		int serverPortNumber = 3306;
		connectionStringBuilder.setServerPortNumber(serverPortNumber);
		String username = "root";
		connectionStringBuilder.setUsername(username);

		String mySqlDatabase = "mysql";
		connectionStringBuilder.setDatabase(mySqlDatabase);

		Properties authenticationProperties = connectionStringBuilder.buildAuthenticationProperties();
		String connectionString = connectionStringBuilder.buildConnectionString();
		Connection connection = DriverManager.getConnection(connectionString, authenticationProperties);

		String databaseName = "NewDba123a";

		if(isDatabaseNameInUse(databaseName, connection))
		{
			System.out.println("Database name is in use");
		}
		else
		{
			createDatabase(databaseName, connection);
			System.out.println(String.format("Database %s has been created", databaseName));
			String sqlScriptFilePath = "*.sql";
			executeSqlScript(sqlScriptFilePath, connection);
		}
	}
	public static void createDatabase(String databaseName, Connection connection) throws SQLException
	{
		String createDatabaseSql = String.format("Create Database `%s`", databaseName);

		try(Statement createStatement = connection.createStatement())
		{
			createStatement.executeUpdate(createDatabaseSql);
		}
	}
	private static void executeSqlScript(String sqlScriptFilePath, Connection databaseConnection) throws FileNotFoundException, IOException
	{
		ScriptRunner scriptRunner = new ScriptRunner(databaseConnection);
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(sqlScriptFilePath)))
		{
			scriptRunner.runScript(bufferedReader);
		}
	}
	public static boolean isDatabaseNameInUse(String databaseName, Connection connection) throws SQLException
	{
		String sql = "SHOW DATABASES LIKE ?;";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, databaseName);
			try(ResultSet resultSet = preparedStatement.executeQuery())
			{
				return resultSet.next();
			}
		}
	}
}
