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
    	renderer.setZoomEnabled(false, false);//成功控制--嘿嘿 Color.GRAY, Color.LTGRAY
        setChartSettings(renderer, "巷道任务完成度", "巷道编号", "进度", 0, 15, 0, 105, Color.GRAY, Color.LTGRAY);
        //renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
        //renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
        renderer.setXLabels(0);//设置x轴上的下标数量
        renderer.setYLabels(0); //设置y轴上的下标数量
        //renderer.setShowGrid(true);  //设置显示网格(不生效)
        renderer.setBarWidth(50);//设置柱子宽度
        renderer.setXLabelsAlign(Align.CENTER);
        renderer.setYLabelsAlign(Align.LEFT);//y轴 数字表示在坐标还是右边
        renderer.setPanEnabled(false, false);//设置是否允许平移
        //renderer.addXTextLabel(2.0, "马赛克马赛克");//在指定坐标处显示文字
        for(int i = 1;i<=14;i++)
        {
        	renderer.addXTextLabel(i, i+"巷道");
        }
        for(int j = 1;j<=10;j++)
        {
        	renderer.addYTextLabel(j*10, j*10+"%");
        }
        
        //renderer.clearXTextLabels();//清除 labels
        //renderer.setZoomRate(1.1f);//设置放缩比
        renderer.setBarSpacing(1f);// 设置柱状的间距
        
        //renderer.setLabelsTextSize(30);//设置坐标轴上数字的大小
        renderer.setXLabelsAngle(0.0f);//设置文字旋转角度 对文字顺时针旋转,x轴文字
        renderer.setXLabelsPadding(10);//设置文字和轴的距离
        
        //转换成simpleSeriesRenderer才能在柱子上方显示数值。如果添加了第二个类型的数据，这里一定要改动
        SimpleSeriesRenderer seriesrenderer =renderer.getSeriesRendererAt(0); 
        ((XYSeriesRenderer) seriesrenderer).setDisplayChartValues(true);
 
        
        
       /* SimpleSeriesRenderer seriesrenderer1 =renderer.getSeriesRendererAt(1); 
        ((XYSeriesRenderer) seriesrenderer1).setDisplayChartValues(true);*/
       
        
        renderer.setFitLegend(true);// 调整合适的位置
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
		              renderer.setMarginsColor(Color.WHITE); //设置颜色需要分为两个部分，边界颜色和中部颜色
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
