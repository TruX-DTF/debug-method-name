package edu.lu.uni.serval.method.parser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.lu.uni.serval.utils.FileHelper;
import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Distribution {

	public void readDistribution(File file, int sheetNo) throws IOException {
		Map<Integer, Integer> distribution = new HashMap<>();
		
		String sizes = FileHelper.readFile(file);
		BufferedReader reader = new BufferedReader(new StringReader(sizes));
		String sizeStr = reader.readLine();
		
		int totality = 0;
		while ((sizeStr = reader.readLine()) != null) {
			int size = Integer.parseInt(sizeStr);
			if (distribution.containsKey(size)) {
				distribution.put(size, distribution.get(size) + 1);
			} else {
				distribution.put(size, 1);
			}
			
			totality ++;
		}
		
		reader.close();
		
		outputToExcel(file, distribution, totality, sheetNo);
		
	}

	private void outputToExcel(File file, Map<Integer, Integer> distribution, int totality, int sheetNo) {
		String fileName = file.getParent() + "/sizes distribution.xls";
		File outputFile = new File(fileName);
		Map<Integer, Integer> treeMap = new TreeMap<Integer, Integer>(distribution);
		try {
			Workbook wb = null;
			// new excel file.
			WritableWorkbook book = null;
			WritableSheet sheet = null;
			if (sheetNo == 1) {
				book = Workbook.createWorkbook(outputFile);
				sheet = book.createSheet(FileHelper.getFileNameWithoutExtension(file), sheetNo - 1);
			} else {
				wb = Workbook.getWorkbook(outputFile);
				book = Workbook.createWorkbook(outputFile, wb);
				sheet = book.createSheet(FileHelper.getFileNameWithoutExtension(file), sheetNo - 1);
			}


			CellView cellView = new CellView();
			cellView.setAutosize(true);
			for (int i = 0; i <= 1; i++) {
				sheet.setColumnView(i, cellView);
			}

			sheet.addCell(new Label(0, 0, "SIZE"));
			sheet.addCell(new Label(1, 0, "QUANTITY"));

			int index = 1;
			for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
				sheet.addCell(new Label(0, index, entry.getKey().toString()));
				sheet.addCell(new Label(1, index, entry.getValue().toString()));
				index ++;
			}
			
			sheet.addCell(new Label(0, index, "TOTALITY"));
			sheet.addCell(new Label(1, index, totality + ""));

			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
