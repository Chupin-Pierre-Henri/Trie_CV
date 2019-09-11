Mif01 - TP Maven-Git-Forge
==========================

Objectif
--------

Mettre en place et maîtriser les outils de gestion de code utilisés tout
au long de l’année.

-   Outil de build avec Maven (mvn)
-   Outil de versioning avec Git (git)
-   Outil de gestion de projet avec Gitlab (la Forge)

Déroulement
-----------

Ce TP est à réaliser de préférence sous Linux (accès à `mvn` et `git`
en ligne de commande). L’utilisation d’IDE est n’est pas recommandée
pour ce TP.

Sur les machines de Lyon 1, Maven n'est pas installé sous Windows.
Vous pouvez tout de même travailler sous Windows mais vous devrez
[installer Maven sur votre
compte](https://www.mkyong.com/maven/how-to-install-maven-in-windows/).
Vous pouvez aussi travailler sous Linux pour les manipulations avec
`mvn`.

Pour les TPs suivants, vous pourrez travailler avec un IDE (Eclipse
avec plugin m2e et IntelliJ sont installés sous Windows à Lyon 1).

## Démarrage

### Configuration de base de Git

Vous devriez déjà avoir fait un fork de votre projet sur la forge, et
un `git clone` pour en récupérer une copie locale. Si ce n'est pas le
cas, reprenez les instructions du [lab1-java](../lab1-java).

Vous pouvez passer directement à la section suivante si vous avez déjà
configuré Git sur votre machine.

Sur votre ordinateur, dans un terminal, ajoutez les informations de base
à votre compte git (les données seront rangées dans un fichier
`~/.gitconfig`, que vous pouvez aussi éditer à la main si vous
préférez) :

```
git config --global core.editor "votre_editeur_prefere"
git config --global user.name "Nom Prenom"
git config --global user.email "votre_email@etu.univ-lyon1.fr"
```

La première ligne spécifie l'éditeur de texte à utiliser pour saisir
les messages de commits. Vous pouvez spécifier par exemple `emacs` ou
`vim`. Si vous utilisez gvim, écrivez ici `gvim -f` et si vous
utilisez gedit, `gedit -s` (Si un `gvim` est déjà
lancé, la commande `git commit` va se connecter au `gvim` déjà
lancé pour lui demander d'ouvrir le fichier, et le processus lancé par
`git` va terminer immédiatement. Git va croire que le message de
commit est vide, et abandonner le commit. Utiliser `gvim -f`
permet d'ouvrir une nouvelle instance de gvim ce qui règle le
problème).

Les lignes suivantes seront utilisées pour le champ « auteur » des
commits que vous ferez. Utilisez votre vrai nom (pas votre login) et
votre adresse email.

Pour vous assurer que votre environnement est opérationnel, faites une
modification triviale (par exemple adapter l'URL de votre projet dans
`pom.xml`, mais vous avez sans doute également les modifications
non-commitées du lab1), puis faites un commit et un push :

```
git status
git add pom.xml
git commit
git push
```

Dans le navigateur, ouvrez la page de votre projet sur la forge: vous
pouvez voir les révisions déjà présentes et même regarder le code
source en ligne, ainsi que les différences entre les révisions.

Il est probable que vous voyez une erreur comme celle-ci :

![commit: running](../fig/commit-error.png)

Ne paniquez pas : le commit est bien là, c'est l'intégration continue
qui a échouée, vous verrez pourquoi à la fin du TP.

## .gitignore et gestion d’un ticket

Les utilitaires comme Maven génèrent un grand nombre de fichiers qu'ils ne
faut pas ajouter à vos dépôts. Vous allez donc configurer ce projet de
manière à les ignorer. Pour cela vous allez utiliser en plus l'outil de
gestion de tickets.

Depuis l'interface web de la forge, créez une nouvelle demande
(*issue*) intitulée: “ignorer le répertoire target et les fichiers des
IDE”. En effet, ce dossier `target` sera créé par Maven au moment du build, et
contiendra les fichiers `.class` et le jar. Les fichiers comme
`.classpath` sont générés par les IDE comme Eclipse et ne doivent pas
être versionnés (pas de `git add .classpath`), car ceux-si peuvent être
différents d'un utilisateur à l'autre (par exemple parce qu'ils
contiennent des chemins absolus comme `/home/toto/.m2/...`).

Accédez à la liste des demandes de votre projet puis modifiez la demande
précédente en affectant un des membres du projet à cette tâche. Notez bien
le numéro `#xxxx` de la demande (sans doute #1).

Créez un fichier `.gitignore` à la base du répertoire `cv-search/` et
ajoutez-y les lignes suivantes :

```
# Ignore les fichiers de configuration d'Eclipse
.classpath
.project
.settings/

# Ignore les fichiers de configuration d'Intellij
.idea/
*.iml
*.iws

# Ignore les fichiers produits par Maven pour ne versionner que le code,
# pas les executables ou les logs.
log/
target/
```

Ce fichier contient la liste des fichiers que git doit ignorer: la
commande `git status` ne les mentionnera pas dans la section
`Untracked files`, et la commande `git add` refusera par défaut de les
ajouter.

```
git status
```

n’affiche à présent plus les fichiers dans target, mais affiche le
fichier `.gitignore`. Ajoutez ce fichier dans les fichiers versionnés :

```
git add .gitignore
```

puis valider en indiquant le numéro de la demande `\#xxxx` dans le message
de commit:

```
git commit -m "Gestion des fichier à ignorer (fixes #xxxx)"
```

puis faire le push

```
git push
```

Dans le projet forge, allez voir le dépôt et cliquez sur le dernier
commit. Vous pouvez vérifier que le `#xxxx` est un lien vers votre
demande, et que la présence de `fixes #xxxx` dans un message de commit
a automatiquement fermé le ticket correspondant.

### Un gitignore local par utilisateur

Vous avez sans doute vos habitudes et vos outils qui vous font
manipuler des fichiers que vous ne voulez pas partager et donc ajouter
au `.gitignore` (par exemple, ignorer `*~` si votre éditeur de texte
laisse des `fichier~` en guise de backup, ou ignorer `.DS_Store` si
vous êtes sous Mac OS). Mais d'une part, vous voulez ignorer ces fichiers
pour tous vos projets (sans avoir à modifier le `.gitignore` de chaque
projet), et d'autre part vous ne voulez pas forcément faire gonfler le
`.gitignore` des projets auxquels vous contribuez avec des entrées qui
ne concernent pas les autres.

Pour cela, il existe un fichier `~/.config/git/ignore` (le créer s'il
n'existe pas). Mettez-y par exemple ce contenu :

```
# Emacs's backup files
*~

# Mac's .DS_Store
.DS_Store
```

### Travail avec plusieurs dépôts distants

Si vous avez suivi les instructions jusqu'ici, vous devriez travailler
avec deux dépôts distants :

* https://forge.univ-lyon1.fr/matthieu.moy/m1if01-2019, qui est le
  squelette fourni par les enseignants. Vous n'y avez accès qu'en
  lecture (`pull`, `clone`).
  
* https://forge.univ-lyon1.fr/<votre-login>/m1if01-2019, qui est votre
  « fork » privé. Vous avez tous les droits dessus, y compris `push`.

Pour votre travail au quotidien, c'est votre dépôt privé que vous
utiliserez. Mais quand les enseignants envoient des nouveautés (ou des
corrections) dans le dépôt enseignant, vous devez également vous
mettre à jour :

Ajoutez le dépôt enseignant comme dépôt distant (à faire une fois en
début de projet) :

```
git remote add moy https://forge.univ-lyon1.fr/matthieu.moy/m1if01-2019.git
```

Pour récupérer les mises à jour du dépôt enseignant, faites :

```
git pull moy master  # Récupérer les modifications en local
git push  # Les envoyer vers votre fork privé
```

## Invocation de maven

Vous avez déjà vu au TP précédent la commande pour compiler :

```
mvn compile
```

Le répertoire target contient tout ce qui est généré par maven. Explorer
le contenu du répertoire, puis invoquer

```
mvn clean
```

Regarder ce qui a été supprimé.

On lancer les tests associés au projet avec:

```
mvn test
```

La phase de vérification doit renvoyer au moins une erreur : vos
enseignants sont taquins et vous ont volontairement fourni du code
avec un ou des défauts. Vous corrigerez ces défauts un peu plus tard.

En pratique on veut souvent nettoyer le dossier target, et relancer le
processus de build, tests compris :

```
mvn clean install
```

Pour lancer l’application en ligne de commande on utilise :

```
mvn compile
mvn exec:java
```

Ou bien, on peut lancer l'application via la commande `java` après
avoir généré le `.jar` avec `mvn install`:

```
# Pour cette fois, on autorise la construction du .jar même en
# présence d'erreur dans le tests et le style (-DskipTests
# -Dcheckstyle.skip) :
mvn -DskipTests  -Dcheckstyle.skip install
```

## Packaging

Le plugin `maven-assembly-plugin` est configuré pour vous pour
générer un jar exécutable incluant les bibliothèques utilisées (voir
[ici](http://stackoverflow.com/questions/574594/how-can-i-create-an-executable-jar-with-dependencies-using-maven)).

Tester en lancer java via (adaptez l'argument de `--module-path` à
votre installation si besoin) :

Sous Linux au Nautibus :

```
java --module-path /home/tpetu/m1if01/javafx-sdk-11.0.2/lib --add-modules=javafx.controls -jar target/cv_search-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

Sous Ubuntu avec le package `openjfx` installé :

```
java --module-path /usr/share/openjfx/lib --add-modules=javafx.controls -jar target/cv_search-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Gérer les conflits

Nous n’allons pas mettre en pratique la gestion de conflits dans ce TP,
mais c’est quelque chose qui arrive fréquemment. La [documentation de Git
traite du
sujet](https://git-scm.com/book/fr/v2/Les-branches-avec-Git-Branches-et-fusions%C2%A0%3A-les-bases)),
et [Github a un guide expliquant plutôt bien les
choses](https://help.github.com/articles/resolving-a-merge-conflict-using-the-command-line/).

Pour télécharger la dernière version depuis le dépôt et la fusionner
avec votre version locale, faire :

```
git pull
```

Pour lancer la fusion de deux branches on utilise la commande merge :

```
git merge <branche-a-fusionner>
```

Le conflits interviennent souvent lors du pull d’une branche dans la
branche courante. Cette commande importe les modification de la branche
crée lors du pull dans la branche courante.

`git status` signale les fichiers en conflits. Editer ces fichiers
pour intégrer de manière cohérente les modifications effectuées dans
le deux branches. Une fois les modifications effectuées, si la
construction `mvn install` fonctionne, indiquer que les conflits sont
résolus via

```
git add le_fichier_concerne
git commit
```

## Intégration continue avec GitLab-CI

### Principe

Le principe de l'intégration continue (Continuous Integration, CI) est
de maintenir une base de code opérationnelle en permanence. Pour cela,
on lance des tests (automatiques) le plus souvent possible.
[Gitlab-CI](https://docs.gitlab.com/ee/ci/quick_start/) permet de
lancer des tests à chaque push envoyé sur la forge. Un ensemble de
tests à lancer s'appelle un « pipeline » dans la terminologie GitLab.

Avant tout, vérifiez que les pipelines sont activés sur votre projet :

* Ouvrez la page d'accueil de votre projet dans votre navigateur.

* Dans la barre latérale de gauche, en bas, « Settings → General »,
  puis « permissions ». (Vous devriez retrouver « project visibility =
  private », si ce n'est pas le cas relisez le début de ce document,
  c'est important).
  
* Vérifiez que l'interrupteur « pipelines » est sur « enabled ».

* Si vous avez modifié quelque chose, cliquez sur « save changes ».

Il faut maintenant dire à GitLab quelle commande il doit lancer à
chaque push, et dans quel environnent. Cela se fait dans le fichier
`.gitlab-ci.yml` à la racine de votre projet Git. Un `.gitlab-ci.yml`
typique pour un petit projet (*pas* pour le notre) ressemble à ceci :

```yaml
tests:
  image: ubuntu
  script:
  - ./my-test-script.sh
```

Avec cette configuration, le moteur d'intégration continue va lancer
le script `my-test-script.sh` dans un conteneur docker (sorte de
machine virtuelle) sur lequel est installé Ubuntu. Si
`my-test-script.sh` a un statut de retour non-nul (par exemple s'il
termine par `exit 1`), on considère que les tests échouent.

### Mise en place

Pour notre projet, nous vous fournissons un `.gitlab-ci.yml` qui fait
un peu plus que cela. Ouvrez le fichier
[../.gitlab-ci.yml](../.gitlab-ci.yml). Ce fichier permet de :

* Utiliser une image docker sur laquelle Maven et JavaFX sont
  disponibles. Cette image configure également un proxy HTTP qui est
  nécessaire sur la forge Lyon 1, mais c'est transparent pour vous.
  
* Mettre en cache le répertoire `.m2/repository`, pour éviter de
  re-télécharger toutes les dépendances à chaque pipeline.
  
Ouvrez ce fichier dans votre éditeur de texte. La dernière ligne
(derrière `script:`) ne fait pour l'instant rien d'intelligent.
Remplacez-la par :

```
    - cd cv-search && mvn test --batch-mode
```

(Attention, l'indentation compte : cette ligne doit être indentée en
retrait de la ligne `script:` qui précède)

Faites un `commit` pour valider ce changement, et envoyez-le sur la
forge avec `git push`. Ouvrez la page d'accueil de votre projet dans
votre navigateur.

Le dernier commit est affiché, et vous devez maintenant avoir un
indicateur « commit: running » :

![commit: running](../fig/commit-running.png)

Au bout d'un certain temps, l'indicateur passera en rouge : le projet
que nous vous fournissons ne passe pas les tests !

Pour avoir une notification par email à chaque échec de pipeline,
choisissez dans la barre latérale : « Settings → Integrations », puis
« Pipelines emails ».

Vous pouvez examiner la sortie de la commande `mvn test` lancée par
GitLab en choisissant dans la barre latérale : « CI / CD → Jobs » puis
en choisissant le dernier commit passé (le premier dans la liste). Si
vous avez correctement réalisé l'étape précédente vous devriez voir la
sortie de Maven.

### Correction des défauts

Pour corriger les problèmes, le mieux est de travailler en local.
L'échec sur la forge se produit à l'exécution de la commande `mvn
test`, donc vous pouvez reproduire le problème en faisant `mvn test`
de votre côté.

Il y a deux choses à corriger :

* Un test échoue (vous devez avoir le message
  `Tests run: 4, Failures: 1, Errors: 0, Skipped: 0`).
  Ouvrez le fichier `ApplicantTest.java` et regardez rapidement son
  contenu. Un commentaire `TODO` vous dit ce qu'il faut corriger :
  faites-le et relancez les tests.

* Dans `ApplicantList.java`, une erreur de style attrapée par le [plugin
  maven](https://maven.apache.org/plugins/maven-checkstyle-plugin/) de
  l'outil [checkstyle](http://checkstyle.sourceforge.net/). Un `TODO`
  devrait vous indiquer ce qu'il faut faire.
  
Une fois ces deux corrections faites, vérifiez que `mvn test` ne lève
plus d'erreur, faites un commit et un push, et vérifiez que
l'intégration continue de la forge valide ce commit.

### Quelques détails sur le pom.xml

Si ce n'est pas déjà fait, parcourez le fichier `pom.xml`. C'est
verbeux (XML), mais il n'y a rien de sorcier. En pratique, on édite
rarement ce fichier entièrement à la main : soit on utilise un outil
automatique, soit on copie-colle et on adapte des portions de code
fournies sur le site des outils utilisés (https://search.maven.org/
est un bon point de départ pour chercher les dépendances disponibles).
Pour le cas qui nous intéresse, les portions importantes sont :

Le plugin `exec-maven-plugin` pour lancer l'application :

```xml
  <plugin>
	<groupId>org.codehaus.mojo</groupId>
	<artifactId>exec-maven-plugin</artifactId>
	<version>1.5.0</version>
	<executions>
	  <execution>
	    <goals>
	      <goal>java</goal>
	    </goals>
	  </execution>
	</executions>
	<configuration>
 	  <mainClass>fr.univ_lyon1.info.m1.cv_search.App</mainClass>
	</configuration>
  </plugin>
```

L'important ici est de spécifier la classe principale (`<mainClass>`).

Les tests utilisent l'API JUnit :

```xml
    <!-- https://mvnrepository.com/artifact/junit/junit -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
```

Ce morceau de code est copié-collé depuis l'URL donnée en commentaire.
Nous utilisons ici JUnit 4.12 même si la version 5 est sortie, car la
combinaison Maven + JUnit 5 + Eclipse semble poser problème pour
l'instant.

La mention `<scope>test</scope>` permet de rendre cette dépendance
disponible pendant la commande `mvn test`, mais pas dans les
exécutions autres que les tests.

Le plugin checkstyle :

```xml
     <plugin> <!-- https://maven.apache.org/plugins/maven-checkstyle-plugin/usage.html -->
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-checkstyle-plugin</artifactId>
       <version>3.0.0</version>
       <executions>
         <execution>
           <id>validate</id>
           <phase>test</phase>
           <configuration>
             <configLocation>src/main/config/checkstyle.xml</configLocation>
             <encoding>UTF-8</encoding>
             <consoleOutput>true</consoleOutput>
	         <!-- mvn test fails for any warning or error -->
             <failsOnError>true</failsOnError>
             <violationSeverity>warning</violationSeverity>
             <linkXRef>false</linkXRef>
           </configuration>
           <goals>
             <goal>check</goal>
           </goals>
         </execution>
       </executions>
     </plugin>
```

Checkstyle peut être configuré pour forcer des règles différentes
selon les projets. Le fichier de configuration est spécifié avec
`<configLocation>`. Ici, il est configuré de manière assez stricte :
la validation échoue pour les warnings et pas seulement pour les
erreurs. On demande à exécuter pendant `mvn test` (via
`<phase>test</phase>`) la cible `check` (`<goal>check</goal>`) du
plugin.
  
## Et les merge-requests (alias pull-requests sur GitHub) ?

Les merge-requests sont un mécanisme **très pratique** et **très
efficace** pour organiser la revue de code. Le principe :

* Le contributeur commite chaque nouvelle fonctionalité dans une
  branche (autre que master), et envoie (`push`) cette branche sur la
  forge.

* Le contributeur demande à fusionner cette branche depuis la forge
  (onglet « merge request », bouton « new merge request »).
  
* Si [Gitlab-CI](https://docs.gitlab.com/ee/ci/quick_start/) est
  configuré, les tests sont lancés automatiquement.

* Les développeurs discutent de la demande, ajoutent et modifient les
  commits à fusionner si besoin.
  
* Le propriétaire du projet accepte la demande (bouton « merge » en
  bas de la discussion sur la page de la merge-request, ou `git` en
  ligne de commande), ou la rejette. Si un pipeline d'intégration
  continue est en cours, on peut aussi demander à faire la fusion
  après succès des tests.

Le bénéfice n'est pas forcément évident sur un petit TP ou projet
scolaire, mais si le temps le permet et que vous êtes déjà à l'aise
avec les bases, nous vous encourageons **très vivement** à
expérimenter et pourquoi pas à lire la [documentation de GitLab sur
les
merge-requests](https://docs.gitlab.com/ce/user/project/merge_requests/).

## Et maintenant ?

L'essentiel du travail de votre mini-projet se trouve dans le
[lab3](../lab3-patterns), ne perdez pas de temps !
