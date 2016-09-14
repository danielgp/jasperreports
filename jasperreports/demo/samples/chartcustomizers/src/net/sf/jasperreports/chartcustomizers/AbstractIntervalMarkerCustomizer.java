/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2016 TIBCO Software Inc. All rights reserved.
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
package net.sf.jasperreports.chartcustomizers;

import static net.sf.jasperreports.chartcustomizers.AbstractValueMarkerCustomizer.STROKE_DASHED;
import static net.sf.jasperreports.chartcustomizers.AbstractValueMarkerCustomizer.STROKE_DOTTED;
import static net.sf.jasperreports.chartcustomizers.AbstractValueMarkerCustomizer.STROKE_SOLID;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleInsets;

import net.sf.jasperreports.chartcustomizers.utils.ChartCustomizerUtils;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

/**
 * This customizer plots a line chart as spline.
 * 
 * @author gtoffoli
 */
public abstract class AbstractIntervalMarkerCustomizer implements JRChartCustomizer, ConfigurableChartCustomizer {

	protected Map<String, String> configuration = null;
    
    @Override
    public abstract void customize(JFreeChart jfc, JRChart jrc);

    @Override
    public void setConfiguration(Map<String, String> properties) {
        this.configuration = properties;
    }
    
    
    protected IntervalMarker createMarker()
    {
        Double startValue = ChartCustomizerUtils.asDouble(configuration.get("start-value"));
        Double endValue = ChartCustomizerUtils.asDouble(configuration.get("end-value"));
        
        if (startValue == null || endValue == null) return null;
        
        IntervalMarker marker = new IntervalMarker(startValue, endValue);
        
        if (configuration.containsKey("label"))
        {
            String text = (String) configuration.get("label");
            if (text != null && text.length() > 0)
            {
                marker.setLabel(text);
            }
        }
        
        // All cases..
        if (configuration.containsKey("label-position"))
        {
            RectangleAnchor position = ChartCustomizerUtils.getLabelAnchorByName((String) configuration.get("label-position") );
            
            if (position != null)
            {
                marker.setLabelAnchor(position);
            }
        }
        
        if (configuration.containsKey("label-position-offset"))
        {
            String offsets = (String)configuration.get("label-position-offset");
            
            String[] vals = offsets.split(",");
            System.out.println("Offsets: " + Float.parseFloat(vals[0]) + " " + Float.parseFloat(vals[1])+ " " +Float.parseFloat(vals[2])+ " " +Float.parseFloat(vals[3]));
            marker.setLabelOffset(new RectangleInsets(Float.parseFloat(vals[0]),Float.parseFloat(vals[1]),Float.parseFloat(vals[2]),Float.parseFloat(vals[3])));
        }
        
        if (configuration.containsKey("color"))
        {
            Color color = ChartCustomizerUtils.asColor(configuration.get("color"));
            if (color != null)
            {
                marker.setPaint( (Color)color);
            }
        }
        
        if (configuration.containsKey("alpha"))
        {
            Float alpha = ChartCustomizerUtils.asFloat(configuration.get("alpha"));
            if (alpha != null)
            {
                marker.setAlpha(alpha);
            }
        }
        
        BasicStroke basicStroke = new BasicStroke(1f);
        
        Float strokeWidth = 0f;
        
        if (configuration.containsKey("stroke-width"))
        {
            strokeWidth = ChartCustomizerUtils.asFloat((String)configuration.get("stroke-width") );
            
            if (strokeWidth == null) 
            {
                strokeWidth = 0f;
            }
            
            basicStroke = new BasicStroke(strokeWidth, basicStroke.getEndCap(), basicStroke.getLineJoin(), basicStroke.getMiterLimit(), basicStroke.getDashArray(), basicStroke.getDashPhase());
        }
        
        if (strokeWidth > 0)
        {
            if (configuration.containsKey("stroke-style"))
            {
                String strokeStyle = (String) configuration.get("stroke-style");

                if (STROKE_SOLID.equals(strokeStyle))
                {
                    basicStroke = new BasicStroke(basicStroke.getLineWidth(), basicStroke.getEndCap(), basicStroke.getLineJoin(), basicStroke.getMiterLimit(), new float[]{25.0f, 25.0f}, basicStroke.getDashPhase());
                }
                else if (STROKE_DOTTED.equals(strokeStyle))
                {
                    basicStroke = new BasicStroke(basicStroke.getLineWidth(), basicStroke.getEndCap(), basicStroke.getLineJoin(), basicStroke.getMiterLimit(), new float[]{1.0f, 1.0f}, basicStroke.getDashPhase());
                }
                else if (STROKE_DASHED.equals(strokeStyle))
                {
                    basicStroke = new BasicStroke(basicStroke.getLineWidth(), basicStroke.getEndCap(), basicStroke.getLineJoin(), basicStroke.getMiterLimit(), new float[]{10.0f, 10.0f}, basicStroke.getDashPhase());
                }
            }
            marker.setOutlineStroke(basicStroke);
            
            
            if (configuration.containsKey("outline-color"))
            {
                Color color = ChartCustomizerUtils.asColor(configuration.get("outline-color"));
                if (color != null)
                {
                    marker.setOutlinePaint( (Color)color);
                }
            }
        }
        
        return marker;
    }
    
    
    
}