/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentrick.Factories;

import documentrick.Sagahs.Sagah;
import documentrick.Sagahs.SagahCSV;

/**
 *
 * @author talal_000
 */
public class SagahFactory {
    private SagahFactory(){}
    public static Sagah createSagah(String path){
        if(path.endsWith(".csv")){
            return new SagahCSV(path);
        }
        else{
            return null;
        }
    }
}
