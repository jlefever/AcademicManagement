package edu.ycp.cs320.acadman.controller;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acadman.persist.DatabaseProvider;
import edu.ycp.cs320.acadman.persist.FakeDatabase;
import edu.ycp.cs320.acadman.persist.IDatabase;

public class ControllerTest {
	
	IDatabase db;
	
	@Before
	public void setUp(){
		db = new FakeDatabase();
	}
}
