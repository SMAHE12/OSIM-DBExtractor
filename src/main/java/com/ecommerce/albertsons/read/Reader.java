package com.ecommerce.albertsons.read;

import com.ecommerce.albertsons.model.CsvItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Reader {
  
  public abstract List<String> readTextToItems(String fileName);
  
}
