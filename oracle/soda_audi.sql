Oracle SODA doesn’t have a native $setOnInsert. Instead, check first, then insert:

DECLARE
  l_doc CLOB;
  l_count NUMBER;
BEGIN
  -- Check existence
  SELECT COUNT(*) INTO l_count
  FROM JSON_TABLE(
    SODA_COLLECTION.find().filter('{"uniqueId": "svc-001"}').getCursor(),
    '$' COLUMNS (uniqueId PATH '$.uniqueId')
  );

  IF l_count = 0 THEN
    -- Insert if not exists
    SODA_COLLECTION.insertOne(SODA_DOCUMENT_T.createFromJSONString('{
      "uniqueId": "svc-001",
      "environment": "prod",
      "serviceName": "user-authentication",
      "collectionRefId": "colrefid31232",
      "status": "created",
      "createdBy": "system",
      "createdDateTime": "' || TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') || '",
      "updatedBy": "system",
      "updatedDateTime": "' || TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD"T"HH24:MI:SS"Z"') || '",
      "_class": "com.company.services.JobStatus"
    }'));
  END IF;
END;
/


Oracle SODA supports patch operations using JSON Merge Patch.

BEGIN
  SODA_COLLECTION.find().key('svc-001').patch(
    SODA_DOCUMENT_T.createFromJSONString('{
      "$set": {
        "updatedBy": "system",
        "updatedDateTime": "' || TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"') || '"
      },
      "$push": {
        "auditTrail": {
          "status": "completed",
          "message": "Job executed successfully",
          "updatedBy": "system",
          "updatedDateTime": "' || TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"') || '"
        }
      }
    }')
  );
END;
/



Use Case 3: Find documents with "fileName" or "fileRenameTo" = "myfile.log"

SELECT *
FROM JSON_TABLE(
  SODA_COLLECTION.find().filter('{
    "$or": [
      { "auditTrail.fileName": "myfile.log" },
      { "auditTrail.fileRenameTo": "myfile.log" }
    ]
  }').getCursor(),
  '$' COLUMNS (
    uniqueId PATH '$.uniqueId',
    status PATH '$.status'
  )
);


Use Case 4: Find audits where auditTrail.status = "failed"

SELECT *
FROM JSON_TABLE(
  SODA_COLLECTION.find().filter('{
    "auditTrail.status": "failed"
  }').getCursor(),
  '$' COLUMNS (
    uniqueId PATH '$.uniqueId',
    status PATH '$.status'
  )
);


