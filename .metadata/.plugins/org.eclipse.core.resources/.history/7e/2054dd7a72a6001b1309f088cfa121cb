package com.oracle.db.soda.workshop;

import java.io.IOException;
import java.util.Scanner;

import oracle.soda.OracleCollection;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleException;

public class menuList {
	
	Scanner input = new Scanner(System.in);
	 private static int menu, subMenuCollection, subMenuDocument;
	 private static String COLLECTION_NAME;
	 private static OracleDatabase db;
	 private static OracleCollection col;
	
	 public void mainMenu() {
		
		 this.cleanScreen();
	    	while(true) {  
	    		this.cleanScreen();
	    		if(menu == 3) {
	    			System.out.println("Exiting the app");
		    		break;
		    	}
	    	 
	    	 System.out.println("Please select the options from list :");
	    	 System.out.println("1. Work with JSON Collection");
	    	 System.out.println("2. Work with JSON Document");
	    	 System.out.println("3. Exit");
	    	 
	    	 
	    	 menu = input.nextInt();

	    	 switch (menu) {
	    	  case 1:
	    		this.subMenuCollection();			    	    
	    	    break;
	    	  case 2:
	    	    this.subMenuDocument();
	    	    break;   
	    	 }
	    	}	        
	    }
	 
	 public void subMenuCollection() {
		 this.cleanScreen();
 	    System.out.println("Work with JSON Collection");
 	    System.out.println("===========================");
 	   subMenuCollection = 0;
 	    while(true) {  
 	    	 this.cleanScreen();
 	    	if(subMenuCollection == 4) {    
 	    		System.out.println("Go to Main Menu");
 	    		break;
 	    	}
 	    	 
 	    	 System.out.println("Please select the options from list :");
 	    	 System.out.println("1. Enter/Change JSON Collection");
 	    	 System.out.println("2. Create new JSON Collection");
 	    	 System.out.println("3. Drop JSON Collection");
 	    	 System.out.println("4. Go to previous menu");
 	    	 
 	    	
 	    	subMenuCollection = input.nextInt();
 	    	 
 	    	 switch (subMenuCollection) {
		      	  case 1:
		      		System.out.println();
		      	    System.out.println("\n Enter the name of the collection");
		      	    COLLECTION_NAME = input.next();
		      	    break;
		      	  case 2:   		      	    	 
		      	    	
						try {								
							System.out.println("\n Enter the name of the collection");
				      	    COLLECTION_NAME = input.next();
				      	    sodaProducer sodaproducer = new sodaProducer();
							db = sodaproducer.dbConnect();	
							col = this.db.admin().createCollection(COLLECTION_NAME);
							System.out.println ("\nCollection " + COLLECTION_NAME +" was created");
							sodaproducer.dbDisconnect();
						} catch (OracleException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		                
		      	    
		      	    break; 
		      	  case 3:	 	    	
						try {
							System.out.println("\n Enter the name of the collection");
				      	    COLLECTION_NAME = input.next();
							sodaProducer sodaproducer = new sodaProducer();
							db = sodaproducer.dbConnect();						          
							col = this.db.admin().createCollection(COLLECTION_NAME);
							col.admin().drop();
				            System.out.println ("\nCollection " + COLLECTION_NAME +" dropped\n");
				            sodaproducer.dbDisconnect();
						} catch (OracleException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	      	    
		      	    break;
		      		 
	      	 }
 	    }
		 
	 }
	 
	 public void subMenuDocument() {
		 this.cleanScreen();
	 	    System.out.println("Work with JSON Document");
	 	    System.out.println("===========================");
	 	   subMenuDocument = 0;
	 	    while(true) {  
	 	    	 this.cleanScreen();
	 	    	if(subMenuDocument == 6) {    
	 	    		System.out.println("Go to Main Menu");
	 	    		break;
	 	    	}
	 	    	 
	 	    	 System.out.println("Please select the options from list :");
	 	    	 System.out.println("1. Enter/Change JSON Collection");
	 	    	 System.out.println("2. Create documents from String");
	 	    	 System.out.println("3. Create documents from File");
	 	    	 System.out.println("4. Get all documents");
	 	    	 System.out.println("5. Get document by ID");
	 	    	 System.out.println("6. Go to previous menu");
	 	    	 
	 	    	
	 	    	subMenuDocument = input.nextInt();
	 	    	 
	 	    	 switch (subMenuDocument) {
			      	  case 1:
			      		System.out.println();
			      	    System.out.println("\n Enter the name of the collection");
			      	    COLLECTION_NAME = input.next();
			      	    break;
			      	case 2:
			      	    System.out.println("Create documents from String \n");
			      	    
			      	 
			      
			      	    
			      	    if(COLLECTION_NAME == null) {
			      	    	System.out.println("\n Please select a SODA Collection to continue....\n");
			      	    	break;
			      	    } else {		      	    	 
			      	    	
							try {

						        input.nextLine();
						        
						     // [{\"name\":\"Alexander\"}, {\"name\":\"Shashi\"}]
								System.out.println("Enter json string to create document:");
								
				                String jsonString = input.nextLine();
				      
				               
				                System.out.println(jsonString);
								sodaProducer sodaproducer = new sodaProducer();
								
								db = sodaproducer.dbConnect();
								col = this.db.admin().createCollection(COLLECTION_NAME);			                
				        
				                com.oracle.db.soda.workshop.Documents createdoc =new Documents();
				                Boolean createDocStatus = createdoc.createDocument(db, col, jsonString);
				                System.out.println("Create Document Status : " + createDocStatus);
				          
				                
				               
							} catch (OracleException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}		                
			      	    }
			      	    break;
			      	  case 4:
			      	    System.out.println("Get All documents \n");
			      	    System.out.println("Current SODA Collection : " + COLLECTION_NAME + "\n");
			      	    
			      	    if(COLLECTION_NAME == null) {
			      	    	System.out.println("\n Select a SODA Collection to continue....\n");
			      	    	break;
			      	    } else {		      	    	 
			      	    	
							try {
								sodaProducer sodaproducer = new sodaProducer();
								db = sodaproducer.dbConnect();
								col = this.db.admin().createCollection(COLLECTION_NAME);
								com.oracle.db.soda.workshop.Documents getdoc = new Documents();
				                Boolean getDocStatus = getdoc.getAllDocument(col);
				                System.out.println("get All Document Status : " + getDocStatus);
				                
				                System.out.println("\n Please enter 'Y' to continue");
				                String enter = input.next();
							} catch (OracleException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}		                
			      	    }
			      	    break;   	    
		      	 }
	 	    }
		
	 }
	 
	 public void cleanScreen() {
		 try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	  

}
