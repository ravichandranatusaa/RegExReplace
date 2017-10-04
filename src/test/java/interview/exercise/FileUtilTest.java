package interview.exercise;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import interview.exercise.domain.FileEntry;
import interview.exercise.util.FileUtil;

public class FileUtilTest {

    @Test
    public void testSanity() {
        System.out.println("JUnit test works");
    }
    
    @Test
    public void testFileUtilChecks() throws Exception {
    	FileUtil obj = FileUtil.getInstance();
    	
    	String validPath = "C:\\sample_dir\\folder1\\folder1-1\\folder1-1-sample2.txt";
    	assertEquals(Boolean.TRUE, obj.isValidFile(validPath, "*.txt"));
    	assertEquals(Boolean.FALSE, obj.isValidFile(validPath, "*.doc"));
    	assertEquals(Boolean.FALSE, obj.isValidFile(validPath, "?.doc"));
    	
    	String invalidFilePath = "z:"+File.separator+"wrongfilename.txt";
    	List<FileEntry> testList = new ArrayList<FileEntry>();
    	assertEquals(testList, obj.processFileSearch(invalidFilePath, "potato", "potato2", null));
    	
    	testList = new ArrayList<FileEntry>();
    	assertEquals(2, obj.processFileSearch(validPath, "potato", "potato2", null).get(0).getReplaceCount());
    	
    }
    
}
