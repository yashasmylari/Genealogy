package genealogy.dto;

import static genealogy.constants.Relations.*;

import java.util.Arrays;
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

	// Disabling it because it seems like an extra relationship that is not required
	/*@Relationship(type = RELATED_TO)
	private Set<Person> relatedTo = new HashSet<>();*/

	@Relationship(type = FATHER_OF)
	private Set<Person> fatherOf = new HashSet<>();

	@Relationship(type = MOTHER_OF)
	private Set<Person> motherOf = new HashSet<>();

	@Relationship(type = CHILD_OF)
	private Set<Person> childOf = new HashSet<>();

	@Relationship(type = SON_OF)
	private Set<Person> sonOf = new HashSet<>();

	@Relationship(type = DAUGHTER_OF)
	private Set<Person> daughterOf = new HashSet<>();

	@Relationship(type = FRIENDS_WITH)
	private Set<Person> friendsWith = new HashSet<>();

	@Relationship(type = Relations.MARRIED_TO)
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

	// Disabling the getter and setter
	/*public Set<Person> getRelatedTo() {
		return relatedTo;
	}
	public void setRelatedTo(Person person) {
		relatedTo.add(person);
	}*/

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
		// Set Relationship Map
		try {
			relationMap = new JSONObject(relationMap).put(person.name, relation).toString();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}

		// Relate
		// relatedTo.add(person);

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
		return json().toString();
	}



	public JSONObject json() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("name", name);
			jsonObject.put("firstName", firstName);
			jsonObject.put("lastName", lastName);
			jsonObject.put("dateOfBirth", dateOfBirth);
			jsonObject.put("placeOfBirth", placeOfBirth);
			jsonObject.put("isAlive", isAlive);
			jsonObject.put("dateOfDeath", dateOfDeath);
			jsonObject.put("region", region);
			jsonObject.put("language", language);
			jsonObject.put("religion", religion);
			jsonObject.put("clan", clan);
			jsonObject.put("ethinicity", ethinicity);
			jsonObject.put("occupation", occupation);
			jsonObject.put("physicalTraits", physicalTraits);
			jsonObject.put("education", education);
			jsonObject.put("specialCharacteristic", specialCharacteristic);
			jsonObject.put("medicalCondition", Arrays.toString(medicalCondition));
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
