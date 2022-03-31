/**
 * 
 */
package app.util;

import java.util.Random;

/**
 * A utility class to build prices
 * 
 * @author kmeehan
 *
 */
public class PriceUtils {

	public static final int NUMBER_OF_HISTORICAL_PRICES = 250;

	public static double[] buildRandomPrices() {
		double[] initializedPrices = new double[NUMBER_OF_HISTORICAL_PRICES];
		Random random = new Random();
		for (int i = 0; i < NUMBER_OF_HISTORICAL_PRICES; i++) {
			if (i == 0) {
				initializedPrices[i] = 100;
			} else {
				double previousPrice = initializedPrices[i - 1];
				initializedPrices[i] = random.nextDouble(previousPrice - 10, previousPrice + 10);
			}
		}
		return initializedPrices;
	}

}
