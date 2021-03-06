package com.utils;

import com.Account;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelDAO implements DAO {
    private static final String EXCEL_FILE_PATH = "Phones.xls";

    @Override
    public synchronized Account save(Account account) {
        try (FileInputStream inputStream = new FileInputStream(new File(EXCEL_FILE_PATH));
             Workbook workbook = getWorkbook(inputStream, EXCEL_FILE_PATH);
             FileOutputStream outputStream = new FileOutputStream(new File(EXCEL_FILE_PATH))) {

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
            System.err.println("ExcelDAO method save() something gone wrong");
        }
        return null;
    }

    @Override
    public synchronized Account getByPhone(String phone) {
        try (FileInputStream inputStream = new FileInputStream(EXCEL_FILE_PATH);
             Workbook workbook = getWorkbook(inputStream, EXCEL_FILE_PATH)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(findRowByValue(sheet, 0, phone));
            return rowToAccount(row);
        } catch (IOException e) {
            System.err.println("ExcelDAO method getByPhone() something gone wrong");
        }
        return null;
    }

    @Override
    public synchronized List<Account> getByAddress(String address) {
        List<Account> accounts = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(EXCEL_FILE_PATH);
             Workbook workbook = getWorkbook(inputStream, EXCEL_FILE_PATH)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int index : findFewRowsByValue(sheet, 2, address))
                accounts.add(rowToAccount(sheet.getRow(index)));
        } catch (IOException e) {
            System.out.println();
        }
        return accounts;
    }

    private synchronized int findRowByValue(Sheet sheet, int cellIndex, String value) {
        for (Row row : sheet) {
            Cell cell = row.getCell(cellIndex);
            if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(value))
                return row.getRowNum();
        }
        return 0;
    }

    private synchronized List<Integer> findFewRowsByValue(Sheet sheet, int cellIndex, String value){
        List<Integer> indexesOfRows = new ArrayList<>();
        for (Row row : sheet){
            Cell cell = row.getCell(cellIndex);
            if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(value))
                indexesOfRows.add(row.getRowNum());
        }
        return indexesOfRows;
    }

    private synchronized Workbook getWorkbook(FileInputStream fis, String excelFilePath) throws IOException {
        Workbook wb;
        if (new File(excelFilePath).exists())
            wb = WorkbookFactory.create(new File(excelFilePath));
        else {
            if (excelFilePath.endsWith("xlsx")) {
                wb = new XSSFWorkbook(fis);
            } else if (excelFilePath.endsWith("xls")) {
                wb = new HSSFWorkbook(fis);
            } else {
                throw new IOException("File has wrong format.");
            }
        }
        return wb;
    }

    private synchronized Account rowToAccount(Row row) {
        Account account = new Account();

        account.setPhone(row.getCell(0).getStringCellValue());
        account.setName(row.getCell(1).getStringCellValue());
        account.setAddress(row.getCell(2).getStringCellValue());
        account.setDateRegistered(row.getCell(3).getStringCellValue());

        return account;
    }
}
