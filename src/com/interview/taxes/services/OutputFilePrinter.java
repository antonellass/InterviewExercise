package com.interview.taxes.services;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;

import com.interview.taxes.ItemI;
import com.interview.taxes.calculators.ChainOfResponsabilityCalculator;
/**
 * Print Output on file .txt like:
 * Output(n).txt
 * __________________
 * 1 book : 12.49
 * 1 music CD: 16.49
 * 1 chocolate bar: 0.85
 * Sales Taxes: 1.50
 * Total: 29.83
 * __________________
 * @author antonella
 *
 */
public class OutputFilePrinter implements Output {

	/**
	 * Logger
	 */
	public Logger logger= Logger.getLogger(OutputFilePrinter.class);
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
	/**
	 * Directory output
	 */
	private static final String DIR_OUT ="data/Out";
	/**
	 * keyword name file output
	 */
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
			ChainOfResponsabilityCalculator cal= new ChainOfResponsabilityCalculator(item);
			cal.calculateInvoice(item);
			outputB.append(formatItem(item));
			outputB.append("\n");
		}
		outputB.append(TAXES_TERM);
		BigDecimal taxes= new BigDecimal(BigInteger.ZERO,2);
		for (ItemI item : itemsList) {
		 taxes = taxes.add(item.getTotalTaxes());}
		outputB.append(taxes);
		outputB.append("\n");
		outputB.append(TOTAL_TERM);
		BigDecimal total= new BigDecimal(BigInteger.ZERO,2);
		for (ItemI item : itemsList) {
			total= total.add(item.getSingleImport());
		}
		outputB.append(total);
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
	public String formatItem(ItemI item) {
		StringBuilder formattedItem = new StringBuilder();

		// quantity
		formattedItem.append(item.getQuantity());
		formattedItem.append(" ");

		// name
		formattedItem.append(item.getName());

		// price
		formattedItem.append(": ");
		formattedItem.append(item.getSingleImport());

		return formattedItem.toString();
	}

}
