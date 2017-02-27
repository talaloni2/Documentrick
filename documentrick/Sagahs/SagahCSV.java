/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentrick.Sagahs;

import documentrick.Factories.NumBaseFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author talal_000
 */
public class SagahCSV extends Sagah{

    public SagahCSV(String path){
        try {
            File requestedfile = new File(path);
            Scanner content = new Scanner(requestedfile);
            fileContent = "";
            while (content.hasNext()) {                    
                fileContent = fileContent+content.next()+"\n";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SagahCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void convertBase(String base) {
        Scanner content;
        String changes = "";
        convertionBase = NumBaseFactory.createNumBase(base);
        
        content = new Scanner(fileContent);
        while(content.hasNext()&&convertionBase !=null){
            String rowContent = content.next();
            Matcher m = Pattern.compile("[\\d]*").matcher(rowContent);
            while (m.find()) {                
                String match = m.group();
                if(match != null && !match.equals("")){
                    rowContent = rowContent.replaceFirst(match, convertionBase.convertNumBase(Integer.parseInt(match)));
                }
            }
            changes = changes+rowContent+"\n";
        }
        fileContent = changes;
    }

    @Override
    public void encloseFields(String enclosure) {
        Scanner content = new Scanner(fileContent);
        String changes = "";
        
        enclosure = enclosure.replaceAll("\"", "\"\"");
        
        while(content.hasNext()){
            String[] currentRow = splitRow(content.next());
            String currentRowContent = "";
            for(int i = 0; i < currentRow.length; i++){
               if(currentRow[i].startsWith("\"") && currentRow[i].endsWith("\"")){
                   //dismissing enclosing double quotes
                   currentRow[i] = currentRow[i].substring(1, currentRow[i].length()-1);
               } 
               currentRow[i] = "\""+enclosure+currentRow[i]+enclosure+"\"";
               currentRowContent = currentRowContent+currentRow[i]+",";
            }
            changes = changes+currentRowContent.substring(0, currentRowContent.length()-1)+"\n";
        }
        fileContent = changes;
    }
    
    private String[] splitRow(String row){
        String[] splittedRow = row.split(",");
        List<String> correctedSplittedRow = new ArrayList<String>();
        for(int i = 0; i < splittedRow.length ; i++){
            if(splittedRow[i].startsWith("\"") &&
                    !splittedRow[i].endsWith("\"")&&
                    i+1 < splittedRow.length &&
                    splittedRow[i+1].endsWith("\"")){
                correctedSplittedRow.add(splittedRow[i]+splittedRow[i+1]);
                i++;
            }
            else{
                correctedSplittedRow.add(splittedRow[i]);
            }
        }
        return correctedSplittedRow.toArray(splittedRow);
    }

    /**
     * printing the n'th column of the file
     * @param param the n'th column requested for printing
     */
    @Override
    public void performSpecial(String param) {
        try{
            int column = Integer.parseInt(param)-1;
            Scanner content = new Scanner(fileContent);
            while(content.hasNext()){
                String[] currentRow = splitRow(content.next());
                if(column < currentRow.length && column >=0){
                    System.out.println(currentRow[column]);
                }
                else{
                    System.out.println("Index out of range, couldn't print column");
                }
            }
        }catch(NumberFormatException ex){
            System.err.println("couldn't perform action\n"
                    + "because you didn't enter a valid numbeer of column to print");
        }
    }

    @Override
    public void saveContent(String path) {
        boolean create = true;
        if(Files.exists(Paths.get(path))){
            System.out.println("file already exists, override? y/n");
            Scanner answer = new Scanner(System.in);
            if(answer.hasNext()){
                String res = answer.next();
                if(res.startsWith("y")){
                    try {
                        Files.delete(Paths.get(path));
                    } catch (IOException ex) {
                        Logger.getLogger(SagahCSV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(res.startsWith("n")){
                    create = false;
                }
            }
            else{
                System.out.println("error with input");
                create = false;
            }
        }
        if(create){
                try {
                    Files.createFile(Paths.get(path));
                    File newFile = new File(path);
                    PrintWriter fileWriter = new PrintWriter(newFile);
                    fileWriter.write(fileContent);
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(SagahCSV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    @Override
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
    
    
    
}
