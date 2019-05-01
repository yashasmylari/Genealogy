package genealogy.dto;

import static genealogy.constants.Relations.*;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import genealogy.constants.Relations;

@NodeEntity
public class Person extends PersonAttributes {

	@Id
	@GeneratedValue
	private Long id;

	private String relation;
	private String relationMap;

	@Relationship(type = RELATED_TO, direction = Relationship.UNDIRECTED)
	private Set<Person> relatedTo = new HashSet<>();

	@Relationship(type = FATHER_OF, direction = Relationship.UNDIRECTED)
	private Set<Person> fatherOf = new HashSet<>();

	@Relationship(type = MOTHER_OF, direction = Relationship.UNDIRECTED)
	private Set<Person> motherOf = new HashSet<>();

	@Relationship(type = CHILD_OF, direction = Relationship.UNDIRECTED)
	private Set<Person> childOf = new HashSet<>();

	@Relationship(type = SON_OF, direction = Relationship.UNDIRECTED)
	private Set<Person> sonOf = new HashSet<>();

	@Relationship(type = DAUGHTER_OF, direction = Relationship.UNDIRECTED)
	private Set<Person> daughterOf = new HashSet<>();

	@Relationship(type = FRIENDS_WITH, direction = Relationship.UNDIRECTED)
	private Set<Person> friendsWith = new HashSet<>();

	@Relationship(type = Relations.MARRIED_TO, direction = Relationship.UNDIRECTED)
	private Person marriedTo;




	public Person() {
		relationMap = "{}";
	}

	public Person(String name) {
		this.name = name;
		try {
			relationMap = new JSONObject().put(name, "SELF").toString();
		} catch (JSONException e) {
			relationMap = "{}";
		}
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getRelation() {
		return relation;
	}
	public Person setRelation(String relation) {
		this.relation = relation;
		return this;
	}

	public String getRelationMap() {
		return relationMap;
	}

	public void setRelationMap(String relationMap) {
		this.relationMap = relationMap;
	}

	public Set<Person> getRelatedTo() {
		return relatedTo;
	}
	public void setRelatedTo(Person person) {
		relatedTo.add(person);
	}

	public Set<Person> getFatherOf() {
		return fatherOf;
	}
	public void setFatherOf(Person person) {
		fatherOf.add(person);
	}

	public Set<Person> getMotherOf() {
		return motherOf;
	}
	public void setMotherOf(Person person) {
		motherOf.add(person);
	}

	public Set<Person> getChildOf() {
		return childOf;
	}
	public void setChildOf(Person person) {
		childOf.add(person);
	}

	public Set<Person> getSonOf() {
		return sonOf;
	}
	public void setSonOf(Person person) {
		sonOf.add(person);
	}

	public Set<Person> getDaughterOf() {
		return daughterOf;
	}
	public void setDaughterOf(Person person) {
		daughterOf.add(person);
	}

	public Set<Person> getFriendsWith() {
		return friendsWith;
	}
	public void setFriendsWith(Person person) {
		friendsWith.add(person);
	}

	public Person getMarriedTo() {
		return marriedTo;
	}
	public void setMarriedTo(Person marriedTo) {
		this.marriedTo = marriedTo;
	}

	// Set Relationship
	public Person setRelationShip(Person person) {
		if(person==null) {
			relation = null;
			return this;
		}
			
		try {
			String key = new StringBuilder(name).append("-").append(person.name).toString();
			relationMap = new JSONObject(relationMap).put(key, relation).toString();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}

		// Relate
		relatedTo.add(person);

		// Add Relationship
		if(FATHER_OF.equals(relation))
			fatherOf.add(person);
		else if(MOTHER_OF.equals(relation))
			motherOf.add(person);
		else if(CHILD_OF.equals(relation))
			childOf.add(person);
		else if(SON_OF.equals(relation))
			sonOf.add(person);
		else if(DAUGHTER_OF.equals(relation))
			daughterOf.add(person);
		else if(FRIENDS_WITH.equals(relation))
			friendsWith.add(person);
		else if(MARRIED_TO.equals(relation))
			marriedTo = person;
		return this;
	}


	@Override
	public String toString() {
		return relationMap;
	}



	public String jsonString() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("name", id);
			jsonObject.put("name", name);
			// ALOKA >> Format this
		} catch (JSONException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(gender).append(firstName).append(lastName).append(dateOfBirth);
		sb.append(dateOfDeath).append(isAlive).append(region).append(language);
		sb.append(religion).append(clan).append(ethinicity).append(occupation);
		sb.append(physicalTraits).append(education).append(medicalCondition).append(specialCharacteristic);
		return sb.toString();
	}
}
