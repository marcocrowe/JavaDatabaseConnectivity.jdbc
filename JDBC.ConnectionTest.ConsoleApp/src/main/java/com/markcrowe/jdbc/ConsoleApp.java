/*
 * Copyright (c) 2020 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package com.markcrowe.jdbc;

import java.util.Scanner;

/**
 * Console Application
 */
public class ConsoleApp
{
	public ConsoleApp()
	{
	}
	/**
	 * Run the application
	 */
	public void run()
	{
		printInstructions();
		consoleScanner = new Scanner(System.in);
		do
		{
			output("Enter a command:");
			String userinput = consoleScanner.next();
			switch(userinput)
			{
				case exit ->
					exitApplication();
				case help ->
					printInstructions();
				case mysql ->
					testDefaultMySqlRootConnectionValues();
				case test ->
					testDatabase();
				default ->
					handleUnrecognizedCommand(userinput);
			}
		}
		while(contineRunningApp);
	}
	/**
	 * Exit the application
	 */
	private void exitApplication()
	{
		contineRunningApp = false;
		output("Exiting Program.");
	}
	/**
	 * Print instructions for the user
	 */
	private void printInstructions()
	{
		output(String.format("Type '%s' to test a JDBC connection", test));
		output(String.format("Type '%s' to test a MySQL Root JDBC connection", mysql));
		output(String.format("Type '%s' to print the program instructions.", help));
		output(String.format("Type '%s' to exit the program.", exit));
	}
	private void testDefaultMySqlRootConnectionValues()
	{
		System.out.println("Enter the database name:");
		String database = consoleScanner.next();

		System.out.println("Enter the root user password:");
		String password = consoleScanner.next();

		ConnectionStringBuilder connectionStringBuilder = new ConnectionStringBuilder();

		connectionStringBuilder.setApplication("mysql");
		connectionStringBuilder.setDatabase(database);
		connectionStringBuilder.setPassword(password);
		connectionStringBuilder.setServer("localhost");
		connectionStringBuilder.setServerPortNumber(3306);
		connectionStringBuilder.setUsername("root");

		JdbcConnectionTest.testConnection(connectionStringBuilder);
	}
	private void testDatabase()
	{
		output("Enter the database application type (e.g. mysql, sqlite):");
		String application = consoleScanner.next();

		output("Enter the server name/IP address:");
		String server = consoleScanner.next();

		output("Enter the server Port Number:");
		String serverPortNumber = consoleScanner.next();

		output("Enter the database name:");
		String database = consoleScanner.next();

		output("Enter the username:");
		String username = consoleScanner.next();

		output("Enter the password:");
		String password = consoleScanner.next();

		ConnectionStringBuilder connectionStringBuilder = new ConnectionStringBuilder();
		connectionStringBuilder.setApplication(application);
		connectionStringBuilder.setDatabase(database);
		connectionStringBuilder.setPassword(password);
		connectionStringBuilder.setServer(server);
		connectionStringBuilder.setServerPortNumber(serverPortNumber);
		connectionStringBuilder.setUsername(username);

		JdbcConnectionTest.testConnection(connectionStringBuilder);
	}
	/**
	 * Handle unrecognized Commands
	 *
	 * @param userinput The command the use enter
	 */
	private void handleUnrecognizedCommand(String userinput)
	{
		output(String.format("'%s' is not a unrecognised command", userinput));
		output(String.format("Type '%s' to print the program instructions.", help));

	}
	private void output(String text)
	{
		System.out.println(text);
	}
	//
	//	Private Fields
	//
	private Scanner consoleScanner;
	private Boolean contineRunningApp = true;
	//
	//	menu options
	//
	private final String help = "help";
	private final String exit = "exit";
	private final String mysql = "mysql";
	private final String test = "test";
}
