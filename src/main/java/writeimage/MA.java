package writeimage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import java.util.ArrayList;
import javax.swing.*;

class MA extends MouseAdapter {
	DrawPanel dp;

	MA(DrawPanel dp) {
		super();
		this.dp = dp;
	}

	@Override
	public void mousePressed(MouseEvent e) {// 鼠标按下
		if (dp.tempshape != null) {
			dp.da.shapelist.add(dp.tempshape);
		}
		switch (dp.model)
		// 据绘制模式对“当前绘制图形（tempshape）”进行初始化
		{
		case "Draw":
		case "Line": {
			dp.tempshape = new Dshape();
			Line2D l = new Line2D.Double();
			l.setLine(e.getX(), e.getY(), e.getX(), e.getY());
			dp.tempshape.c = dp.currColor;
			dp.tempshape.s = l;
			dp.tempshape.f = dp.fill;
			dp.tempshape.m = "Line";
			break;
		}
		case "Ellipse": {
			dp.tempshape = new Dshape();
			Ellipse2D ep = new Ellipse2D.Double();
			ep.setFrame(e.getX(), e.getY(), 0, 0);
			dp.tempshape.c = dp.currColor;
			dp.tempshape.s = ep;
			dp.tempshape.f = dp.fill;
			dp.tempshape.m = "Ellipse";
			break;
		}
		case "Rectangle": {
			dp.tempshape = new Dshape();
			Rectangle2D ra = new Rectangle2D.Double();
			ra.setFrame(e.getX(), e.getY(), 0, 0);
			dp.tempshape.c = dp.currColor;
			dp.tempshape.s = ra;
			dp.tempshape.f = dp.fill;
			dp.tempshape.m = "Rectangle";
			break;
		}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {// 鼠标松开
		switch (dp.model)
		// 完成tempshape的绘制
		{
		case "Draw":
		case "Line": {
			Line2D l = (Line2D) (dp.tempshape.s);
			l.setLine(l.getX1(), l.getY1(), e.getX(), e.getY());
			dp.repaint();
			break;
		}
		case "Ellipse": {
			Ellipse2D ep = (Ellipse2D) (dp.tempshape.s);
			ep.setFrame(ep.getX(), ep.getY(), e.getX() - ep.getX(), e.getY() - ep.getY());
			dp.repaint();
			break;
		}
		case "Rectangle": {
			Rectangle2D ra = (Rectangle2D) (dp.tempshape.s);
			ra.setFrame(ra.getX(), ra.getY(), e.getX() - ra.getX(), e.getY() - ra.getY());
			dp.repaint();
			break;
		}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {// 鼠标点击
		// 插入文本
		if (e.getButton() == MouseEvent.BUTTON3) {
			String inputValue = JOptionPane.showInputDialog("Please input text");
			Font f = new Font("宋体", 20, 20);
			Dstr ms = new Dstr();
			ms.s = inputValue;
			ms.c = dp.currColor;
			ms.f = f;
			ms.x = e.getX();
			ms.y = e.getY();
			dp.da.strlist.add(ms);
			dp.repaint();
		}

	}
}