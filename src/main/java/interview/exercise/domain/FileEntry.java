package interview.exercise.domain;


/**
 * 
 * @author Ravichandran Gopinath
 *
 * FileEntry domain class captures the data for each file getting replaced, to report.
 *
 */
public class FileEntry {

	private String fileName;
	private String fullPath;
	private int replaceCount;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public int getReplaceCount() {
		return replaceCount;
	}
	public void setReplaceCount(int replaceCount) {
		this.replaceCount = replaceCount;
	}
}
