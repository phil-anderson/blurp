package com.bigcustard.blurp.bootstrap.languages;

public abstract class SupportedLanguage {

    private String description;
    private String[] fileExtensions;

    protected NamingStrategy constantStrategy = NamingStrategy.PASS_THROUGH;
    protected NamingStrategy globalStrategy = NamingStrategy.PASS_THROUGH;

    protected SupportedLanguage(String description, String... fileExtensions) {

        this.description = description;
        this.fileExtensions = fileExtensions;
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

    public abstract Runner getRunner();
}
