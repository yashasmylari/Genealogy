package genealogy.dto.mongo;

import org.springframework.data.annotation.Id;

public class ImageData {

	@Id
	public String id;

	public Object image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getImage() {
		return image;
	}

	public void setImage(Object image) {
		this.image = image;
	}
}
