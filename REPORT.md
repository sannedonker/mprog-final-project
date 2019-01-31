# Eindrapport

## De app
Ik heb een app gemaakt waar je de tafels 1 t/m 10 kan oefenen. Hierbij worden je resultaten opgeslagen. Op deze manier komen de sommen die je moeilijk vindt vaker terug dan de sommen die je al goed kan. In deze app kan je ook specifieke tafels los oefenen en trofeën verdienen.

![alt_text](https://github.com/sannedonker/mprog-final-project/blob/master/doc/main_activity.png)

## Technisch ontwerp
Vanaf het hoofdscherm (te zien in bovenstaande afbeelding) kunnen bijna alle activities bereikt worden. De gebruiker kan een begin level bepalen waarbij alle 100 sommen van de tafels van 1 t/m 10 gehusseld gemaakt worden. Na het maken van de sommen krijgt de gebruiker te zien of er trofeeën zijn verdiend en wordt hij/zij doorgestuurd naar een scherm waar de resultaten zichtbaar zijn. Zowel de antwoorden van de gebruiker als de goede antwoorden worden weergegeven. Ook kan de gebruiker zien op welk niveau de vragen zijn gemaakt.
Start rekenen werkt hetzelfde. Echter het verschil tussen start rekenen en bepaal begin level is het aantal sommen dat de gebruiker moet maken. Bij bepaal begin level zijn dit er 100 en bij start rekeken 20. 
Verder kan de gebruiker specifieke tafels oefenen. De gebruiker kan de tafels die hij/zij wil oefenen selecteren en deselecteren en krijgt alle 10 de sommen van de geselecteerde tafels gehusseld te zien.

De sommen worden opgehaald uit een SQLite database, die bij het eerste gebruik van de app wordt aangemaakt. In deze database staat ook welk level de gebruiker heeft per som. Deze levels worden geupdate bij het rekenen in bepaal begin level en start rekenen maar niet bij specifiek oefenen.

Verder kunnen de resultaten bekeken worden. De resultaten zijn weergegeven in piecharts waarin te zien is hoe goed de tafel wordt beheersd. Op deze piecharts kan geklikt worden voor een overzicht van de tafel. In dat overzicht zijn alle 10 de sommen te zien iedere som in de kleur van het level waarin het wordt beheersd (voor een uitleg van de kleuren verwijs ik je naar de README.md).

Ook kunnen er trofeeën worden verdiend. Wanneer een troffee is verdiend komt dit na het rekenen in beeld. Ook zijn ze te zien in de trofeeën gallerij die bereikbaar is vanaf het hoofdscherm. De trofeeën worden opgeslagen in een SQLite database waarin wordt bijgehouden welke trofeeën wel en niet zijn verdiend.

Er is een scherm waar alle functies van de app kort worden uitgelicht en de betekenis van de verschillende kleuren te zien zijn.

Als laatste optie is er een reset optie waarbij zowel de resultaten als de trofeeën worden gewist. Wanneer er wordt gereset, is er een toast te zien dat dit succesful is gebeurd.

In de onderstaande afbeelding is een overzicht van alle schermen met bijbehorende namen en de mogelijke navigatie daartussen:  
![alt_text](https://github.com/sannedonker/mprog-final-project/blob/master/doc/final_design_0.png)


Er wordt gebruik van gemaakt van een aantal adapters, classes en libraries. Het gebruik van hiervan is samen met alle activities weergegeven in onderstaand uml diagram:  
![alt_text](https://github.com/sannedonker/mprog-final-project/blob/master/doc/final_uml.png)

Zowel in de database waarin de sommen staan als de database met de trofeeën geldt dat iedere som/trofee een id heeft. Op deze manier kunnen de levels en de 'verdien status' van de trofee worden geupdate. Ook kunnen de sommen en trofeeën goed worden doorgegeven naar andere activities door de som en trofee classes.

## Uitdagingen en veranderingen
Bij het oorspronkelijke idee van deze app werd er geen gebruik van een externe library of API. Omdat alle data lokaal op de telefoon van de gebruiker staat vond ik het moeilijk om te bedenken op welke manier ik dit kon implementeren. Omdat het gebruik van een van deze een vereiste is voor het succesvol afsluiten van dit project kwam ik op het idee van het gebruiken van piecharts. Dit loste meteen het probleem op van welke kleur de knop moest zijn als de gebruiker verschillende levels had binnen een tafel.

Toen ik op het idee van deze app kwam, was ik niet van plan de gebruiker een begin level te laten bepalen. Maar tijdens het werken aan de app kwam ik erachter dat ik het moeilijk vond om te bepalen hoe ik de sommen zou laten voorkomen op moeilijkheidsgraad als niet bekend is wat het level van elke som is. Om dit probleem op te lossen heb ik dus het bepalen van een begin level geimplementeerd. Uiteindelijk was dit niet noodzakelijk voor het bepalen van het voorkomen van de sommen (de app kan ook succesvol gebruikt worden als het begin level niet wordt bepaald). Wel denk ik dat deze optie een verbetering is voor de app omdat het anders erg lang zou kunnen duren voordat de gebruiker alle sommen heeft gemaakt.

Verder hebben er geen grote veranderingen plaats gevonden tussen het oorspronkelijke idee en het uitendelijke resultaat. Wel was ik in het begin van plan alle informatie op te slaan in lijsten waarin objecten (sommen) staan. Echter realiseerde ik me al erg snel dat dit niet een handig of zelfs een haalbaar idee was. Hierdoor heb ik ervoor gekozen om zowel de sommen als de trofeeën op te slaan in een SQLite database.

Een aantal van mijn medestudenten vroegen waarom ik geen bericht geef na het maken van de som of deze goed of fout is. Ik heb er echter bewust voor gekozen dit niet te implementeren. Het zien van zo'n bericht kan afleidend zijn. Dit kan er dus voor zorgen dat de  reactietijd voor de volgende som langzamer wordt. Aangezien tijd wordt meegenomen in de bepaling van het level is dit niet bevorderend voor het gebruik van de app. Ook zijn na het maken de resultaten van de gemaakte sommen te zien, het geven van directe feedback is dus niet noodzakelijk.

De grootste uitdagingen zijn geweest bij het gebruiken van externe libraries. Ik kwam erachter dat de uitleg van het gebruik hierbij vaak minimaal is en je goed moet zoeken om te vinden wat je nodig hebt. Zo duurde het bij het implementeren van de piecharts erg lang voordat ik ze zichtbaar kreeg. Na vele verschillende dingen geprobeerd te hebben, bleek dat het lag aan het dat de grootte van de charts op 'match-constraint' stonden. Wanneer ik dit wijzigde naar een standaard grootte werden ze zichtbaar.

Een vergelijkbare uitdaging kwam ik tegen bij het implementeren van confetti. Er zijn verschillende libraries hierover gevonden: een in kotlin en een in java. Eerst heb ik geporbeerd de kotlin library te gebruiken in java, er werd namelijk aangegeven dat dit mogelijk was, maar dat was me niet gelukt. Vervolgens ben ik in de java library gedoken. Ik wilde dat de confetti zichtbaar zou worden als een scherm werd geopend. Omdat dit niet lukte mbv de uitleg in de readme ben ik in de libarary zelf gaan kijken. Ik heb de voorbeeld app gedownload en zo ben ik ieder aspect van de library gaan onderzoeken en deels gaan begrijpen. Helaas was het na een paar uur nog steeds niet gelukt. Het bleek dat de confetti geactiveerd moest worden door een onClick. Hier ben ik achter gekomen doordat ik tijdens de wekelijkse teammeeting gefrustreerd over de confetti vertelde. Een van teamgenoten had even een pauze van zijn eigen project nodig en besloot zelf ook confetti te implementeren. Hem lukte dit snel omdat hij de confetti wilde aanroepen via een knop. Samen kwamen we er vervolgens achter dat deze activatie noodzakelijk was.

Verder is het me opgevallen dat ik vaak heb vastgezeten op kleine dingen die makkelijker bleken dan ik dacht. Dit is ofwel geweest omdat ik ergens een type fout heb gemaakt bij het doorgeven van een intent, ofwel omdat ik de verkeerde functie aanriep. Zulke fouten hebben soms veel tijd gekost en onndodige frustratie meegebracht. Wel heb ik geleerd dat dit hoort bij programmeren en dat precies werken noodzakelijk is voor het maken van een goed werkend programma.

## Verbeterpunten
Gegeven meer tijd zou ik een aantal extra opties toevoegen. Ik zou meerdere trofeeën implementeren en zorgen dat de gebruiker zelf tafels kan toevoegen. Omdat ik wilde dat het algemene gebruik van de app zo goed mogelijk zou zijn, heb ik gekozen om meer aandacht te besteden aan de kwaliteit van de app dan aan de kwantiteit van de verschillende mogelijkheden.

## Dankwoord
Dank gaat uit naar de TA's en mijn teamgenoten. Zonder het kunnen stellen van vragen en de wekelijkse teammeetings had ik niet een app kunnen afleveren waarop ik trots ben en waar confetti in zit.
