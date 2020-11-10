package edu.lu.uni.serval.renamed.methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.lu.uni.serval.git.exception.GitRepositoryNotFoundException;
import edu.lu.uni.serval.git.exception.NotValidGitRepositoryException;
import edu.lu.uni.serval.git.travel.CommitDiffEntry;
import edu.lu.uni.serval.git.travel.GitRepository;
import edu.lu.uni.serval.utils.FileHelper;

/**
 * Collect all commits and generate previous java files and revised java files.
 * 
 * @author kui.liu
 *
 */
public class CommitDiffs {

	private static Logger log = LoggerFactory.getLogger(CommitDiffs.class);
	
	public static void main(String[] args) {
		List<String> projectList = readList(Configuration.JAVA_REPO_NAMES_FILE);
		for (String project : projectList) {
			traverseGitRepos(project, Configuration.JAVA_REPOS_PATH + project + "/.git");
		}
	}

	public static List<String> readList(String fileName) {
		List<String> list = new ArrayList<>();
		String content = FileHelper.readFile(fileName);
		BufferedReader reader = new BufferedReader(new StringReader(content));
		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void traverseGitRepos(String projectName, String projectGit) {
		String revisedFilesPath = Configuration.COMMIT_DIFF_PATH + projectName + "/revFiles/";
		String previousFilesPath = Configuration.COMMIT_DIFF_PATH + projectName + "/prevFiles/";
		FileHelper.createDirectory(revisedFilesPath);
		FileHelper.createDirectory(previousFilesPath);
		FileHelper.deleteDirectory(Configuration.COMMIT_DIFF_PATH + projectName + "/DiffEntries/");
		GitRepository gitRepo = new GitRepository(projectGit, revisedFilesPath, previousFilesPath);
		try {
			gitRepo.open();
			List<RevCommit> commits = gitRepo.getAllCommits(false);
			log.error(projectName + " Commits: " + commits.size());
			List<CommitDiffEntry> commitDiffentries = gitRepo.getCommitDiffEntries(commits);
			// previous java file vs. modified java file
			gitRepo.createFilesForGumTree(commitDiffentries, true);
		} catch (RevisionSyntaxException e) {
			e.printStackTrace();
		} catch (NoHeadException e) {
			e.printStackTrace();
		} catch (AmbiguousObjectException e) {
			e.printStackTrace();
		} catch (IncorrectObjectTypeException e) {
			e.printStackTrace();
		} catch (MissingObjectException e) {
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
}
