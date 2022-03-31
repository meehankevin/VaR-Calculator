/**
 * 
 */
package stats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.stats.VaRCalculator;
import app.util.PriceUtils;

/**
 * @author kmeehan
 *
 */
class VarCalculatorTest {

	private static final double ZERO_PRECISION = 0.000001d;

	@Test
	void testDailyReturns() {
		System.out.println("Testing daily returns");
		double[] prices = PriceUtils.buildRandomPrices();

		double[] returns = VaRCalculator.computeDailyReturns(prices);

		assertTrue(returns.length > 0);
		assertEquals(prices.length - 1, returns.length, ZERO_PRECISION);

		for (int i = 0; i < returns.length; i++) {
			assertEquals(prices[i + 1], prices[i] * (1 + returns[i]), ZERO_PRECISION);
		}
	}

	@Test
	void testSortedDailyReturns() {
		double[] prices = PriceUtils.buildRandomPrices();

		double[] returns = VaRCalculator.computeSortedDailyReturns(prices);

		assertTrue(returns.length > 0);
		assertEquals(prices.length - 1, returns.length, ZERO_PRECISION);

		double previousReturn = returns[0];
		for (int i = 1; i < returns.length; i++) {
			assertTrue(returns[i] > previousReturn);
			previousReturn = returns[i];
		}
	}

	@Test
	void testVaRComputation() {
		double[] prices = PriceUtils.buildRandomPrices();
		double[] returns = VaRCalculator.computeSortedDailyReturns(prices);

		for (Double confidenceLevel : VaRCalculator.CONFIDENCE_LEVELS) {
			double valueAtRisk = VaRCalculator.computeHistoricalDailyVaR(prices, confidenceLevel);

			double returnsLeVaR = 0; // returns less than or equal VaR
			double returnsGtVaR = 0; // returns greater than

			for (double ret : returns) {
				if (ret > valueAtRisk) {
					returnsGtVaR++;
				} else {
					returnsLeVaR++;
				}
			}

			assertEquals(Math.ceil((1 - confidenceLevel) * returns.length), returnsLeVaR, ZERO_PRECISION);
			assertEquals(Math.floor(confidenceLevel * returns.length), returnsGtVaR, ZERO_PRECISION);
		}

	}

}
