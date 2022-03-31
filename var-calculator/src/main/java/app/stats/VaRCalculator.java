/**
 * 
 */
package app.stats;

import java.util.stream.DoubleStream;

import app.model.Portfolio;
import app.model.Position;

/**
 * A class containing methods used to calculate historical daily Value at Risk.
 * 
 * @author kmeehan
 * 
 * @see <a href="https://www.investopedia.com/articles/04/092904.asp">Investopedia</a>
 *
 */
public class VaRCalculator {

	public static final Double[] CONFIDENCE_LEVELS = new Double[] { 0.95, 0.975, 0.99 };

	/**
	 * @param portfolio
	 * @param confidenceLevel
	 * @return the historical daily Value at Risk of <code>portfolio</code> with confidence <code>confidenceLevel</code>
	 */
	public static double computePortfolioHistoricalDailyVaR(Portfolio portfolio, double confidenceLevel) {
		return computeHistoricalDailyVaR(portfolio.getPrices(), confidenceLevel);

	}

	/**
	 * @param position
	 * @param confidenceLevel
	 * @return the historical daily Value at Risk of <code>position</code> with confidence <code>confidenceLevel</code>
	 */
	public static double computePositionHistoricalDailyVaR(Position position, double confidenceLevel) {
		return computeHistoricalDailyVaR(position.getPrices(), confidenceLevel);
	}

	/**
	 * @param prices
	 *            - an array of price data
	 * @param confidenceLevel
	 *            - the level of confidence that we assume
	 * @return the historical Value at Risk of <code>prices</code> with confidence <code>confidenceLevel</code>
	 * 
	 * @see <a href="https://www.investopedia.com/articles/04/092904.asp#toc-1-historical-method">Investopedia</a>
	 */
	public static double computeHistoricalDailyVaR(double[] prices, double confidenceLevel) {
		double[] returns = computeSortedDailyReturns(prices);
		return returns[getNearestRankPercentileLowerBoundIndex(confidenceLevel, returns)];
	}


	/**
	 * @param confidenceLevel
	 *            - in the range <i>[0, 100]</i>
	 * @param values
	 *            - the population values. It is assumed that these are already sorted in ascending order.
	 * @return an index - in the range <i>[0, <code>values.length</code> - 1]</i> <br>
	 * 
	 *         The returned index points to a value <i>v</i>. We can assume at a confidence of <code>confidenceLevel</code>% that any other <i>v_i</i> in
	 *         <code>values</code> will be greater than <i>v</i> for all <i>i</i>.
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Percentile#The_nearest-rank_method">Wikipedia</a>
	 */
	public static int getNearestRankPercentileLowerBoundIndex(double confidenceLevel, double[] values) {
		int ordinalRank = (int) Math.ceil((1 - confidenceLevel) * values.length);
		return ordinalRank - 1;
	}

	/**
	 * @param prices
	 * @return an array of daily returns, sorted in ascending order
	 */
	public static double[] computeSortedDailyReturns(double[] prices) {
		return DoubleStream.of(computeDailyReturns(prices)).sorted().toArray();
	}

	/**
	 * @param prices
	 * @return an array of daily returns derived from <code>prices</code> <br>
	 * 
	 *         <i>dailyReturn[T]</i> is defined as <i>(price[T] - price[T-1]) / price[T-1]</i>, which is equivalent to <i>(price[T] / price[T-1]) -1</i>
	 * 
	 * 
	 */
	public static double[] computeDailyReturns(double[] prices) {
		int n = prices.length;
		double[] returns = new double[n - 1];
		for (int i = 0; i < n - 1; i++) {
			returns[i] = prices[i + 1] / prices[i] - 1;
		}
		return returns;
	}

}
