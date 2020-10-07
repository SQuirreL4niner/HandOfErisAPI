package com.handoferis.DALIMPL;

import com.handoferis.DAL.UploadJamDAL;
import com.handoferis.pojos.UploadJam;
import com.handoferis.repository.UploadJamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UploadJamDALIMPL implements UploadJamDAL {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<UploadJam> getAllUploadJams() {
        return mongoTemplate.findAll(UploadJam.class);
    }

    @Override
    public UploadJam getUploadJamById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, UploadJam.class);
    }

    @Override
    public UploadJam addNewUploadJam(UploadJam jam) {
        mongoTemplate.save(jam);
        return jam;
    }
}
