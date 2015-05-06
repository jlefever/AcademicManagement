package ed.ycp.cs320.acadman.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Rubric;
	

public class RubricTest {
		Rubric test1;

		@Before
		public void setup(){
			
			test1 = new Rubric(1,3,10,10,50);
		}
		
		@Test
	    public void testGetMeasurementId(){
			assertEquals(test1.getMeasurementId(),1); 
	    }
		
		@Test
	    public void testGetBelow(){
			assertEquals(test1.getBelow(),3); 
	    }
		
		@Test
	    public void testGetMeets(){
			assertEquals(test1.getMeets(),10); 
	    }
		
		@Test
	    public void testGetExceeds(){
			assertEquals(test1.getExceeds(),10); 
	    }
		
		@Test
	    public void testGetTarget(){
			assertEquals(test1.getTarget(),50); 
	    }
		@Test
	    public void testSetMeasurementId(){
			test1.setMeasurementId(2);
			assertEquals(test1.getMeasurementId(),2); 
	    }
		
		@Test
	    public void testSetBelow(){
			test1.setBelow(4);
			assertEquals(test1.getBelow(),4); 
	    }
		
		@Test
	    public void testSetMeets(){
			test1.setMeets(11);
			assertEquals(test1.getMeets(),11); 
	    }
		
		@Test
	    public void testSetExceeds(){
			test1.setExceeds(11);
			assertEquals(test1.getExceeds(),11); 
	    }
		
		@Test
	    public void testSetTarget(){
			test1.setTarget(10);
			assertEquals(test1.getExceeds(),10); 
	    }
}
