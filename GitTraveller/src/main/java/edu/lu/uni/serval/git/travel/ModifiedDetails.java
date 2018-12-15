package edu.lu.uni.serval.git.travel;

public class ModifiedDetails {
	
	private String lineNumber;
	private String delLines;  // deleted lines without context or deleted fragment with context
	private String addLines;  // added lines without context or added fragment with context
	private String fragment;
	
	public ModifiedDetails() {
	}
	
	public ModifiedDetails(String lineNumber, String delLines, String addLines) {
		this.lineNumber = lineNumber;
		this.delLines = delLines;
		this.addLines = addLines;
	}

	public String getFragment() {
		return fragment;
	}

	public void setFragment(String fragment) {
		this.fragment = fragment;
	}

	public String getLineNumber() {
		return lineNumber;
	}
	
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public String getDelLines() {
		return delLines;
	}
	
	public void setDelLines(String delLines) {
		this.delLines = delLines;
	}
	
	public String getAddLines() {
		return addLines;
	}
	
	public void setAddLines(String addLines) {
		this.addLines = addLines;
	}

	@Override
	public String toString() {
		return lineNumber + "\n" + delLines + addLines + "\n";
	}
	
	public int getDelLineStartsNum() {
		if (lineNumber != null) {
			int startIndex = lineNumber.indexOf("-");
			if (startIndex > 0) {
				int endIndex1 = lineNumber.indexOf(",");
				int endIndex2 = lineNumber.substring(startIndex).indexOf(" ") + startIndex;
				if (endIndex2 < endIndex1 || endIndex1 == -1) {
					return Integer.parseInt(lineNumber.substring(startIndex + 1, endIndex2));
				} else {
					return Integer.parseInt(lineNumber.substring(startIndex + 1, endIndex1));
				}
			} 
		}
		
		return -1;
	}
	
	public int getDelLineCounts() {
		if (lineNumber != null) {
			int startIndex = lineNumber.indexOf("-");
			if (startIndex > 0) {
				int endIndex1 = lineNumber.indexOf(",");
				int endIndex2 = lineNumber.substring(startIndex).indexOf(" ") + startIndex;
				if (endIndex2 < endIndex1 || endIndex1 == -1) {
					return 1;
				} else {
					return Integer.parseInt(lineNumber.substring(endIndex1 + 1, endIndex2));
				}
			} 
		}
		return 0;
	}
	
	public int getAddLineStartsNum() {
		if (lineNumber != null) {
			int startIndex = lineNumber.indexOf("+");
			if (startIndex > 0) {
				int endIndex1 = lineNumber.substring(startIndex).indexOf(",");
				int endIndex2 = lineNumber.substring(startIndex).indexOf(" ") + startIndex;
				if (endIndex1 == -1) {
					return Integer.parseInt(lineNumber.substring(startIndex + 1, endIndex2));
				} else {
					return Integer.parseInt(lineNumber.substring(startIndex + 1, endIndex1 + startIndex));
				}
			} 
		}
		
		return -1;
	}
	
	public int getAddLineCounts() {
		if (lineNumber != null) {
			int startIndex = lineNumber.indexOf("+");
			if (startIndex > 0) {
				int endIndex1 = lineNumber.substring(startIndex).indexOf(",");
				int endIndex2 = lineNumber.substring(startIndex).indexOf(" ") + startIndex;
				if (endIndex1 == -1) {
					return 1;
				} else {
					return Integer.parseInt(lineNumber.substring(startIndex + endIndex1 + 1, endIndex2));
				}
			} 
		}
		return 0;
	}
	
	public int getLineStartsMinNumber() {
		int delStarts = getDelLineStartsNum();
		int addStarts = getAddLineStartsNum();
		if (delStarts > addStarts)
			return addStarts;
		return delStarts;
	}
	
	public int getLineStartsMaxNumber() {
		int delStarts = getDelLineStartsNum();
		int addStarts = getAddLineStartsNum();
		if (delStarts >= addStarts)
			return delStarts;
		return addStarts;
	}
	
	public static void main(String[] args) {
		ModifiedDetails md = new ModifiedDetails();
		md.setLineNumber("@@ -895 +896,6 @@");
		System.out.println(md.getDelLineStartsNum());
		System.out.println(md.getDelLineCounts());
		System.out.println(md.getAddLineStartsNum());
		System.out.println(md.getAddLineCounts());
	}
}
