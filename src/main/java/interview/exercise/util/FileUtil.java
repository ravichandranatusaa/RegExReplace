package interview.exercise.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import interview.exercise.domain.FileEntry;

/**
 * 
 * @author Ravichandran Gopinath
 *
 * Utility/Service class for File operations
 *
 */

public class FileUtil {

	private static FileUtil obj = null;

	private FileUtil() {
		if (obj != null) {
			throw new IllegalStateException("File Util object is already instantiated");
		}
	}

	public static FileUtil getInstance() {
		if(obj==null) {
			obj = new FileUtil();
		}
		return obj;
	}

	private boolean isValidFile(String fileName, String fileAcceptPattern) {
		if(fileName!=null) {
			if(fileAcceptPattern!=null) {
				fileAcceptPattern = fileAcceptPattern.replaceAll("\\*", "\\\\\\\\*");
				Pattern pattern = Pattern.compile(fileAcceptPattern);
				return pattern.matcher(fileName).matches();
			}
			return true;
		}
		return false;
	}

	public Map<String, FileEntry> processFileSearch(String startingPath, String regexPattern,
			String replacement, String fileAcceptPattern) throws Exception {
		Map<String, FileEntry> retEntries = new HashMap<String, FileEntry>();

		if(startingPath!=null && regexPattern!=null && replacement!=null) {
			File[] files = new File(startingPath).listFiles();

			try {
				Pattern p = Pattern.compile(regexPattern);
				Matcher m = null; //p.matcher("one cat two cats in the yard");
				StringBuffer sb = new StringBuffer();
				String line = null;
				BufferedReader br = null;
				
				for (File file : files) {
					if (file.isDirectory()) {
						processFileSearch(file.getCanonicalPath(), regexPattern, replacement, fileAcceptPattern);
					}
					else {

						if(isValidFile(file.getName(), fileAcceptPattern)) {
							br = new BufferedReader(new FileReader(file));
							while((line = br.readLine())!=null) {
								m = p.matcher(line);
								while (m.find()) {
									m.appendReplacement(sb, replacement);
								}
								m.appendTail(sb);
								System.out.println(sb.toString());
							}
							
						}
					}
				}


			} catch(PatternSyntaxException patternException) {
				System.out.println("Exception in Pattern given");
				throw patternException;
			} catch(FileNotFoundException fileNotFoundException) {
				System.out.println("Exception: Given file is not found");
				throw fileNotFoundException;
			} catch (IOException ioException) {
				System.out.println("Exception: IO Exception");
				throw ioException;
			}

		}

		return retEntries;

	}
}