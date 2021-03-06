package interview.exercise.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

	/**
	 * 
	 * @param fileName
	 * @param fileAcceptPattern
	 * @return boolean
	 * 
	 * Checks whether the given filename is valid with accepted pattern (if any)
	 */
	public boolean isValidFile(String fileName, String fileAcceptPattern) {
		if(fileName!=null) {
			if(fileAcceptPattern!=null) {
				fileAcceptPattern = fileAcceptPattern.replace("?", ".?").replace("*", ".*");
				Pattern pattern = Pattern.compile(fileAcceptPattern);
				return pattern.matcher(fileName).matches();
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param list
	 * @param file
	 * @param replaceCount
	 * @return
	 * @throws IOException
	 * 
	 * private method to get the list of files in collection
	 * 
	 */
	private FileEntry addFileEntry(List<FileEntry> list, File file, int replaceCount) throws IOException {
		FileEntry obj = null;

		if(file!=null && replaceCount>0) {
			obj = new FileEntry();
			obj.setFileName(file.getName());
			obj.setFullPath(file.getCanonicalPath());
			obj.setReplaceCount(replaceCount);
			list.add(obj);
		}

		return obj;
	}

	
	/**
	 * 
	 * @param startingPath
	 * @param allFiles
	 * @return
	 * 
	 * Provides list of all files given in directory/file path
	 */
	private List<File> getAllFiles(String startingPath, List<File> allFiles) {

		if(allFiles==null) allFiles = new ArrayList<File>();
		if(startingPath!=null) {

			File checkfile = new File(startingPath);

			if(checkfile.exists()) {
				if(checkfile.isFile()) {
					allFiles.add(checkfile);
				} else {
					File[] files = new File(startingPath).listFiles();
					for (File file : files) {
						if (file.isFile()) {
							allFiles.add(file);
						} else if (file.isDirectory()) {
							getAllFiles(file.getAbsolutePath(), allFiles);
						}
					}
				}
			}
		}
		return allFiles;
	}


	/**
	 * 
	 * @param startingPath
	 * @param regexPattern
	 * @param replacement
	 * @param fileAcceptPattern
	 * @return List<FileEntry>
	 * @throws Exception
	 * 
	 * It is service/Util class for file regex processing 
	 * 
	 */
	public List<FileEntry> processFileSearch(String startingPath, String regexPattern,
			String replacement, String fileAcceptPattern) throws Exception {
		List<FileEntry> retList = new ArrayList<FileEntry>();

		if(startingPath!=null && regexPattern!=null && replacement!=null) {

			try {
				Pattern p = Pattern.compile(regexPattern);
				Matcher m = null; 
				String line = null;
				BufferedReader br = null;
				BufferedWriter bw = null;
				
				int replaceCount = 0;

				List<File> allFiles = new ArrayList<File>();
				getAllFiles(startingPath, allFiles);

				for (File file : allFiles) {

						if(isValidFile(file.getName(), fileAcceptPattern)) {
						replaceCount = 0;
							br = new BufferedReader(new FileReader(file));
						bw = new BufferedWriter(new FileWriter(file.getCanonicalPath()+".processed"));
							while((line = br.readLine())!=null) {
								m = p.matcher(line);
								while (m.find()) {
								replaceCount++;
								}
							bw.write(m.replaceAll(replacement));
							bw.newLine();
							}
							
						addFileEntry(retList, file, replaceCount);
						br.close();
						bw.close();
						br = null;
						bw = null;
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
		return retList;
	}




}