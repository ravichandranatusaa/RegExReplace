package interview.exercise.util;

import java.io.File;
import java.util.regex.PatternSyntaxException;

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
            throw new IllegalStateException("Already instantiated");
        }
	}
	
	public static FileUtil getInstance() {
		if(obj==null) {
			obj = new FileUtil();
		}
		return obj;
	}
	
	private boolean isValidFile(File file) {
		return false;
	}
	
	public Map<String, FileEntry> processFileSearch(String startingPath) {
		try {
			
		} catch(PatternSyntaxException exception) {
			
		}
	}
	
}
