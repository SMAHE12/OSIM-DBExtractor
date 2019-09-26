package com.ecommerce.albertsons.write;

import java.io.File;
import java.io.IOException;

public abstract class Writer {
  public abstract boolean writeLine(String line) throws IOException;
  
  /** writeLine to write a generic line of data to given csv file.
   * @param file file object.
   * @return boolean write status.
   */
  public boolean writeLine(File file) {
    return true;
  }
}
