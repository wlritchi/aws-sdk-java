/*
 * Copyright 2010-2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.glacier.model.transform;

import static com.amazonaws.util.StringUtils.UTF8;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.glacier.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.*;

/**
 * Set Vault Notifications Request Marshaller
 */
public class SetVaultNotificationsRequestMarshaller implements Marshaller<Request<SetVaultNotificationsRequest>, SetVaultNotificationsRequest> {

    public Request<SetVaultNotificationsRequest> marshall(SetVaultNotificationsRequest setVaultNotificationsRequest) {
    if (setVaultNotificationsRequest == null) {
        throw new AmazonClientException("Invalid argument passed to marshall(...)");
    }

        Request<SetVaultNotificationsRequest> request = new DefaultRequest<SetVaultNotificationsRequest>(setVaultNotificationsRequest, "AmazonGlacier");
        String target = "Glacier.SetVaultNotifications";
        request.addHeader("X-Amz-Target", target);

        request.setHttpMethod(HttpMethodName.PUT);
        String uriResourcePath = "/{accountId}/vaults/{vaultName}/notification-configuration"; 
        uriResourcePath = uriResourcePath.replace("{accountId}", (setVaultNotificationsRequest.getAccountId() == null) ? "" : StringUtils.fromString(setVaultNotificationsRequest.getAccountId())); 
        uriResourcePath = uriResourcePath.replace("{vaultName}", (setVaultNotificationsRequest.getVaultName() == null) ? "" : StringUtils.fromString(setVaultNotificationsRequest.getVaultName())); 

        uriResourcePath = uriResourcePath.replaceAll("//", "/");

        if (uriResourcePath.contains("?")) {
            String queryString = uriResourcePath.substring(uriResourcePath.indexOf("?") + 1);
            uriResourcePath    = uriResourcePath.substring(0, uriResourcePath.indexOf("?"));

            for (String s : queryString.split("[;&]")) {
                String[] nameValuePair = s.split("=");
                if (nameValuePair.length == 2) {
                    if(!(nameValuePair[1].isEmpty()))
                        request.addParameter(nameValuePair[0], nameValuePair[1]);
                }
            }
        }
        request.setResourcePath(uriResourcePath);
        
        try {
          StringWriter stringWriter = new StringWriter();
          JSONWriter jsonWriter = new JSONWriter(stringWriter);

            VaultNotificationConfig vaultNotificationConfig = setVaultNotificationsRequest.getVaultNotificationConfig();
            if (vaultNotificationConfig != null) {

                jsonWriter.object();

                if (vaultNotificationConfig.getSNSTopic() != null) {
                    jsonWriter.key("SNSTopic").value(vaultNotificationConfig.getSNSTopic());
                }

                com.amazonaws.internal.ListWithAutoConstructFlag<String> eventsList = (com.amazonaws.internal.ListWithAutoConstructFlag<String>)(vaultNotificationConfig.getEvents());
                if (eventsList != null && !(eventsList.isAutoConstruct() && eventsList.isEmpty())) {

                    jsonWriter.key("Events");
                    jsonWriter.array();

                    for (String eventsListValue : eventsList) {
                        if (eventsListValue != null) {
                            jsonWriter.value(eventsListValue);
                        }
                    }
                    jsonWriter.endArray();
                }
                jsonWriter.endObject();
            }

          String snippet = stringWriter.toString();
          byte[] content = snippet.getBytes(UTF8);
          request.setContent(new StringInputStream(snippet));
          request.addHeader("Content-Length", Integer.toString(content.length));
          request.addHeader("Content-Type", "application/x-amz-json-1.0");
        } catch(Throwable t) {
          throw new AmazonClientException("Unable to marshall request to JSON: " + t.getMessage(), t);
        }

        return request;
    }
}
