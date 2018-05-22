package com.example.bsdprojectone.model;

import java.util.List;

import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.graphics.Paint.Align;

public class BarPic {

	//XYMultipleSeriesDataset dataset;
	//XYMultipleSeriesRenderer render;
	public static int[] COlORS={Color.RED};
	XYMultipleSeriesDataset dataset;
    XYMultipleSeriesRenderer renderer;
    public static Type type=Type.DEFAULT;
    
    public BarPic()
    {
    	//renderer=buildBarRenderer(COlORS);
        
    }
    public void Init()
    {
    	renderer.setZoomEnabled(false, false);//�ɹ�����--�ٺ� Color.GRAY, Color.LTGRAY
        setChartSettings(renderer, "���������ɶ�", "������", "����", 0, 15, 0, 105, Color.GRAY, Color.LTGRAY);
        //renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
        //renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
        renderer.setXLabels(0);//����x���ϵ��±�����
        renderer.setYLabels(0); //����y���ϵ��±�����
        //renderer.setShowGrid(true);  //������ʾ����(����Ч)
        renderer.setBarWidth(50);//�������ӿ��
        renderer.setXLabelsAlign(Align.CENTER);
        renderer.setYLabelsAlign(Align.LEFT);//y�� ���ֱ�ʾ�����껹���ұ�
        renderer.setPanEnabled(false, false);//�����Ƿ�����ƽ��
        //renderer.addXTextLabel(2.0, "������������");//��ָ�����괦��ʾ����
        for(int i = 1;i<=14;i++)
        {
        	renderer.addXTextLabel(i, i+"���");
        }
        for(int j = 1;j<=10;j++)
        {
        	renderer.addYTextLabel(j*10, j*10+"%");
        }
        
        //renderer.clearXTextLabels();//��� labels
        //renderer.setZoomRate(1.1f);//���÷�����
        renderer.setBarSpacing(1f);// ������״�ļ��
        
        //renderer.setLabelsTextSize(30);//���������������ֵĴ�С
        renderer.setXLabelsAngle(0.0f);//����������ת�Ƕ� ������˳ʱ����ת,x������
        renderer.setXLabelsPadding(10);//�������ֺ���ľ���
        
        //ת����simpleSeriesRenderer�����������Ϸ���ʾ��ֵ���������˵ڶ������͵����ݣ�����һ��Ҫ�Ķ�
        SimpleSeriesRenderer seriesrenderer =renderer.getSeriesRendererAt(0); 
        ((XYSeriesRenderer) seriesrenderer).setDisplayChartValues(true);
 
        
        
       /* SimpleSeriesRenderer seriesrenderer1 =renderer.getSeriesRendererAt(1); 
        ((XYSeriesRenderer) seriesrenderer1).setDisplayChartValues(true);*/
       
        
        renderer.setFitLegend(true);// �������ʵ�λ��
    }
    
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
    
	public void buildBarDataSet(String[] titles, List<double[]> values) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int seriesLength = v.length;//	5120.0,21251.0,25610.0
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
            }
            dataset.addSeries(series.toXYSeries());
        }
        this.dataset=dataset;
    }
	public XYMultipleSeriesDataset getBarDataset()
    {
    	return this.dataset;
    }
	
	public void buildBarRenderer(int []colors) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		     		  renderer.setAxisTitleTextSize(16);
		              renderer.setChartTitleTextSize(20);
		              renderer.setLabelsTextSize(15);
		              renderer.setLegendTextSize(15);
		              renderer.setMarginsColor(Color.WHITE); //������ɫ��Ҫ��Ϊ�������֣��߽���ɫ���в���ɫ
		              renderer.setBackgroundColor(Color.WHITE);
		              
		              int length = colors.length;
		               for (int i = 0; i < length; i++) {
		                 XYSeriesRenderer r = new XYSeriesRenderer();
		                 r.setColor(colors[i]);
		                 renderer.addSeriesRenderer(r);
		                }
		             this.renderer=renderer;
    }
	public XYMultipleSeriesRenderer getBarRenderer()
	{
		return this.renderer;
	}

}
