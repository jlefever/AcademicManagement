package ed.ycp.cs320.acadman.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acadman.model.User;

public class UserTest {
	
	User test1;

	@Before
	public void setup(){
		
		test1 = new User("test", "testEmail", "password",0);
	}
	
	@Test
    public void testUsername(){
		assertEquals(test1.getUsername(),"test"); 
    }
	
	@Test
    public void testGetEmail(){
		assertEquals(test1.getEmail(),"testEmail"); 
    }
	
	@Test
    public void testGetPassword(){
		assertEquals(test1.getPassword(),"password"); 
    }
	
	@Test
    public void testGetPermissions(){
		assertEquals(test1.getPermissions(),0); 
    }
	
	@Test
    public void testSetUsername(){
		test1.setUsername("new");
		assertEquals(test1.getUsername(),"new"); 
    }
	
	@Test
    public void testSetEmail(){
		test1.setEmail("newE");
		assertEquals(test1.getEmail(),"newE"); 
    }
	
	@Test
    public void testSetPassword(){
		test1.setPassword("newP");
		assertEquals(test1.getPassword(),"newP"); 
    }
	
	@Test
    public void testSetPermissions(){
		test1.setPermissions(1);
		assertEquals(test1.getPermissions(),1); 
    }
}
