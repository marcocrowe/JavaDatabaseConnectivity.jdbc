/*
 * Copyright (c) 2020 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package com.markcrowe.jdbc;

/**
 * SQLException Suggested Fixes
 */
public class SQLExceptionSuggestedFixes
{
	public static final String AccessDenied = "Check user permissions for '%s' on database.";
	public static final String AuthenticationError = "Check username and password.";
	public static final String MissingDriver = "Check JDBC application and then check project dependancies. eg. mysql and mysql-connector-java.";
	public static final String UndefinedErrorCode = "Undefined error code. You are on your own.";
	public static final String UnknownDatabase = "Check database name.";
	/* Public Static Methods */
	/**
	 * Gets a message that suggests how to fix for the error.
	 *
	 * @param sqlErrorCode
	 * @param username
	 * @return
	 */
	public static String getSuggestedFixMessage(int sqlErrorCode, String username)
	{
		switch(sqlErrorCode)
		{
			case SQLExceptionErrorCodes.AccessDenied:
				return String.format(AccessDenied, username);
			case SQLExceptionErrorCodes.AuthenticationError:
				return AuthenticationError;
			case SQLExceptionErrorCodes.MissingDriver:
				return MissingDriver;
			case SQLExceptionErrorCodes.UnknownDatabase:
				return UnknownDatabase;
			default:
				return UndefinedErrorCode;
		}
	}
}
