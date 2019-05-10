package genealogy.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

import genealogy.service.impl.PersonServiceImpl;

public class MongoUtil {

	private final static Logger log = LoggerFactory.getLogger(MongoUtil.class);

	public static boolean saveDocument(String imagePath, String personId) {
		try {
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("imagedb");
			DBCollection collection = db.getCollection("Image");
	
			String newFileName = "picture";
	        //Speicfy the path of the image
			File imageFile = new File("D:\\all_nodes.PNG");
	
			// create a "photo" namespace
			GridFS gfsPhoto = new GridFS(db, "photo");
	
			GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
			// set a new filename for identify purpose
			gfsFile.setFilename(newFileName);
	
			// save the image file into mongoDB
			gfsFile.save();

			BasicDBObject document = new BasicDBObject();
			document.put("name", imagePath);
			document.put("key", gfsFile.getId());
			WriteResult wrImage = collection.insert(document);
			Object imageId = wrImage.getUpsertedId();

			DBCollection imagePersonCollection = db.getCollection("Image_Person");
			BasicDBObject documentPerson = new BasicDBObject();
			documentPerson.put("imageId", imageId);
			documentPerson.put("personKey", personId);
			WriteResult wrImagePerson = imagePersonCollection.insert(documentPerson);
			Object imagePersonId = wrImagePerson.getUpsertedId();

			log.info("Image added imageId for the person with id: "+personId);
			log.info("Image path: "+imagePath);
			log.info("Image Person Id: "+imagePersonId);
			return true;
		}
		catch (IOException e) {
			log.error("An error occurred while saving data into Mongo Document Database",e);
			return false;
		}
	}
}
