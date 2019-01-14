# Dag 1
Vandaag heb ik mijn definitieve voorstel gemaakt van de tafel oefen app.

(todo: plaatjes toevoegen)

# Dag 2
Vandaag heb ik het design document gemaakt. Wat ik hierbij realiseerde was:
- ik ga een SQL database gebruiken in plaats van lijsten met daarin objecten
- ik ga bij de resultGrid ipv de knoppen te kleuren van de knoppen pie charts maken waarin je kan zien hoe goed die tafel beheersd is.

(todo: plaatjes toevoegen)

# Dag 3
Ik vond het moeilijk om te bedenken hoe ik de sommen wil husselen en hoe vaak ik ze wil laten voorkomen als nog niet alle sommen al een keer gemaakt zijn. Daarom wil ik een extra optie toevoegen. De eerste keer als je de app gebruikt moet je een 'start level' bepalen. Dit houdt in dat je alle 100 sommen moet maken.
Voor nu zet ik deze optie in het hoofdscherm, misschien wil ik deze later naar het 'start reken' scherm verplaatsen.

Verder heb ik vandaag bijna alle activities aangemaakt (alleen de resultList nog niet) en heb ik een begin gemaakt aan de DatabaseHelper.

# Dag 4
Alle activities zijn er nu en navigatie tussen de activiteiten gaat zonder problemen. Ook bij klikken op de terugknop gaat het goed. Verder is er begonnen aan het data halen uit de database. Wel is er hier nog een bug.

# Dag 5
De database bug is opgelost. De tafels kunnen uit de database gehaald worden. De database updaten met de nieuwe levels moet echter nog wel gebeuren. Verder is er gewerkt aan de resultListAdaper. De kleuren voor de levels zijn ingesteld en er is ingesteld wat er op de textViews komt te staan. Helaas is er wel nog een bug bij het doorgeven van de resultaten. Het doorgeven van een ArrayList<Exercise> lukt namelijk nog niet.
