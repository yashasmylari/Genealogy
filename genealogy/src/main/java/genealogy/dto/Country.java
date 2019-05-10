package genealogy.dto;

import static genealogy.constants.Relations.CONTAINS;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Country {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@Relationship(type = CONTAINS)
	private Set<Province> contains = new HashSet<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Set<Province> getContains() {
		return contains;
	}
	public void setContains(Set<Province> contains) {
		this.contains = contains;
	}

}
