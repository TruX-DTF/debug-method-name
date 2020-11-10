#!/bin/bash


# download Java projects from GitHub.
cd Data/JavaRepos/
./git-clone-repos.sh


# parse Java methods of downloaded Java projects.
cd ../../DebugMethodName
java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.MainParser


# Collect renamed methods in Java project repositories.
cd ../RenamedMethodsCollector
java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.renamed.methods.Main


# Prepare data for model learning.
cd ../LearningModel
java -cp "target/dependency/*" -Xmx8g edu.lu.uni.serval.dlMethods.DataPreparer
