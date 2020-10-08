package com.handoferis.services;

import com.handoferis.DAL.UploadJamDAL;
import com.handoferis.blob.BlobConfig;
import com.handoferis.pojos.RehearsalJam;
import com.handoferis.pojos.UploadJam;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class AdminService {

    private final BlobConfig blobConfig ;

    private final UploadJamDAL uploadJamDAL;

    public AdminService( UploadJamDAL uploadJamDAL, BlobConfig blobConfig)
    {
        this.uploadJamDAL = uploadJamDAL;
        this.blobConfig = blobConfig;
    }

    public URI upload(MultipartFile multipartFile, String title, String notes, Date date, String user)
    {
        URI uri = null;
        CloudBlockBlob blob = null;

        try
        {
            blob = blobConfig.getBlobContainer()
                    .getBlockBlobReference(multipartFile.getOriginalFilename());

            blob.upload(multipartFile.getInputStream(), -1);

            uri = blob.getUri();

            var jam = new UploadJam(title, date, new Date(), user, uri.toString(), notes);

            uploadJamDAL.addNewUploadJam(jam);
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

    public List<UploadJam> getAllSongs()
    {
        List jams = new ArrayList<UploadJam>();

        try
        {
            jams = uploadJamDAL.getAllUploadJams();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return jams;
    }

    public UploadJam getJamById(String id)
    {
        var jam = new UploadJam();
        try
        {
            jam = uploadJamDAL.getUploadJamById(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return jam;
    }
}
