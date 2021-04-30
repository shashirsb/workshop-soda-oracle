package com.oracle.db.soda.workshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
 
import oracle.soda.rdbms.OracleRDBMSClient;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleCursor;
import oracle.soda.OracleCollection;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
 
import java.util.Properties;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.stream.Stream;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import oracle.jdbc.OracleConnection;
import com.oracle.db.soda.workshop.menuList;
import com.oracle.db.soda.workshop.Producer;

import org.apache.commons.lang3.StringUtils;
 
public class testSoda
{
	public static OracleDatabase db;

  public static void main(String[] arg)
  {
	  Producer sodaproducer = new Producer();
 
      try
      {

    	  // Calling Main Menu for user input
    	  menuList menulist = new menuList();
    	  menulist.mainMenu();    	  
    	  
    	  
    	 db = sodaproducer.dbConnect();
 
    }
    
    catch (Exception e) { e.printStackTrace(); }
    finally 
    {
    	sodaproducer.dbDisconnect();
    }
  }

  

}