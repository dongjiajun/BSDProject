package com.example.bsdprojectone.model;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.widget.LinearLayout;


//��ʱ�Ȳ��ã���ǰ����Զ�ͨ�������Կ����Ż�
public class BrokenLinePic {
	
	public static GraphicalView graphicalView;//achartengine
	  /**
     * ��ȡ��������
     * @param size ���е���
     * @param values  y��ֵ
     * @return  ����x������   ��   ����y������
     */
    public static List<double[]> getlist(int size,List<String> values)
    {
        List<double[]> xy = new ArrayList<double[]>();
        
        double[] list =  new double[size];
        for(int i = 0 ; i < size ;i++)
        {
            list[i] = (values.isEmpty())? i : Double.valueOf(values.get(i));
        }
        
        xy.add(list);
        
        return xy;
    }
    
    
     /** 
       * ����XYMultipleSeriesRenderer. 
       *  
       * @param colors ÿ�����е���ɫ 
       * @param styles ÿ�����е������(����������,Բ��,����,����ȶ���) 
       *                            ( PointStyle.CIRCLE, PointStyle.DIAMOND,PointStyle.TRIANGLE, PointStyle.SQUARE )
       * @return XYMultipleSeriesRenderer 
       */  
    public static XYMultipleSeriesRenderer buildRenderer(int[] colors,PointStyle[] styles)
    {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        // ���ƺ�����������ִ�С
        renderer.setAxisTitleTextSize(15);
        // ���ƺ������ֵ��С
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setPointSize(5f);
        renderer.setMargins(new int[]
        { 20, 30, 15, 0 });
        int length = colors.length;
        for (int i = 0; i < length; i++)
        {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

     /** 
       * ����renderer��һЩ����������. 
       *  
       * @param renderer Ҫ���õ�renderer 
       * @param title ͼ����� 
       * @param xTitle X����� 
       * @param yTitle Y����� 
       * @param xMin X����Сֵ 
       * @param xMax X�����ֵ 
       * @param yMin Y����Сֵ 
       * @param yMax Y�����ֵ 
       * @param axesColor X����ɫ 
       * @param labelsColor Y����ɫ 
       */  
    public static void setChartSettings(XYMultipleSeriesRenderer renderer,
            String title, String xTitle, String yTitle, double xMin,
            double xMax, double yMin, double yMax, int axesColor,
            int labelsColor)
    {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
    }

     /** 
       * ������ʱ���йص�XYMultipleSeriesDataset,���������buildDataset�ڲ�������������ҪList<Date[]>������. 
       *  
       * @param titles ����ͼ�� 
       * @param xValues X��ֵ 
       * @param yValues Y��ֵ 
       * @return XYMultipleSeriesDataset 
       */  
    public static XYMultipleSeriesDataset buildDataset(String[] titles,
            List<double[]> xValues, List<double[]> yValues)
    {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++)
        {
            XYSeries series = new XYSeries(titles[i]);
            double[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++)
            {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
        return dataset;
    }
    
    
    
    /**
     * ����ͼ��
     * @param context  ��ǰ����
     * @param layout   ����ͼ�������
     * @param titles   ��������
     * @param colors   ������ɫ
     * @param data     y����������
     * @param xname    x������
     * @param yname    y������
     */
    public static void setchart(Context context,LinearLayout layout,String[] titles,int[] colors,
            List<String> data,String xname,String yname)
    {
        
        //��ȡx������
        List<double[]> x = getlist(data.size(), new ArrayList<String>());
        //��ȡy������
        List<double[]> values = getlist(data.size(),data);
        
        PointStyle[] styles = new PointStyle[] {PointStyle.CIRCLE};
        //����XYMultipleSeriesRenderer
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        
        setChartSettings(renderer, "", xname, yname, 0.5, 12.5, 0, 30,Color.LTGRAY, Color.LTGRAY);
        
        int length = renderer.getSeriesRendererCount();//��ȡ������
        for (int i = 0; i < length; i++) 
        {
              ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//����ͼ�ϵĵ�Ϊʵ��  
        }
        renderer.setXLabels(12);//����x����ʾ12����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��  
        renderer.setYLabels(10);//����y����ʾ10����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��  
        renderer.setShowGrid(true);//�Ƿ���ʾ����  
        renderer.setMarginsColor(Color.WHITE);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setXLabelsAlign(Align.RIGHT);//�̶�����̶ȱ�ע֮������λ�ù�ϵ  
        renderer.setYLabelsAlign(Align.CENTER);//�̶�����̶ȱ�ע֮������λ�ù�ϵ  
        renderer.setZoomButtonsVisible(true);//�Ƿ���ʾ�Ŵ���С��ť  
        renderer.setPanLimits(new double[] { -10, 300, -10, 300 }); //�����϶�ʱX��Y����������ֵ��Сֵ.  
        renderer.setZoomLimits(new double[] {  -10, 300, -10, 300 });//���÷Ŵ���СʱX��Y������������Сֵ. 
        
        //����view
        graphicalView= ChartFactory.getLineChartView(context,buildDataset(titles, x, values),renderer); 
        
        layout.addView(graphicalView);
    }
}
