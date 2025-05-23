/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2025 Cloud Software Group, Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

/**
 * A component handler used while filling the report.
 * 
 * <p>
 * The fill component implementation is responsible for managing a component
 * at fill time.  A typical implementation would evaluate a set of expressions
 * and create a print element to be included in the generated report.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public interface FillComponent
{

	/**
	 * Initializes the fill component with the fill context.
	 * 
	 * <p>
	 * This method is called before the fill component is used.
	 * 
	 * @param fillContext the fill context
	 */
	void initialize(FillContext fillContext);
	
	/**
	 * Evaluates the fill component.
	 * 
	 * <p>
	 * This method would evaluate the component expressions and store the
	 * results to be used in {@link #fill()}.
	 * 
	 * <p>
	 * If the component needs to delay the evaluation of some of its
	 * expressions, it would call
	 * {@link FillContext#registerDelayedEvaluation(JRPrintElement, EvaluationTimeEnum, String)}
	 * to register a delayed evaluation print element, and perform the delayed
	 * evaluations on {@link #evaluateDelayedElement(JRPrintElement, byte)}.
	 * 
	 * @param evaluation the evaluation type
	 * @throws JRException
	 * @see FillContext#evaluate(net.sf.jasperreports.engine.JRExpression, byte)
	 */
	void evaluate(byte evaluation) throws JRException;

	/**
	 * @deprecated Replaced by {@link #prepare(int, boolean)}.
	 */
	FillPrepareResult prepare(int availableHeight);

	/**
	 * Prepares to fill the component by deciding whether the component will
	 * print, and how much vertical space it will require.
	 * 
	 * @param availableHeight the amount of vertical space available for the
	 * component, starting from the top of the component element.
	 * @param isOverflowAllowed flag indicating if overflow is allowed for the component.
	 * @return the result of the preparation, which specifies whether the
	 * component will print and how much it will stretch vertically.
	 */
	default FillPrepareResult prepare(int availableHeight, boolean isOverflowAllowed)
	{
		return prepare(availableHeight);
	}

	/**
	 * Fills the component by creating a print element which will be included
	 * in the generated report.
	 * 
	 * <p>
	 * This method will get called only if {@link #prepare(int)} returned
	 * a result that indicated that the component will print.
	 * 
	 * @return the print element generated by the component
	 */
	JRPrintElement fill();

	/**
	 * Rewinds the component.
	 * 
	 * <p>
	 * This method is called when filling the component has been canceled and
	 * is about to be restarted.  The component needs to reset its state
	 * (if any) to initial values.
	 */
	void rewind();

	/**
	 * Perform delayed evaluations and fill the print element with the
	 * resulting data.
	 * 
	 * @param element the print element for which delayed evaluation has
	 * been registered
	 * @param evaluation the evaluation type
	 * @throws JRException
	 * @see #evaluate(byte)
	 */
	void evaluateDelayedElement(JRPrintElement element, byte evaluation) throws JRException;

}
