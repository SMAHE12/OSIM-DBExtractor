# OSIM-DBExtractor
Store item extraction TAN-1195

Run Instructions:
Step 1 :
 1) Make sure the application yml has the required inputs
2) cicIdsFile: Store Item input file path for example E:\extractor\Sample_Input-300000.txt
3) upcIdsFile: Item input file path for example E:\extractor\Upc-Ids.txt
4) itemsOutputFile: Item Out file path for example  E:\extractor\item-output.csv
5) storeItemsOutputFile: Store Item Out file path for example E:\extractor\output.csv
6) itemColumns: Header values required from Store item collection.
7) storeItemColumns: Header values required from Store item collection.
8) Make sure you point the required DB.

Step 2: Run the itemStoreItemDBUtil-1.0-SNAPSHOT.jar file with following commands
 1) Go to the Jar location.
 2) For Item job to run  java -jar itemStoreItemDBUtil-1.0-SNAPSHOT.jar item
 3) For Item job to run  java -jar itemStoreItemDBUtil-1.0-SNAPSHOT.jar store_item
 4) Output is saved in the file mentioned yml.