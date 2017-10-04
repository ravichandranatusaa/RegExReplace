package interview.exercise.util;

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
	
	public boolean isDirectory() {
		return false;
	}
	
}
