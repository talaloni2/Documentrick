/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentrick;

import documentrick.Controller.OperationEngine;
import documentrick.Enums.CommandNames;
import documentrick.Factories.SagahFactory;
import documentrick.Sagahs.Sagah;
import java.io.Console;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author talal_000
 */
public class Documentrick {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Console console = System.console();
        String input = console.readLine();//"C:\\Users\\talal_000\\Desktop\\jjj\\server -changeNumBase -b encloseBy '\"'";
        HashMap<String, String> commands = null;
        Sagah sagah = null;
        commands = OperationEngine.mapCommands(input);
        if(commands != null && commands.containsKey(CommandNames.CreateFile.getCommandName())){
            sagah = SagahFactory.createSagah(commands.get(CommandNames.CreateFile.getCommandName()));
            OperationEngine.handleOperations(commands, sagah);
            
            System.out.println("enter parameter for special action");
            input =  console.readLine();
            sagah.performSpecial(input);
            
            System.out.println("enter path to save file");
            input = console.readLine();
            sagah.saveContent(input);
        }
    }
}
