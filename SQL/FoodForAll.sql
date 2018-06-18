DROP DATABASE IF EXISTS FoodForAll;

CREATE DATABASE FoodForAll;

USE FoodForAll;

CREATE TABLE DinnerGuest (
  dinnerGuestId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstName CHAR(45) NOT NULL,
  lastName CHAR(45) NOT NULL,
  eMailAddress VARCHAR(60) NULL,
  phone VARCHAR(15) NULL
);

CREATE TABLE Allergy (
  allergyId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` CHAR(100) NOT NULL 
);

CREATE TABLE DinnerGuestAllergy (
  dinnerGuestId INT,
  allergyId INT,
  PRIMARY KEY(dinnerGuestId, allergyId)
);

ALTER TABLE DinnerGuestAllergy
ADD CONSTRAINT fk_PersonAllergy_DinnerGuest
FOREIGN KEY (dinnerGuestId) REFERENCES DinnerGuest(dinnerGuestId);

ALTER TABLE DinnerGuestAllergy 
ADD CONSTRAINT fk_DinnerGuestAllergy_Allergy
FOREIGN KEY (allergyId) REFERENCES Allergy(allergyId);


CREATE TABLE Intolerance (
  intoleranceId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` CHAR(100) NOT NULL
);

CREATE TABLE DinnerGuestIntolerance (
  dinnerGuestId INT,
  intoleranceId INT,
  PRIMARY KEY(dinnerGuestId, intoleranceId)
);

ALTER TABLE DinnerGuestIntolerance
ADD CONSTRAINT fk_DinnerGuestIntolerance_DinnerGuest
FOREIGN KEY (dinnerGuestId) REFERENCES DinnerGuest(dinnerGuestId);

ALTER TABLE DinnerGuestIntolerance 
ADD CONSTRAINT fk_DinnerGuestIntolerance_Intolerance
FOREIGN KEY (intoleranceId) REFERENCES Intolerance(intoleranceId);

CREATE TABLE FoodAversion (
  foodAversionId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` CHAR(100) NOT NULL
);

CREATE TABLE DinnerGuestFoodAversion (
  dinnerGuestId INT,
  foodAversionId INT,
  PRIMARY KEY(dinnerGuestId, foodAversionId)
);

ALTER TABLE DinnerGuestFoodAversion
ADD CONSTRAINT fk_DinnerGuestFoodAversion_DinnerGuest
FOREIGN KEY (dinnerGuestId) REFERENCES DinnerGuest(dinnerGuestId);

ALTER TABLE DinnerGuestFoodAversion 
ADD CONSTRAINT fk_DinnerGuestFoodAversion_FoodAversion
FOREIGN KEY (foodAversionId) REFERENCES FoodAversion(foodAversionId);


CREATE TABLE Recipe (
  recipeId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL
  
);

CREATE TABLE Ingredient (
  ingredientId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL
  
);


CREATE TABLE AllergyIngredient (
  allergyId INT,
  ingredientId INT,
  PRIMARY KEY(allergyId, ingredientId)
);

ALTER TABLE AllergyIngredient
ADD CONSTRAINT fk_AllergyIngredient_Allergy
FOREIGN KEY (allergyId) REFERENCES Allergy(allergyId);

ALTER TABLE AllergyIngredient 
ADD CONSTRAINT fk_AllergyIngredient_Ingredient
FOREIGN KEY (ingredientId) REFERENCES Ingredient(ingredientId);

CREATE TABLE IntoleranceIngredient (
  intoleranceId INT,
  ingredientId INT,
  PRIMARY KEY(intoleranceId, ingredientId)
);

ALTER TABLE IntoleranceIngredient
ADD CONSTRAINT fk_IntoleranceIngredient_Intolerance
FOREIGN KEY (intoleranceId) REFERENCES Intolerance(intoleranceId);

ALTER TABLE IntoleranceIngredient 
ADD CONSTRAINT fk_IntoleranceIngredient_Ingredient
FOREIGN KEY (ingredientId) REFERENCES Ingredient(ingredientId);

CREATE TABLE FoodAversionIngredient (
  foodAversionId INT,
  ingredientId INT,
  PRIMARY KEY(foodAversionId, ingredientId)
);

ALTER TABLE FoodAversionIngredient
ADD CONSTRAINT fk_FoodAversionIngredient_FoodAversion
FOREIGN KEY (foodAversionId) REFERENCES FoodAversion(foodAversionId);

ALTER TABLE FoodAversionIngredient 
ADD CONSTRAINT fk_FoodAversionIngredient_Ingredient
FOREIGN KEY (ingredientId) REFERENCES Ingredient(ingredientId);

insert into Allergy (name) values 
	('peanut'),
    ('tree nut'),
    ('milk'),
	('wheat'),
    ('soy'),
	('fish'),
    ('shellfish'),
	('sesame'),
    ('egg');
    
insert into Intolerance (name) values 
	('milk'),
    ('egg'),
	('peanut'),
    ('gluten'),
	('corn'),
    ('shellfish'),
	('soy'),
    ('beef');
    
insert into FoodAversion (name) values 
	('tomatoes'),
    ('eggs'),
	('meat'),
    ('coffee'),
	('onions'),
    ('garlic'),
	('mushroom'),
    ('fish'),
    ('liver');
    
    
insert into dinnerguest (firstName, lastName, emailAddress, phone) values
	('Darth', 'Vader', 'dvader@gmail.com', '8675309'),
	('Luke', 'Skywalker', 'lskywalker@yahoo.com', '555-5555'),
	('Mark', 'Scherr', 'mrscherr@gmail.com', '555-1234'),
	('Han', 'Solo', 'hsolo@aol.com', '555-5523'),
	('Catey', 'Scherr', 'cscherr@gmailcom', '555-4655');

insert into dinnerguestAllergy (dinnerguestId, allergyId) values
	(2 , 1),
    (3 , 3),
    (1 , 4),
    (5 , 6),
    (2 , 2),
    (3 , 4),
    (4 , 7),
    (5 , 4),
    (2 , 3),
    (4 , 1);

insert into dinnerguestIntolerance (dinnerguestId, intoleranceId) values
	(2 , 1),
    (3 , 3),
    (1 , 4),
    (5 , 6),
    (2 , 2),
    (3 , 4),
    (4 , 7),
    (5 , 4),
    (2 , 3),
    (4 , 1);
    
insert into dinnerguestFoodAversion (dinnerguestId, foodAversionId) values
	(2 , 1),
    (3 , 3),
    (1 , 4),
    (5 , 6),
    (2 , 2),
    (3 , 4),
    (4 , 7),
    (5 , 4),
    (2 , 3),
    (4 , 1);
    
select * from allergy;
select * from dinnerguestallergy;
select * from dinnerguestIntolerance;
select * from dinnerguest;	
delete from DinnerGuest where dinnerguestid=9;