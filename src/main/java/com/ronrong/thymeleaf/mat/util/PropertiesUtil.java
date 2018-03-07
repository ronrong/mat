package com.ronrong.thymeleaf.mat.util;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;


public class PropertiesUtil {

    public static Properties load(final String propertiesFile) {
        Properties properties = null;

        try {
                properties = loadFromTemplatePath(null, propertiesFile);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Could not load properties: " + e.getMessage());
        }

        return properties;
    }


    public static Properties loadFromTemplatePath(String templatePath, final String propertiesFile)
            throws Exception {
        Properties properties = new Properties();

        if (templatePath == null) templatePath = "";

        StringTokenizer st = new StringTokenizer(templatePath, ",");
        while (st.hasMoreTokens()) {
            String templateDir = st.nextToken();
            InputStream stream = null;
            try {

                String fullPath = propertiesFile;

                if (!fullPath.startsWith(templateDir)) {
                    fullPath = templateDir + "/" + propertiesFile;
                }

                stream = new FileInputStream(fullPath);
                properties.load(stream);

                break;
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
        }
        return properties;
    }


    public static Properties loadFromClassPath(final String propertiesName)
            throws Exception {
        Properties properties = new Properties();
        ClassLoader classLoader = PropertiesUtil.class.getClassLoader();

        InputStream inputStream = null;

        try {

            inputStream = classLoader.getResourceAsStream(propertiesName);
            properties.load(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return properties;
    }
}
