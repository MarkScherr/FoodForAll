DROP DATABASE IF EXISTS TestFoodForAll;

CREATE DATABASE TestFoodForAll;

USE TestFoodForAll;

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


CREATE TABLE RecipeIngredient (
  recipeId INT,
  ingredientId INT,
  PRIMARY KEY(recipeId, ingredientId)
);

ALTER TABLE RecipeIngredient
ADD CONSTRAINT fk_RecipeIngredient_Recipe
FOREIGN KEY (recipeId) REFERENCES Recipe(recipeId);

ALTER TABLE RecipeIngredient 
ADD CONSTRAINT fk_RecipeIngredient_Ingredient
FOREIGN KEY (ingredientId) REFERENCES Ingredient(ingredientId);

insert into Allergy (name) values 
	('Nut'),
    ('Milk'),
	('Wheat'),
    ('Soy'),
	('Fish'),
    ('Shellfish'),
	('Honey'),
    ('Bacon');
    
insert into Intolerance (name) values 
	('Dairy'),
    ('Eggs'),
	('Peanuts'),
    ('Gluten'),
	('Corn'),
    ('Shellfish'),
	('Soy'),
    ('Beef');
    
insert into FoodAversion (name) values 
	('Tomatoes'),
    ('Eggs'),
	('Meat'),
    ('Coffee'),
	('Onions'),
    ('Garlic'),
	('Mushroom'),
    ('Liver');
    