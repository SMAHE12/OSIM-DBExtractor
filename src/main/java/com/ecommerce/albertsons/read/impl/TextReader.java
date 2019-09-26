package com.ecommerce.albertsons.read.impl;

import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.read.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextReader extends Reader {
  
  /**
   * readLines to read a generic line of data from given text file.
   *
   * @param fileName file name.
   * @return String Lines of data.
   */
  @Override
  public List<String> readTextToItems(String fileName) {
    List<String> cicIds = processInputFile(fileName);
    return cicIds;
  }
  
  private List<String> processInputFile(String inputFilePath) {
    List<String> inputList = new ArrayList<String>();
    try {
      File inputF = new File(inputFilePath);
      InputStream inputFS = new FileInputStream(inputF);
      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
      inputList = br.lines().collect(Collectors.toList());
      br.close();
      System.out.println("CIC Data Loaded Successfully");
     /* for (String cic : inputList) {
        System.out.println("CIC : " + cic);
        
      }*/
     
    } catch (IOException e) {
      e.printStackTrace();
    }
    return inputList;
  }
  
  private Function<String, CsvItem> mapToItem = (line) -> {
    CsvItem item = new CsvItem(line,null,null,
        null,
        false,null,null);
    return item;
  };
}
