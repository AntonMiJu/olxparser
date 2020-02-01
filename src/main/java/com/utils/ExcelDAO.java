package com.utils;

import com.Account;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class ExcelDAO implements DAO {
    private static final String excelFilePath = "test.xlsx";

    @Override
    public Account save(Account account) {
        try (FileInputStream inputStream = new FileInputStream(excelFilePath);
             Workbook workbook = getWorkbook(inputStream, excelFilePath);
             FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath))) {

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowIndex = sheet.getLastRowNum();
            Row row = sheet.createRow(++lastRowIndex);

            row.createCell(0).setCellValue(account.getPhone());
            row.createCell(1).setCellValue(account.getName());
            row.createCell(2).setCellValue(account.getAddress());
            row.createCell(3).setCellValue(account.getDateRegistered());

            workbook.write(outputStream);
            return account;
        } catch (IOException e) {
            System.out.println("ExcelDAO method save() something gone wrong");
        }
        return null;
    }

    @Override
    public Account getByPhone(String phone) {
        try (FileInputStream inputStream = new FileInputStream(excelFilePath);
                Workbook workbook = getWorkbook(inputStream, excelFilePath)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(findRow(sheet, 0, phone));
            return rowToAccount(row);
        } catch (IOException e) {
            System.out.println("ExcelDAO method getByPhone() something gone wrong");
        }
        return null;
    }

    //TODO write logic for that method
    @Override
    public List<Account> getByAddress(String address) {
        return null;
    }

    private int findRow(Sheet sheet, int cellIndex, String value) {
        for (Row row : sheet) {
            Cell cell = row.getCell(cellIndex);
            if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(value))
                return row.getRowNum();
        }
        return 0;
    }

    private Workbook getWorkbook(FileInputStream fis, String excelFilePath) throws IOException {
        Workbook wb = null;
        if (excelFilePath.endsWith("xlsx")) {
            wb = new XSSFWorkbook(fis);
        } else if (excelFilePath.endsWith("xls")) {
            wb = new HSSFWorkbook(fis);
        } else {
            throw new IOException("File has wrong format.");
        }
        return wb;
    }

    private Account rowToAccount(Row row) {
        Account account = new Account();

        account.setPhone(row.getCell(0).getStringCellValue());
        account.setName(row.getCell(1).getStringCellValue());
        account.setAddress(row.getCell(2).getStringCellValue());
        account.setDateRegistered(row.getCell(3).getStringCellValue());

        return account;
    }
}
