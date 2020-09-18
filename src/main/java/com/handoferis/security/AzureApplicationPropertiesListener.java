package com.handoferis.security;

import com.google.common.collect.HashBiMap;
import com.handoferis.blob.AzureProperties;
import com.handoferis.config.YAMLConfig;
//import com.handoferis.utils.EnvironmentPropertyName;
//import com.handoferis.utils.EnvironmentPropertyName;
import com.microsoft.azure.keyvault.KeyVaultClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;

//###################################import static com.handoferis.security.KeyVaultsSecretReader.readApplicationPropertiesFromAzure;
//import static com.handoferis.utils.EnvPropertiesUtils.getRequiredEnvProperty;

@Component
public class AzureApplicationPropertiesListener implements InitializingBean {

    @Autowired
    private Environment environment;

    @Autowired
    private YAMLConfig yamlConfig;

    @Autowired
    private KeyVaultsSecretReader keyVaultsSecretReader;

//    @Override
//    public void onApplicationEvent(ApplicationStartingEvent event) {
//        try {
//            afterPropertiesSet();
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//
//        var client = buildAzureIntegrationClient();
//        var properties = readApplicationPropertiesFromAzure(client);
//        event.getSpringApplication().setDefaultProperties(properties);
//    }

//    @PostConstruct
//    public void init(){
//        var test = yamlConfig.getClientId();
//        System.out.println(test);
//    }


    public void getActiveProfiles() {

    }

    private KeyVaultClient buildAzureIntegrationClient() {

        for (String profileName : environment.getActiveProfiles()) {
            System.out.println("Currently active profile - " + profileName);
        }

        var clientId = yamlConfig.getClientId();
        var clientSecret = yamlConfig.getClientSecret();
        var azureCreds = new KeyVaultClientCredentials(clientId, clientSecret);
        var client = new KeyVaultClient(azureCreds);
        System.out.println(client);
        yamlConfig.setKeyVaultClient(client);

        var variableMap = (Map) keyVaultsSecretReader.readApplicationPropertiesFromAzure(yamlConfig.getKeyVaultClient());
        HashBiMap<String, String> appVariables = HashBiMap.create(variableMap);
        var azureStorageConnectionString = (String) appVariables.get("AzureStorageConnectionString");
        var azureStorageContainerName = (String) appVariables.get("AzureStorageContainerName");
        System.out.println(Arrays.toString(appVariables.entrySet().toArray()));
        yamlConfig.setConnectionString(azureStorageConnectionString);
        yamlConfig.setContainerName(azureStorageContainerName);
        return client;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(yamlConfig.getClientId());
        System.out.println(yamlConfig.getUri());
        buildAzureIntegrationClient();
    }
}
