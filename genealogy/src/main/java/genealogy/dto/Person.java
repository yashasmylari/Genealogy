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
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	private String relation;
	private String relationMap;

	private String name;
	private String gender;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String placeOfBirth;
	private Boolean isAlive;
	private String dateOfDeath;
	private String imageUrl;
	private String imageData;
	private String occupation;
	private String education;
	private String[] medicalCondition;


	/* ***** SEPERATE ALL OF THE BELOW INTO DIFFERENT NODES ***** */

	/*
	String religion;
	String clan;
	String residence;
	String nationality;
	String ethnicity;

	String[] locations;
	String[] languages;
	String[] physicalTraits;
	String[] specialCharacteristic;

	// New
	String[] hobbies;
	String[] cusine;
	String[] favouriteGenre;
	String[] favouriteSports;
	*/

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
			jsonObject.put("id", id)
			.put("name", name)
			.put("gender", gender)
			.put("firstName", firstName)
			.put("lastName", lastName)
			.put("dateOfBirth", dateOfBirth)
			.put("placeOfBirth", placeOfBirth)
			.put("isAlive", isAlive)
			.put("dateOfDeath", dateOfDeath)
			.put("imageUrl", imageUrl)
			.put("imageUrl", imageData)
			.put("occupation", occupation)
			.put("education", education)
			.put("medicalCondition", Arrays.toString(medicalCondition));
			/*
			jsonObject.put("residence", residence)
			.put("nationality", nationality)
			.put("religion", religion)
			.put("religion", religion)
			.put("clan", clan)
			.put("ethnicity", ethnicity)
			.put("locations", Arrays.toString(locations))
			.put("languages", Arrays.toString(languages))
			.put("physicalTraits", Arrays.toString(physicalTraits))
			.put("specialCharacteristic", Arrays.toString(specialCharacteristic));
			*/
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Boolean getIsAlive() {
		return isAlive;
	}
	public void setIsAlive(Boolean isAlive) {
		this.isAlive = isAlive;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageData() {
		return imageData;
	}
	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}

	public String[] getMedicalCondition() {
		return medicalCondition;
	}
	public void setMedicalCondition(String[] medicalCondition) {
		this.medicalCondition = medicalCondition;
	}

	/*
	public String getResidence() {
		return residence;
	}
	public void setResidence(String region) {
		this.residence = region;
	}

	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getClan() {
		return clan;
	}
	public void setClan(String clan) {
		this.clan = clan;
	}

	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String[] getPhysicalTraits() {
		return physicalTraits;
	}
	public void setPhysicalTraits(String[] physicalTraits) {
		this.physicalTraits = physicalTraits;
	}

	public String[] getLanguages() {
		return languages;
	}
	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String[] getLocations() {
		return locations;
	}
	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	public String[] getSpecialCharacteristic() {
		return specialCharacteristic;
	}
	public void setSpecialCharacteristic(String[] specialCharacteristic) {
		this.specialCharacteristic = specialCharacteristic;
	}
	*/
}
