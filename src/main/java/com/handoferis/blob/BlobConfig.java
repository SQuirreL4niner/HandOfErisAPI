package com.handoferis.blob;

import com.handoferis.config.YAMLConfig;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;


import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Configuration()
public class BlobConfig {

    @Autowired
    YAMLConfig yamlConfig;
//    @Value("${AzureStorageConnectionString}")
//    private String connectionString;

//    Yaml yaml = new Yaml(new Constructor(Azure.class));
//
//    InputStream inputStream = this.getClass()
//            .getClassLoader()
//            .getResourceAsStream("azure.yml");
//    Azure azureProperties = yaml.load(inputStream);

    @Bean
    public CloudBlobClient cloudBlobClient() throws URISyntaxException,
            StorageException, InvalidKeyException {
        try
        {
            System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
            CloudStorageAccount storage = CloudStorageAccount.parse(yamlConfig.getConnectionString());
            return storage.createCloudBlobClient();
        }
        catch(URISyntaxException | InvalidKeyException e)
        {
            System.out.println(e.toString());
            return null;
        }
    }

    @Bean
    public CloudBlobContainer getBlobContainer() throws URISyntaxException,
            StorageException, InvalidKeyException {
        try
        {
            return cloudBlobClient().getContainerReference(yamlConfig.getContainerName());
        }
        catch(URISyntaxException | StorageException | InvalidKeyException e)
        {
            System.out.println(e);
            return null;
        }
    }
}
