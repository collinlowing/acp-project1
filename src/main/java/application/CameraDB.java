package application;

import java.util.ArrayList;

import database.DerbyDatabase;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

class CameraDB {
	private int n = 10;
	private ArrayList<Camera> cameras = new ArrayList<Camera>();
	private ArrayList<String> columns = new ArrayList<String>();
	private ArrayList<String> types = new ArrayList<String>();
	private Connection conn;

	public CameraDB() {
		this(10);
	}

	public CameraDB(int n) {
		this.n = n;

		// Create n number of random cameras.
		for (int i = 0; i < this.n; i++) {
			Camera c = CameraFactory.createRandomCamera();
			cameras.add(c);
		}

		// Print out all cameras in ArrayList
		System.out.println("List of cameras:");
		for (int i = 0; i < cameras.size(); i++) {
			System.out.println(cameras.get(i).toString());
		}

		try {
			// Create Database and get connection
			DerbyDatabase.init();
			conn = DerbyDatabase.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void storeCameras() {
		// Reflection to write SQL statements
		// Get class name for table name
		String className = cameras.get(0).getClass().getName();
		String tableName = parseClassName(className);
		// Get class fields for table columns
		Field[] fields = cameras.get(0).getClass().getDeclaredFields();
		// Parse for table columns to define table
		String tableColumns = parseColumns(fields);
		String dropTableStatement = "DROP TABLE " + tableName;
		String createTableStatement = "CREATE TABLE " + tableName + " (" + tableColumns + ")";

		ArrayList<String> insertStatements = new ArrayList<String>();

		for (int i = 0; i < cameras.size(); i++) {
			String rowValues = parseRows(cameras.get(i));
			insertStatements.add("INSERT INTO " + tableName + " VALUES (" + rowValues + ")");
		}

		try {
			// Drop already created table
			Statement stmt = conn.createStatement();
			stmt.execute(dropTableStatement);
			System.out.println("Table dropped");
		} catch (SQLException e) {
			System.out.println("Table drop failed");
		}

		try {
			// Create statement for executing commands
			Statement stmt = conn.createStatement();
			// Create new table
			stmt.execute(createTableStatement);
			System.out.println("Table created");

			// Insert list of rows into database
			for (int i = 0; i < insertStatements.size(); i++) {
				stmt.execute(insertStatements.get(i));
			}
			System.out.println("Rows inserted\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String parseClassName(String className) {
		String str;
		String[] tokens = className.split("\\.");
		int tokenSize = tokens.length;
		if (tokenSize > 1)
			str = tokens[tokenSize - 1];
		else
			str = className;

		return str;
	}

	private String parseColumns(Field[] fields) {
		String str = "";
		for (int i = 0; i < fields.length; i++) {
			if (i > 0)
				str += ", ";
			// Get Field name for SQL column name
			columns.add(fields[i].getName());
			str += fields[i].getName() + " ";
			// Convert to SQL types
			if (fields[i].getType().getSimpleName().compareTo("String") == 0)
			{
				types.add("String");
				str += "CHAR(50)";
			}
			
			if (fields[i].getType().getSimpleName().compareTo("int") == 0)
			{
				types.add("int");
				str += "INTEGER";
			}
		}
		return str;
	}

	private String parseRows(Camera cam) {
		String str = "";
		
		str += "'" + cam.getBrand() + "',";
		str += "'" + cam.getModel() + "',";
		str += cam.getMegaPx() + ",";
		str += cam.getPrice();

		return str;
	}

	public void processQuery(String sqlQuery) {
		try {
			// Create statement
			Statement stmt = conn.createStatement();
			// Execute query and get result set
			ResultSet result = stmt.executeQuery(sqlQuery);
			// Get metadata for result set
			ResultSetMetaData rsm = result.getMetaData();
			// Get number of columns
			int cols = rsm.getColumnCount();
			// Print out query results
			while (result.next()) {
				for (int i = 1; i <= cols; i++)
					System.out.print(result.getString(i) + " ");
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}