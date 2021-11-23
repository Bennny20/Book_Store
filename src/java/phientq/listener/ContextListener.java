/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.listener;

import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import phientq.utils.PropertiesFileHelper;

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String siteMapLocation = context.getInitParameter("SITE_MAP_LOCATION");
        String authenticationPropertyLocation = context.getInitParameter("AUTHENTICATION_FILE_LOCATION");
        Properties sitemapProperty = PropertiesFileHelper.getProperties(context, siteMapLocation);
        Properties authenProperty = PropertiesFileHelper.getProperties(context, authenticationPropertyLocation);
        context.setAttribute("SITE_MAP", sitemapProperty);
        context.setAttribute("AUTHENTICATION", authenProperty);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Destroying....");
    }
}
