/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentrick.Sagahs;

import documentrick.NumBases.NumBase;

/**
 *
 * @author talal_000
 */
public abstract class Sagah {

    /**
     * Containing the content of the requested file
     */
    protected String fileContent;

    /**
     * For converting numeric bases 
     */
    protected NumBase convertionBase;
    
    /**
     * Iterating the file and converting the numbers to the requested numeric base
     * @param base the requested base for convertion
     */
    public abstract void convertBase(String base);
    
    /**
     * adding the enclosuse in the begining and the end of each cell
     * @param enclosure the requested enclosure to the fields
     */
    public abstract void encloseFields(String enclosure);
    
    public abstract void performSpecial(String param);
    
    /**
     * Saving the fileContent in the class to a file.
     * @param path location to save the file in.
     */
    public abstract void saveContent(String path);
    
    public abstract void setFileContent(String fileContent);
    
}
