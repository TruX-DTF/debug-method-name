package edu.lu.uni.serval.git.travel;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.lu.uni.serval.git.exception.GitRepositoryNotFoundException;
import edu.lu.uni.serval.git.exception.NotValidGitRepositoryException;
import edu.lu.uni.serval.git.filter.CommitFilter;
import edu.lu.uni.serval.git.filter.DiffEntryFilter;
import edu.lu.uni.serval.git.filter.LineDiffFilter;
import edu.lu.uni.serval.utils.FileHelper;

public class GitRepository {
	
	private static Logger log = LoggerFactory.getLogger(GitRepository.class);

	private String repositoryPath = null;
	private static FileRepositoryBuilder fileRepositoryBuilder = null;
	private static Repository repository = null;
	private static Git git = null;
	
	private String revisedFilePath;
	private String previousFilePath;

	/**
	 * Creates a new GitRepository with a correct local path of a git repository.
	 * @param repositoryPath
	 * 			The local path of a git repository.
	 */
	public GitRepository(String repositoryPath, String revisedFilePath, String previousFilePath) {
		this.repositoryPath = repositoryPath;
		this.revisedFilePath = revisedFilePath;
		this.previousFilePath = previousFilePath;
	}

	public String getRevisedFilePath() {
		return revisedFilePath;
	}
	
	public String getPreviousFilePath() {
		return previousFilePath;
	}
	
	/**
	 * Open the git repository.
	 * @throws NotValidGitRepositoryException 
	 * @throws GitRepositoryNotFoundException 
	 * @throws IOException 
	 */
	public void open() throws GitRepositoryNotFoundException,
			NotValidGitRepositoryException, IOException {
		this.gitPathExisting(repositoryPath);
		File gitFile = new File(this.repositoryPath);
		fileRepositoryBuilder = new FileRepositoryBuilder().setGitDir(gitFile)
				.readEnvironment().findGitDir();
		repository = fileRepositoryBuilder.build();
		git = new Git(repository);
	}

	/**
	 * Close the git repository.
	 */
	public void close() {
		if (git != null) {
			git.close();
			git = null;
		}
		if (repository != null) {
			repository.close();
			repository = null;
		}
		fileRepositoryBuilder = null;
		repositoryPath = null;
	}

	/**
	 * Check out whether the provided git path is correct or not.
	 * 
	 * @param gitPath
	 *            The provided git path.
	 * @throws GitRepositoryNotFoundException 
	 * @throws NotValidGitRepositoryException 
	 */
	private void gitPathExisting(String gitPath)
			throws GitRepositoryNotFoundException,
			NotValidGitRepositoryException {
		File file = new File(gitPath);
		if (gitPath.endsWith("/.git")) {
			if (file.exists()) {
			// The git repository is existed.
			} else {
				throw new GitRepositoryNotFoundException("The git path is not found, please provide the existed path of the local git repository!");
			}
		} else {
			throw new NotValidGitRepositoryException("The git path is invalid, please provide the valid path of the local git repository!");
		}
	}

	/**
	 * Get all RevCommits recorded in the log of Git.
	 * 
	 * @return A Iterator of RevCommits
	 * @throws IOException 
	 * @throws GitAPIException 
	 * @throws NoHeadException 
	 */
	private Iterator<RevCommit> getAllLogs() throws NoHeadException, GitAPIException, IOException {
		Iterable<RevCommit> logs = null;
		Iterator<RevCommit> revCommits = null;

		logs = git.log().all().call();
		revCommits = logs.iterator();

		return revCommits;
	}
	
	public RevCommit getHeadCommit() throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		ObjectId lastCommitId = repository.resolve(Constants.HEAD);
		@SuppressWarnings("resource")
		RevWalk revWalk = new RevWalk(repository);
		RevCommit commit = revWalk.parseCommit(lastCommitId); // org.eclipse.jgit.errors.MissingObjectException TODO
		return commit;
	}
	
	public List<RevCommit> getAllCommits(boolean isBugRelated) throws NoHeadException, GitAPIException, IOException	 {
		Iterator<RevCommit> revCommits = null;
		List<RevCommit> commits = new ArrayList<>();
		revCommits = this.getAllLogs();
		while (revCommits.hasNext()) {
			RevCommit commit = revCommits.next();
			if (isBugRelated) {
				if (filter(commit)) {
					commits.add(commit);
				}
			} else {
				commits.add(commit);
			}
		}

		return commits;
	}

	public List<RevCommit> getAllFindBugsCommits(boolean onlyOne) throws NoHeadException, GitAPIException, IOException	 {
		Iterator<RevCommit> revCommits = null;
		List<RevCommit> commits = new ArrayList<>();
		revCommits = this.getAllLogs();
		while (revCommits.hasNext()) {
			RevCommit commit = revCommits.next();
			/*
			 *  filter commits by identifying whether the committed short or full messages contain term 'findbugs' or 'findbug'.
			 */
			String shortMessage = commit.getShortMessage().toLowerCase(); 
			String fullMessage = commit.getFullMessage().toLowerCase();
			if (shortMessage.toLowerCase(Locale.ENGLISH).contains("findbugs") || fullMessage.toLowerCase(Locale.ENGLISH).contains("findbugs")) {
				commits.add(commit);
				if (onlyOne) {
					break;
				}
			}
		}

		return commits;
	}
	
	public List<RevCommit> filterCommits(List<RevCommit> commits) {
		List<RevCommit> selectedCommits = new ArrayList<>();
		for (RevCommit commit : commits) {
			if (filter(commit)) {
				selectedCommits.add(commit);
			}
		}
		
		return selectedCommits;
	}

	public List<RevCommit> filterCommitsByKeywordsPair(List<RevCommit> commits) {
		List<RevCommit> selectedCommits = new ArrayList<>();
		for (RevCommit commit : commits) {
			if (filter2(commit)) {
				selectedCommits.add(commit);
			}
		}
		
		return selectedCommits;
	}

	private boolean filter(RevCommit commit) {
		if (CommitFilter.filterInitializeCommit(commit)){// || CommitFilter.filterMergeCommit(commit)) {
			// filter out initialize and merge commits.
			return false;
		}
		/*
		 *  filter commits by identifying whether the committed short or full messages contain bug-related words.
		 *  Remove "default",
		 */
		String shortMessage = commit.getShortMessage().toLowerCase(); 
		String fullMessage = commit.getFullMessage().toLowerCase();
		List<String> bugRelatedWords = BugRelatedWords.BUG_RELATED_KEY_WORDS;
		
		for (String relatedWord : bugRelatedWords) {
			if (shortMessage.contains(relatedWord) || fullMessage.contains(relatedWord)) {
				if (shortMessage.contains("fix typo") || shortMessage.contains("fix build")
						|| shortMessage.contains("non-bug") || shortMessage.contains("non-error")
						|| shortMessage.contains("non-fix") || shortMessage.contains("default")) {
					return false;
				}
				return true;
			}
		}
		
		return false;
	}

	private boolean filter2(RevCommit commit) {
		if (CommitFilter.filterInitializeCommit(commit)){// || CommitFilter.filterMergeCommit(commit)) {
			// filter out initialize and merge commits.
			return false;
		}
		/*
		 *  filter commits by identifying whether the committed short or full messages contain bug-related words.
		 */
		String shortMessage = commit.getShortMessage().toLowerCase(); 
		String fullMessage = commit.getFullMessage().toLowerCase();
		/*
		 * <bug, fix>, <bug, repair>, <bug, patch>.
		 * <fault, fix>, <fault, repair>, <fault, patch>.
		 * <error, fix>, <error, repair>, <error, patch>.
		 */
		
		if (isBugRealtedMessage(shortMessage, fullMessage, "bug", "fix") ||
				isBugRealtedMessage(shortMessage, fullMessage, "bug", "repair") ||
				isBugRealtedMessage(shortMessage, fullMessage, "bug", "patch") ||
				isBugRealtedMessage(shortMessage, fullMessage, "fault", "fix") ||
				isBugRealtedMessage(shortMessage, fullMessage, "fault", "repair") ||
				isBugRealtedMessage(shortMessage, fullMessage, "fault", "patch") ||
				isBugRealtedMessage(shortMessage, fullMessage, "error", "fix") ||
				isBugRealtedMessage(shortMessage, fullMessage, "error", "repair") ||
				isBugRealtedMessage(shortMessage, fullMessage, "error", "patch")) {
			if (shortMessage.contains("fix typo") || shortMessage.contains("fix build")
					|| shortMessage.contains("non-bug") || shortMessage.contains("non-error")
					|| shortMessage.contains("non-fix") || shortMessage.contains("default")) {
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	private boolean isBugRealtedMessage(String shortMessage, String fullMessage, String keyword1, String keyword2) {
		if ((shortMessage.contains(keyword1) || fullMessage.contains(keyword1)) && 
			(shortMessage.contains(keyword2) || fullMessage.contains(keyword2))) {
			return true;
		}
		return false;
	}
	
	public List<RevCommit> filterCommits(List<RevCommit> commits, String url, String bugId) {
		List<RevCommit> selectedCommits = new ArrayList<>();
		for (RevCommit commit : commits) {
			if (isBugReported(commit, bugId, url)) {
				selectedCommits.add(commit);
			}
		}
		
		return selectedCommits;
	}
	
	/**
	 *  Filter commits by identifying whether the committed short or full messages contain bug ID in bug reports.
	 */
	private boolean isBugReported(RevCommit commit, String bugId, String bugReportFile) {
		String shortMessage = commit.getShortMessage(); 
		String fullMessage = commit.getFullMessage();
		if (shortMessage.contains(bugId) || fullMessage.contains(bugId)) {
			String commitId = commit.getId().name().substring(0, 6);
			return connectedBugReport(shortMessage, fullMessage, bugId, bugReportFile, commitId);
		}
		return false;
	}

	private boolean connectedBugReport(String shortMessage, String fullMessage, String bugId, String bugReportFile, String commitId) {
		// Get the bug Id.
		int index = shortMessage.indexOf(bugId);
		String idNum = "0";
		if (index > -1) {
			idNum = identifyBugId(shortMessage, bugId);
			if (idNum == null) idNum = identifyBugId(fullMessage, bugId);
		} else idNum = identifyBugId(fullMessage, bugId);

		if (idNum == null) return false;

		// Get the info of this bug in bug reports.
		try {
			bugReportFile += bugId + idNum + ".txt";
			 String bugR = commitId + bugReportFile;
			 if (!BugReports.contains(bugR))
				 BugReports.add(bugR);
			File bugReportF = new File(bugReportFile);
			if (bugReportF.exists()) {
				Document doc = Jsoup.parse(bugReportF, "utf-8");
				Element issueDetails = doc.getElementById("issuedetails");
				Elements e = issueDetails.children();
				// System.out.println(e);// 0: Type, 1: Status, 2: Priority, 3: Resolution.
				String type = e.get(0).children().get(0).child(1).text();
				// String status = e.get(1).children().get(0).child(1).text();
				// String priority = e.get(2).children().get(0).child(1).text();
				// String resolution = e.get(3).children().get(0).child(1).text();
				// if (!types.contains(type)) types.add(type);
				// if (!statuses.contains(status)) statuses.add(status);
				// if (!priorities.contains(priority)) priorities.add(priority);
				// if (!resolutions.contains(resolution)) resolutions.add(resolution);
				if (type.equals("Bug")) {
					bugs++;
					if (!bugsL.contains(bugR)) bugsL.add(bugR);
					return true;
				} else if (type.equals("Improvement")) {
					improvements++;
					if (!improvementsL.contains(bugR)) improvementsL.add(bugR);
					return true;
				}
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String identifyBugId(String commitMessage, String bugId) {
		int index = commitMessage.indexOf(bugId);
		int startIndex = index + bugId.length();
		int i = 0;
		String idNum = "0";
		while (true) {
			i ++;
			idNum = commitMessage.substring(startIndex, startIndex + i);
			if (!NumberUtils.isDigits(idNum)) break;
			if (i >= commitMessage.length() - startIndex) {
				i ++;
				break;
			}
		}
		i --;
		if (i > 0) {
			idNum = idNum.substring(0, i);
			if (NumberUtils.isDigits(idNum)) return idNum;
		}
		return null;
	}

	public List<RevCommit> filterCommits(List<RevCommit> commits, String url, String bugId1, String bugId2) {
		List<RevCommit> selectedCommits = new ArrayList<>();
		for (RevCommit commit : commits) {
			if (isBugReported(commit, bugId1, bugId2, url)) {
				selectedCommits.add(commit);
			}
		}
		
		return selectedCommits;
	}

	private boolean isBugReported(RevCommit commit, String bugId1, String bugId2, String bugReportFile) {
		String shortMessage = commit.getShortMessage(); 
		String fullMessage = commit.getFullMessage();
		String commitId = commit.getId().name().substring(0, 6);
		if (shortMessage.contains(bugId1) || fullMessage.contains(bugId1)) {
			return connectedBugReport(shortMessage, fullMessage, bugId1, bugReportFile, commitId);
		} else if (shortMessage.contains(bugId2) || fullMessage.contains(bugId2)) {
			return connectedBugReport(shortMessage, fullMessage, bugId2, bugReportFile, commitId);
		}
		return false;
	}

	public List<RevCommit> filterCommitsByBug(List<RevCommit> commits, String url, String bugId1, String bugId2) {
		List<RevCommit> selectedCommits = new ArrayList<>();
		for (RevCommit commit : commits) {
			if (isBugReportedWithBug(commit, bugId1, url)) {
				selectedCommits.add(commit);
			} else if (isBugReportedWithBug(commit, bugId2, url)) {
				selectedCommits.add(commit);
			}
		}
		
		return selectedCommits;
	}

	public List<RevCommit> filterCommitsByBug(List<RevCommit> commits, String url, String bugId) {
		List<RevCommit> selectedCommits = new ArrayList<>();
		for (RevCommit commit : commits) {
			if (isBugReportedWithBug(commit, bugId, url)) {
				selectedCommits.add(commit);
			}
		}
		
		return selectedCommits;
	}
	
	/**
	 *  Filter commits by identifying whether the committed short or full messages contain bug ID in bug reports.
	 */
	private boolean isBugReportedWithBug(RevCommit commit, String bugId, String bugReportFile) {
		String shortMessage = commit.getShortMessage(); 
		String fullMessage = commit.getFullMessage();
		if (shortMessage.contains(bugId) || fullMessage.contains(bugId)) {
			// Get the bug Id.
			int index = shortMessage.indexOf(bugId);
			String idNum = "0";
			if (index > -1) {
				idNum = identifyBugId(shortMessage, bugId);
				if (idNum == null) {
					idNum = identifyBugId(fullMessage, bugId);
				}
			} else {
				idNum = identifyBugId(fullMessage, bugId);
			}
			
			if (idNum == null) return false;
			
			// Get the info of this bug in bug reports.
			try {
				bugReportFile += bugId + idNum + ".txt";
				String bugR = commit.getId().name().substring(0, 10) + bugReportFile;
				if (!BugReports.contains(bugR))  BugReports.add(bugR);
				File bugReportF = new File(bugReportFile);
				if (bugReportF.exists()) {
					Document doc = Jsoup.parse(bugReportF, "utf-8");
					Element issueDetails = doc.getElementById("issuedetails"); 
					Elements e = issueDetails.children();
//		        	System.out.println(e);// 0: Type, 1: Status, 2: Priority, 3: Resolution.
					String type = e.get(0).children().get(0).child(1).text();
//					String status = e.get(1).children().get(0).child(1).text();
//					String priority = e.get(2).children().get(0).child(1).text();
					String resolution = e.get(3).children().get(0).child(1).text();
//					if (!types.contains(type)) types.add(type);
//					if (!statuses.contains(status)) statuses.add(status);
//					if (!priorities.contains(priority)) priorities.add(priority);
//					if (!resolutions.contains(resolution)) resolutions.add(resolution);
					if (type.equals("Bug") && resolution.equals("Fixed")) {
						return true;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public int bugs = 0;
	public int improvements = 0;
	public List<String> bugsL = new ArrayList<>();
	public List<String> improvementsL = new ArrayList<>();
	public List<String> BugReports = new ArrayList<>();
	public List<String> types = new ArrayList<>();
	public List<String> statuses = new ArrayList<>();
	public List<String> priorities = new ArrayList<>();
	public List<String> resolutions = new ArrayList<>();

	/**
	 * Return the DiffEntry List of a RevCommit compared with its parent-RevCommits.
	 * 
	 * @param revCommit 
	 * 				A RevCommit.
	 * @return A ArrayList of DiffEntry.
	 * @throws GitAPIException 
	 * @throws IOException 
	 * @throws IncorrectObjectTypeException 
	 * @throws AmbiguousObjectException 
	 * @throws RevisionSyntaxException 
	 */
	private List<CommitDiffEntry> getDiffEntriesForEachCommit(RevCommit revCommit) throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException, GitAPIException {
		List<CommitDiffEntry> diffEntries = new ArrayList<CommitDiffEntry>();
		RevCommit[] parentRevCommits = revCommit.getParents();
		if (parentRevCommits != null && parentRevCommits.length != 0) {
			for (int i = 0; i < parentRevCommits.length; i++) {
				RevCommit parentCommit = parentRevCommits[i];
				AbstractTreeIterator oldTreeParser = prepareTreeParser(parentCommit);
		        AbstractTreeIterator newTreeParser = prepareTreeParser(revCommit);

		        List<DiffEntry> diffs = git.diff(). setOldTree(oldTreeParser).
		        					   setNewTree(newTreeParser). call();
	            if (!diffs.isEmpty()) {
	            	for (DiffEntry diffentry : diffs) {
						CommitDiffEntry gtDiffentry = new CommitDiffEntry(revCommit, parentCommit, diffentry);
						diffEntries.add(gtDiffentry);
					}
	            }
			}
		}
		
		return diffEntries;
	}
	
	private AbstractTreeIterator prepareTreeParser(RevCommit commit) throws IOException, MissingObjectException, IncorrectObjectTypeException {
		// from the commit we can build the tree which allows us to construct the TreeParser
		RevTree tree = commit.getTree();
		CanonicalTreeParser treeParser = new CanonicalTreeParser();

		ObjectReader objReader = repository.newObjectReader();
		treeParser.reset(objReader, tree.getId());// org.eclipse.jgit.errors.MissingObjectException TODO

		return treeParser;
	}
	
	public List<CommitDiffEntry> getCommitDiffEntries(List<RevCommit> commits) throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException, GitAPIException {
		
		List<CommitDiffEntry> diffentries = new ArrayList<CommitDiffEntry>();
		
		for (RevCommit commit : commits) {
//			if (commit.getParentCount() > 1) {
//				continue;
//			}
			List<CommitDiffEntry> d = getDiffEntriesForEachCommit(commit);
			if (!d.isEmpty()) {
				diffentries.addAll(d);
//				createFilesForGumTree(d, true);
			}
		}
		return diffentries;
	}

	/**
	 * Return the changed details of the DiffEntry in a Commit.
	 * @param entry
	 * 			A DiffEnty.
	 * @return changedDetails
	 * 			The changed details of the DiffEntry.
	 * @throws IOException 
	 */
	private BufferedReader getDiffEntryChangedDetails(DiffEntry entry) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedReader br = null;
		
		@SuppressWarnings("resource")
		DiffFormatter formatter = new DiffFormatter(baos);
		formatter.setRepository(repository);
		formatter.setContext(1); // 0: without context, 1: with context
		formatter.format(entry); // org.eclipse.jgit.errors.MissingObjectException TODO
		br = new BufferedReader(new StringReader(baos.toString()));
		
		return br;
	}
	
	/**
	 * Get deleted and add lines of source code.
	 * @param commitDiffentries
	 * @return
	 * @throws IOException
	 */
	public List<MyDiffEntry> getMyDiffEntries(List<CommitDiffEntry> commitDiffentries) throws IOException {
		List<MyDiffEntry> myDiffEntries = new ArrayList<MyDiffEntry>();
		
		for (CommitDiffEntry commitDiffentry : commitDiffentries) {
			DiffEntry diffentry = commitDiffentry.getDiffentry();
			
			// Select 'MODIFY' DiffEntry of java files.
			if (DiffEntryFilter.filterJavaFile(diffentry) && DiffEntryFilter.filterModifyType(diffentry)) {
				MyDiffEntry myDiffentry = null;
				List<ModifiedDetails> mdList = new ArrayList<ModifiedDetails>();
				String line = null;
				boolean flag = false;
				ModifiedDetails md = null;
				String commitId = "";
				String revFile = "";
				String prevFile = "";
				String delLine = "";
				String addLine = "";
				int lineNum = 0;
				
				BufferedReader changedDetails = getDiffEntryChangedDetails(diffentry);
				
				while ((line = changedDetails.readLine()) != null) {
					lineNum ++;
					if (lineNum == 1){
						if (line.toLowerCase().contains("test")) {
							// filter testing java file
							break;
						}
						commitId = commitDiffentry.getCommit().getId().name().substring(0, 6);
						String parentCommitId = commitDiffentry.getParentCommit().getId().name().substring(0, 6);
						String fileName = diffentry.getNewPath().replaceAll("/", "#");
						fileName = createFileName(fileName, commitId, parentCommitId);
						revFile = fileName;
						prevFile = "prev_" + fileName;
						continue;
					}
					
					if (LineDiffFilter.filterSignal(line)) {
						// changed line number "@@ -24,2 +24,2 @@"
						if (md != null && (!delLine.equals("") || !addLine.equals(""))) {
							md.setDelLines(delLine);
							md.setAddLines(addLine);
							mdList.add(md);
							delLine = "";
							addLine = "";
						}
						flag = true;
						md = new ModifiedDetails();
						md.setLineNumber(line.trim());
						continue;
					}

					if (flag) {
						if (isNonSourceCodeLine(line)) {
							continue;
						}
						
						if (line.startsWith("-")) { // deleted line
							delLine += line + "\n";
							continue;
						}

						if (line.startsWith("+")) { // added line
							addLine += line + "\n";
							continue;
						}
					}
				}//end of read lines.
				changedDetails.close();
				
				if (md != null && (!delLine.equals("") || !addLine.equals(""))) {
					md.setDelLines(delLine);
					md.setAddLines(addLine);
					mdList.add(md);
				}
				
				if (!revFile.equals("") && !mdList.isEmpty()) {
					myDiffentry = new MyDiffEntry();
					myDiffentry.setRevFile(revFile);
					myDiffentry.setPrevFile(prevFile);
					myDiffentry.setModifiedDetails(mdList);
					myDiffentry.setCommitId(commitId);
					myDiffentry.setPrevCommitId(commitDiffentry.getParentCommit().getParents()[0].getId().name().substring(0, 6));
					myDiffEntries.add(myDiffentry);
				}
			}//end of if
		}//end of for
		
		return myDiffEntries;
	}
	
	private boolean isNonSourceCodeLine(String line) {
		/*
		 *  TODO: how to filter out non-source code fragment?
		 *   added or deleted non-source code fragment?
		 *   
		 *   Method: can be parsed by AST or not?
		 */
		if (LineDiffFilter.filterImport(line)) {
			// Do not take the ImportDeclaration statement into account, for now.
			return true;
		}
		
		if (LineDiffFilter.filterComment(line)) {
			// Filter all changed comments of code fragment.
			return true;
		}
		
		if (LineDiffFilter.filterAnnotation(line)) {
			// Filter all annotations (@) of code fragment.
			return true;
		}
		return false;
	}

	/**
	 * Get previous source code fragment and modified source code fragment with context.
	 * @param commitDiffentries
	 * @return
	 * @throws IOException
	 */
	public List<MyDiffEntry> getMyDiffEntriesWithContext(List<CommitDiffEntry> commitDiffentries, boolean ignoreTest) throws IOException {
		List<MyDiffEntry> myDiffEntries = new ArrayList<MyDiffEntry>();
//		StringBuilder outputDiffEnties = new StringBuilder();
//		outputDiffEnties.append("=======\n");
//		int counter = 0;

		for (CommitDiffEntry commitDiffentry : commitDiffentries) {
			DiffEntry diffentry = commitDiffentry.getDiffentry();
			
			// Select 'MODIFY' DiffEntry of java files.
			if (DiffEntryFilter.filterJavaFile(diffentry) && DiffEntryFilter.filterModifyType(diffentry)) {
				MyDiffEntry myDiffentry = null;
				List<ModifiedDetails> mdList = new ArrayList<ModifiedDetails>();
				String line = null;
				boolean isSourceCodeRange = false;
				ModifiedDetails md = null;
				String commitId = "";
				String revFile = "";
				String prevFile = "";
				String fragment = "";
				int lineNum = 0;
				
				BufferedReader changedDetails = getDiffEntryChangedDetails(diffentry);
//				outputDiffEnties.append("Short Message: " + commitDiffentry.getCommit().getShortMessage() + "\n");
//				outputDiffEnties.append("Full Message: " + commitDiffentry.getCommit().getFullMessage() + "\n");
//				outputDiffEnties.append(commitDiffentry.getCommit().getId().name() + " ------ ");
//				outputDiffEnties.append(diffentry.getNewPath() + "\n");
//				counter ++;
				while ((line = changedDetails.readLine()) != null) {
//					outputDiffEnties.append(line + "\n");
					lineNum ++;
					if (lineNum == 1){
						if (line.toLowerCase().contains("test") && ignoreTest) {
							// filter testing java file
							break;
						}
						commitId = commitDiffentry.getCommit().getId().name().substring(0, 6);
						String parentCommitId = commitDiffentry.getParentCommit().getId().name().substring(0, 6);
						String fileName = diffentry.getNewPath().replaceAll("/", "#");
						fileName = createFileName(fileName, commitId, parentCommitId);
						revFile = fileName;
						prevFile = "prev_" + fileName;
//						String prevFileName = diffentry.getOldPath().replace("/", "#");
//						prevFile = "prev_" + commitDiffentry.getCommit().getParents()[0].getId().name().substring(0, 6) + prevFileName;
						continue;
					}
					
					if (LineDiffFilter.filterSignal(line)) {
						// changed line number "@@ -24,2 +24,2 @@"
						if (md != null && !fragment.equals("")) {
							md.setFragment(fragment);
							mdList.add(md);
							fragment = "";
						}
						isSourceCodeRange = true;
						md = new ModifiedDetails();
						md.setLineNumber(line.trim());
						continue;
					}
					
					if (isSourceCodeRange) {
						fragment += line + "\n";
					}
				}//end of read lines.
				
				if (md != null && !fragment.equals("")) {
					md.setFragment(fragment);
					mdList.add(md);
				}
				
				if (!revFile.equals("") && !mdList.isEmpty()) {
					myDiffentry = new MyDiffEntry();
					myDiffentry.setRevFile(revFile);
					myDiffentry.setPrevFile(prevFile);
					myDiffentry.setModifiedDetails(mdList);
					myDiffentry.setCommitId(commitDiffentry.getCommit().getId().name());
					myDiffentry.setPrevCommitId(commitDiffentry.getParentCommit().getId().name());
					myDiffEntries.add(myDiffentry);
				}
//				outputDiffEnties.append("\n\n");
//				if (counter % 1000 == 0) {
//					FileHelper.outputToFile("OUTPUT/" + FileHelper.getRepositoryName(repositoryPath) + ".txt", outputDiffEnties, true);
//					outputDiffEnties.setLength(0);;
//				}
			}//end of if
		}//end of for
//		FileHelper.outputToFile("OUTPUT/" + FileHelper.getRepositoryName(repositoryPath) + ".txt", outputDiffEnties, true);
		return myDiffEntries;
	}
	
	/**
	 * Return the changed file content of a commit.
	 * 
	 * @param commit
	 * @param path the new path in the DiffEntry of the commit.
	 * @return changed file content.
	 * @throws IOException 
	 * @throws MissingObjectException 
	 */
	public String getFileContent(RevCommit commit, String path) throws MissingObjectException, IOException {
		String content = null;
		
		@SuppressWarnings("resource")
		TreeWalk treeWalk = new TreeWalk(repository);
		RevTree tree = commit.getTree();
		
		treeWalk.addTree(tree);  // org.eclipse.jgit.errors.MissingObjectException TODO
		treeWalk.setRecursive(true);
		treeWalk.setFilter(PathFilter.create(path));
		
		if (!treeWalk.next()) {
			// logger.error("Can't find file: $path in " + commit.getName)
		} else {
			String resultingPath = treeWalk.getPathString();
			if (!path.equals(resultingPath)) {
				// logger.info("Resulting path is different from requested one: " +
				// resultingPath)
			} else {
				ObjectId objectID = treeWalk.getObjectId(0);
				ObjectLoader loader = repository.open(objectID);
				
				content = new String(loader.getBytes());
			}
		}
		
//		treeWalk.close();
		treeWalk = null;
	  	
	  	return content;
	}
	
	public void createFilesForGumTree(List<CommitDiffEntry> gtDiffentries, boolean ignoreTestCases) throws MissingObjectException, IOException	{
		String fileName = null;
		String revisedFileContent = null;
		String previousFileContent = null;
		File revisedFile = null;
		File previousFile = null;

		int a = 0;
		int b = 0;
		int c = 0;
		for (CommitDiffEntry gtDiffentry : gtDiffentries) {
			DiffEntry diffentry = gtDiffentry.getDiffentry();
			if (DiffEntryFilter.filterJavaFile(diffentry) && DiffEntryFilter.filterModifyType(diffentry)) {
				a ++;
				RevCommit commit = gtDiffentry.getCommit();
				RevCommit parentCommit = gtDiffentry.getParentCommit();
				if (commit.getParentCount() > 1) {
					continue;
				}
				String commitId = commit.getId().name().substring(0, 6);
				fileName = diffentry.getNewPath().replaceAll("/", "#");
				if (fileName.toLowerCase(Locale.ENGLISH).contains("test") && ignoreTestCases) {
					continue;
				}
				String parentCommitId = parentCommit.getId().name().substring(0, 6);
				
				fileName = createFileName(fileName, commitId, parentCommitId);
				
				revisedFileContent = getFileContent(commit, diffentry.getNewPath());
				revisedFile = new File(this.revisedFilePath + fileName);
				previousFileContent = getFileContent(parentCommit, diffentry.getOldPath());
				previousFile = new File(this.previousFilePath + "prev_" + fileName);
				if (revisedFileContent.equals(previousFileContent)) {
	        		c ++;
//	        		System.out.println(commitId + "===" + revisedFile.getName());
//	        		System.out.println(commit.getParents()[0].getId().name().substring(0, 6) + "===" + previousFile.getName());
//	        		System.out.println("=======");
	        	}
				if (revisedFileContent != null && previousFileContent != null && !"".equals(revisedFileContent) && !"".equals(previousFileContent)) {
					FileHelper.createFile(revisedFile, revisedFileContent);
					FileHelper.createFile(previousFile, previousFileContent);
					// output DiffEntries
					BufferedReader reader = getDiffEntryChangedDetails(diffentry);
					String line = null;
					StringBuilder diffentryStr = new StringBuilder();
					diffentryStr.append(parentCommit.getId().name().substring(0, 6)).append("\n");
					boolean isDiff = false;
					while ((line = reader.readLine()) != null) {
						if (!isDiff) {
							if (LineDiffFilter.filterSignal(line)) {
								isDiff = true;
								diffentryStr.append(line).append("\n");
							}
						} else {
							diffentryStr.append(line).append("\n");
						}
					}
					FileHelper.outputToFile(this.revisedFilePath.replace("revFiles", "DiffEntries") + fileName.replace(".java", ".txt"), diffentryStr, false);
					b ++;
				}
			}
		}
		log.debug("All modified java files: " + a + "=========All Non-test java files:" + b + "==========Unchanged non-test java files: " + c);
	}
	
	public void createFilesForGumTree(String outputPath, List<CommitDiffEntry> gtDiffentries) throws MissingObjectException, IOException	{
		String fileName = null;
		String revisedFileContent = null;
		String previousFileContent = null;
		File revisedFile = null;
		File previousFile = null;

		int a = 0;
		int b = 0;
		int c = 0;
		for (CommitDiffEntry gtDiffentry : gtDiffentries) {
			DiffEntry diffentry = gtDiffentry.getDiffentry();
			if (DiffEntryFilter.filterJavaFile(diffentry) && DiffEntryFilter.filterModifyType(diffentry)) {
				a ++;
				RevCommit commit = gtDiffentry.getCommit();
				RevCommit parentCommit = gtDiffentry.getParentCommit();
				if (commit.getParentCount() > 1) {
					continue;
				}
				String commitId = commit.getId().name().substring(0, 6);
				fileName = diffentry.getNewPath().replaceAll("/", "#");
				if (fileName.toLowerCase(Locale.ENGLISH).contains("test")) {
					continue;
				}
				String parentCommitId = parentCommit.getId().name().substring(0, 6);
				
				fileName = createFileName(fileName, commitId, parentCommitId);
				
				revisedFileContent = getFileContent(commit, diffentry.getNewPath());
				revisedFile = new File(outputPath + "revFiles/" + fileName);
				previousFileContent = getFileContent(parentCommit, diffentry.getOldPath());
				previousFile = new File(outputPath + "prevFiles/prev_" + fileName);
				if (revisedFileContent.equals(previousFileContent)) {
	        		c ++;
//	        		System.out.println(commitId + "===" + revisedFile.getName());
//	        		System.out.println(commit.getParents()[0].getId().name().substring(0, 6) + "===" + previousFile.getName());
//	        		System.out.println("=======");
	        	}
				if (revisedFileContent != null && previousFileContent != null && !"".equals(revisedFileContent) && !"".equals(previousFileContent)) {
					FileHelper.createFile(revisedFile, revisedFileContent);
					FileHelper.createFile(previousFile, previousFileContent);
					// output DiffEntries
					BufferedReader reader = getDiffEntryChangedDetails(diffentry);
					String line = null;
					StringBuilder diffentryStr = new StringBuilder();
					diffentryStr.append(parentCommit.getId().name().substring(0, 6)).append("\n");
					boolean isDiff = false;
					while ((line = reader.readLine()) != null) {
						if (!isDiff) {
							if (LineDiffFilter.filterSignal(line)) {
								isDiff = true;
								diffentryStr.append(line).append("\n");
							}
						} else {
							diffentryStr.append(line).append("\n");
						}
					}
					FileHelper.outputToFile(outputPath + "DiffEntries/" + fileName.replace(".java", ".txt"), diffentryStr, false);
					b ++;
				}
			}
		}
		log.debug("All modified java files: " + a + "=========All Non-test java files:" + b + "==========Unchanged non-test java files: " + c);
	}
	
	private String createFileName(String fileName, String commitId, String parentCommitId) {
		fileName = commitId + "_" + parentCommitId + "_" + fileName;
		if (fileName.length() > 200) {
			if (tooLongFileNames.containsKey(fileName)) {
				fileName = tooLongFileNames.get(fileName);
			} else {
				int size = tooLongFileNames.size();
				size ++;
				String newFileName = commitId + "_" + parentCommitId + "_" + String.format("%05d", size) + ".java";
				tooLongFileNames.put(fileName, newFileName);
				fileName = newFileName;
			}
		}
		return fileName;
	}

	private Map<String, String> tooLongFileNames = new HashMap<>();
	
	public void createFilesOfAllCommits(List<RevCommit> commits) throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException, GitAPIException {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (RevCommit commit : commits) {
			List<CommitDiffEntry> diffEntries = getDiffEntriesForEachCommit(commit);
			for (CommitDiffEntry cDiffentry : diffEntries) {
				DiffEntry diffentry = cDiffentry.getDiffentry();
				if (DiffEntryFilter.filterJavaFile(diffentry) && !DiffEntryFilter.filterDeleteType(diffentry)) {
					String commitId = commit.getId().name().substring(0, 6);
					String fileName = diffentry.getNewPath().replaceAll("/", "#");
					if (fileName.toLowerCase().contains("test")) {
						continue;
					}
					
					fileName = createFileName(fileName, commitId, "");
					String revisedFileContent = getFileContent(commit, diffentry.getNewPath());
					File revisedFile = new File(this.revisedFilePath + fileName);
					
					if (revisedFileContent != null) {
						if (revisedFile.exists()) {
							continue;
						}
						count ++;
						FileHelper.createFile(revisedFile, revisedFileContent);
						CommitFile cf = new CommitFile();
						cf.setFileName(fileName);
						cf.setCommitId(commitId);
						cf.setCommitTime(commit.getCommitTime());
						if (commitFiles.containsKey(fileName)) {
							commitFiles.get(fileName).add(cf);
						} else {
							List<CommitFile> cfs = new ArrayList<>();
							cfs.add(cf);
							commitFiles.put(fileName, cfs);
						}
						builder.append(fileName + "\n");
						builder.append(commitId + "\n");
						builder.append(commit.getCommitTime() + "\n");
					}
				}
			}
		}
		
		File commitFile = new File(new File(this.previousFilePath).getParent() + "/Non_Test_Java_Files.txt");
		FileHelper.createFile(commitFile, builder.toString());
		log.info("Number of Create non-test java files: " + count);
	}
	
	Map<String, List<CommitFile>> commitFiles = new HashMap<>();
	public Map<String, List<CommitFile>> getCommitFiles() {
		return commitFiles;
	}
	
	@SuppressWarnings("resource")
	public String getFileContentByCommitIdAndFileName(String commitIdStr, String fileName) {
		String fileContent = null;
		try {
			ObjectId commitId = repository.resolve(commitIdStr);
			RevWalk revWalk = new RevWalk(repository);
			RevCommit commit = revWalk.parseCommit(commitId);
			fileContent = getFileContent(commit, fileName);
		} catch (RevisionSyntaxException e) {
			e.printStackTrace();
		} catch (AmbiguousObjectException e) {
			e.printStackTrace();
		} catch (IncorrectObjectTypeException e) {
			e.printStackTrace();
		} catch (MissingObjectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	@SuppressWarnings("resource")
	public String getDiffentryByTwoCommitIds(String buggyCommitId, String fixedCommitId, String fileName) {
		RevWalk revWalk = new RevWalk(repository);
		try {
			ObjectId commitId1 = repository.resolve(buggyCommitId);
			RevCommit parentCommit = revWalk.parseCommit(commitId1);
			ObjectId commitId2 = repository.resolve(fixedCommitId);
			RevCommit revCommit = revWalk.parseCommit(commitId2);
			
			AbstractTreeIterator oldTreeParser = prepareTreeParser(parentCommit);
			AbstractTreeIterator newTreeParser = prepareTreeParser(revCommit);

			List<DiffEntry> diffs = git.diff(). setOldTree(oldTreeParser).
								   setNewTree(newTreeParser). call();
			if (!diffs.isEmpty()) {
				for (DiffEntry diff : diffs) {
					String filePath = diff.getNewPath();
					if (fileName.equals(filePath)) {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
			            try (DiffFormatter formatter = new DiffFormatter(baos)) {
			                formatter.setRepository(repository);
			                formatter.format(diff);
			            }
			            String diffContent = baos.toString();
			            baos.close();
						return diffContent;
					}
				}
			}
		} catch (RevisionSyntaxException e) {
			e.printStackTrace();
		} catch (AmbiguousObjectException e) {
			e.printStackTrace();
		} catch (IncorrectObjectTypeException e) {
			e.printStackTrace();
		} catch (MissingObjectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void outputCommitMessages(String outputFileName, List<RevCommit> commits) {
		StringBuilder builder = new StringBuilder();
		for (RevCommit commit : commits) {
			String shortMessage = commit.getShortMessage(); 
			String fullMessage = commit.getFullMessage();
			builder.append("======Commit: ").append(commit.getId().name().substring(0, 10)).append("======\n======Short Message======\n").append(shortMessage).append("\n");
			builder.append("======Full Message======\n").append(fullMessage).append("\n\n\n");
		}
		FileHelper.outputToFile(outputFileName, builder, false);
		builder.setLength(0);
	}
	
	public RevCommit getRevCommitById(String commitId) throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		ObjectId id = repository.resolve(commitId);
		@SuppressWarnings("resource")
		RevWalk revWalk = new RevWalk(repository);
		RevCommit commit = revWalk.parseCommit(id);
		return commit;
	}
}
