package org.usf.assertapi.server.service;

import org.usf.assertapi.core.ApiRequest;
import org.usf.assertapi.server.model.ApiRequestServer;

import java.util.List;
import java.util.Set;

public interface RequestService {
    List<ApiRequest> getRequestList(long[] ids, String app, Set<String> envs);

    List<ApiRequestServer> getRequestList();

    ApiRequest getRequestOne(long id);

    long addRequest(String app, List<String> releases, ApiRequest req);

    long[] addRequestList(String app, List<String> releases, List<ApiRequest> requests);

    void updateRequest(long id, String app, List<String> releases, ApiRequest req);

    void removeRequest(long[] ids);

    void updateState(long[] ids, boolean state);
}
