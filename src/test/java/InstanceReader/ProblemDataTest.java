/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstanceReader;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author renansantos
 */
public class ProblemDataTest {

    public ProblemDataTest() {
    }

    @Test
    public void testInstanceCreation() {
        
        String path = "C:\\Doutorado - Renan\\Excel Instances\\";
        Instance instance = new Instance();
        instance.setNumberOfRequests(50)
                .setRequestTimeWindows(10)
                .setInstanceSize("s")
                .setNumberOfNodes(12)
                .setNumberOfVehicles(250)
                .setVehicleCapacity(4);

        ExcelDataFileReader reader = new ExcelDataFileReader(path, instance);
        System.out.println(reader.getAdjacenciesListOfTimes());
        System.out.println(reader.getListOfNodes());
        Assert.assertEquals(12, reader.getListOfNodes().size());
    }

}
