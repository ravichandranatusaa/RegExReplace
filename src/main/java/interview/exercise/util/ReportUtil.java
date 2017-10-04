package interview.exercise.util;

import java.util.Iterator;
import java.util.Map;

import interview.exercise.domain.FileEntry;

public class ReportUtil {

private static ReportUtil obj = null;
	
	private ReportUtil() {
		if (obj != null) {
            throw new IllegalStateException("Report Util object is already instantiated");
        }
	}
	
	public static ReportUtil getInstance() {
		if(obj==null) {
			obj = new ReportUtil();
		}
		return obj;
	}
	
	public void printReport(Map<String, FileEntry> map) {
		if(map!=null && !map.isEmpty()) {
			FileEntry obj = null;
			Iterator<String> keyIter = map.keySet().iterator();
			System.out.println("S.No"
					+ "\tFileName"
					+ "\tNumber of Replacement"
					+ "\tStatus"
					+ "\tFull Path");
			System.out.println("===="
					+ "\t========"
					+ "\t====================="
					+ "\t======"
					+ "\t=========");
			int count = 1;
			while(keyIter.hasNext()) {
				obj = map.get(keyIter.next());
				System.out.print(count);
				System.out.print("\t"+obj.getFileName());
				System.out.print("\t"+obj.getReplaceCount());
				System.out.print("\t"+obj.getStatus());
				System.out.print("\t"+obj.getFullPath());
				count++;
			}
		}
	}
	
	
}
