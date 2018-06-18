6/11/2018

This is my project that I have been working on for about 2 weeks at this point.  This web app will allow the user to
enter the contact name with their allergy/intolerance/food aversion.  This then takes that data and stores it in 
MySQL using Java Hibernate.  The Java also uses a REST Controller to communicate to the site with AJAX calls.  The HTML
is set using Bootstrap.  The site then allows for the user to enter in recipe ingredients.  These ingredients are 
then flagged when they appear in the entry to show who cannot eat the food.  Their are also API calls to Edamam using 
AJAX that search for a recipe among 600k recipes.  The API results are also able to be searched for allergies through 
the database.