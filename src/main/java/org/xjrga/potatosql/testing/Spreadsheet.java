package org.xjrga.potatosql.testing;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Spreadsheet
{

    public Spreadsheet(){

    }

    public static void main(String[] args)
    {

    }

    private void eventActionPerformed_mnuiExportFoodList(ActionEvent e) {

        StringBuilder sb = new StringBuilder();
        String pattern = "yyyy-MMMMM-dd'_at_'HH-mma";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        String format = dateFormat.format(date);
        sb.append("food_list_");
        sb.append(format);
        sb.append(".xls");

        String filename = sb.toString();

        try {

            FileOutputStream out = new FileOutputStream(filename);
            Workbook wb = new HSSFWorkbook();
            Row row = null;
            Cell cell = null;

            String sheetname = "food list";
            Sheet s = wb.createSheet();
            wb.setSheetName(0, sheetname);

            int rownum = 0;
            row = s.createRow(rownum++);
            //Create cells
            Cell cell0 = row.createCell(0);
            Cell cell1 = row.createCell(1);
            Cell cell2 = row.createCell(2);
            Cell cell3 = row.createCell(3);
            Cell cell4 = row.createCell(4);
            Cell cell5 = row.createCell(5);
            Cell cell6 = row.createCell(6);
            Cell cell7 = row.createCell(7);
            Cell cell8 = row.createCell(8);
            Cell cell9 = row.createCell(9);
            Cell cell10 = row.createCell(10);
            Cell cell11 = row.createCell(11);
            Cell cell12 = row.createCell(12);
            Cell cell13 = row.createCell(13);
            Cell cell14 = row.createCell(14);
            Cell cell15 = row.createCell(15);
            Cell cell16 = row.createCell(16);
            //Set cell value
            cell0.setCellValue("Category");
            cell1.setCellValue("Food");
            cell2.setCellValue("ServingSize");
            cell3.setCellValue("Calories");
            cell4.setCellValue("Fat");
            cell5.setCellValue("Sodium");
            cell6.setCellValue("Carbs");
            cell7.setCellValue("Fiber");
            cell8.setCellValue("Protein");
            cell9.setCellValue("Complete");
            cell10.setCellValue("Incomplete");
            cell11.setCellValue("Satfat");
            cell12.setCellValue("Monoufat");
            cell13.setCellValue("Polyufat");
            cell14.setCellValue("Cholesterol");
            cell15.setCellValue("Cost");
            cell16.setCellValue("Note");

            //LinkedList list = (LinkedList) dbLink.food_db_select_details();
            LinkedList list = null;

            Iterator it = list.iterator();

            while (it.hasNext()) {

                HashMap rowm = (HashMap) it.next();

                int foodid = (int) rowm.get("FOOD_ID");
                String category = (String) rowm.get("FOODCATEGORY");
                String foodnom = (String) rowm.get("FOOD");
                String note = (String) rowm.get("NOTE");
                double servingsize = (double) rowm.get("SERVINGSIZE");
                double calories = (double) rowm.get("CALORIES");
                double fat = (double) rowm.get("FAT");
                double sodium = (double) rowm.get("SODIUM");
                double carbs = (double) rowm.get("CARBOHYDRATES");
                double fiber = (double) rowm.get("FIBER");
                double protein = (double) rowm.get("PROTEIN");
                double satfat = (double) rowm.get("SATURATEDFAT");
                double monoufat = (double) rowm.get("MONOUNSATURATEDFAT");
                double polyufat = (double) rowm.get("POLYUNSATURATEDFAT");
                double cholesterol = (double) rowm.get("CHOLESTEROL");
                double complete = (double) rowm.get("COMPLETEPROTEIN");
                double incomplete = (double) rowm.get("INCOMPLETEPROTEIN");
                double cost = (double) rowm.get("COSTO");

                //Create new row
                row = s.createRow(rownum++);
                //Create new cells
                cell0 = row.createCell(0);
                cell1 = row.createCell(1);
                cell2 = row.createCell(2);
                cell3 = row.createCell(3);
                cell4 = row.createCell(4);
                cell5 = row.createCell(5);
                cell6 = row.createCell(6);
                cell7 = row.createCell(7);
                cell8 = row.createCell(8);
                cell9 = row.createCell(9);
                cell10 = row.createCell(10);
                cell11 = row.createCell(11);
                cell12 = row.createCell(12);
                cell13 = row.createCell(13);
                cell14 = row.createCell(14);
                cell15 = row.createCell(15);
                cell16 = row.createCell(16);
                //Set cell value
                cell0.setCellValue(category);
                cell1.setCellValue(foodnom);
                cell2.setCellValue(servingsize);
                cell3.setCellValue(calories);
                cell4.setCellValue(fat);
                cell5.setCellValue(sodium);
                cell6.setCellValue(carbs);
                cell7.setCellValue(fiber);
                cell8.setCellValue(protein);
                cell9.setCellValue(complete);
                cell10.setCellValue(incomplete);
                cell11.setCellValue(satfat);
                cell12.setCellValue(monoufat);
                cell13.setCellValue(polyufat);
                cell14.setCellValue(cholesterol);
                cell15.setCellValue(cost);
                cell16.setCellValue(note);

            }

            wb.write(out);
            out.close();

            JOptionPane.showMessageDialog(null, "Spreadsheet is ready (Food List).");

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }
}
