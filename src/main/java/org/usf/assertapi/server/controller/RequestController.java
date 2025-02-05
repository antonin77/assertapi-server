package org.usf.assertapi.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.usf.assertapi.core.ApiRequest;
import org.usf.assertapi.core.ContentComparator;
import org.usf.assertapi.server.model.ApiMigration;
import org.usf.assertapi.server.service.RequestService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/model/request")
public class RequestController {
    
	private final RequestService service;

    @GetMapping
    public List<ApiRequest> get(
            @RequestParam(name="id", required = false) int[] ids,
            @RequestParam(name="app", required = false) String app,
            @RequestParam(name="env", required = false) List<String> envs) {
        return service.getRequestList(ids, app, envs != null ? envs : new ArrayList<>());
    }

    @PutMapping //TODO put env + app in path no => delete  ApiRequestServer
    public long put(
            @RequestParam(name="app") String app,
            @RequestParam(name="release") List<String> releases,
            @RequestBody ApiRequest query
    ) {
        return service.addRequest(app, releases, query);
    }

    @PutMapping("import")
    public long[] putImport(
            @RequestParam(name="app") String app,
            @RequestParam(name="release") List<String> releases,
            @RequestBody List<ApiRequest> queries
    ) {
        return service.addRequestList(app, releases, queries);
    }

    @PostMapping("{id}") //TODO put env + app in path no => delete  ApiRequestServer & no need to couple
    public void update(
            @PathVariable("id") int id,
            @RequestParam("app") String app,
            @RequestParam("release") List<String> releases,
            @RequestBody ApiRequest query) {
        service.updateRequest(id, app, releases, query);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") int[] ids) {
        service.removeRequest(ids);
    }

    @PatchMapping("enable")
    public void enable(@RequestParam("id") int[] ids) {
        service.updateState(ids, true);
    }

    @PatchMapping("disable")
    public void disable(@RequestParam("id") int[] ids) {
        service.updateState(ids, false);
    }

    @PutMapping("migration")
    public long put(
            @RequestBody ApiMigration migration
    ) {
        return service.addMigration(migration);
    }

    @PutMapping("migration/{id}")
    public long update(
            @PathVariable("id") int id,
            @RequestBody ApiMigration migration
    ) {
        return service.updateMigration(id, migration);
    }
}
