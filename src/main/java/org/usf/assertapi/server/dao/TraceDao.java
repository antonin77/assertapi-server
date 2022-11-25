package org.usf.assertapi.server.dao;

import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.usf.assertapi.core.ApiAssertionsResult;
import org.usf.assertapi.core.AssertionContext;
import org.usf.assertapi.server.model.ApiAssertionsResultServer;
import org.usf.assertapi.server.model.ApiTraceGroup;

import java.util.List;

public interface TraceDao {
    List<ApiAssertionsResultServer> select(long[] ids);

    void insert(long id, ApiAssertionsResult res);

    long register(AssertionContext ctx, String app, String actEnv, String expEnv);

    List<ApiTraceGroup> selectTraceGroup();
}
