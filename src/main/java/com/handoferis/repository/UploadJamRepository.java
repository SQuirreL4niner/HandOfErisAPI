package com.handoferis.repository;

import com.handoferis.pojos.UploadJam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadJamRepository extends MongoRepository<UploadJam, String> {
//    List<UploadJam> getAllUploadJams();
//    UploadJam getUploadJamById(String id);
//    UploadJam addNewUploadJam(UploadJam jam);
}
