The description of the Project
=================
Un recruteur veut pouvoir selectionner des candidats selon certains critère de compétence comme par exemple leur compétence en C et apliquer un filtre sur sa recherche comme par exemple avoir une note suppérieur à 50 dans la compétence demandé. &nbsp; 

Pour ce faire nous avons à disposition un projet maven qui est fonctionel et que nous avons du améliorée avec plus de fonctionallité possible et une meilleure architecture. &nbsp;

Une section « éthique » où vous expliquerez les choix que vous avez
fait en terme de stratégie de sélection de CV : comment vous
assurez-vous que vous choisissez les bons CV, et que vous ne ratez
pas de canidat intéressant (en particulier, pourrait-on accuser
votre algorithme de discrimination ?) ? L'algorithme peut-il être
mis en défaut dans des cas où un humain aurait fait un meilleur
travail ? Si oui, donnez des exemples et justifiez. &nbsp;

Nous avons du faire trois stratégie imposé qui sont:
- superiorTo50 --> selectionne tous les candidats qui ont des notes dans les critères demandés 
- superiorTo60 --> selectionne ici quand il dépace les 60
- Moyenne

nous avons du en ajouter et nous avons pensée à ajouter comme stratégie le:
- superiorTo50InfTo80 --> selectionne tous les candidats qui ont des notes supérior à 50 mais inférieur à 80 car certaines fois avoir un candidat surqualifier coute chère alors que l'on à pas besoin d'une personne aussi performente. Le problème de cette stratégie c'est que normalement c'est compliqué de refuser un candidat parcequ'il est trop bon et si un humain classait les candidats il l'aurai gardé et le salaire que on lui aurai proposé serait surement trop bas par rapport à ses prétentions.