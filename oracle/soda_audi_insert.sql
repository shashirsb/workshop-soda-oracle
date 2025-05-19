BEGIN
  SODA_COLLECTION.insertOne(
    SODA_DOCUMENT_T.createFromJSONString('{
      "uniqueId": "svc-001",
      "environment": "prod",
      "serviceName": "user-authentication",
      "collectionRefId": "colrefid31232",
      "status": "completed",
      "auditTrail": [
        {
          "jobId": "job-001",
          "jobDetailId": "jdID324321",
          "path": "/jobs/auth",
          "fileName": "auth_job_001.log",
          "jobDate": "2025-05-15T10:00:00Z",
          "initiatedFrom": "scheduler",
          "status": "success",
          "jobName": "AuthJob",
          "jobGroup": "auth-jobs",
          "updatedDateTime": "2025-05-15T10:05:00Z",
          "updatedBy": "system"
        }
      ],
      "createdBy": "admin",
      "createdDateTime": "2025-05-14T08:00:00Z",
      "updatedBy": "admin",
      "updatedDateTime": "2025-05-15T10:05:00Z",
      "_class": "com.company.services.JobStatus"
    }')
  );
END;
/
