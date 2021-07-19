/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstanceReader;


import org.junit.BeforeClass;
import InstanceReader.VrpdrtInstanceData;
import org.junit.Test;

/**
 *
 * @author renan
 */
public class VrpdrtInstanceDataTest {
    private static Instance instance;
    private static String path = "C:\\Doutorado - Renan\\Excel Instances\\";
    private static VrpdrtInstanceData instanceData;
    
    public VrpdrtInstanceDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new Instance();
        instance.setNumberOfRequests(50)
                .setRequestTimeWindows(10)
                .setInstanceSize("s")
                .setNumberOfNodes(12)
                .setNumberOfVehicles(250)
                .setVehicleCapacity(4);
    }
    
    @Test
    public void testVrpdrtInstanceDataTestCreation(){
        instanceData = new VrpdrtInstanceData(instance);
    }
    
}
