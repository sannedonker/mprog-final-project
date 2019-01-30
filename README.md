# Eindproject -- tafel oefen app
Deze app is gemaakt door Sanne Donker als eindproject voor de minor programmeren aan de Universiteit van Amsterdam.
[![BCH compliance](https://bettercodehub.com/edge/badge/sannedonker/mprog-final-project?branch=master)](https://bettercodehub.com/)

## Concept
Er zijn al veel apps op de markt waarbij je de tafels van 1 t/m 10 kan oefenen. Echter houdt geen van deze apps bij welke tafels de gebruiker nog niet kent. Daarom is een app die dit wel bijhoudt op dit moment nog een gemis op de markt.
Het hoofddoel van de app is dus om de tafels gehusseld te kunnen oefenen waarbij de tafels die moeilijker worden gevonden met een grotere kans voorkomen.

De app houdt dus bij welke sommen van de tafels de gebruiker al beheersd en welke nog niet. De beheersing van een som wordt opgedeeld in 4 verschillende categorieën:
- groen: de gebruiker heeft de som goed beantwoord binnen 3 seconden (gememoriseerd).
- geel: de gebruiker heeft de som goed beantwoord binnen 5 seconden (geautomatiseerd).
- oranje: de gebruiker heeft de som goed en beantwoord maar heeft er meer dan 5 seconden over gedaan.
- rood: de gebruiker heeft de som fout geantwoord.

Aan iedere categorie is een level verbonden. De levels zijn 1 t/m 4 waarbij groen 1 is, geel 2, oranje 3 en rood 4. Bij het eerste gebruik van de app kan een begin level bepaald worden. Daarna worden de levels geupdate advh hoe ze de sommen hebben gemaakt. Bij groen is de update -1, bij geel 0, bij oranje +1 en bij rood +2 met minimum level 1 en maximum level 4. De bepaling van deze levels gebeurt achter de schermen, de gebruiker ziet dus alleen de verschillende kleuren wanneer hij/zij de verkregen resultaten bekijkt.

## Visualisatie
De app met bijbehorende navigatie ziet er als volgt uit, hierbij is 'MainActivity' het begin- en hoofdscherm.
![alt_text](https://github.com/sannedonker/mprog-final-project/blob/master/doc/final_design_0.png)

## Demo video
TODO

## Referenties
Er wordt gebruik gemaakt van de hello-charts library en de confetti library.
- hello-charts: https://github.com/lecho/hellocharts-android, Apache License
- confetti: https://github.com/jinatonic/confetti, Apache License

Ook zijn enkele stackoverflow pagina's essentieel geweest bij de ontwikkeling van de app:
- toetsenbord input tijdens het rekenen: https://developer.android.com/training/keyboard-input/style#java
- adapter die de piecharts laat zien in de resultaten: https://stackoverflow.com/questions/19466757/hashmap-to-listview/19467717#19467717

De gebruikte afbeeldingen zijn verkregen via gratis resources, sommigen hiervan zijn zelf bewerkt.

## License
Copyright 2019 Sanne Donker

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
