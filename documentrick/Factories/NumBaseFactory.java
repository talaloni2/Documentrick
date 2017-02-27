/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentrick.Factories;

import documentrick.NumBases.BinaryNumBase;
import documentrick.NumBases.NumBase;

/**
 *
 * @author talal_000
 */
public class NumBaseFactory {
    private NumBaseFactory(){}
    public static NumBase createNumBase(String base){
        if(base.equals("b")){
            return new BinaryNumBase();
        }
        else{
            return null;
        }
    }
}
