package sk.tuke.gamestudio.server.service;

import java.io.IOException;
import java.util.Properties;

public class DatabaseSettings {
	
	protected static final String DATABASE_PROPERTIES = "/application.properties";


    public static String DRIVERCLASS;

    public static String URL;

    public static String USER;

    public static String PASSWORD;

    public DatabaseSettings() {
        loadProperties();
    }

    

    private void loadProperties() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream(DATABASE_PROPERTIES));
            DRIVERCLASS = properties.getProperty("spring.datasource.driver-class-name");
            URL = properties.getProperty("spring.datasource.url");
            USER = properties.getProperty("spring.datasource.username");
            PASSWORD = properties.getProperty("spring.datasource.password");
        } catch (IOException e) {
           e.printStackTrace();
        }
        
       
    }
}


