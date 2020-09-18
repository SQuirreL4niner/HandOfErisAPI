package com.handoferis.security;

import com.handoferis.blob.Azure;
import com.handoferis.blob.AzureProperties;
import com.handoferis.config.YAMLConfig;
//import com.handoferis.utils.EnvironmentPropertyName;
import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.models.SecretItem;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.replace;
import static java.util.stream.Collectors.toMap;
//import static com.handoferis.utils.EnvPropertiesUtils.getRequiredEnvProperty;

@Component
public class KeyVaultsSecretReader {

    @Autowired
    YAMLConfig yamlConfig;

    private static final String ARTIFICIAL_NAME_SEPARATOR = "--";

    public Map<String, Object> readApplicationPropertiesFromAzure(KeyVaultClient client) {

        return client.getSecrets(yamlConfig.getUri()).stream()
                .map(SecretItem::id)
                .map(client::getSecret)
                .map(secretBundle -> Pair.of(secretBundle.secretIdentifier().name(), secretBundle.value()))
                .map(pair -> Pair.of(replaceArtificialSeparatorWithPropertiesStyle(pair.getLeft()), pair.getRight()))
                .collect(toMap(Pair::getLeft, Pair::getRight));
    }

    private static String replaceArtificialSeparatorWithPropertiesStyle(String secretName) {
        return replace(secretName, ARTIFICIAL_NAME_SEPARATOR, ".");
    }
}
