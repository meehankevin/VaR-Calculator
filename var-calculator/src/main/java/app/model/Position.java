/**
 * 
 */
package app.model;

import app.util.PriceUtils;

/**
 * @author kmeehan
 *
 */
public class Position {

	private final String name;
	private final double[] prices;

	public Position(String name) {
		this.name = name;
		this.prices = PriceUtils.buildRandomPrices();
	}

	public String getName() {
		return name;
	}

	public double[] getPrices() {
		return prices;
	}

}
