package org.usf.assertapi.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.usf.assertapi.core.ApiRequest;
import org.usf.assertapi.server.dao.RequestDao;
import org.usf.assertapi.server.exception.NotFoundException;
import org.usf.assertapi.server.exception.TooManyResultException;
import org.usf.assertapi.server.model.ApiRequestServer;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestDao dao;

    @Override
    public List<ApiRequest> getRequestList(long[] ids, String app, Set<String> envs) {
        return dao.selectRequest(ids, app, envs);
    }

    @Override
    public List<ApiRequestServer> getRequestList() {
        return dao.selectRequest();
    }

    @Override
    public ApiRequest getRequestOne(long id) {
        long[] ids = {id};
        var requests = getRequestList(ids, null, null);
        if(requests == null || requests.isEmpty()) {
            throw new NotFoundException();
        } else if (requests.size() > 1) {
            throw new TooManyResultException(); 
        }
        return requests.iterator().next();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long addRequest(String app, List<String> releases, ApiRequest req) {
        long nextId = dao.nextId(); //TODO db column in service
        dao.insertRequest(nextId, req);
        dao.insertRequestGroup(nextId, app, releases);
        return nextId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long[] addRequestList(String app, List<String> releases, List<ApiRequest> requests) {
        long[] ids = new long[requests.size()];
        for (int i = 0; i < requests.size(); i++) {
            ids[i] = addRequest(app, releases, requests.get(i));
        }
        return ids;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRequest(long id, String app, List<String> releases, ApiRequest req) {
        long[] ids = {id};
        dao.updateRequest(id, req);
        dao.deleteRequestGroup(ids);
        dao.insertRequestGroup(id, app, releases);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeRequest(long[] ids){
        dao.deleteRequest(ids);
        dao.deleteRequestGroup(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateState(long[] ids, boolean state){
        dao.updateState(ids, state);
    }
}
