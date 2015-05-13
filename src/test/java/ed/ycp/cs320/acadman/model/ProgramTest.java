package ed.ycp.cs320.acadman.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acadman.model.Program;


public class ProgramTest {
	Program test1;

	@Before
	public void setup(){
		
		test1 = new Program(1,"Program", "test",2014);
	}
	
	@Test
    public void testGetId(){
		assertEquals(test1.getId(),1); 
    }
	
	@Test
    public void testGetName(){
		assertEquals(test1.getName(),"Program"); 
    }
	
	@Test
    public void testDesciption(){
		assertEquals(test1.getDescription(),"test"); 
    }
	
	@Test
    public void testGetYear(){
		assertEquals(test1.getYear(),2014); 
    }
	
	
	@Test
    public void testSetId(){
		test1.setId(2);
		assertEquals(test1.getId(),2); 
    }
	
	@Test
    public void testSetName(){
		test1.setName("new");
		assertEquals(test1.getName(),"new"); 
    }
	
	@Test
    public void testSetDescription(){
		test1.setDescription("newD");
		assertEquals(test1.getDescription(),"newD"); 
    }
	
	@Test
    public void testSetYear(){
		test1.setYear(2015);
		assertEquals(test1.getYear(),2015); 
    }
	
	@Test
    public void testEqual(){
		Program test = new Program(1,"Program", "test",2014);
		Program other = new Program(1,"Program", "test",2014);
		//test1.setOutcomeId(2);
		assertEquals(test.equals(other),true);
    }
	
	@Test
    public void testToString(){
		String desired = "Program [id=1, name=test, description=test, yearId=2014]";
		//test1.setOutcomeId(2);
		Program tester = new Program(1,"test", "test",2014);
		
		String actual= tester.toString();
		assertEquals(actual,desired);
	}
	
}
