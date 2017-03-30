package com.swiftsnail;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, JoranException {
//        PropertyConfigurator.configure("conf/log4j.properties");

        initLogbackConfiguration();
        initJULConfiguration();

        //jul log
        Logger julLog = Logger.getLogger("julLog");
        julLog.log(Level.SEVERE, "jul log error");

        //apache common log
        Log aclLog = LogFactory.getLog("aclLog");
        aclLog.fatal("aclLog log error");

        //log4j
        org.apache.log4j.Logger l4jLog = org.apache.log4j.Logger.getLogger(App.class);
        l4jLog.error("l4jLog log error");

        //logback
        org.slf4j.Logger lbLog = LoggerFactory.getLogger(App.class);
        lbLog.error("lbLog log error");
    }

    private static void initJULConfiguration() {
        SLF4JBridgeHandler.install();
    }

    private static void initLogbackConfiguration() throws JoranException {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        configurator.doConfigure("conf/logback.xml");
    }
}
