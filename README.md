# debug-method-name

Description
------------
A tool of spotting and refactoring inconsistent method names learned from real-world code bases.
This work will be presented at ICSE 2019.

Requirement
------------
  - Java 1.8
  - Maven 3.3.9
  

How to run debug-method-name
-----------------------------
1. Clone the repo:
  - `git clone https://github.com/TruX-DTF/debug-method-name.git`

2. Preparing process:
  - `cd debug-method-name/simple-utils`
  - `mvn install`
  - `cd ../GitTraveller`
  - `mvn install`
  - `cd ../gumtree`
  - `mvn install -DskipTests=true`
  
3. Prepare data: clone Java repositories from GitHub.
  - `cd ../Data/JavaRepos/`
  - `./git-clone-repos.sh`

4. Prepare data: Collect the renamed methods from the commit history of Java programs.
  - `cd ../../RenamedMethodsCollector`
  - `mvn dependency:copy-dependencies`
  - `mvn package`
  - `mv target/RenamedMethodsCollector-0.0.1-SNAPSHOT.jar target/dependency`
  - `java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.renamed.methods.Main`
  
5. Prepare data: Parse methods in Java projects.
   **Note that:** it will take **a long time and a big space** to prepare the data for this experiment, we recommend to use the data we already have to proceed the following steps.
  - `cd ../DebugMethodName`
  - `mvn dependency:copy-dependencies`
  - `mvn package`
  - `mv target/DebugMethodName-0.0.1-SNAPSHOT.jar target/dependency`
  - `java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.MainParser <Java_Project_Index>`, `<Java_Project_Index>` is the index of a Java project in the Java project list (`repos.txt`).
  
6. Prepare data: Prepare data for deep learning of methods.	
  - `cd ../LearningModel`
  - `mvn dependency:copy-dependencies`
  - `mvn package`
  - `mv target/LearningModel-0.0.1-SNAPSHOT.jar target/dependency`
  - `java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.dlMethods.DataPreparer` Prepare data for learning process.
  
7. Model Learning:
   **Note that:** it will take **a long time and a huge space** to finish the learning process.
  - `java -cp "target/dependency/*" -Xmx256g edu.lu.uni.serval.dlMethods.EmbedCodeTokens` Embed method body code tokens.
  - `java -cp "target/dependency/*" -Xmx1024g edu.lu.uni.serval.dlMethods.MethodBodyCodeLearner` Learn method body features with CNNs.
  - `java -cp "target/dependency/*" -Xmx1024g edu.lu.uni.serval.dlMethods.MethodNameLearner` Learn method name features with ParagraphVectors.

8. Spot and Refactor inconsistent method names:
  - `cd ../DebugMethodName`
  - `java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.Main`
