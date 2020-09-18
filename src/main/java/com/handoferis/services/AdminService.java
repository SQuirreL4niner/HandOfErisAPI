package com.handoferis.services;

import com.handoferis.blob.BlobConfig;
import com.handoferis.models.RehearsalJam;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminService {

    @Autowired
    private BlobConfig blobConfig ;

    public URI upload(MultipartFile multipartFile)
    {
        URI uri = null;
        CloudBlockBlob blob = null;

        try
        {
            blob = blobConfig.getBlobContainer()
                    .getBlockBlobReference(multipartFile.getOriginalFilename());

            blob.upload(multipartFile.getInputStream(), -1);

            uri = blob.getUri();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        catch (StorageException e)
        {
            e.printStackTrace();
        }
        catch (IOException | InvalidKeyException e)
        {
            e.printStackTrace();
        }
        return uri;
    }

    public List getSongs()
    {
        List jams = new ArrayList<RehearsalJam>();

        try
        {
            var container = blobConfig.getBlobContainer();

            for (var blobItem : container.listBlobs())
            {
                List<String> uriSegments = UriComponentsBuilder.fromUriString(blobItem.getUri().toString()).build().getPathSegments();
                var decodedSegments = URLDecoder.decode(uriSegments.get(1), "UTF-8");
                jams.add(new RehearsalJam(decodedSegments, blobItem.getUri().toString()));
            }
        }
        catch (URISyntaxException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (StorageException | InvalidKeyException e)
        {
            e.printStackTrace();
        }

        return jams;
    }
}
