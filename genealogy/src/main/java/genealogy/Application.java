
package genealogy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import genealogy.dto.Person;
import genealogy.service.PersonService;
import genealogy.struct.RelatedPerson;

@SpringBootApplication
@EnableNeo4jRepositories
public class Application {


	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner demo(PersonService personService) {
		return args -> {
			if(delete)
				personService.deleteAll();
			if(ret)
				return;
			
			RelatedPerson<Person, Person> relatedPerson1 = null, relatedPerson2 = null, relatedPerson3 = null;

			relatedPerson1 = personService.addAndRelatePerson(person1, relation1, person2);

			relatedPerson2 = personService.addAndRelatePerson(person1, relation2, person3);

			relatedPerson3 = personService.addAndRelatePerson(person1, relation3, person4);
			
			relatedPerson3 = personService.addAndRelatePerson(person5, relation4, person1);

			Thread.sleep(500);
			if(relatedPerson1!=null) {
				System.out.println(relatedPerson1);
				System.out.println(relatedPerson2);
				System.out.println(relatedPerson3);
			}
			System.exit(0);
		};
	}


	public static String person1 = "{ \"name\":\"Greg\" , \"gender\":\"male\"}";
	public static String person2 = "{ \"name\":\"Daniel\" , \"gender\":\"male\" }";
	public static String person3 = "{ \"name\":\"Roy\" , \"gender\":\"male\" }";
	public static String person4 = "{ \"name\":\"Rita\" , \"gender\":\"female\" }";
	public static String person5 = "{ \"name\":\"Jack\" , \"gender\":\"male\"}";

	public static String relation1 = "Child";
	public static String relation2 = "Parent";
	public static String relation3 = "Parent";
	public static String relation4 = "Child";

	public static boolean delete = true;
	public static boolean ret = true;

}
