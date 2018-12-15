package edu.lu.uni.serval;

import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.revwalk.RevCommit;

import edu.lu.uni.serval.git.exception.GitRepositoryNotFoundException;
import edu.lu.uni.serval.git.exception.NotValidGitRepositoryException;
import edu.lu.uni.serval.git.travel.GitRepository;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Hello world!
 *
 */
public class App {
	private static final String DATASET_FILE_PATH = "OUTPUT/";
	private static final String REVISION_FILE_PATH = "/revisionFiles/";
	private static final String PREVIOUS_FILE_PATH = "/prev/";
	private static final String GIT_REPOSITORY_PATH = "../dataset/commons-math/.git";
	
    public static void main( String[] args ) {
    	
    	createFilesOfAllCommits(); // Create non-test java files in all commits. 
		/*
		 *  TODO:
		 *  analyze the line diff or fragment diff of each DiffEntry.
		 */
    }
    
    /**
     * Create non-test java files in all commits.
     */
    public static void createFilesOfAllCommits() {
    	String revisedFilesPath = DATASET_FILE_PATH + getRepositoryName(GIT_REPOSITORY_PATH) + REVISION_FILE_PATH;
		String previousFilesPath = DATASET_FILE_PATH + getRepositoryName(GIT_REPOSITORY_PATH) + PREVIOUS_FILE_PATH;
		FileHelper.createDirectory(revisedFilesPath);
		FileHelper.createDirectory(previousFilesPath);
		
		GitRepository gitRepo = new GitRepository(GIT_REPOSITORY_PATH, revisedFilesPath, previousFilesPath);
		try {
			gitRepo.open();
			List<RevCommit> commits = gitRepo.getAllCommits(true);
			System.out.println("Selected Commits: " + commits.size());
			gitRepo.createFilesOfAllCommits(commits);
		} catch (RevisionSyntaxException e) {
			e.printStackTrace();
		} catch (NoHeadException e) {
			e.printStackTrace();
		} catch (AmbiguousObjectException e) {
			e.printStackTrace();
		} catch (IncorrectObjectTypeException e) {
			e.printStackTrace();
		} catch (GitRepositoryNotFoundException e) {
			e.printStackTrace();
		} catch (NotValidGitRepositoryException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		} finally {
			gitRepo.close();
		}
    }
    
    private static String getRepositoryName(String gitRepositoryPath) {
		// ../../git/commons-math/.git
		String gitRepositoryName = FileHelper.getFileName(FileHelper.getFileParentPath(gitRepositoryPath));

		return gitRepositoryName;
	}
}
