#!/bin/bash


# Embed code tokens of all selected method bodies.
cd LearningModel
java -cp "target/dependency/*" -Xmx256g edu.lu.uni.serval.dlMethods.EmbedCodeTokens

# Build method body code learning model and learn method body code features.
java -cp "target/dependency/*" -Xmx1024g edu.lu.uni.serval.dlMethods.MethodBodyCodeLearner

# Build method names learning model and learn method names features.
java -cp "target/dependency/*" -Xmx256g edu.lu.uni.serval.dlMethods.MethodNameLearner
