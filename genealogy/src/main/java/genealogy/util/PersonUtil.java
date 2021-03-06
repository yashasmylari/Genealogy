package genealogy.util;

import static genealogy.constants.Relations.*;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import genealogy.dao.PersonRepository;
import genealogy.dto.*;
import genealogy.struct.RelatedPerson;

public final class PersonUtil {

	private PersonUtil() { }


	/**
	 * Returns the DTO towards the upper node (Parent)
	 * @param person
	 * @return person
	 */
	public static Person getParentRelation(Person person) {
		String g = person.getGender().toUpperCase();
		person.setGender(g);

		switch(g) {
			case "MALE" :
				return person.setRelation(FATHER_OF);
			case "FEMALE" :
				return person.setRelation(MOTHER_OF);
		}
		return person;
	}


	/**
	 * Returns the DTO towards the lower node (Child)
	 * @param person
	 * @return person
	 */
	public static Person getChildRelation(Person person) {
		String g = person.getGender().toUpperCase();
		person.setGender(g);

		switch(g) {
			case "MALE" :
				return person.setRelation(SON_OF);
			case "FEMALE" :
				return person.setRelation(DAUGHTER_OF);
		}
		return person;
	}


	/**
	 * Returns the DTO towards the upper and lower node
	 * @param person1
	 * @param person2
	 * @param relation
	 * @return relatedPerson
	 */
	public static RelatedPerson<Person, Person> getPersonRelation(Person person1, Person person2, String relation) {
		if(relation==null)
			return null;

		String rel = relation.toUpperCase();

		switch(rel) {
			case MARRIED :
				return new RelatedPerson<Person, Person>(person1.setRelation(MARRIED_TO), person2.setRelation(MARRIED_TO));
			case FRIEND :
				return new RelatedPerson<Person, Person>(person1.setRelation(FRIENDS_WITH), person2.setRelation(FRIENDS_WITH));
			// Person 1 >> Parent of >> Person 2
			case PARENT :
				return new RelatedPerson<Person, Person>(getParentRelation(person1), getChildRelation(person2));
			// Person 1 >> Child of >> Person 2
			case CHILD :
				return new RelatedPerson<Person, Person>(getChildRelation(person1), getParentRelation(person2));
			// Person 1 >> Blood Relative of >> Person 2
			case BLOOD_RELATIVE :
				return new RelatedPerson<Person, Person>(person1, person2);
			// Person 1 >> Related to >> Person 2
			default :
				return new RelatedPerson<Person, Person>(person1, person2);
		}
	}



	/**
	 * Creates the {@link Person} model from the JSONObject input.
	 * @param jsonPerson
	 * @return person
	 * @throws JSONException
	 */
	public static Person getPersonFromJsonString(JSONObject jsonPerson) throws JSONException {

		String name = jsonPerson.getString("name");

		Person person = new Person(name);

		// Gender
		try { person.setGender(jsonPerson.getString("gender")); }
		catch(Exception ex) { System.err.println("Gender not defined for >> " + name); }

		// First Name
		try { person.setFirstName(jsonPerson.getString("firstName")); }
		catch(Exception ex) { System.err.println("First Name not defined for >> " + name); }

		// Last Name
		try { person.setLastName(jsonPerson.getString("lastName")); }
		catch(Exception ex) { System.err.println("Last Name not defined for >> " + name); }

		// Date Of Birth
		try { person.setDateOfBirth(jsonPerson.getString("dateOfBirth")); }
		catch(Exception ex) { System.err.println("Date Of Birth not defined for >> " + name); }

		// Place Of Birth
		try { person.setPlaceOfBirth(jsonPerson.getString("placeOfBirth")); }
		catch(Exception ex) { System.err.println("Place Of Birth not defined for >> " + name); }

		// Is Alive
		try { person.setIsAlive(jsonPerson.getBoolean("isAlive")); }
		catch(Exception ex) { System.err.println("Is Alive not defined for >> " + name); }

		// Date Of Death
		try { person.setDateOfDeath(jsonPerson.getString("dateOfDeath")); }
		catch(Exception ex) { System.err.println("Date Of Death not defined for >> " + name); }

		// Date Of Death
		try { person.setImageUrl(jsonPerson.getString("imageUrl")); }
		catch(Exception ex) { System.err.println("Image URL not defined for >> " + name); }

		// Date Of Death
		try { person.setImageData(jsonPerson.getString("imageData")); }
		catch(Exception ex) { System.err.println("Image Data not defined for >> " + name); }

		// Occupation
		try { person.setOccupation(jsonPerson.getString("occupation")); }
		catch(Exception ex) { System.err.println("Occupation not defined for >> " + name); }

		// Education
		try { person.setEducation(jsonPerson.getString("education")); }
		catch(Exception ex) { System.err.println("Education not defined for >> " + name); }

		// Medical Condition
		try { person.setMedicalCondition(Common.jsonArrayToStringArray(jsonPerson.getJSONArray("medicalCondition"))); }
		catch(Exception ex) { System.err.println("Medical Condition not defined for >> " + name); }

		// Physical Traits
		try { person.setPhysicalTraits(Common.jsonArrayToStringArray(jsonPerson.getJSONArray("physicalTraits"))); }
		catch(Exception ex) { System.err.println("Physical Traits not defined for >> " + name); }


		/*
		// Residence
		try { person.setResidence(jsonPerson.getString("residence")); }
		catch(Exception ex) { System.err.println("Residence not defined for >> " + name); }

		// Nationality
		try { person.setNationality(jsonPerson.getString("nationality")); }
		catch(Exception ex) { System.err.println("Nationality not defined for >> " + name); }

		// Religion
		try { person.setReligion(jsonPerson.getString("religion")); }
		catch(Exception ex) { System.err.println("Religion not defined for >> " + name); }

		// Clan
		try { person.setClan(jsonPerson.getString("clan")); }
		catch(Exception ex) { System.err.println("Clan not defined for >> " + name); }

		// Ethnicity
		try { person.setEthnicity(jsonPerson.getString("ethnicity")); }
		catch(Exception ex) { System.err.println("Ethnicity not defined for >> " + name); }

		// Medical Condition
		try { person.setLocations(Common.jsonArrayToStringArray(jsonPerson.getJSONArray("locations"))); }
		catch(Exception ex) { System.err.println("Medical Condition not defined for >> " + name); }

		// Language
		try { person.setLanguages(Common.jsonArrayToStringArray(jsonPerson.getJSONArray("languages"))); }
		catch(Exception ex) { System.err.println("Language not defined for >> " + name); }

		// Special Characteristic
		try { person.setSpecialCharacteristic(Common.jsonArrayToStringArray(jsonPerson.getJSONArray("specialCharacteristic"))); }
		catch(Exception ex) { System.err.println("Special Characteristic not defined for >> " + name); }
		*/

		return person;
	}



	/**
	 * Writes the relationship between the {@link Person} nodes in format.
	 * (Person1) >> [RelationShip] >> (Person2)
	 * @param sbTop
	 * @param relationMap
	 * @param relationKey
	 * @param nextNode
	 * @return strRelationship
	 * @throws JSONException
	 */
	public static String strRelationship(String sbTop, JSONObject relationMap, String relationKey, Person nextNode)
			throws JSONException {
		StringBuilder sb = new StringBuilder(sbTop);
		// Set Relation
		String rel = relationMap.getString(relationKey);
		sb.append(" >> [").append(rel).append("] >> ");
		sb.append("(").append(nextNode.getName()).append(":").append(nextNode.getGender()).append(")");

		return sb.toString();
	}



	/**
	 * Sets the Person attribute values from one person object to the another.
	 * @param person {@link PersonAttributes}
	 * @param personNode  {@link PersonAttributes}
	 */
	public static Person setAttributeFromPerson(Person person, Person personNode) {
		try {
			boolean attributeSet = false;

			if(person.getGender()!=null) {
				personNode.setGender(person.getGender());
				attributeSet = true;
			}
			if(person.getFirstName()!=null) {
				personNode.setFirstName(person.getFirstName());
				attributeSet = true;
			}
			if(person.getLastName()!=null) {
				personNode.setLastName(person.getLastName());
				attributeSet = true;
			}
			if(person.getDateOfBirth()!=null) {
				personNode.setDateOfBirth(person.getDateOfBirth());
				attributeSet = true;
			}
			if(person.getPlaceOfBirth()!=null) {
				personNode.setPlaceOfBirth(person.getPlaceOfBirth());
				attributeSet = true;
			}
			if(person.getDateOfDeath()!=null) {
				personNode.setDateOfDeath(person.getDateOfDeath());
				attributeSet = true;
			}
			if(person.getIsAlive()!=null) {
				personNode.setIsAlive(person.getIsAlive());
				attributeSet = true;
			}

			if(person.getImageUrl()!=null) {
				personNode.setImageUrl(person.getImageUrl());
				attributeSet = true;
			}
			if(person.getImageData()!=null) {
				personNode.setImageData(person.getImageData());
				attributeSet = true;
			}
			if(person.getOccupation()!=null) {
				personNode.setOccupation(person.getOccupation());
				attributeSet = true;
			}
			if(person.getEducation()!=null) {
				personNode.setEducation(person.getEducation());
				attributeSet = true;
			}
			if(person.getMedicalCondition()!=null) {
				personNode.setMedicalCondition(person.getMedicalCondition());
				attributeSet = true;
			}
			if(person.getPhysicalTraits()!=null) {
				personNode.setPhysicalTraits(person.getPhysicalTraits());
				attributeSet = true;
			}

			/*
			if(person.getResidence()!=null) {
				personNode.setResidence(person.getResidence());
				attributeSet = true;
			}
			if(person.getNationality()!=null) {
				personNode.setNationality(person.getNationality());
				attributeSet = true;
			}
			if(person.getReligion()!=null) {
				personNode.setReligion(person.getReligion());
				attributeSet = true;
			}
			if(person.getClan()!=null) {
				personNode.setClan(person.getClan());
				attributeSet = true;
			}
			if(person.getEthnicity()!=null) {
				personNode.setEthnicity(person.getEthnicity());
				attributeSet = true;
			}

			if(person.getLocations()!=null) {
				personNode.setLocations(person.getLocations());
				attributeSet = true;
			}
			if(person.getLanguages()!=null) {
				personNode.setLanguages(person.getLanguages());
				attributeSet = true;
			}
			if(person.getSpecialCharacteristic()!=null) {
				personNode.setSpecialCharacteristic(person.getSpecialCharacteristic());
				attributeSet = true;
			}
			*/

			if(attributeSet)
				return personNode;

			return null;
		}
		catch(Exception ex) {
			System.err.println("Attributes not set "+ex.getMessage());
			return null;
		}

	}
}
