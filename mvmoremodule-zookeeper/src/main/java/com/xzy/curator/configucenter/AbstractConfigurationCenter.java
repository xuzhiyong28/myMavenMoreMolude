package com.xzy.curator.configucenter;

import java.util.Map;
//https://www.jianshu.com/p/7d324ac9cc64
public abstract class AbstractConfigurationCenter implements ConfigurationCenter{

    private static final String CONFIGURATION_ROOT_PATH = "/configuration";
    @Override
    public void addConfiguration(String key, String value) throws Exception {

    }

    @Override
    public void deleteConfiguration(String key) throws Exception {

    }

    @Override
    public void updateConfiguration(String key, String value) throws Exception {

    }

    @Override
    public String getConfiguration(String key) throws Exception {
        return null;
    }

    @Override
    public Map<String, String> getAllConfiguration() throws Exception {
        return null;
    }
}
