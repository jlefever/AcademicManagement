package ed.ycp.cs320.acadman.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acadman.model.Measurement;

public class MeasurementTest {
		Measurement test1;
		
		@Before
		public void setup(){
			
			test1 = new Measurement(1,"measure", "test", false,1);
		}
		
		@Test
	    public void testGetId(){
			assertEquals(test1.getId(),1); 
	    }
		
		@Test
	    public void testGetName(){
			assertEquals(test1.getName(),"measure"); 
	    }
		
		@Test
	    public void testDesciption(){
			assertEquals(test1.getDescription(),"test"); 
	    }
		
		@Test
	    public void testGetMinMet(){
			assertEquals(test1.isMet(),false); 
	    }
		
		@Test
	    public void testGetOutcomeId(){
			assertEquals(test1.getIndicatorId(),1); 
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
			test1.setMet(true);;
			assertEquals(test1.isMet(),true); 
	    }
		
		@Test
	    public void testSetOutcomeID(){
			test1.setIndicatorId(2);
			assertEquals(test1.getIndicatorId(),2); 
	    }
		
		@Test
	    public void testEqual(){
			Measurement test = new Measurement(1,"measure", "test", false,1);
			Measurement other = new Measurement(1,"measure", "test", false,1);
			//test1.setOutcomeId(2);
			assertEquals(test.equals(other),true);
	    }
		
		@Test
	    public void testToString(){
			String desired = "Measurement [id=2, name=test, description=test, isMet=false, indicatorId=4]";
			//test1.setOutcomeId(2);
			Measurement tester = new Measurement(2,"test","test",false,4);
			
			String actual= tester.toString();
			assertEquals(actual,desired);
		}
		
}
