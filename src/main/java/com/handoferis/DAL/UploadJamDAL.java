package com.handoferis.DAL;

import com.handoferis.pojos.UploadJam;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UploadJamDAL {

    List<UploadJam> getAllUploadJams();
    UploadJam getUploadJamById(String id);
    UploadJam addNewUploadJam(UploadJam jam);
}
