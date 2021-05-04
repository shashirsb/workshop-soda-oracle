package com.oracle.db.soda.workshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import oracle.soda.OracleDatabase;
import oracle.soda.rdbms.OracleRDBMSClient;

public class Producer {
	
	 public Connection conn = null;
	    public static OracleDatabase db = null;
	    
	    /**
	     * database connection info
	     */
	    private static boolean UseDB = true;
	    private final static String ATP_CONNECT_NAME = "altdb_medium";
	    private final static String ATP_PASSWORD_FILENAME = "atp_password.txt";
	    private final static String WALLET_LOCATION = "C:/oracle/engagement/2020-21/json4alltitude/code/Wallet_ALTDB";
	    private final static String DB_URL = "jdbc:oracle:thin:@" + ATP_CONNECT_NAME + "?TNS_ADMIN=" + WALLET_LOCATION;
	    private final static String DB_USER = "admin";
	    private static String DB_PASSWORD = "zT7_P53Ia1_A";
	
	public void dbDisconnect() {
		System.out.println("Disconnecting from Oracle SODA");
	      try { if (conn != null)  conn.close(); 
	      System.out.println("Closed db connection, thank you for using oracle soda.");
	      System.exit(0);
	      }
	      catch (Exception e) { }
	    
	}
	 public OracleDatabase dbConnect(){
// Set up the JDBC connection string, schemaName, and password.
// Replace with info appropriate for your Oracle Database instance.
//	      String url = "jdbc:oracle:thin:@//hostName:port/serviceName";
//	      Properties props = new Properties();
//	      props.setProperty("user", schemaName);
//	      props.setProperty("password", password);
// 
//	      OracleConnection conn = null;
	        try {       
	        	
        	 // Get a JDBC connection to an Oracle instance.
	         //	conn = (OracleConnection) DriverManager.getConnection(url, props);
	         //
	         // Enable JDBC implicit statement caching
             // conn.setImplicitCachingEnabled(true);
             //	conn.setStatementCacheSize(50);
	         // 
             // Get an OracleRDBMSClient - starting point of SODA for Java application.
             //	OracleRDBMSClient cl = new OracleRDBMSClient();
	         // 
	         // Get a database.
	         //	OracleDatabase db = cl.getDatabase(conn);
	        	
	         //
             // load password from file in wallet location
			 //	StringBuilder contentBuilder = new StringBuilder();
			 //	   try (Stream<String> stream = Files.lines( Paths.get(WALLET_LOCATION + "/" + ATP_PASSWORD_FILENAME), StandardCharsets.UTF_8)) {
			 //	        stream.forEach(s -> contentBuilder.append(s).append("\n"));
			 //	   }
			 //	   catch (IOException e) {
			 //	         e.printStackTrace();
			 //	    }
			
			 //	DB_PASSWORD = contentBuilder.toString();

	            // set DB properties
	            Properties props = new Properties();
	            props.setProperty("user", DB_USER);
	            props.setProperty("password", DB_PASSWORD);


	            // Get a JDBC connection to an Oracle instance.
	            conn = DriverManager.getConnection(DB_URL, props);

	             // Get an OracleRDBMSClient - starting point of SODA for Java application.
	             OracleRDBMSClient cl = new OracleRDBMSClient();

	             // Get a database.
	             db = cl.getDatabase(conn);

	            System.out.println("DB Connection established successfully!!!");
	         

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return db;
	    }

}
