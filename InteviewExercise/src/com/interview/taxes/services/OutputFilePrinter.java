package com.interview.taxes.services;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.interview.taxes.ItemI;
import com.interview.taxes.SalesCalculator;

public class OutputFilePrinter implements Output {

	/**
	 * Logger
	 */
	public Logger logger= Logger.getLogger(OutputRenderer.class);
	/**
//	 * OutputRenderer Instance
//	 */
//	private static final OutputRenderer INSTANCE = new OutputRenderer();
//
//	/**
//	 * Static factory method to singleton instance
//	 * 
//	 * @return OutputRenderer Instance
//	 */
//	public static OutputRenderer getInstance() {
//		return INSTANCE;
//	}
//
	/**
	 * SalesCalculator instance
	 */
	private SalesCalculator calculator = SalesCalculator.INSTANCE;
	/**
	 * keyword to print
	 */
	private static final String TAXES_TERM = "Sales Taxes: ";
	/**
	 * keyword to print
	 */
	private static final String TOTAL_TERM = "Total: ";
	/**
	 * The Builder to produce file
	 */
	private StringBuilder outputB;
	private static final String DIR_OUT ="data/Out";

	private static final String F_OUTPUT="Output";


	/**
	 * Print output
	 * 
	 * @param orderFile
	 * @param outputFile
	 * @throws IOException
	 */
	@Override
	public String printOutput(File orderFile) {
		File outputFile = new File(DIR_OUT,orderFile.getName().replaceAll("(?:.+)(\\d+\\.txt)", F_OUTPUT+"$1"));
		List<ItemI> itemsList = InputReader.getInstance().loadInput(orderFile);
			render(itemsList, outputFile);
			LogMF.debug(logger,"Create Output File in:{0} ",outputFile.getAbsolutePath());
			return outputFile.getPath();
		
	}

	
	/**
	 * Render the output into a File
	 * 
	 * @param itemsList
	 *            list of items
	 * @param fOutput
	 *            File
	 */
	public void render(List<ItemI> itemsList, File fOutput) {
		outputB = new StringBuilder();
		for (ItemI item : itemsList) {
			outputB.append(formatItem(item, calculator));
			outputB.append("\n");
		}
		outputB.append(TAXES_TERM);
		BigDecimal taxes = calculator.calculateTaxes(itemsList);
		outputB.append(taxes);
		outputB.append("\n");
		outputB.append(TOTAL_TERM);
		outputB.append(calculator.calculateTotalImport(itemsList));
		try {
			FileUtils.writeStringToFile(fOutput, outputB.toString());
		} catch (IOException e) {
			logger.error("IOException: "+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Format a single line
	 * 
	 * @param item
	 * @param calculator
	 * @return String of Item
	 */
	public String formatItem(ItemI item, SalesCalculator calculator) {
		StringBuilder formattedItem = new StringBuilder();

		// quantity
		formattedItem.append(item.getQuantity());
		formattedItem.append(" ");

		// descriptor
		formattedItem.append(item.getName());

		// price
		formattedItem.append(": ");
		formattedItem.append(calculator.calculateSingleImport(item));

		return formattedItem.toString();
	}

}
