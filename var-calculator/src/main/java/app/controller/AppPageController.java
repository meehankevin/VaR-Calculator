/**
 * 
 */
package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.form.VaRCalculatorForm;
import app.model.Portfolio;
import app.stats.VaRCalculator;

/**
 * @author kmeehan
 *
 */
@Controller
public class AppPageController {

	@GetMapping("/index")
	public String varForm(Model model) {
		VaRCalculatorForm varForm = new VaRCalculatorForm();
		varForm.setPortfolio(new Portfolio("Main Portfolio"));
		varForm.setConfidenceLevel(VaRCalculator.CONFIDENCE_LEVELS[0]);
		varForm.computeVaRs();

		model.addAttribute("varForm", varForm);

		return "varForm";
	}

	@PostMapping("/index")
	public String varFormSubmit(@ModelAttribute VaRCalculatorForm form, Model model) {
		System.out.println("Submitten ptf = " + form.getPortfolio().getName());
		form.computeVaRs();
		model.addAttribute("varForm", form);

		return "result";
	}

}
