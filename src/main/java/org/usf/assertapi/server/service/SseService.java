package org.usf.assertapi.server.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.usf.assertapi.core.ApiAssertionsResult;
import org.usf.assertapi.server.model.ApiTraceGroup;

public interface SseService {
    SseEmitter init(long ctx);

    void start(long ctx, ApiTraceGroup apiTraceGroup);

    void update(long ctx, ApiAssertionsResult result);

    SseEmitter subscribe(long ctx);

    void complete(long ctx);
}
