package com.handoferis.blob;

public class AzureProperties {

    private String connectionString;
    private String containerName;
    private String applicationId;
    private String clientSecret;
    private String baseUrl;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String toString() {
        return "AzureProperties{" +
                "connectionString='" + connectionString + '\'' +
                ", containerName='" + containerName + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                '}';
    }
}