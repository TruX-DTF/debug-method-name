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
		int numberOfWorkers = 430;
		MultipleThreadsJavaFileGetter getter = new MultipleThreadsJavaFileGetter(Configuration.JAVA_REPOS_PATH, numberOfWorkers);
		getter.getJavaFiles();
	}

}
