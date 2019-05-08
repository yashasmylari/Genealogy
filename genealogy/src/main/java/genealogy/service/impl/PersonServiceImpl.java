package genealogy.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import genealogy.dao.PersonRepository;
import genealogy.dto.*;
import genealogy.service.PersonService;
import genealogy.struct.RelatedPerson;
import genealogy.util.PersonUtil;

@Service
public class PersonServiceImpl implements PersonService {

	private final static Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	PersonRepository personRepository;



	@Override
	public void deleteAll() {
		personRepository.deleteAll();
	}



	@Override
	public Person addPerson(JSONObject person) {
		try {
			String name = person.getString("name");
			Person pb = personRepository.findByName(name);
			Person p = PersonUtil.getPersonFromJsonString(person);
			if(pb==null) {
				personRepository.save(p);
				return personRepository.findByName(name);
			}
			else {
				Person personNode = PersonUtil.setAttributeFromPerson(p, pb);
				if(personNode!=null)
					personRepository.save(personNode);
				return personNode;
			}
		}
		catch(Exception ex) {
			log.error("An error occurred while adding person", ex);
			return null;
		}
	}



	@Override
	public RelatedPerson<Person, Person> relatePerson(String person1, String person2, String relation) {
		try {
			if(relation==null || relation.length()==0)
				return null;

			Person p1 = personRepository.findByName(person1);
			Person p2 = personRepository.findByName(person2);

			if(p1==null || p2==null)
				return null;

			RelatedPerson<Person, Person> relatedPerson = PersonUtil.getPersonRelation(p1, p2, relation);

			p1 = relatedPerson.getP1();
			p2 = relatedPerson.getP2();

			p1.setRelationShip(p2);
			p2.setRelationShip(p1);

			relatedPerson.generateRelationship();

			p1.setRelation(null);
			p2.setRelation(null);

			personRepository.save(p1);
			personRepository.save(p2);

			return relatedPerson;
		}
		catch(Exception ex) {
			log.error("An error occurred while related person", ex);
			return null;
		}
	}



	@Override
	public String findRelation(String person1, String person2) {
		try {
			Person pb1 = personRepository.findByName(person1);
			Person pb2 = personRepository.findByName(person2);

			if(pb1==null || pb2==null)
				return "one of the node is non existent";

			StringBuilder sbTop = new StringBuilder();
			sbTop.append("(").append(person1).append(":").append(pb1.getGender()).append(")");

			
			String relationMap = pb1.getRelationMap();
			String relationKey = pb2.getName();
			JSONObject jsonRelation = new JSONObject(relationMap);

			if(jsonRelation.has(relationKey))
				return PersonUtil.strRelationship(sbTop.toString(), jsonRelation, relationKey, pb2);

			Set<String> scannedNodes = new HashSet<>();
			scannedNodes.add(pb1.toString());
			RelatedPerson<Person, Person> relatedPerson = new RelatedPerson<Person, Person>(pb1, pb2);
			String relation = PersonUtil.searchRelationShip(relatedPerson, jsonRelation, sbTop.toString(), 1, personRepository, scannedNodes, new HashSet<>());
			
			if(relation.isEmpty())
				return "no relation";
			
			return relation;
		}
		catch(Exception ex) {
			log.error("An error occurred while finding relation between person", ex);
			return null;
		}
	}



	@Override
	public Person findPerson(String name) {
		try {
			Person person = personRepository.findByName(name);
			if(person!=null)
				return person;
			return null;
		}
		catch(Exception ex) {
			log.error("An error occurred while finding the person", ex);
			return null;
		}
	}


}
