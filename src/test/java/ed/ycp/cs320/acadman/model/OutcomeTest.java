package ed.ycp.cs320.acadman.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;

public class OutcomeTest {
	Outcome test1;
	
	@Before
	public void setup(){
		
		test1 = new Outcome(1, "outcome", "test", 1,1);
	}
	
	@Test
    public void testGetId(){
		assertEquals(test1.getId(),1); 
    }
	
	@Test
    public void testGetName(){
		assertEquals(test1.getName(),"outcome"); 
    }
	
	@Test
    public void testDesciption(){
		assertEquals(test1.getDescription(),"test"); 
    }
	
	@Test
    public void testGetMinMet(){
		assertEquals(test1.getMinMet(),1); 
    }
	
	@Test
    public void testGetProgramId(){
		assertEquals(test1.getProgramId(),1); 
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
		test1.setMinMet(2);
		assertEquals(test1.getMinMet(),2); 
    }
	
	@Test
    public void testSetProgramID(){
		test1.setProgramId(2);
		assertEquals(test1.getProgramId(),2); 
    }
	
	@Test
    public void testEqual(){
		Outcome test = new Outcome(1, "outcome", "test", 1,1);
		Outcome other = new Outcome(1, "outcome", "test", 1,1);
		//test1.setOutcomeId(2);
		assertEquals(test.equals(other),true);
    }
	
	@Test
    public void testToString(){
		String desired = "Outcome [id=2, name=test, description=test, minMet=1, programId=1]";
		//test1.setOutcomeId(2);
		Outcome tester = new Outcome(2,"test","test",1,1);
		
		String actual= tester.toString();
		assertEquals(actual,desired);
	}
}
