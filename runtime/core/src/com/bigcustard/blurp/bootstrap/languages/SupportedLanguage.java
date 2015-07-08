package com.bigcustard.blurp.bootstrap.languages;

public class SupportedLanguage {

    private String name;
    private String description;
    private String[] fileExtensions;

    private NamingStrategy constantStrategy;
    private NamingStrategy globalStrategy;

    protected SupportedLanguage(String name, String description, NamingStrategy globalStrategy, NamingStrategy constantStrategy, String... fileExtensions) {

        this.name = name;
        this.description = description;
        this.globalStrategy = globalStrategy;
        this.constantStrategy = constantStrategy;
        this.fileExtensions = fileExtensions;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    public String[] getFileExtensions() {

        return fileExtensions;
    }

    public NamingStrategy getGlobalStrategy() {

        return globalStrategy;
    }

    public NamingStrategy getConstantStrategy() {

        return constantStrategy;
    }

    public boolean acceptsFile(String fileName) {

        for(String extension : fileExtensions) {
            if(fileName.toLowerCase().endsWith("." + extension)) {
                return true;
            }
        }
        return false;
    }
}
