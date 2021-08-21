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
			String userInput = consoleScanner.next();
			switch(userInput)
			{
				case exit:
					exitApplication();
					break;
				case help:
					printInstructions();
					break;
				case mysql:
					testDefaultMySqlRootConnectionValues();
					break;
				case test:
					testDatabase();
					break;
				default:
					handleUnrecognizedCommand(userInput);
					break;
			}
		}
		while(isRunning);
	}
	/**
	 * Exit the application
	 */
	private void exitApplication()
	{
		isRunning = false;
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

		ConnectionStringBuilder connectionStringBuilder = new ConnectionStringBuilder()
				.setApplication("mysql")
				.setDatabase(database)
				.setPassword(password)
				.setServer("localhost")
				.setServerPortNumber(3306)
				.setUsername("root");

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

		ConnectionStringBuilder connectionStringBuilder = new ConnectionStringBuilder()
				.setApplication(application)
				.setDatabase(database)
				.setPassword(password)
				.setServer(server)
				.setServerPortNumber(serverPortNumber)
				.setUsername(username);

		JdbcConnectionTest.testConnection(connectionStringBuilder);
	}
	/**
	 * Handle unrecognized Commands
	 *
	 * @param userInput The command the use enter
	 */
	private void handleUnrecognizedCommand(String userInput)
	{
		output(String.format("'%s' is not a unrecognized command", userInput));
		output(String.format("Type '%s' to print the program instructions.", help));

	}
	private void output(String text)
	{
		System.out.println(text);
	}
	/* Private Fields */
	private Scanner consoleScanner;
	private boolean isRunning = true;
	/* menu options */
	private final String help = "help";
	private final String exit = "exit";
	private final String mysql = "mysql";
	private final String test = "test";
}
