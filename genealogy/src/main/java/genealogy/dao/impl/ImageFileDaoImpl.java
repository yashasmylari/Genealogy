package genealogy.dao.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

import genealogy.dao.ImageFileDao;

public class ImageFileDaoImpl implements ImageFileDao {

	@Autowired
	GridFsTemplate gridFsTemplate;

	public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData) {
		return this.gridFsTemplate.store(inputStream, fileName, contentType, metaData).getId().toString();
	}

	public GridFSDBFile getById(String id) {
		return this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
	}

	public GridFSDBFile getByFilename(String fileName) {
		return gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
	}

	public GridFSDBFile retrive(String fileName) {
		return gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
	}

	public List<GridFSDBFile> findAll() {
		return gridFsTemplate.find(null);
	}
}