package tools;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParser {

    public static String _filePath = "src/test/resources/testdata/%s".concat(".xlsx");

    public Object[] parseExcel(FileInputStream fileData) throws IOException {
        Workbook _workbookData           = new XSSFWorkbook(fileData);
        Sheet    _sheetColumnsDefinition = _workbookData.getSheetAt(0);
        Sheet    _sheetData              = _workbookData.getSheetAt(1);

        // new object map with columns size
        List<Map<String, Object>> _lisDataMapping = new ArrayList<>();

        // get data definitions
        List<String> _lisDataDefinitions = new ArrayList<>();
        for (Row _row : _sheetColumnsDefinition) {
            Cell         _cellEndOfTestData = _row.getCell(0);
            if (_cellEndOfTestData.getCellType().equals(CellType.STRING) && _cellEndOfTestData.getStringCellValue().equals(FileStatus.DATA_END)) {
                break;
            }
                String _strDefinition = _row.getCell(0).getStringCellValue();
                _lisDataDefinitions.add(_strDefinition);
        }


        // get test data
        List<List<Object>> _lisAllRowData = new ArrayList<>();
        for (Row _row : _sheetData) {
            List<Object> _lisARowData       = new ArrayList<>();
            Cell         _cellEndOfTestData = _row.getCell(0);
            if (_cellEndOfTestData.getCellType().equals(CellType.STRING) && _cellEndOfTestData.getStringCellValue().equals(FileStatus.DATA_END)) {
                break;
            }
            for (Cell _dataCell : _row) {
                switch (_dataCell.getCellType()) {
                    case STRING:
                        _lisARowData.add(_dataCell.getStringCellValue());
                        break;
                    case NUMERIC:
                        _lisARowData.add(_dataCell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        _lisARowData.add(_dataCell.getBooleanCellValue());
                    default:
                        _lisARowData.add(null);
                        break;
                }
            }

            _lisAllRowData.add(_lisARowData);

        }

        // put all data to map
        for (int i = 0; i < _lisAllRowData.size(); i++) {
            HashMap<String, Object> _dataMapping = new HashMap<>();
            List<Object>            _lisARowData = _lisAllRowData.get(i);
            for (int j = 0; j < _lisDataDefinitions.size(); j++) {
                _dataMapping.put(_lisDataDefinitions.get(j), _lisARowData.get(j));
            }
            _lisDataMapping.add(_dataMapping);
        }


        return _lisDataMapping.toArray();
    }

    final class FileStatus {
        static final String DATA_END = "End of test data";
    }
}
