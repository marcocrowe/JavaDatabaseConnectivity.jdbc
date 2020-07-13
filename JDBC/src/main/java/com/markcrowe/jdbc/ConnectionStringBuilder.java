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
	//
	//	Public Properties
	//
	public String getApplication()
	{
		return application;
	}
	public void setApplication(String application)
	{
		this.application = application;
	}
	public String getDatabase()
	{
		return database;
	}
	public void setDatabase(String database)
	{
		this.database = database;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getServer()
	{
		return server;
	}
	public void setServer(String server)
	{
		this.server = server;
	}
	public int getServerPortNumber()
	{
		return serverPortNumber;
	}
	public void setServerPortNumber(int serverPortNumber)
	{
		this.serverPortNumber = serverPortNumber;
	}
	public void setServerPortNumber(String serverPortNumber)
	{
		this.setServerPortNumber(Integer.parseInt(serverPortNumber));
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	//
	//	Public Methods
	//
	public Properties buildAuthenticationProperties()
	{
		Properties properties = new Properties();
		properties.put("user", username);
		properties.put("password", password);
		//
		//	return
		//
		return properties;
	}
	public String buildConnectionString()
	{
		return "jdbc:" + application + "://" + server + ":" + serverPortNumber + "/" + database;
	}
	//
	//	Private Fields
	//
	private String application;
	private String database;
	private String password;
	private String server;
	private int serverPortNumber;
	private String username;
}
