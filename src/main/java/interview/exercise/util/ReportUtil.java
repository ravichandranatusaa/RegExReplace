package interview.exercise.util;

import java.util.List;

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
	
	
	/**
	 * 
	 * @param List<FileEntry>
	 * 
	 * Print method for Replaced files (reporting)
	 */
	public void printReport(List<FileEntry> list) {
		if(list!=null && !list.isEmpty()) {
			FileEntry obj = null;
			System.out.println("S.No"
					+ "\tFileName"
					+ "\tNumber of Replacement"
					+ "\tFull Path");
			System.out.println("===="
					+ "\t========"
					+ "\t====================="
					+ "\t=========");
			int count = 1;
			for(int i=0;i<list.size();i++) {
				obj = list.get(i);
				System.out.print(count);
				System.out.print("\t"+obj.getFileName());
				System.out.print("\t"+obj.getReplaceCount());
				System.out.print("\t"+obj.getFullPath());
				System.out.print("\n");
				count++;
			}
		}
	}
	
	
}
