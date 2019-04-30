package genealogy.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import genealogy.Application;
import genealogy.dao.PersonRepository;
import genealogy.dto.*;
import genealogy.service.PersonService;
import genealogy.struct.RelatedPerson;
import genealogy.util.PersonUtil;

@Service
public class PersonServiceImpl implements PersonService {

	private final static Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	PersonRepository personRepository;


	@Override
	public boolean relatePerson(RelatedPerson<Person, Person> relatedPerson) {
		try {
			Person p1 = personRepository.findByName(relatedPerson.getP1().getName());
			Person p2 = personRepository.findByName(relatedPerson.getP2().getName());

			p1.setRelationShip(p2);
			p2.setRelationShip(p1);

			personRepository.save(p1);
			personRepository.save(p2);

			relatedPerson.getP1().setRelationShip(relatedPerson.getP2().setRelationShip(relatedPerson.getP1()));

			return true;
		}
		catch(Exception ex) {
			log.error("An error occurred while related person", ex);
			return false;
		}
	}


	@Override
	public void deleteAll() {
		personRepository.deleteAll();
	}


	@Override
	public Person addPerson(JSONObject person) {
		try {
			Person p1 = PersonUtil.getPersonFromJsonString(person);
			String nameP1 = p1.getName();
			Person pb1 = personRepository.findByName(nameP1);
			return pb1 = PersonUtil.savePerson(p1, pb1, personRepository);
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
			String relationKey = pb1.getName()+"-"+pb2.getName();
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
			log.error("An error occurred while related person", ex);
			return null;
		}
	}


}
