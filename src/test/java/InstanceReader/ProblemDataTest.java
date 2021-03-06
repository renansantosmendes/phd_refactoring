/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstanceReader;

import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import InstanceReader.VrpdrtInstanceData;
/**
 *
 * @author renansantos
 */
public class ProblemDataTest {

    private static Instance instance;
    private static String path = "C:\\Doutorado - Renan\\Excel Instances\\";
    private static VrpdrtInstanceData instanceData;
    
    @BeforeClass
    public static void beforeAllTestMethods() {
        instance = new Instance();
        instance.setNumberOfRequests(50)
                .setRequestTimeWindows(10)
                .setInstanceSize("s")
                .setNumberOfNodes(12)
                .setNumberOfVehicles(250)
                .setVehicleCapacity(4);
    }

    @Test
    public void testInstanceCreation() {
        ExcelDataFileReader instanceReader = new ExcelDataFileReader(path, instance);
        Assert.assertEquals(12, instanceReader.getListOfNodes().size());
    }

    @Test
    public void testInstanceDataCreation() {
         
    }

}
