package org.usf.assertapi.server.dao;

import org.usf.assertapi.core.ApiRequest;
import org.usf.assertapi.server.model.ApiRequestServer;

import java.util.List;
import java.util.Set;

public interface RequestDao {

    List<ApiRequest> selectRequest(long[] ids, String app, Set<String> envs);

    List<ApiRequestServer> selectRequest();

    void insertRequest(long id, ApiRequest req);

    void updateRequest(long id, ApiRequest req);

    void deleteRequest(long[] ids);

    void insertRequestGroup(long id, String app, List<String> releases);

    void deleteRequestGroup(long[] ids);

    void updateState(long[] ids, boolean state);

    long nextId();
}
