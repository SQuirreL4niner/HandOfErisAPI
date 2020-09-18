package com.handoferis.blob;

public class Azure {

    private AzureProperties azure;

    public AzureProperties getAzure() {
        return azure;
    }

    public void setAzure(AzureProperties azure) {
        this.azure = azure;
    }

    @Override
    public String toString() {
        return "Azure{" +
                "azure=" + azure +
                '}';
    }
}