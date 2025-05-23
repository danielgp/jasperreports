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
package net.sf.jasperreports.engine.fonts;

import java.util.List;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class FontSetInfo
{

	private FontSet fontSet;
	private FontSetFamilyInfo primaryFamily;
	private List<FontSetFamilyInfo> families;
	
	public FontSetInfo(FontSet fontSet, FontSetFamilyInfo primaryFamily, List<FontSetFamilyInfo> families)
	{
		this.fontSet = fontSet;
		this.primaryFamily = primaryFamily;
		this.families = families;
	}

	public FontSet getFontSet()
	{
		return fontSet;
	}

	public FontSetFamilyInfo getPrimaryFamily()
	{
		return primaryFamily;
	}
	
	public List<FontSetFamilyInfo> getFamilies()
	{
		return families;
	}
	
	@Override
	public String toString()
	{
		return "font set " + (fontSet == null ? null : fontSet.getName());
	}
}
