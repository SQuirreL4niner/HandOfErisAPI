package com.handoferis.security;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.azure.keyvault.authentication.KeyVaultCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class KeyVaultClientCredentials extends KeyVaultCredentials {

    private final static Logger LOG = LoggerFactory.getLogger(KeyVaultClientCredentials.class);

    private String clientId;
    private String clientSecret;

    public KeyVaultClientCredentials(String clientId, String clientSecret) {
        super();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public String doAuthenticate(String authorization, String resource, String scope) {
        return getAccessToken(authorization, resource, clientId, clientSecret).getAccessToken();
    }

    private static AuthenticationResult getAccessToken(String authorization, String resource, String clientId,
                                                       String clientSecret) {
        ExecutorService executor = null;
        try {
            executor = newSingleThreadExecutor();
            var context = new AuthenticationContext(authorization, false, executor);
            var credentials = new ClientCredential(clientId, clientSecret);
            return context.acquireToken(resource, credentials, null).get();
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during communication to Azure.", e);
        } finally {
            if (executor != null) {
                gracefullyShutdownExecutor(executor);
            }
        }
    }

    private static void gracefullyShutdownExecutor(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOG.error("Thread interrupted. ", e);
            Thread.currentThread().interrupt();
        } finally {
            var stillRunnableTasks = executor.shutdownNow();
            LOG.error("There are still {} running task after executor shutdown.", stillRunnableTasks);
        }
    }
}
