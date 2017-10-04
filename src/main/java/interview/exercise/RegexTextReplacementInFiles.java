package interview.exercise;

import interview.exercise.util.FileUtil;
import interview.exercise.util.ReportUtil;

public class RegexTextReplacementInFiles {

	public static void process(String startingPath, String regexPattern,
			String replacement, String fileAcceptPattern) {

		try {
			ReportUtil.getInstance().printReport(
					FileUtil.getInstance().processFileSearch(startingPath, regexPattern, replacement, fileAcceptPattern)
					);
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
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
			process(startingDir, regexPattern, replacement, fileAcceptPattern);
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
