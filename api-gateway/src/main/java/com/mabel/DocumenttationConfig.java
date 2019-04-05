package com.mabel;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-04-05 11:33
 **/
@Component
@Primary
public class DocumenttationConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List resources = Lists.newArrayList();
        resources.add(swaggerResource("user", "/user/v2/api-docs", "2.0"));
        resources.add(swaggerResource("admin", "/admin/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}