package com.oracle.db.soda.workshop;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import oracle.soda.OracleCollection;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleException;

public class menuList {

	Scanner input = new Scanner(System.in);
	private static int menu, subMenuCollection, subMenuDocument;
	private static String COLLECTION_NAME;
	
	Producer sodaproducer = new Producer();
	public OracleDatabase db = sodaproducer.dbConnect();
	public static OracleCollection col;

	public void mainMenu() {

		this.cleanScreen();
		while (true) {
			this.cleanScreen();
			if (menu == 3) {
				System.out.println("Exiting the app");
				sodaproducer.dbDisconnect();
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
		Producer sodaproducer = new Producer();
		String enter = null;
		while (true) {
			this.cleanScreen();
			if (subMenuCollection == 5) {
				System.out.println("Go to Main Menu");
				break;
			}

			System.out.println("Please select the options from list :");
			System.out.println("1. Check JSON Collection Exists & Use");
			System.out.println("2. List all JSON Collections");
			System.out.println("3. Create new JSON Collection");
			System.out.println("4. Drop JSON Collection");
			System.out.println("5. Go to previous menu");

			subMenuCollection = input.nextInt();

			switch (subMenuCollection) {
			case 1:

				System.out.println();
				System.out.println("\n Enter the name of the collection");
				COLLECTION_NAME = input.next();

				try {
					//db = sodaproducer.dbConnect();
					col = this.db.openCollection(COLLECTION_NAME);
					if (col != null) {
						System.out.println("\nCollection " + COLLECTION_NAME + " exists :-) ");
					} else {
						System.out.println("\nCollection " + COLLECTION_NAME + " does not exists!!");
						COLLECTION_NAME = null;
					}

					//sodaproducer.dbDisconnect();
				} catch (OracleException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println("\n Please enter 'Y' to continue");
				enter = input.next();
				break;
			case 2:

				try {

					//db = sodaproducer.dbConnect();
					List<String> names = db.admin().getCollectionNames();
					for (String name : names)
						System.out.println("Collection name: " + name);
					//sodaproducer.dbDisconnect();
					System.out.println("\n Please enter 'Y' to continue");
					enter = input.next();
				} catch (OracleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 3:

				try {
					System.out.println("\n Enter the name of the collection");
					COLLECTION_NAME = input.next();
					//db = sodaproducer.dbConnect();
					col = this.db.admin().createCollection(COLLECTION_NAME);
					System.out.println("\nCollection " + COLLECTION_NAME + " was created");
					//sodaproducer.dbDisconnect();
					System.out.println("\n Please enter 'Y' to continue");
					enter = input.next();
				} catch (OracleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 4:
				try {
					System.out.println("\n Enter the name of the collection");
					COLLECTION_NAME = input.next();
					//db = sodaproducer.dbConnect();
					col = this.db.admin().createCollection(COLLECTION_NAME);
					col.admin().drop();
					System.out.println("\nCollection " + COLLECTION_NAME + " dropped\n");
					//sodaproducer.dbDisconnect();
					System.out.println("\n Please enter 'Y' to continue");
					enter = input.next();
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
		while (true) {
			this.cleanScreen();
			if (subMenuDocument == 10) {
				System.out.println("Go to Main Menu");
				break;
			}

			System.out.println("Please select the options from list :");
			System.out.println("1. Enter/Change JSON Collection");
			System.out.println("2. Create documents from String");
			System.out.println("3. Create documents from File");
			System.out.println("4. Save a document");
			System.out.println("5. Get all documents");
			System.out.println("6. Get document by Key");
			System.out.println("7. Search for document with filter");
			System.out.println("8. Replace a document");
			System.out.println("9. Remove document by Key");
			System.out.println("10. Go to previous menu");

			subMenuDocument = input.nextInt();

			switch (subMenuDocument) {
			case 1:
				System.out.println();
				System.out.println("\n Enter the name of the collection");
				COLLECTION_NAME = input.next();
				break;
			case 2:
				System.out.println("Create documents from String \n");

				if (COLLECTION_NAME == null) {
					System.out.println("\n Please select a SODA Collection to continue....\n");
					System.out.println("\n Enter 'Y' to continue");
					String enter = input.next();
					break;
				} else {

					try {

						input.nextLine();

						// [{\"name\":\"Alexander\"}, {\"name\":\"Shashi\"}]
						System.out.println("Enter json string to create document:");

						String jsonString = input.nextLine();

						System.out.println(jsonString);
						Producer sodaproducer = new Producer();

						//db = sodaproducer.dbConnect();
						col = this.db.admin().createCollection(COLLECTION_NAME);

						com.oracle.db.soda.workshop.Documents createdoc = new Documents();
						Boolean createDocStatus = createdoc.createDocumentByString(db, col, jsonString);
						System.out.println("Create Document Status : " + createDocStatus);
						System.out.println("\n Please enter 'Y' to continue");
						String enter = input.next();

					} catch (OracleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 3:
				System.out.println("Create documents from File \n");

				if (COLLECTION_NAME == null) {
					System.out.println("\n Please select a SODA Collection to continue....\n");
					System.out.println("\n Enter 'Y' to continue");
					String enter = input.next();
					break;
				} else {

					try {

						input.nextLine();

						// [{\"name\":\"Alexander\"}, {\"name\":\"Shashi\"}]
						System.out.println("Enter file containing json string to create document: \n");
						System.out.println("e.g. C:\\Users\\shasi\\Desktop\\test.txt");
						System.out.println("e.g. /home/opc/jsonfiles/test.txt \n");

						String jsonFile = input.nextLine();

						System.out.println(jsonFile);
						Producer sodaproducer = new Producer();

						//db = sodaproducer.dbConnect();
						col = this.db.openCollection(COLLECTION_NAME);

						com.oracle.db.soda.workshop.Documents document = new Documents();
						Boolean createDocStatus = document.createDocumentByFile(db, col, jsonFile);
						System.out.println("Create Document Status : " + createDocStatus);
						System.out.println("\n Please enter 'Y' to continue");
						String enter = input.next();

					} catch (OracleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 4:
				System.out.println("Save a document \n");
				System.out.println("Current SODA Collection : " + COLLECTION_NAME + "\n");

				if (COLLECTION_NAME == null) {
					System.out.println("\n Select a SODA Collection to continue....\n");
					break;
				} else {

					try {
						System.out.println("\n Please enter key of the document you want to save:");
						String docKey = input.next();
						System.out.println("\n Please enter document in json format:");
						String docToSave = input.next();
						Producer sodaproducer = new Producer();
						//db = sodaproducer.dbConnect();
						col = this.db.openCollection(COLLECTION_NAME);
						com.oracle.db.soda.workshop.Documents documents = new Documents();
						Boolean getDocStatus = documents.saveDocument(db, col, docToSave, docKey);
						System.out.println("Document saved status : " + getDocStatus);

						System.out.println("\n Please enter 'Y' to continue");
						String enter = input.next();
					} catch (OracleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 5:
				System.out.println("Get All documents \n");
				System.out.println("Current SODA Collection : " + COLLECTION_NAME + "\n");

				if (COLLECTION_NAME == null) {
					System.out.println("\n Select a SODA Collection to continue....\n");
					break;
				} else {

					try {
						Producer sodaproducer = new Producer();
						//db = sodaproducer.dbConnect();
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
			case 6:
				System.out.println("Get documents by Key \n");
				System.out.println("Current SODA Collection : " + COLLECTION_NAME + "\n");

				if (COLLECTION_NAME == null) {
					System.out.println("\n Select a SODA Collection to continue....\n");
					break;
				} else {

					try {
						System.out.println("\n Please enter key of the document you want to find:");
						String docKey = input.next();
						Producer sodaproducer = new Producer();
						//db = sodaproducer.dbConnect();
						col = this.db.openCollection(COLLECTION_NAME);
						com.oracle.db.soda.workshop.Documents getdoc = new Documents();
						Boolean getDocStatus = getdoc.getDocumentByKey(col, docKey);
						System.out.println("get All Document Status : " + getDocStatus);

						System.out.println("\n Please enter 'Y' to continue");
						String enter = input.next();
					} catch (OracleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 7:
				System.out.println("Search for documents with filters \n");
				System.out.println("Current SODA Collection : " + COLLECTION_NAME + "\n");

				if (COLLECTION_NAME == null) {
					System.out.println("\n Select a SODA Collection to continue....\n");
					break;
				} else {

					try {
						System.out.println("========================================");
						System.out.println("\nExample:  {\"name\" :\"oracle\"}");
						System.out.println("key = name");
						System.out.println("values = oracle john shashi \n\n");
						System.out.println("========================================");
						System.out.println("\nPlease filter key");
						String key = input.next();

						input.nextLine();
						System.out.println("Enter values:");
						String values = input.nextLine();

						Producer sodaproducer = new Producer();
						//db = sodaproducer.dbConnect();
						col = this.db.openCollection(COLLECTION_NAME);
						com.oracle.db.soda.workshop.Documents documents = new Documents();

						String getDocStatus = documents.searchDocuments(db, col, key, values);

						System.out.println("Document search status : " + getDocStatus);

					} catch (OracleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						// IMPORTANT: YOU MUST CLOSE THE CURSOR TO RELEASE RESOURCES.
						System.out.println("\n Please enter 'Y' to continue");
						input.next();
					}
				}
				break;
			case 8:
				System.out.println("Replace document by Key \n");
				System.out.println("Current SODA Collection : " + COLLECTION_NAME + "\n");

				if (COLLECTION_NAME == null) {
					System.out.println("\n Select a SODA Collection to continue....\n");
					break;
				} else {

					try {
						System.out.println("\n Please enter document key : ");
						String docKey = input.next();
						System.out.println("\n Please enter JSON document you want to replace : ");
						String docContent = input.next();
						Producer sodaproducer = new Producer();
						//db = sodaproducer.dbConnect();
						col = this.db.openCollection(COLLECTION_NAME);
						com.oracle.db.soda.workshop.Documents documents = new Documents();
						Boolean getDocStatus = documents.replaceDocumentByKey(db,col, docKey, docContent);
						System.out.println("Replace document Status : " + getDocStatus);

						System.out.println("\n Please enter 'Y' to continue");
						String enter = input.next();
					} catch (OracleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
				
			case 9:
				System.out.println("Remove documents by Key \n");
				System.out.println("Current SODA Collection : " + COLLECTION_NAME + "\n");

				if (COLLECTION_NAME == null) {
					System.out.println("\n Select a SODA Collection to continue....\n");
					break;
				} else {

					try {
						System.out.println("\n Please enter key of the document you want to remove:");
						String docKey = input.next();
						Producer sodaproducer = new Producer();
						//db = sodaproducer.dbConnect();
						col = this.db.openCollection(COLLECTION_NAME);
						com.oracle.db.soda.workshop.Documents documents = new Documents();
						Boolean getDocStatus = documents.removeDocumentByKey(col, docKey);
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
