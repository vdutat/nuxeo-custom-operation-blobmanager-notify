package com.acme.operation;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

@RunWith(FeaturesRunner.class)
@Features(AutomationFeature.class)
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
@Deploy("com.acme.operation.nuxeo-custom-operation-blobmanager-notify-core")
public class TestBlobManagerNotifyChangesOperation {

    @Inject
    protected CoreSession session;

    @Inject
    protected AutomationService automationService;

    @Test
    public void shouldCallTheOperation() throws OperationException {
        OperationContext ctx = new OperationContext(session);
        ctx.setInput("/");
        DocumentModel doc = (DocumentModel) automationService.run(ctx, BlobManagerNotifyChangesOperation.ID);
        assertEquals("/", doc.getPathAsString());
    }

    @Test
    public void shouldCallWithParameters() throws OperationException {
        final String path = "blob:mime-type";
        OperationContext ctx = new OperationContext(session);
        ctx.setInput("/");
        Map<String, Object> params = new HashMap<>();
        params.put("xpath", path);
        DocumentModel doc = (DocumentModel) automationService.run(ctx, BlobManagerNotifyChangesOperation.ID, params);
        assertEquals("/", doc.getPathAsString());
    }
}
