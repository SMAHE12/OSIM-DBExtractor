package com.ecommerce.albertsons.util;

import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.model.CsvStoreItem;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;

public class ItemFileUtils {
  private String fileName;
  private CSVReader CSVReader;
  private CSVWriter CSVWriter;
  private FileReader fileReader;
  private FileWriter fileWriter;
  private File file;
  private String[] itemColumns;
  
  public ItemFileUtils(String fileName, String[] itemColumns) {
    this.fileName = fileName;
    this.itemColumns = itemColumns;
  }
  
  public CsvItem readLine() throws Exception {
    if (CSVReader == null)
      initReader();
    String[] line = CSVReader.readNext();
    if (line == null)
      return null;
    return new CsvItem(null, line[0], null,null,
        null,null,null,null,
        null,null,null,
        null,null,null);
  }
  
  public void writeLine(CsvItem line) throws Exception {
    if (CSVWriter == null)
      initWriter();
    /*String[] lineStr = new String[14];
   
    lineStr[0] = line.getCic();
    lineStr[1] = line.getUpcId();
    lineStr[2] = line.getInternetDesc();
    lineStr[3] = line.getProductGroupCd();
    lineStr[4] = line.getProductGroupNm();
    lineStr[5] = line.getProductCategoryCd();
    lineStr[6] = line.getProductCategoryNm();
    lineStr[7] = line.getProductClassCd();
    lineStr[8] = line.getProductClassNm();
    lineStr[9] = line.getRetailSectionCd();
    lineStr[10] = line.getProductSubClassCdLevel1();
    lineStr[11] = line.getProductSubClassNmLevel1();
    lineStr[12] = line.getProductSubClassCdLevel2();
    lineStr[13] = line.getProductSubClassNmLevel2();*/
    String[] lineStr = line.getCsvItemLineData().split("-,-");
    CSVWriter.writeNext(lineStr);
  }
  
  private void initReader() throws Exception {
    ClassLoader classLoader = this
        .getClass()
        .getClassLoader();
    if (file == null) file = new File(fileName);
    if (fileReader == null) fileReader = new FileReader(file);
    if (CSVReader == null) CSVReader = new CSVReader(fileReader);
  }
  
  private void initWriter() throws Exception {
    if (file == null) {
      file = new File(fileName);
      file.createNewFile();
    }
    if (fileWriter == null) fileWriter = new FileWriter(file, true);
    if (CSVWriter == null) {
      CSVWriter = new CSVWriter(fileWriter);
      CSVWriter.writeNext(itemColumns);
      CSVWriter.flushQuietly();
    }
  }
  
  public void closeWriter() {
    try {
      if(CSVWriter != null) {
        CSVWriter.close();
        fileWriter.close();
      }
    } catch (IOException e) {
      System.out.println("Error while closing writer.");
    }
  }
  
  public void closeReader() {
    try {
      CSVReader.close();
      fileReader.close();
    } catch (IOException e) {
      System.out.println("Error while closing reader.");
    }
  }
}