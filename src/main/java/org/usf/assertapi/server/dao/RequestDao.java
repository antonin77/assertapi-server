package org.usf.assertapi.server.dao;

import java.util.List;

import org.usf.assertapi.core.ApiNonRegressionCheck;
import org.usf.assertapi.server.model.ApiRequestGroupServer;
import org.usf.assertapi.server.model.ApiRequestServer;

import lombok.NonNull;

public interface RequestDao {

    List<ApiRequestServer> selectRequest(int[] ids, List<String> envs, String app);

    void insertRequest(long id, @NonNull ApiNonRegressionCheck req);

    void insertRequestGroup(long id, @NonNull List<ApiRequestGroupServer> requestGroupList);

    void deleteRequestGroup(long id);

    void updateRequest(@NonNull ApiNonRegressionCheck req);

    void deleteRequest(@NonNull int[] id);

    void updateState(@NonNull int[] id, boolean state);

    Long nextId(String col, String table);
}
