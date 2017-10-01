package Config;

public enum ConfigurationFactory {
    INSTANCE;

    public ConfigurationProvider getConfigurationProvider(){
        return ConfigurationReader.getInstance();
    }
}
