/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentrick.Enums;

/**
 *
 * @author talal_000
 */
public enum CommandNames {
    ChangeNumBase("changeNumBase"),
    EncloseBy("encloseBy"),
    CreateFile("createFile");
    
    private String name;
    
    private CommandNames(String cname){
        this.name = cname;
    }
    
    public String getCommandName(){
        return this.name;
    }
    
}