/*
 * Copyright (c) 2020 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package com.markcrowe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides methods to Test a JDBC Connection
 */
public class JdbcConnectionTest
{
	/**
	 * Tests a Database Connection String (built by the ConnectionStringBuilder)
	 *
	 * @param connectionStringBuilder The ConnectionStringBuilder
	 */
	public static void testConnection(ConnectionStringBuilder connectionStringBuilder)
	{
		Properties authenticationProperties = connectionStringBuilder.buildAuthenticationProperties();
		String connectionString = connectionStringBuilder.buildConnectionString();
		String username = connectionStringBuilder.getUsername();

		testConnection(connectionString, authenticationProperties, username);
	}
	/**
	 * Tests a Database Connection String (with an authentication Properties object)
	 *
	 * @param connectionString The Database Connection String
	 * @param authenticationProperties The Authentication Properties (username, password, ...) for the Database connection.
	 * @param username The username for the Database Connection.
	 */
	public static void testConnection(String connectionString, Properties authenticationProperties, String username)
	{
		Connection connection = null;
		try
		{
			connection = DriverManager.getConnection(connectionString, authenticationProperties);
			if(connection != null)
			{
				System.out.println("Connected to the database.");
			}
		}
		catch(SQLException exception)
		{
			String suggestedFixMessage = SQLExceptionSuggestedFixes.getSuggestedFixMessage(exception.getErrorCode(), username);
			System.out.println(suggestedFixMessage);
			System.out.println(exception);
			//Logger.getLogger(JdbcConnectionTest.class.getName()).log(Level.SEVERE, null, exception);
		}
		finally
		{
			if(connection != null)
			{
				try
				{
					connection.close();
				}
				catch(SQLException exception)
				{
					Logger.getLogger(JdbcConnectionTest.class.getName()).log(Level.SEVERE, null, exception);
				}
				finally
				{

				}
			}
		}
	}
}
