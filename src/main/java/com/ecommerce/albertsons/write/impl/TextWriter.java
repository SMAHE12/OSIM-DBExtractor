package com.ecommerce.albertsons.write.impl;

import com.ecommerce.albertsons.write.Writer;

import java.io.FileWriter;
import java.io.IOException;

public class TextWriter extends Writer {
  
  private FileWriter getWriter() throws IOException {
    FileWriter fw = null;
    if (fw == null) {
      fw = new FileWriter("StoreItem-Details.csv");
      fw.write("UpcID,StoreId,ItemDesc,isOrderable,stopBuy,itemType \n");
      return fw;
    }
    return fw;
  }
  
  @Override
  public boolean writeLine(String line) throws IOException {
    FileWriter fw =  getWriter();
    try {
     fw.write(line);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    } finally {
      fw.close();
    }
  }
}
