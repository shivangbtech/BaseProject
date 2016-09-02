package com.example.baseproject.database;

import android.database.SQLException;

/**
 *This class is used to create database query 
 */
public class CreateCommands {
//	private String createTableCmd = "Create Table "; // comments " + "(_id
		private String createTableCmd = "Create Table IF NOT EXISTS "; // comments " + "(_id
	// integer primary key
	// autoincrement,
	// " + "comment text not
	// null);";
	private String insertCommand = "INSERT INTO ";// table_name (column1,

	// column2,
	// column3,...)VALUES
	// (value1, value2,
	// value3,...)";

	public CreateCommands() {

	}
/**
 * Call this method to generate database query
 * @param tableName Name of table 
 * @param arguments array of arguments
 * @param argTypes array of arguments 
 * @return generated sqlite query 
 * @throws SQLException
 */
	public String createCommand(String tableName, String[] arguments, String[] argTypes) throws SQLException {
		if (arguments.length != argTypes.length) {
			throw new SQLException();
		}
		int lenght = arguments.length;
		String createTableCommand = createTableCmd + tableName + " (";

		for (int i = 0; i < lenght; ++i) {
			createTableCommand += arguments[i] + " " + argTypes[i];
			if (i < lenght - 1) {
				createTableCommand += ", ";
			}
		}
		return createTableCommand + ");";

	}
/**
 * Call this method to insert values in database tables
 * @param tableName Name of table
 * @param columnNames array of column's name
 * @param values array of values
 * @return generated sqlite query
 * @throws SQLException
 */
	public String insertCommand(String tableName, String[] columnNames, String[] values) throws SQLException {
		if (columnNames.length != values.length) {
			throw new SQLException();
		}
		insertCommand += tableName + " (";
		int lenght = columnNames.length;
		for (int i = 0; i < lenght; ++i) {
			insertCommand += columnNames[i];
			if (i < lenght - 1) {
				insertCommand += ", ";
			}
		}
		insertCommand += ") VALUES (";
		for (int i = 0; i < lenght; ++i) {
			insertCommand += values[i];

			if (i < lenght - 1) {
				insertCommand += ", ";
			}
		}
		return insertCommand + ");";

	}
}
