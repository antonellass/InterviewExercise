package com.interview.test;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.interview.taxes.services.Output;
import com.interview.taxes.services.OutputFilePrinter;
 

public class TaxCalculatorTest extends TestCase{
	
	
	private static final String F_INPUT1="/Input1.txt";
	private static final String F_INPUT2="/Input2.txt";	
	private static final String F_INPUT3="/Input3.txt";
	private static final String F_OUTPUT1="/Output1.txt";
	private static final String F_OUTPUT2="/Output2.txt";
	private static final String F_OUTPUT3="/Output3.txt";
	private static final String DIR_IN ="data/In";
	private static final String DIR_EXPECTED ="data/Expected";
	
	

	

	@Test
	public void testInput1_OK() throws IOException {
	    final File expected = new File(DIR_EXPECTED,F_OUTPUT1);
	    Output outputI = new OutputFilePrinter();
	    File output = new File(outputI.printOutput(new File(DIR_IN,F_INPUT1)));
	    Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
	}
	@Test
	public void testInput2_OK() throws IOException {
		 final File expected = new File(DIR_EXPECTED,F_OUTPUT2);
		    Output outputI = new OutputFilePrinter();
		    File output = new File(outputI.printOutput(new File(DIR_IN,F_INPUT2)));
		    Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
	}
	@Test
	public void testInput3_OK() throws IOException {
		 final File expected = new File(DIR_EXPECTED,F_OUTPUT3);
		  Output outputI = new OutputFilePrinter();
		    File output = new File(outputI.printOutput(new File(DIR_IN,F_INPUT3)));
		    Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
	}
	public static void main(String args[]) {
	      org.junit.runner.JUnitCore.main("com.interview.test.TaxCalculatorTest");
	    }

}
