package service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.text.InternationalFormatter;

public class ValorFormato extends AbstractFormatterFactory{

	@Override
	public AbstractFormatter getFormatter(JFormattedTextField tf) {
		NumberFormat format = DecimalFormat.getInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		format.setRoundingMode(RoundingMode.HALF_UP);
		InternationalFormatter formatter = new InternationalFormatter(format);
		formatter.setAllowsInvalid(false);
		formatter.setMinimum(0.00);
		formatter.setMaximum(1000000.00);
		return formatter;
	}
};


