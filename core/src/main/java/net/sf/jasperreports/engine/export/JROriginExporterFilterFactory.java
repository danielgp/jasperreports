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
package net.sf.jasperreports.engine.export;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JasperReportsContext;


/**
 * Factory of {@link JROriginExporterFilter} instances.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @see JROriginExporterFilter#getFilter(JasperReportsContext, JRPropertiesMap, String)
 */
public class JROriginExporterFilterFactory implements ExporterFilterFactory
{

	@Override
	public ExporterFilter getFilter(JRExporterContext exporterContext)
	{
		JRAbstractExporter<?, ?, ?, ?> exporter = 
			exporterContext.getExporterRef() instanceof JRAbstractExporter<?, ?, ?, ?> 
			? (JRAbstractExporter<?, ?, ?, ?>)exporterContext.getExporterRef() 
			: null;
		if (exporter != null)
		{
			String originFilterPrefix = exporter.getExporterPropertiesPrefix() 
					+ JROriginExporterFilter.PROPERTY_EXCLUDE_ORIGIN_PREFIX;
			return 
				JROriginExporterFilter.getFilter(
					exporterContext.getJasperReportsContext(),
					exporterContext.getExportedReport().getPropertiesMap(), 
					originFilterPrefix
					);
		}
		return null;
	}

}
