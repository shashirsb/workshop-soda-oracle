package com.oracle.db.soda.workshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import oracle.soda.OracleCollection;
import oracle.soda.OracleCursor;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;
import org.apache.commons.lang3.StringUtils;

public class Documents {
	
	Scanner input = new Scanner(System.in);

	public boolean createDocumentByString(OracleDatabase db, OracleCollection col, String stringToParse) {
		try {

			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(stringToParse.replace("\\", ""));

			for (int i = 0; i < jsonArray.size(); i++) {

				// Create a JSON document.
				OracleDocument doc = db.createDocumentFromString(jsonArray.get(i).toString());// .createDocumentFromString(jsonArray.get(i).toString());

				// Insert the document into a collection.
				col.insertAndGet(doc);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// SODA for Java throws a checked OracleException
		catch (OracleException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean createDocumentByFile(OracleDatabase db, OracleCollection col, String fileToParse) {
		try {

			// We need to provide file path as the parameter:
			// double backquote is to avoid compiler interpret words
			// like \test as \t (ie. as a escape sequence)

			File file = new File(fileToParse);

			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			String stringToParse = "";
			while ((st = br.readLine()) != null) {
				stringToParse += st;
			}
				
//			System.out.println("********************* 1");
//			System.out.println(stringToParse);
//			System.out.println("********************* 2");
			
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(stringToParse.replace("\\", ""));
			
//			System.out.println("********************* 3");
//			System.out.println(jsonArray.toString());
//			System.out.println("********************* 4");

			for (int i = 0; i < jsonArray.size(); i++) {

				// Create a JSON document.
				OracleDocument doc = db.createDocumentFromString(jsonArray.get(i).toString());// .createDocumentFromString(jsonArray.get(i).toString());

				// Insert the document into a collection.
				col.insertAndGet(doc);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// SODA for Java throws a checked OracleException
		catch (OracleException e) {
			e.printStackTrace();
			return false;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}
	
	public boolean getAllDocument(OracleCollection col) {
		try {

			// Find all documents in the collection.
			OracleCursor c = null;

			try {
				c = col.find().getCursor();
				OracleDocument resultDoc;

				while (c.hasNext()) {
					// Get the next document.
					resultDoc = c.next();

					// Print document components
					System.out.println("Key:         " + resultDoc.getKey());
					System.out.println("Content:     " + resultDoc.getContentAsString());
					System.out.println("Version:     " + resultDoc.getVersion());
					System.out.println("Last modified: " + resultDoc.getLastModified());
					System.out.println("Created on:    " + resultDoc.getCreatedOn());
					System.out.println("Media:         " + resultDoc.getMediaType());
					System.out.println("\n");
				}
			} finally {
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
		catch (OracleException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	
	public boolean getDocumentByKey(OracleCollection col, String key) {
		try {

			// Find all documents in the collection.
			OracleCursor c = null;

			try {
		
				OracleDocument resultDoc = col.find().key(key).getOne();;


					// Print document components
					System.out.println("Key:         " + resultDoc.getKey());
					System.out.println("Content:     " + resultDoc.getContentAsString());
					System.out.println("Version:     " + resultDoc.getVersion());
					System.out.println("Last modified: " + resultDoc.getLastModified());
					System.out.println("Created on:    " + resultDoc.getCreatedOn());
					System.out.println("Media:         " + resultDoc.getMediaType());
					System.out.println("\n");

			} finally {
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
		catch (OracleException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public String searchDocuments(OracleDatabase db, OracleCollection col, String key, String filterString) {
		Integer count = 0;
		try {				
			
			String _valueFilter =filterHelper(key, filterString);
			System.out.println("\nFilter : " + _valueFilter + "\n");
			OracleDocument filterSpec =
			  db.createDocumentFromString(_valueFilter);
			 
			OracleCursor c = col.find().filter(filterSpec).getCursor();

			try {
			
				OracleDocument resultDoc;
				

				while (c.hasNext()) {
					// Get the next document.
					resultDoc = c.next();

					// Print document components
					System.out.println("Key:         " + resultDoc.getKey());
					System.out.println("Content:     " + resultDoc.getContentAsString());
					System.out.println("Version:     " + resultDoc.getVersion());
					System.out.println("Last modified: " + resultDoc.getLastModified());
					System.out.println("Created on:    " + resultDoc.getCreatedOn());
					System.out.println("Media:         " + resultDoc.getMediaType());
					System.out.println("\n");
					count++;
				}
			} finally {
		
				if (c != null)
					try {
						c.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return "Error while searching";
					}
			}

		}
		// SODA for Java throws a checked OracleException
		catch (OracleException e) {
			e.printStackTrace();
			return "Error while searching";
		}
		
		return "Documents found : " + count ;

	}
	
	public boolean saveDocument(OracleDatabase db, OracleCollection col, String stringToParse, String key) {
		try {

			// Create a JSON document.
			OracleDocument doc = db.createDocumentFrom(key, stringToParse);

			// Insert the document into a collection.
			col.saveAndGet(doc);

		}
		// SODA for Java throws a checked OracleException
		catch (OracleException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
	
	public boolean replaceDocumentByKey(OracleDatabase db, OracleCollection col, String key, String docContent) {
		try {
			
				//docContent.replaceAll("^\"|\"$", "\"");
				
				OracleDocument newDoc = db.createDocumentFromString(docContent);
				OracleDocument resultDoc = col.find().key(key).replaceOneAndGet(newDoc);
				

					// Print document components
					System.out.println("Key:         " + resultDoc.getKey());
					System.out.println("Content:     " + resultDoc.getContentAsString());
					System.out.println("Version:     " + resultDoc.getVersion());
					System.out.println("Last modified: " + resultDoc.getLastModified());
					System.out.println("Created on:    " + resultDoc.getCreatedOn());
					System.out.println("Media:         " + resultDoc.getMediaType());
					System.out.println("\n");

		

		}
		// SODA for Java throws a checked OracleException
		catch (OracleException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	
	public boolean removeDocumentByKey(OracleCollection col, String key) {
		try {
			
			col.find().key(key).remove();	

		}
		// SODA for Java throws a checked OracleException
		catch (OracleException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	private String filterHelper(String key, String values) {
		String _Filter = "";
		if (key != null && values != null) {
			List < String > _values = Arrays.asList(values.split("\\s*,\\s*"));

			String step1 = StringUtils.join(_values,"\", \""); 
			String step2 = StringUtils.wrap(step1, "\"");			
			_Filter = "{\""+ key+"\" : {\"$in\" : [" + step2 + "]}}";
	
			
			return _Filter;
		} else {
			_Filter = "{}";
		}
		return _Filter;
	}
}
