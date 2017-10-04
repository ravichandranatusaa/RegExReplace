package interview.exercise;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import interview.exercise.domain.FileEntry;

public class RegexTextReplacementInFilesTest {

    @Test
    public void testSanity() {
        System.out.println("JUnit test works");
    }
    
    @Test
    public void testRegExTextReplacementChecks() {
    	String invalidFilePath = "z:"+File.separator+"wrongfilename.txt";
    	
    	RegexTextReplacementInFiles obj = new RegexTextReplacementInFiles();
    	List<FileEntry> testList = new ArrayList<FileEntry>();
    	assertEquals(testList, obj.process(invalidFilePath, "potato", "potato2", null));
    	
    	
    	String validPath = "C:\\sample_dir\\folder1\\folder1-1\\folder1-1-sample2.txt";
    	testList = new ArrayList<FileEntry>();
    	assertEquals(2, obj.process(validPath, "potato", "potato2", null).get(0).getReplaceCount());
    }
    
}
