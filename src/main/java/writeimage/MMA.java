package writeimage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import java.util.ArrayList;
import javax.swing.*;

class MMA extends MouseMotionAdapter {
	private static final long serialVersionUID = 1L;
	DrawPanel dp;

	MMA(DrawPanel dp) {
		super();
		this.dp = dp;
	}

	@Override
	public void mouseDragged(MouseEvent e) {// 鼠标拖动
		// 仅自由绘画模式触发
		if (dp.model.equals("Draw")) {
			if (dp.tempshape != null) {
				Line2D l = (Line2D) (dp.tempshape.s);
				l.setLine(l.getX1(), l.getY1(), e.getX(), e.getY());
				dp.da.shapelist.add(dp.tempshape);
			}
			dp.tempshape = new Dshape();
			Line2D l = new Line2D.Double();
			l.setLine(e.getX(), e.getY(), e.getX(), e.getY());
			dp.tempshape.c = dp.currColor;
			dp.tempshape.s = l;
			dp.repaint();
		}
	}
}
