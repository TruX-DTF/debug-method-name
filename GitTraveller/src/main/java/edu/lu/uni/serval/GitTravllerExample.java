package edu.lu.uni.serval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.revwalk.RevCommit;

import edu.lu.uni.serval.git.exception.GitRepositoryNotFoundException;
import edu.lu.uni.serval.git.exception.NotValidGitRepositoryException;
import edu.lu.uni.serval.git.travel.CommitDiffEntry;
import edu.lu.uni.serval.git.travel.GitRepository;
import edu.lu.uni.serval.utils.FileHelper;

public class GitTravllerExample {
	
	private static final String DATASET_FILE_PATH = "OUTPUT/";
	private static final String REVISION_FILE_PATH = "/revisionFiles/";
	private static final String PREVIOUS_FILE_PATH = "/prev/";
	private static final String GIT_REPOSITORY_PATH = "../dataset/lucene-solr/.git";
	
	public static void main(String[] args) throws GitRepositoryNotFoundException, NotValidGitRepositoryException, IOException, NoHeadException, GitAPIException {
		String repoName = getRepositoryName(GIT_REPOSITORY_PATH);
		String revisedFilesPath = DATASET_FILE_PATH + repoName + REVISION_FILE_PATH;
		String previousFilesPath = DATASET_FILE_PATH + repoName + PREVIOUS_FILE_PATH;
		FileHelper.createDirectory(revisedFilesPath);
		FileHelper.createDirectory(previousFilesPath);
		
		GitRepository gitRepo = new GitRepository(GIT_REPOSITORY_PATH, revisedFilesPath, previousFilesPath);
		gitRepo.open();
		List<RevCommit> commits = gitRepo.getAllCommits(true); //
		String keyword = "CVE_";//TODO
		String outputFile = DATASET_FILE_PATH + "CommitMessages/" + repoName + "/";
		List<RevCommit> selectedCommits = readRevCommit(gitRepo, commits, keyword, outputFile);
		List<CommitDiffEntry> commitDiffentries = gitRepo.getCommitDiffEntries(selectedCommits);
		
		boolean ignoreTestCases = false; //TODO 
		// get diff entries of code changes, the revised java file and previous java file.
		gitRepo.createFilesForGumTree(commitDiffentries, ignoreTestCases);
		gitRepo.close();
		
	}
	
	private static List<RevCommit> readRevCommit(GitRepository gitRepo, List<RevCommit> commitIds, String keyword, String outputFile) throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		StringBuilder builder = new StringBuilder();
		List<RevCommit> commits = new ArrayList<>();
		for (RevCommit commitId : commitIds) {
			String shortMessage = commitId.getShortMessage().toLowerCase(); 
			String fullMessage = commitId.getFullMessage().toLowerCase();
			if (shortMessage.toLowerCase(Locale.ENGLISH).contains(keyword) || fullMessage.toLowerCase(Locale.ENGLISH).contains(keyword)) {
				commits.add(commitId);
				builder.append("#COMMIT_ID:" + commitId.getId().name()).append("\n");
				builder.append("#Short Message: \n").append(shortMessage).append("\n");
				builder.append("#Ful Message: \n").append(fullMessage).append("\n\n");
				FileHelper.outputToFile(outputFile + commitId.getId().name() + ".txt", builder, false);
				builder.setLength(0);
			}
		}
		return commits;
	}

	private static String getRepositoryName(String gitRepositoryPath) {
		// ../../git/commons-math/.git
		String gitRepositoryName = FileHelper.getFileName(FileHelper.getFileParentPath(gitRepositoryPath));

		return gitRepositoryName;
	}

}
