package org.usf.assertapi.server.dao;

import java.util.List;

import org.usf.assertapi.core.RuntimeEnvironement;
import org.usf.assertapi.core.ApiCompareResult;
import org.usf.assertapi.server.model.AssertionResultServer;
import org.usf.assertapi.server.model.ApiTraceGroup;
import org.usf.assertapi.server.model.TraceGroupStatus;

import lombok.NonNull;

public interface TraceDao {
    List<AssertionResultServer> select(long[] ids, List<String> status);

    void insert(long idAsr, Long idReq, @NonNull ApiCompareResult res);

    long register(RuntimeEnvironement ctx, String app, String latestRelease, String stableRelease, TraceGroupStatus status);

    List<ApiTraceGroup> selectTraceGroup(Long id);

    void updateStatus(long id, TraceGroupStatus status);
}
