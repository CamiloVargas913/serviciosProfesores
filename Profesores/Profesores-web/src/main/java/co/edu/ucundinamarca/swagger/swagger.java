/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.swagger;

import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import io.swagger.jaxrs.config.BeanConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author PROFESIONAL
 */
//@ApplicationPath("api")
public class swagger extends Application {

    public swagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Swagger API Title");
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("Profesores/api");
        beanConfig.setResourcePackage("co.edu.ucundinamarca.controller");
        beanConfig.setScan(true);
    }
}
