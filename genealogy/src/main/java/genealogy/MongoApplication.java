package genealogy;

import java.io.File;
import java.io.IOException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoApplication {

	public static void main(String[] args) {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
