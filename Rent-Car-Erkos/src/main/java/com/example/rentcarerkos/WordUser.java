package com.example.rentcarerkos;

import javafx.collections.ObservableList;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordUser {
    public void userWordDocument(ObservableList<User> userData, File file) {
        XWPFDocument document = new XWPFDocument();

        // Create a table with userData.size() + 1 rows and 8 columns
        XWPFTable table = document.createTable(userData.size() + 1, 8);

        // Define headers
        String[] headers = {"User ID", "Ime", "Prezime", "Kontakt", "JMPG", "Broj Vozacke", "Drzavljastvo", "Email"};

        // Create the header row
        XWPFTableRow headerRow = table.getRow(0);
        for (int i = 0; i < headers.length; i++) {
            XWPFTableCell cell = headerRow.getCell(i);
            if (cell == null) cell = headerRow.createCell();
            cell.setText(headers[i]);
        }

        // Fill in the data rows
        for (int i = 0; i < userData.size(); i++) {
            User data = userData.get(i);
            XWPFTableRow row = table.getRow(i + 1);

            row.getCell(0).setText(data.getUserId().toString()); // Converted toString() since it's an Integer
            row.getCell(1).setText(data.getIme());
            row.getCell(2).setText(data.getPrezime());
            row.getCell(3).setText(data.getKontakt());
            row.getCell(4).setText(data.getJmpg());
            row.getCell(5).setText(data.getBrojvozacke());
            row.getCell(6).setText(data.getDrzava());
            row.getCell(7).setText(data.getEmail());
        }

        // Write the document to the specified file
        try (FileOutputStream out = new FileOutputStream(file)) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
