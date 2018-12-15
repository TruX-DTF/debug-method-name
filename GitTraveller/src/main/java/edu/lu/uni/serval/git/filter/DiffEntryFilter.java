package edu.lu.uni.serval.git.filter;

import org.eclipse.jgit.diff.DiffEntry;

public class DiffEntryFilter {
	
	public static boolean filterJavaFile(DiffEntry diffentry) {
		boolean isJava = false;
		
		if (diffentry.getNewPath().toString().endsWith(".java")) {
			isJava = true;
		}
		
		return isJava;
	}
	
	public static boolean filterModifyType(DiffEntry diffentry) {
		boolean isModify = false;
		
		if (diffentry.getChangeType().equals(DiffEntry.ChangeType.MODIFY)) {
			isModify = true;
		}
		
		return isModify;
	}
	
	public static boolean filterAddType(DiffEntry diffentry) {
		boolean isAdd = false;
		
		if (diffentry.getChangeType().equals(DiffEntry.ChangeType.ADD)) {
			isAdd = true;
		}
		
		return isAdd;
	}
	
	public static boolean filterDeleteType(DiffEntry diffentry) {
		boolean isDelete = false;
		
		if (diffentry.getChangeType().equals(DiffEntry.ChangeType.DELETE)) {
			isDelete = true;
		}
		
		return isDelete;
	}
	
	public static boolean filterRenameType(DiffEntry diffentry) {
		boolean isRename = false;
		
		if (diffentry.getChangeType().equals(DiffEntry.ChangeType.RENAME)) {
			isRename = true;
		}
		
		return isRename;
	}
	
	public static boolean filterCopyType(DiffEntry diffentry) {
		boolean isCopy = false;
		
		if (diffentry.getChangeType().equals(DiffEntry.ChangeType.COPY)) {
			isCopy = true;
		}
		
		return isCopy;
	}
}
