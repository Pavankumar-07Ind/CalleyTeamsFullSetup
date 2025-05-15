package utilities;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;

public class ExcelUtil {
    public static String getCellData(String path, String sheetName, int row, int col) throws Exception {
        FileInputStream fis = new FileInputStream(new File(path));
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet(sheetName);
        return sheet.getRow(row).getCell(col).getStringCellValue();
    }
}
