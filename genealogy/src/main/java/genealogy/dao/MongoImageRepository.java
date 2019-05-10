package genealogy.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import genealogy.dto.mongo.ImageData;

public interface MongoImageRepository extends MongoRepository<ImageData, String> {

}
