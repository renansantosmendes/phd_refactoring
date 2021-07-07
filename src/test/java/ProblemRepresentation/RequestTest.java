/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemRepresentation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author renan
 */
public class RequestTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getId method, of class Request.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Request instance = new Request();
        Integer result = instance.getId();
        assertTrue(Integer.class.isInstance(result));

    }

    /**
     * Test of setId method, of class Request.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = null;
        Request instance = new Request();
        instance.setId(id);
        assertTrue(Integer.class.isInstance(instance.getId()));
    }

    /**
     * Test of getOrigin method, of class Request.
     */
    @Test
    public void testGetOrigin() {
        System.out.println("getOrigin");
        Request instance = new Request();
        int expResult = 0;
        int result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrigin method, of class Request.
     */
    @Test
    public void testSetOrigin() {
        System.out.println("setOrigin");
        int origin = 1;
        Request instance = new Request();
        instance.setOrigin(origin);
        assertEquals(instance.getOrigin(), origin);
    }

    /**
     * Test of getDestination method, of class Request.
     */
    @Test
    public void testGetDestination() {
        System.out.println("getDestination");
        Request instance = new Request();
        int expResult = 0;
        int result = instance.getDestination();

    }

    /**
     * Test of setDestination method, of class Request.
     */
    @Test
    public void testSetDestination() {
        System.out.println("setDestination");
        int destination = 0;
        Request instance = new Request();
        instance.setDestination(destination);
        assertEquals(destination, instance.getDestination());
    }

    /**
     * Test of getPickupTimeWindowLower method, of class Request.
     */
    @Test
    public void testGetPickupTimeWindowLower() {
        System.out.println("getPickupTimeWindowLower");
        Request instance = new Request();
        long expResult = 0L;
        long result = instance.getPickupTimeWindowLower();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPickupTimeWindowLower method, of class Request.
     */
    @Test
    public void testSetPickupTimeWindowLower() {
        System.out.println("setPickupTimeWindowLower");
        long pickupE = 0L;
        Request instance = new Request();
        instance.setPickupTimeWindowLower(pickupE);
        testGetPickupTimeWindowLower();
    }

    /**
     * Test of getPickupTimeWindowUpper method, of class Request.
     */
    @Test
    public void testGetPickupTimeWindowUpper() {
        System.out.println("getPickupTimeWindowUpper");
        Request instance = new Request();
        long expResult = 0L;
        long result = instance.getPickupTimeWindowUpper();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPickupTimeWindowUpper method, of class Request.
     */
    @Test
    public void testSetPickupTimeWindowUpper() {
        System.out.println("setPickupTimeWindowUpper");
        long pickupTimeWindowUpper = 0L;
        Request instance = new Request();
        instance.setPickupTimeWindowUpper(pickupTimeWindowUpper);
        testGetPickupTimeWindowUpper();
    }

    /**
     * Test of getDeliveryTimeWindowLower method, of class Request.
     */
    @Test
    public void testGetDeliveryTimeWindowLower() {
        System.out.println("getDeliveryTimeWindowLower");
        Request instance = new Request();
        long expResult = 0L;
        long result = instance.getDeliveryTimeWindowLower();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDeliveryTimeWindowLower method, of class Request.
     */
    @Test
    public void testSetDeliveryTimeWindowLower() {
        System.out.println("setDeliveryTimeWindowLower");
        long deliveryTimeWindowLower = 0L;
        Request instance = new Request();
        instance.setDeliveryTimeWindowLower(deliveryTimeWindowLower);
        testGetDeliveryTimeWindowLower();
    }

    /**
     * Test of getDeliveryTimeWindowUpper method, of class Request.
     */
    @Test
    public void testGetDeliveryTimeWindowUpper() {
        System.out.println("getDeliveryTimeWindowUpper");
        Request instance = new Request();
        long expResult = 0L;
        long result = instance.getDeliveryTimeWindowUpper();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDeliveryTimeWindowUpper method, of class Request.
     */
    @Test
    public void testSetDeliveryTimeWindowUpper() {
        System.out.println("setDeliveryTimeWindowUpper");
        long deliveryTimeWindowUpper = 0L;
        Request instance = new Request();
        instance.setDeliveryTimeWindowUpper(deliveryTimeWindowUpper);
        testGetDeliveryTimeWindowUpper();
    }

    /**
     * Test of getPickupTime method, of class Request.
     */
    @Test
    public void testGetPickupTime() {
        System.out.println("getPickupTime");
        Request instance = new Request();
        Long expResult = -1L;
        Long result = instance.getPickupTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPickupTime method, of class Request.
     */
    @Test
    public void testSetPickupTime() {
        System.out.println("setPickupTime");
        Long pickupTime = -1L;
        Request instance = new Request();
        instance.setPickupTime(pickupTime);
        testGetPickupTime();
    }

    /**
     * Test of getDeliveryTime method, of class Request.
     */
    @Test
    public void testGetDeliveryTime() {
        System.out.println("getDeliveryTime");
        Request instance = new Request();
        Long expResult = null;
        Long result = instance.getDeliveryTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeliveryTime method, of class Request.
     */
    @Test
    public void testSetDeliveryTime() {
        System.out.println("setDeliveryTime");
        long deliveryTime = 0L;
        Request instance = new Request();
        instance.setDeliveryTime(deliveryTime);
        testGetTimeWindowDefault();
    }

    /**
     * Test of getTimeWindowDefault method, of class Request.
     */
    @Test
    public void testGetTimeWindowDefault() {
        System.out.println("getTimeWindowDefault");
        Request instance = new Request();
        long expResult = 0L;
        long result = instance.getTimeWindowDefault();
        assertEquals(expResult, result);        
    }

    /**
     * Test of setTimeWindowDefault method, of class Request.
     */
    @Test
    public void testSetTimeWindowDefault() {
        System.out.println("setTimeWindowDefault");
        long timeWindowDefault = 0L;
        Request instance = new Request();
        instance.setTimeWindowDefault(timeWindowDefault);
    }

    /**
     * Test of getStringToFile method, of class Request.
     */
    @Test
    public void testGetStringToFile() {
        System.out.println("getStringToFile");
        Request instance = new Request();
        String expResult = "";
        String result = instance.getStringToFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Request.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Request instance = new Request();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Request.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        Object object = null;
        Request instance = new Request();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Request.
     */
    @Test
    public void testEquals_Request() {
        System.out.println("equals");
        Request request = null;
        Request instance = new Request();
        boolean expResult = false;
        boolean result = instance.equals(request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Request.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Request instance = new Request();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Request.
     */
    @Test
    public void testCompareTo_Object() {
        System.out.println("compareTo");
        Object obj = null;
        Request instance = new Request();
        int expResult = 0;
        int result = instance.compareTo(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Request.
     */
    @Test
    public void testCompareTo_Request() {
        System.out.println("compareTo");
        Request request = null;
        Request instance = new Request();
        int expResult = 0;
        int result = instance.compareTo(request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Request.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Request instance = new Request();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
