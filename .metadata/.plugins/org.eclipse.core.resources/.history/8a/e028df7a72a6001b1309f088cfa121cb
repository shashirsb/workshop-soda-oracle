package com.oracle.db.soda.workshop;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import oracle.soda.OracleCollection;
import oracle.soda.OracleCursor;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;
import com.oracle.db.soda.workshop.testSoda;

public class Documents {

	public boolean createDocument(OracleDatabase db, OracleCollection col, String stringToParse){
		try {
			 
	        
	        JSONParser parser = new JSONParser();
	        JSONArray jsonArray = (JSONArray) parser.parse(stringToParse.replace("\\", ""));
	        
	        
	        for (int i = 0; i < jsonArray.size(); i++) {
	   
				// Create a JSON document.
				OracleDocument doc = db.createDocumentFromString(jsonArray.get(i).toString());// .createDocumentFromString(jsonArray.get(i).toString());
				
				// Insert the document into a collection.
				col.insert(doc);
			}

		}
		 catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// SODA for Java throws a checked OracleException
	    catch (OracleException e) 
		{ e.printStackTrace(); 
	    return false;
	    }
		return true;
		
	}
	
	public boolean getAllDocument(OracleCollection col){
		try {
			 
			// Find all documents in the collection.
	        OracleCursor c = null;
	 
	        try 
	        {
	          c = col.find().getCursor();
	          OracleDocument resultDoc;
	 
	          while (c.hasNext())
	          {
	            // Get the next document.
	            resultDoc = c.next();
	 
	            // Print document components
	            System.out.println ("Key:         " + resultDoc.getKey());
	            System.out.println ("Content:     " + resultDoc.getContentAsString());
	            System.out.println ("Version:     " + resultDoc.getVersion());
	            System.out.println ("Last modified: " + resultDoc.getLastModified());
	            System.out.println ("Created on:    " + resultDoc.getCreatedOn());
	            System.out.println ("Media:         " + resultDoc.getMediaType());
	            System.out.println ("\n");
	          }
	        }
	        finally
	        {
	          // IMPORTANT: YOU MUST CLOSE THE CURSOR TO RELEASE RESOURCES.
	          if (c != null)
				try {
					c.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
	        }

		}
		// SODA for Java throws a checked OracleException
	    catch (OracleException e) 
		{ e.printStackTrace(); 
	    return false;
	    }
		return true;
		
	}
}
