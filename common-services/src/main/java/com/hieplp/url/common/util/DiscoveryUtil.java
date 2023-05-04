package com.hieplp.url.common.util;

import com.hieplp.url.common.constants.discovery.DiscoveryMetadata;
import com.hieplp.url.common.constants.discovery.DiscoveryServiceName;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.HttpEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DiscoveryUtil {

    public static void publicService(ServiceDiscovery discovery,
                                     DiscoveryServiceName name,
                                     String host,
                                     int port,
                                     String root) {

        // Init record
        Record record = HttpEndpoint.createRecord(name.getName(), host, port, root);

        // Init metadata
        JsonObject metadata = new JsonObject();
        metadata.put(DiscoveryMetadata.NAME.getName(), root);
        record.setMetadata(metadata);

        // Publish service
        discovery.publish(record)
                .onSuccess(r -> log.info("Service: {} was published successfully", name.getName()))
                .onFailure(t -> log.error("Service: {} was published failed: ", name.getName(), t));
    }

    public static Future<List<Record>> getAllEndpoints(ServiceDiscovery discovery) {
        return Future.future(event -> discovery.getRecords(record -> record.getType().equals(HttpEndpoint.TYPE), event));
    }

}
