The description of the Project
=================
Un recruteur veut pouvoir selectionner des candidats selon certains critère de compétence comme par exemple leur compétence en C et apliquer un filtre sur sa recherche comme par exemple avoir une note suppérieur à 50 dans la compétence demandé. &nbsp; 

Pour ce faire nous avons à disposition un projet maven qui est fonctionel et que nous avons du améliorée avec plus de fonctionallité possible et une meilleure architecture. &nbsp;
 &nbsp;
 
L'éthique
 -


Nous avons du faire trois stratégie imposé qui sont:
- superiorTo50 --> selectionne tous les candidats qui ont des notes dans les critères demandés 
- superiorTo60 --> selectionne ici quand il dépace les 60
- Moyenne

nous avons du en ajouter et nous avons pensée à ajouter comme stratégie le:
- InfTo"value" --> selectionne tous les candidats qui ont des notes inférieur à "value" car certaines fois avoir un candidat surqualifier coute chère alors que l'on à pas besoin d'une personne aussi performente. Le problème de cette stratégie c'est que normalement c'est compliqué de refuser un candidat parcequ'il est trop bon et si un humain classait les candidats il l'aurai gardé et le salaire que on lui aurai proposé serait surement trop bas par rapport à ses prétentions. &nbsp;

pour éviter de rater des candidats interessants nous nous sommes dis qu'il fallait laisser l'utilisateur choisir les valeurs des strategies et de pouvoir les combinner facilement comme ça il peut avoir plus de liberter de recherche par exemple il peut rechérché facilement:
- les canditas qui ont plus de 45 dans les compétences demandé et inférieur à 80 tous en ayant une moyenne au minimum de 55 &nbsp;

Nous pensons que un humain pourrait faire un meilleur travail car nous ne prennons pas en compte la personne derrière le CV, exemple:
- nous ne pouvons pas vérifier si il à mentie sur les différentes experience professionnelle
- nous ne prenons pas en compte le casier judiciaire du candidat donc notre logiciel sera moins performents qu'un humains pour des métiers sensible comme juge.


Les Tests
-
nous avons tester l'interface graphique de façon manuels afin de vérifier si la view s'affiche correctement et que les boutons et les noms et l'expériences des candidats sont bien affiché au bonne endroit.&nbsp;


Design patterns
-
Nous avons appliqué différent design patterns pendant le projet et nous allons vous les présenters et expliquer ces choix.&nbsp;

Pour les strategies nous avons appliqué le design patterns décorateur qui permet de décorer les différentes strategie et donc de ne pas avoir la multiplication (voire l’explosion) des sous-classes à créer. c'est grâce à que nous avons pu mettre en place une sélection libre dans les strategies, on peut associer les différentes strategie que nous avons créer en leur m'étant à chacune des valeurs différentes ce qui offre une indépendence à l'utilisateur.&nbsp;

Pour 