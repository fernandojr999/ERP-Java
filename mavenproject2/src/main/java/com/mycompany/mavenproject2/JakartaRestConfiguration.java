package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.controller.construtor.ComandosAtualizacao;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {

    public JakartaRestConfiguration() {
        System.out.println("Verificando atualizações...");
        
        ComandosAtualizacao com = new ComandosAtualizacao();
    }
    
}
