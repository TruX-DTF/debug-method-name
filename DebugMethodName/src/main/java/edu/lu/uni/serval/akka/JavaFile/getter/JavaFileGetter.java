package edu.lu.uni.serval.akka.JavaFile.getter;

import edu.lu.uni.serval.Configuration;

/**
 * Get all Java files to accelerate the process of parsing Java methods.
 * 
 * @author kui.liu
 *
 */
public class JavaFileGetter {

	public static void main(String[] args) {
		String projectsPath = Configuration.JAVA_REPOS_PATH;
		int numberOfWorkers = 1000;
		MultipleThreadsJavaFileGetter getter = new MultipleThreadsJavaFileGetter(projectsPath, numberOfWorkers);
		getter.getJavaFiles();
	}

}
