


CREATE TABLE person (
	id INT PRIMARY KEY,
	name TEXT,
	firstName TEXT,
	lastName TEXT,
	dateOfBirth TEXT,
	placeOfBirth TEXT,
	isAlive SMALLINT DEFAULT 1,
	dateOfDeath TEXT,
	imageUrl TEXT,
	imageData TEXT,
	residence TEXT,
	nationality TEXT,
	religion TEXT,
	clan TEXT,
	ethnicity TEXT,
	occupation TEXT,
	education TEXT,
	deleteFlag SMALLINT DEFAULT 0
);



CREATE TABLE relation (
	id INT PRIMARY KEY,
	name TEXT,
	deleteFlag SMALLINT DEFAULT 0
);



CREATE TABLE person_relation (
	id INT PRIMARY KEY,
	person1 INT,
	person2 INT,
	relationId INT,
	deleteFlag SMALLINT DEFAULT 0,
	FOREIGN KEY (person1) REFERENCES person(id),
	FOREIGN KEY (person2) REFERENCES person(id),
	FOREIGN KEY (relationId) REFERENCES relation(id)
);




CREATE TABLE locations (
	id INT PRIMARY KEY,
	name TEXT,
	deleteFlag SMALLINT DEFAULT 0
);


CREATE TABLE person_locations (
	id INT PRIMARY KEY,
	personId INT,
	locationId INT,
	deleteFlag SMALLINT DEFAULT 0,
	FOREIGN KEY (personId) REFERENCES person(id),
	FOREIGN KEY (locationId) REFERENCES locations(id)
);



CREATE TABLE languages (
	id INT PRIMARY KEY,
	name TEXT,
	deleteFlag SMALLINT DEFAULT 0
);


CREATE TABLE person_languages (
	id INT AUTO_INCREMENT PRIMARY KEY,
	personId INT,
	lauguageId INT,
	deleteFlag SMALLINT DEFAULT 0,
	FOREIGN KEY (personId) REFERENCES person(id),
	FOREIGN KEY (lauguageId) REFERENCES languages(id)
);



CREATE TABLE physicalTraits (
	id INT PRIMARY KEY,
	personId INT,
	name TEXT,
	deleteFlag SMALLINT DEFAULT 0,
	FOREIGN KEY (personId) REFERENCES physicalTraits(id)
);


CREATE TABLE person_physicalTraits (
	id INT PRIMARY KEY,
	personId INT,
	physicalTraitsId INT,
	deleteFlag SMALLINT DEFAULT 0,
	FOREIGN KEY (personId) REFERENCES person(id),
	FOREIGN KEY (physicalTraitsId) REFERENCES physicalTraits(id)
);


CREATE TABLE medicalConditions (
	id INT PRIMARY KEY,
	name TEXT,
	deleteFlag SMALLINT DEFAULT 0
);


CREATE TABLE person_medicalConditions (
	id INT PRIMARY KEY,
	personId INT,
	medicalConditionId INT,
	deleteFlag SMALLINT DEFAULT 0,
	FOREIGN KEY (personId) REFERENCES person(id),
	FOREIGN KEY (medicalConditionId) REFERENCES medicalConditions(id)
);


CREATE TABLE specialCharacteristics (
	id INT PRIMARY KEY,
	name TEXT,
	deleteFlag SMALLINT DEFAULT 0
);


CREATE TABLE person_specialCharacteristics (
	id INT PRIMARY KEY,
	personId INT,
	specialCharacteristicId INT,
	deleteFlag SMALLINT DEFAULT 0,
	FOREIGN KEY (personId) REFERENCES person(id),
	FOREIGN KEY (specialCharacteristicId) REFERENCES specialCharacteristics(id)
);





