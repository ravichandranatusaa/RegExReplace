package interview.exercise;

import java.util.List;
import interview.exercise.domain.FileEntry;
import interview.exercise.util.FileUtil;
import interview.exercise.util.ReportUtil;

public class RegexTextReplacementInFiles {

	
	/**
	 * 
	 * @param startingPath
	 * @param regexPattern
	 * @param replacement
	 * @param fileAcceptPattern
	 * @return List<FileEntry> collection of File Entries which got replaced
	 * 
	 * 
	 * Starting point for regex replacement of files
	 * 
	 */
	public List<FileEntry> process(String startingPath, String regexPattern,
			String replacement, String fileAcceptPattern) {

		try {
			return FileUtil.getInstance().processFileSearch(startingPath, regexPattern, replacement, fileAcceptPattern);
		}catch(Exception e) {
			//Handle Exception here
			System.out.println(e.getLocalizedMessage());
			return null;
		}

	}

	public static void main(String[] args) {
		String startingDir = null, regexPattern = null, replacement = null, fileAcceptPattern = null;
		if (args.length >= 3) {
			startingDir = args[0];
			regexPattern = args[1];
			replacement = args[2];
		}
		if (args.length >= 4) {
			fileAcceptPattern = args[3];
		}
		if (startingDir != null && regexPattern!=null && replacement!=null) {
			ReportUtil.getInstance().printReport(new RegexTextReplacementInFiles().process(startingDir, regexPattern, replacement, fileAcceptPattern));
		} else {
			System.out.println("Expected at least 3 parameters but the number of parameters entered : " + args.length);
			System.out.println("This program is designed to consume 4 parameters at Runtime (3 are mandatory)... They are,");
			System.out.println("\n1) Starting directory/file\n"
					+ "2) String or pattern to be replaced\n"
					+ "3) String to be replaced with\n"
					+ "4) File naming pattern (Optional)");
			System.out.println("\nExample : regexTextReplacementInFiles sample_dir '\\w*(text to replace)\\w+' 'Text to Replace with' *.txt");
		}
	}

}
