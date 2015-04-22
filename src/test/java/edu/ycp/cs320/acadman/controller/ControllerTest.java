package edu.ycp.cs320.acadman.controller;


import org.junit.Before;

import edu.ycp.cs320.acadman.persist.FakeDatabase;
import edu.ycp.cs320.acadman.persist.IDatabase;

public class ControllerTest {
	
	IDatabase db;
	
	@Before
	public void setUp(){
		db = new FakeDatabase();
	}
}
