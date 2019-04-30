package genealogy.dao;

import org.springframework.data.repository.CrudRepository;

import genealogy.dto.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByName(String name);

}
