/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mike;
//importing Random for the random numbers 

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author nicholasgenco
 *
 */
public class readExcel {
     private String inputFile;
    static String[][] data = null;
    /**
     *
     * Array array of integers arraySize size of Array sum what we are looking
     * for
     *
     * @param Array
     * @param arraySize
     * @param sum
     * @return
     */
    public static boolean findNumbers(double Array[], int arraySize, double sum) {
        //outter loop keeps first number unless it has to change
        for (int i = 0; i < arraySize - 2; i++) {
            //middle loop keeps second number unless it has to change 
            for (int j = i + 1; j < arraySize - 1; j++) {
                //changes the every time and will break function if sum is found
                for (int k = j + 1; k < arraySize; k++) {
                    //conditional staement if found print and return true 
                    if (Array[i] + Array[j] + Array[k] == sum) {
                        System.out.print("Triplet is " + Array[i] + " ," + Array[j] + " ," + Array[k]);
                        return true;
                    }
                }
            }
        }

        // If we reach here then nothing was found 
        return false;
    }
    
   
    public void setInputFile(String inputFile) 
    {
        this.inputFile = inputFile;
    }

    public String[][] read() throws IOException  
    {
        File inputWorkbook = new File(inputFile);
        Workbook w;

        try 
        {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet


            Sheet sheet = w.getSheet(0);
            data = new String[sheet.getColumns()][sheet.getRows()];
            // Loop over first 10 column and lines
          //System.out.println(sheet.getColumns() +  " " +sheet.getRows());
            for (int j = 0; j <sheet.getColumns(); j++) 
            {
                for (int i = 0; i < sheet.getRows(); i++) 
                {
                    Cell cell = sheet.getCell(j, i);
                    data[j][i] = cell.getContents();
                    //System.out.println(cell.getContents());
                }
            }

        } 
        catch (BiffException e) 
        {
            e.printStackTrace();
        }
    return data;
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BiffException, IOException {
        // TODO code application logic here
        //variables array of 373 ints
 
        //filling array with randoms to test 
        readExcel test = new readExcel();
        test.setInputFile("dummydata.xls");
        test.read();
        double[] anArray  =  new double[data[3].length];
        double temp = 198.13;
        for(int i=1; i<data[3].length; i++){
            System.out.println(data[3][i].substring(1));
            
            anArray[i]= Double.parseDouble(data[3][i].substring(1));
        }
        
        //testing if it works 
        if (findNumbers(anArray,anArray.length, temp) == false) {

            System.out.println("Did not find three numbers that add to Goal Number.");
            System.exit(1);
        }

        //exit 1 for not found 0 for found
        System.exit(0);
    }

}
