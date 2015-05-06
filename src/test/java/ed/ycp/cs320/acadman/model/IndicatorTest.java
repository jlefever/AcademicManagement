package ed.ycp.cs320.acadman.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acadman.model.Indicator;

public class IndicatorTest {
	Indicator test1;
	
	@Before
	public void setup(){
		
		test1 = new Indicator(1,"test","tester",4,1);
	}
	
	@Test
    public void testGetId(){
		assertEquals(test1.getId(),1); 
    }
	
	@Test
    public void testGetName(){
		assertEquals(test1.getName(),"test"); 
    }
	
	@Test
    public void testDesciption(){
		assertEquals(test1.getDescription(),"tester"); 
    }
	
	@Test
    public void testGetMinMet(){
		assertEquals(test1.getMinMet(),4); 
    }
	
	@Test
    public void testGetOutcomeId(){
		assertEquals(test1.getOutcomeId(),1); 
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
    public void testSetMinMet(){
		test1.setMinMet(5);
		assertEquals(test1.getMinMet(),5); 
    }
	
	@Test
    public void testSetOutcomeID(){
		test1.setOutcomeId(2);
		assertEquals(test1.getOutcomeId(),2); 
    }
	
}
