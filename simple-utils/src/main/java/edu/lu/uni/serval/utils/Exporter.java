package edu.lu.uni.serval.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Exporter {

	
	public static void exportOutliers(Map<String, Integer> map, File file, int sheetNo, String[] columns) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Workbook wb = null;
			// new excel file.
			WritableWorkbook book = null;
			WritableSheet sheet = null;
			if (sheetNo == 1) {
				book = Workbook.createWorkbook(file);
				sheet = book.createSheet("sheet ", sheetNo - 1);
			} else {
				wb = Workbook.getWorkbook(file);
				book = Workbook.createWorkbook(file, wb);
				sheet = book.createSheet("sheet ", sheetNo - 1);
			}
			
			// Setting cell width according to the length of content automatically.
			CellView cellView = new CellView();
			cellView.setAutosize(true);
			for (int i = 0; i < columns.length; i++) {
				sheet.setColumnView(i, cellView);
			}
			
			int rowIndex = 0;
			// Adding the names of sub column.
			for (int i = 0; i < columns.length; i++) {
				sheet.addCell(new Label(i, rowIndex, columns[i]));
			}
			rowIndex ++;

			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				sheet.addCell(new Label(0, rowIndex, entry.getKey()));
				sheet.addCell(new Label(1, rowIndex, entry.getValue() + ""));
				rowIndex++;
			}

			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
