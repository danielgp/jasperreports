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
package net.sf.jasperreports.chartthemes.simple.handlers;

import org.exolab.castor.mapping.GeneralizedFieldHandler;


/**
 * @author Sanda Zaharia (shertage@users.sourceforge.net)
 * @deprecated To be removed.
 */
public class ImageAlignmentFieldHandler extends GeneralizedFieldHandler
{
	/**
	 *
	 */
	public ImageAlignmentFieldHandler()
	{
		super();
	}
	
	@Override
	public Object convertUponGet(Object value)
	{
		return ImageAlignmentSerializer.convert((Integer)value);
	}

	@Override
	public Object convertUponSet(Object value)
	{
		return ImageAlignmentDeserializer.convert((String)value);
	}
	
	@Override
	public Class<?> getFieldType()
	{
		return Integer.class;
	}

	@Override
	public Object newInstance(Object parent) throws IllegalStateException
	{
		//-- Since it's marked as a string...just return null,
		//-- it's not needed.
		return null;
	}
}
