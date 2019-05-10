package genealogy;

import java.io.File;
import java.io.IOException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

import genealogy.util.MongoUtil;

public class MongoApplication {

	public static void main(String[] args) {
		MongoUtil.saveDocument("D:\\all_nodes.PNG", "110");
	}

}
