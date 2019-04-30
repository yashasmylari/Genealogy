package genealogy.service;

import org.json.JSONObject;

import genealogy.dto.Person;
import genealogy.struct.RelatedPerson;

public interface PersonService {

	void deleteAll();

	RelatedPerson<Person, Person> addPerson(String person1, String relation, String person2);

	RelatedPerson<Person, Person> addAndRelatePerson(String person1, String relation, String person2);

	RelatedPerson<Person, Person> addPerson(JSONObject person1, String relation, JSONObject person2);

	RelatedPerson<Person, Person> addAndRelatePerson(JSONObject person1, String relation, JSONObject person2);

	boolean relatePerson(RelatedPerson<Person, Person> relatedPerson);

	String findRelation(String person1, String person2);

}
