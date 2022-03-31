/**
 * 
 */
package app.form;

import java.util.List;

import app.model.Portfolio;
import app.model.Position;
import app.stats.VaRCalculator;

/**
 * @author kmeehan
 *
 */
public class VaRCalculatorForm {

	private Double[] confidenceLevels = VaRCalculator.CONFIDENCE_LEVELS;

	private Portfolio portfolio;
	private double confidenceLevel;

	private double portfolioVaR;
	private List<PositionVaR> positionVaRs;

	public void computeVaRs() {
		this.portfolioVaR = VaRCalculator.computePortfolioHistoricalDailyVaR(portfolio, confidenceLevel);
		this.positionVaRs = portfolio.getPositions().stream().map(p -> new PositionVaR(p, VaRCalculator.computePositionHistoricalDailyVaR(p, confidenceLevel))).toList();
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public double getConfidenceLevel() {
		return confidenceLevel;
	}

	public void setConfidenceLevel(double confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}

	public double getPortfolioVaR() {
		return portfolioVaR;
	}

	public void setPortfolioVaR(double portfolioVaR) {
		this.portfolioVaR = portfolioVaR;
	}

	public List<PositionVaR> getPositionVaRs() {
		return positionVaRs;
	}

	public void setPositionVaRs(List<PositionVaR> positionVaRs) {
		this.positionVaRs = positionVaRs;
	}

	private record PositionVaR(Position position, double valueAtRisk) {
	}

	public Double[] getConfidenceLevels() {
		return confidenceLevels;
	}

	public void setConfidenceLevels(Double[] confidenceLevels) {
		this.confidenceLevels = confidenceLevels;
	}

}
