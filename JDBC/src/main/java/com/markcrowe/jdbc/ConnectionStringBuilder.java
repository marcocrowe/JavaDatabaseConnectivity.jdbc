/*
 * Copyright (c) 2020 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package com.markcrowe.jdbc;

import java.util.Properties;

public class ConnectionStringBuilder
{
	public ConnectionStringBuilder()
	{
	}
	public ConnectionStringBuilder(String application, String database, String password, String server, int serverPortNumber, String username)
	{
		this.application = application;
		this.database = database;
		this.password = password;
		this.server = server;
		this.serverPortNumber = serverPortNumber;
		this.username = username;
	}
	/* Public Properties */
	public String getApplication()
	{
		return application;
	}
	public ConnectionStringBuilder setApplication(String application)
	{
		this.application = application;
		return this;
	}
	public String getDatabase()
	{
		return database;
	}
	public ConnectionStringBuilder setDatabase(String database)
	{
		this.database = database;
		return this;
	}
	public String getPassword()
	{
		return password;
	}
	public ConnectionStringBuilder setPassword(String password)
	{
		this.password = password;
		return this;
	}
	public String getServer()
	{
		return server;
	}
	public ConnectionStringBuilder setServer(String server)
	{
		this.server = server;
		return this;
	}
	public int getServerPortNumber()
	{
		return serverPortNumber;
	}
	public ConnectionStringBuilder setServerPortNumber(int serverPortNumber)
	{
		this.serverPortNumber = serverPortNumber;
		return this;
	}
	public ConnectionStringBuilder setServerPortNumber(String serverPortNumber)
	{
		this.setServerPortNumber(Integer.parseInt(serverPortNumber));
		return this;
	}
	public String getUsername()
	{
		return username;
	}
	public ConnectionStringBuilder setUsername(String username)
	{
		this.username = username;
		return this;
	}
	/* Public Methods */
	public Properties buildAuthenticationProperties()
	{
		Properties properties = new Properties();
		properties.put("user", username);
		properties.put("password", password);
		return properties;
	}
	public String buildConnectionString()
	{
		return "jdbc:" + application + "://" + server + ":" + serverPortNumber + "/" + database;
	}
	/* Private Fields */
	private String application;
	private String database;
	private String password;
	private String server;
	private int serverPortNumber;
	private String username;
}
