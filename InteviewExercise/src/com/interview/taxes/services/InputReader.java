package com.interview.taxes.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.interview.taxes.ItemFactory;
import com.interview.taxes.ItemI;

public class InputReader {
	/**
	 * Logger
	 */
	public Logger logger= Logger.getLogger(InputReader.class);
	/**
	 * InputReader Instance
	 */
	private static final InputReader INSTANCE = new InputReader();

	/**
	 * Static factory method to singleton instance
	 */
	public static InputReader getInstance() {
		return INSTANCE;
	}
	
	
	/**
	 * Load input from InputFile
	 * 
	 * @param input  File
	 * @return List of Item
	 */
	public  List<ItemI> loadInput(File input) {
		List<ItemI> items = new ArrayList<ItemI>();
		List<String> lines = reads(input);
		for (String singleLine : lines) {
			ItemI item = ItemFactory.createItem(singleLine);
			items.add(item);
		}

		return items;
	}

	/**
	 * Reads the contents of a file line by line to a List of Strings
	 * 
	 * @param inputFile  File
	 * @return List of single order string
	 */
	public List<String> reads(File inputFile) {
		List<String> lines = new ArrayList<>();

		try {
			final Charset cs = Charset.forName("ASCII");
			lines = FileUtils.readLines(inputFile, cs);
		} catch (IOException e) {
			logger.error("Exception IO");
			e.printStackTrace();
		}
		return lines;
	}

}
