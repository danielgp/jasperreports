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
package net.sf.jasperreports.engine.part;

import net.sf.jasperreports.engine.fill.JRFillCloneFactory;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;

/**
 * A factory of part fill component instances.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @see PartComponentManager
 * @see PartFillComponent
 */
public interface PartComponentFillFactory
{

	/**
	 * Creates a fill component instance for a component.
	 * 
	 * @param component the component
	 * @param factory the fill objects factory
	 * @return the fill component instance
	 */
	PartFillComponent toFillComponent(PartComponent component, JRFillObjectFactory factory);

	/**
	 * Creates a clone of a fill component.
	 * 
	 * <p>
	 * Fill components clones are currently only created when the component
	 * element is placed inside a crosstab.
	 * 
	 * @param component the fill component
	 * @param factory the clone factory
	 * @return a clone of the fill component
	 */
	PartFillComponent cloneFillComponent(PartFillComponent component, JRFillCloneFactory factory);
	
}
