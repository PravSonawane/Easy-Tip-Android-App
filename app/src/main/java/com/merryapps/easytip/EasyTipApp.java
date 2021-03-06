package com.merryapps.easytip;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.merryapps.easytip.model.db.DaoMaster;
import com.merryapps.easytip.model.db.EasyTipOpenHelper;
import com.merryapps.easytip.model.framework.ManagerFactory;
import com.merryapps.easytip.util.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mephisto on 7/16/16.
 */
public class EasyTipApp extends Application {

    private static String TAG = "EasyTipApp";
    private String databaseName;
    private ManagerFactory managerFactory;
    private PropertyFile project;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Log.i(TAG, "Reading project.properties file");
            InputStream is = this.getApplicationContext().getAssets().open("project.properties");

            Properties properties = new Properties();
            properties.load(is);
            this.project = new PropertyFile(properties, "project");

            databaseName = properties.getProperty("database.name");
            Log.i(TAG,"Reading project.properties file - Completed");
        } catch (IOException e) {
            Log.e(TAG,"Error reading project.properties from assets folder");
            throw new ConfigurationException("Error reading project.properties from assets folder");
        }




        Log.d(TAG,"GreenDao initialization - started");
        DaoMaster.DevOpenHelper helper = new EasyTipOpenHelper(this, databaseName, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        Log.d(TAG,"GreenDao initialization - completed");

        managerFactory = new ManagerFactory(daoMaster);
        Log.i(TAG,"ManagerFactory instantiated");
    }

    public ManagerFactory getManagerFactory() {
        return managerFactory;
    }

    /**
     * <pre>
     * Fetches property from properties file in assets folder.
     * Usage:
     * <code>
     *     //Fetch a property called project.name from project.properties located in assets folder.
     *     String projectName = getProperty("project.name","project"); //returns "MyProject"
     * </code>
     * </pre>
     * @param propertyName the name of the property to be fetched
     * @param file the file name
     * @return the property value as string
     */
    public String getProperty(String propertyName, String file) {
        switch (file) {
            case "project":
                return project.getProperties().getProperty(propertyName);
        }

        throw new IllegalArgumentException("Properties file not found: " + file);
    }

    private static final class PropertyFile {
        Properties properties;
        String finalName;

        public PropertyFile(Properties properties, String finalName) {
            this.properties = properties;
            this.finalName = finalName;
        }

        public Properties getProperties() {
            return properties;
        }

        public String getFinalName() {
            return finalName;
        }
    }


}
