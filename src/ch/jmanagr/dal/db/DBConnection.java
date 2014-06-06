package ch.jmanagr.dal.db;

import org.sql2o.Connection;

public class DBConnection
{
	// Todo: idea: wrap a sql2o connection, provide query methods (currently in DB class) and provide transaction
	// (maybe as a "weak" singleton)

	private DB db;
	private Connection connection;
	private static DBConnection instance;
}
