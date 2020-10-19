/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author PROFESIONAL
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.ucundinamarca.controller.AutorController.class);
        resources.add(co.edu.ucundinamarca.controller.ProfesoController.class);
        resources.add(co.edu.ucundinamarca.controller.ProfesoEntityController.class);
        resources.add(co.edu.ucundinamarca.exception.filter.ConstraintViolationFilter.class);
        resources.add(co.edu.ucundinamarca.exception.filter.ExceptionFilter.class);
        resources.add(co.edu.ucundinamarca.exception.filter.FileNotFoundExceptionFilter.class);
        resources.add(co.edu.ucundinamarca.exception.filter.IOExceptionFilter.class);
        resources.add(co.edu.ucundinamarca.exception.filter.IndexOutOfBoundExceptionFilter.class);
        resources.add(co.edu.ucundinamarca.exception.filter.NewClassNotAllowedExceptionFilter.class);
        resources.add(co.edu.ucundinamarca.exception.filter.ObjectNotFoundExceptionFilter.class);
        resources.add(co.edu.ucundinamarca.exception.filter.WebApplicationExceptionFilter.class);
    }
    
}
