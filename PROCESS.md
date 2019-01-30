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

# Dag 6
Voor vandaag had ik een aantal doelen voor mijzelf gezet:
- Bug bij het doorgeven van de resultaten oplossen
- Level updaten in de database
- Sommen husselen en zorgen dat je sommen van alle gevraagde tafels hebt
- Input verplicht maken

Deze doelen zijn allemaal gehaald. Wel heb ik bedacht dat ik eventueel de invoer methode wil veranderen in de calculate activity. Op het moment werkt het namelijk wel maar is het gebruik gemak nog niet heel hoog. Verder wil ik eventueel het laten zien van de resultaten in een lijst na het maken van de sommen veranderen. Nu wordt gezien op welk correctness level de vraag is beantwoord (groen, geel, oranje of rood). Echter als het antwoord fout is wordt het goede antwoord laten zien. Omdat dit verwarrend is, wil ik dit misschien veranderen.

(todo: plaatjes toevoegen (calculate activity & resultList activity).

Verder ben ik begonnen aan het implementeren van de pie charts. Hier loop ik wel nog tegen een probleem aan van het gebruiken van de hellocharts library. Hopelijk kan ik de implementatie hiervan morgen met hulp oplossen.

# Dag 7
Het doel voor vandaag was het implementeren van pie charts in de resultActivity. Het creëren van een voorbeeld piechart is gelukt. Echter zijn er wel nog problemen bij de pieChartAdapter. Ontdekt is dat de genomen strategie (hele database doorgeven en 10 rijen per positie selecteren met een cursorResourceAdapter) niet werkt. Daarom is er nu een HashMap gemaakt. Helaas is er geen standaard HashMapAdapter dus deze zal morgen gemaakt worden.

Verder is er vandaag met het team gewerkt aan de styleguide. Deze is te vinden in STYLE.md (TODO).

# Dag 9
Het hoofddoel voor vandaag is het werkend maken van de PieChartAdapter. Er was een probleem met het zichtbaarmaken van de charts waar lang over gedaan is om op te lossen. Dit bleek echter makkelijker dan gedacht. Er is geprobeerd ipv een listview een scrollview te gebruiken maar dat bleek niet de oplossing te zijn. Het probleem was dat de charts op 'match-constraint' stonden, toen dit verandered werd naar 320dp werden de charts zichtbaar.

Verder is er een OnItemClick geimplementeerd die je vanaf de piechart naar de resultList voor die tafel brent. Ook is er gekeken naar de updateLevel functie in de DatabaseHelper. Het lijkt namelijk dat deze niet werkt aangezien wanneer de levels uit de database gehaald worden voor de piecharts alle levels op 0, de default, staan. Echter wanneer er op een chart geklikt wordt, zie je dat de levels wel goed worden opgeslagen (de sommen hebben verschillende, correcte, kleuren). Met het resetten is dit dubbel gecheckt en daaruit volgde dat dit vermoeden klopt want alle sommen gingen terug naar grijs (de default waarde). Vermoedelijk zit dit probleem in selectLevel.

Tussendoor is er gewerkt aan een aantal kleine todo's zoals:
- het toevoegen van een progressbar in de calculate activitity
- de kleren van de knoppen van de specific activity aanpassen als erop is geklikt
- input verplicht maken bij specific activity

# Dag 10
Het is gelukt om de data in de pie charts te krijgen! Dit bleek makkelijker dan verwacht, er hoefde slechts een functie op een andere manier aangeroepen te worden. Wel beginnen de piecharts allemaal rechts onder met het invullen van de data. Hier is nog geen oplossing voor gevonden, ik vermoed dat dit in de standaardinstellingen van de hellocharts library zit.

Verder is er een nieuwe activity aangemaakt. Het is een tweede resultlist activity zodat er vanuit de resultaten en vanuit de rekenschermen naar een andere activity wordt gegaan. Deze twee activities hebben beide een andere vormgeving gekregen.

(todo: plaatjes toevoegen)

# Weekend
Er is een nieuwe (effectievere) manier van het shuffelen van de sommen gemaakt. Verder is het vaker voorkomen van moeilijkere sommen geimplementeerd. Er hoeft hiervoor alleen nog maar een boolean worden toegevoegd die vanuit de verschillende activiteiten wordt meegegeven. Deze boolean gaat over het feit of het wel of niet nodig is om op het level te letten.

# Dag 11
Vandaag is er een nieuwe input methode geimplementeerd voor de CalculateActivity. Tijdens het weekend is er veel om feedback gevraagd en er kwam naar voren dat de 'ok' knop ver van het toetsenbord af was. Vandaar is er ervoor gekozen om een send optie in het toetsenbord te gebruiken.

(todo: plaatjes toevoegen)

Verder zijn troffeeen geimplementeerd. Je kan nu troffeeen verdienen per tafel, als je een tafel helemaal gememoriseerd hebt, en als je alle tafels helemaal gememoriseerd hebt. Je kan troffeeen niet verliezen. Morgen zal er gewerkt worden aan een onClickListener zodat de troffeeen beter bekeken kunnen worden (uitleg erbij). Ook zal er gewerkt worden aan een manier die de gebruiker verteld dat er een nieuwe troffee is verdiend.

# Dag 12
Vandaag is er een onClickListener gemaakt in de TrophyActivity. De troffeeen kunnen nu per stuk bekeken worden. Ook is er een TrophyEarnedActivity aangemaakt. Met behulp van deze activity wordt wanneer je een trofee hebt gehaald dit laten zien. 

Verder is er oriëntatie gedaan naar het krijgen van confetti over je scherm wanneer er een trofee is verdiend. Eerst is er geprobeerd een library te gebruiken die is geschreven in kotlin, dit is niet gelukt. Er is wel ook een library gevonden die loopt op java: https://github.com/jinatonic/confetti. Morgen wordt hier verder onderzoek naar gedaan en hopelijk geimplementeerd.

# Dag 13
Vandaag is er gewerkt aan het implementeren van confetti. Dit heeft veel langer geduurd dan nodig was. Omdat ik niet doorhad dat de confetti alleen werkte als je hem later aanroept (dus via een button oid). Wel is hierdoor enige kennis opgedaan over het begrijpen van externe libraries en andermans code. De confetti is geimplementeerd als een 'easter egg'. De button is namelijk een image button dus het is niet perse duidelijk dat de confetti optie er is.

# Dag 15
Vandaag is er gewerkt aan het refactoren van code. Verder is er een begin gemaakt aan het verbeteren van de layout.

# Dag 16
Vandaag is het refactoren afgemaakt. Ook is er totaal nieuwe layout gemaakt.

# Dag 17
Vandaag zijn de laatste puntjes op de i gezet op het gebied van code. Ook zijn er unieke afbeeldingen van trofeeën toegevoegd. Verder is er gewerkt aan een overzicht van alle schermen en de navigatie hiertussen.
