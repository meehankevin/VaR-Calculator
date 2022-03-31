/**
 * 
 */
package app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import app.util.PriceUtils;

/**
 * @author kmeehan
 *
 */
public class Portfolio {

	private static final int MAX_NUMBER_POSITIONS = 100;

	private String name;
	private final List<Position> positions;

	private final double[] prices;

	public Portfolio(String name) {
		this.name = name;
		this.positions = initializePositions();
		this.prices = initializePrices();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public double[] getPrices() {
		return prices;
	}

	/**
	 * @return the initialized <code>Position</code>s <br>
	 * 
	 *         Note that each <Code>Portfolio</code> must contain at least 1 and at most <code>MAX_NUMBER_POSITIONS</code> <Code>Position</code>
	 */
	private List<Position> initializePositions() {
		int n = new Random().nextInt(1, MAX_NUMBER_POSITIONS + 1);
		List<Position> initializedPositions = new ArrayList<>(n);
		IntStream.range(0, n - 1).forEach(i -> initializedPositions.add(new Position(String.format("%s - Stock %d", name, i + 1))));
		return initializedPositions;
	}

	/**
	 * @return the historical prices of this <code>Portfolio</code> <br>
	 *         We assume that <code>Portfolio</code>s are evenly-weighted, so the price of the <code>Portfolio</code> as at T is simply the sum of the price of
	 *         all <code>Position</code>s as at T divided by the number of <code>Position</code>s
	 */
	public double[] initializePrices() {
		int n = PriceUtils.NUMBER_OF_HISTORICAL_PRICES;
		double[] initializedPrices = new double[n];

		for (int date = 0; date < n; date++) {
			double priceSum = 0;
			for (Position position : positions) {
				priceSum += position.getPrices()[date];
			}
			initializedPrices[date] = priceSum / n;
		}
		return initializedPrices;
	}

}
