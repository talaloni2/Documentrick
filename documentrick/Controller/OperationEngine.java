/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentrick.Controller;

import documentrick.Enums.CommandNames;
import documentrick.Sagahs.Sagah;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author talal_000
 */
public class OperationEngine {

    private static boolean isSupportedKey(String key) {
        return key.equals(CommandNames.ChangeNumBase.getCommandName()) ||
                key.equals(CommandNames.CreateFile.getCommandName()) || 
                key.equals(CommandNames.EncloseBy.getCommandName());
    }
    private OperationEngine(){}
    public static void handleOperations(HashMap<String, String> commands, Sagah sagah){
        if(commands.containsKey(CommandNames.ChangeNumBase.getCommandName())){
            sagah.convertBase(commands.get(CommandNames.ChangeNumBase.getCommandName()));
        }
        if(commands.containsKey(CommandNames.EncloseBy.getCommandName())){
            sagah.encloseFields(commands.get(CommandNames.EncloseBy.getCommandName()));
        }
        commands.keySet().stream().forEach(key->{
            if(!isSupportedKey(key)){
                System.err.println("the operation: "+key+" is not supported yet");
            }
        });
    }
    public static HashMap<String, String> mapCommands(String query){
        String createFile = CommandNames.CreateFile.getCommandName();
        String[] queryArray = query.split(" ");
        List<String> listedQuery = new ArrayList<>();
        HashMap<String, String> mappedQuery = new HashMap<>();
        listedQuery.add(createFile);
        for(int i = 0; i<queryArray.length; i++){
            if(!queryArray[i].equals(" ")){
                if(queryArray[i].startsWith("-")){
                    queryArray[i] = queryArray[i].substring(1);
                }
                if(queryArray[i].startsWith("'") && queryArray[i].endsWith("'")){
                    queryArray[i] = queryArray[i].substring(1, queryArray[i].length()-1);
                }
                listedQuery.add(queryArray[i]);
            }
        }
        queryArray = listedQuery.toArray(new String[0]);
        if(queryArray.length % 2 != 0){
            return null;
        }
        for(int i = 0; i < queryArray.length; i+=2){
            mappedQuery.put(queryArray[i], queryArray[i+1]);
        }
        return mappedQuery;
    }
}
