package com.acme.operation;

import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.blob.DocumentBlobManager;
import org.nuxeo.ecm.core.model.Document;
import org.nuxeo.ecm.core.model.Repository;
import org.nuxeo.ecm.core.model.Session;
import org.nuxeo.ecm.core.repository.RepositoryService;

/**
 *
 */
@Operation(id=BlobManagerNotifyChangesOperation.ID, category=Constants.CAT_DOCUMENT, label="Document.BlobManagerNotifyChanges", description="Describe here what your operation does.")
public class BlobManagerNotifyChangesOperation {
    
    private static final String PROPERTY_NAME = "blob:mime-type";

    private static final Logger log = LogManager.getLogger(BlobManagerNotifyChangesOperation.class);

    public static final String ID = "Document.BlobManagerNotifyChanges";

    @Context
    protected CoreSession session;
    
    @Context
    protected DocumentBlobManager documentBlobManager;
    
    @Context
    RepositoryService repositoryService;

    @Param(name = "xpath", required = false)
    protected String xpath;

    @OperationMethod
    public DocumentModel run(DocumentModel doc) {
        log.warn("<run> {}", doc.getPathAsString());
        Repository repository = repositoryService.getRepository(doc.getRepositoryName());
        Session session2 = repository.getSession();
        Document document = session2.getDocumentByUUID(doc.getId());
        if(StringUtils.isBlank(xpath)) {
            xpath = PROPERTY_NAME;
        }
        documentBlobManager.notifyChanges(document, Collections.singleton(xpath));
        session2.save();
        return doc;
    }
}
