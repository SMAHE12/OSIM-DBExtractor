package com.ecommerce.albertsons.util;

import com.ecommerce.albertsons.model.CsvStoreItem;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StoreItemFileUtils {
  private String fileName;
  private CSVReader CSVReader;
  private CSVWriter CSVWriter;
  private FileReader fileReader;
  private FileWriter fileWriter;
  private File file;
  
  public StoreItemFileUtils(String fileName) {
    this.fileName = fileName;
  }
  
  public CsvStoreItem readLine() throws Exception {
    if (CSVReader == null)
      initReader();
    String[] line = CSVReader.readNext();
    if (line == null)
      return null;
    return new CsvStoreItem(line[0], null,
        null,
        null, false,
        null, null);
  }
  
  public void writeLine(CsvStoreItem line) throws Exception {
    if (CSVWriter == null)
      initWriter();
    String[] lineStr = new String[7];
    lineStr[0] = line.getCic();
    lineStr[1] = line.getStoreId();
    lineStr[2] = "Desc:-" + line.getItemDescription();
    lineStr[3] = Boolean.toString(line.isOrderable());
    lineStr[4] = line.getStopBy();
    lineStr[5] = line.getItemType();
    
    CSVWriter.writeNext(lineStr);
  }
  
  private void initReader() throws Exception {
    ClassLoader classLoader = this
        .getClass()
        .getClassLoader();
    if (file == null) file = new File(classLoader
        .getResource(fileName)
        .getFile());
    if (fileReader == null) fileReader = new FileReader(file);
    if (CSVReader == null) CSVReader = new CSVReader(fileReader);
  }
  
  private void initWriter() throws Exception {
    if (file == null) {
      file = new File(fileName);
      file.createNewFile();
    }
    if (fileWriter == null) fileWriter = new FileWriter(file, true);
    if (CSVWriter == null) CSVWriter = new CSVWriter(fileWriter);
  }
  
  public void closeWriter() {
    try {
      CSVWriter.close();
      fileWriter.close();
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