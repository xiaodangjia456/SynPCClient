package writeimage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import java.util.ArrayList;
import javax.swing.*;

class DrawPanel extends JPanel {
	DArray da = new DArray();
	Dshape tempshape;
	Color currColor;
	boolean fill = false;
	String model = new String();

	DrawPanel() {
		super();
		MA ma = new MA(this);
		this.addMouseListener(ma);
		MMA mma = new MMA(this);
		this.addMouseMotionListener(mma);
		// 设置监听器
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (Dshape ms : da.shapelist) {
			g2d.setColor(ms.c);
			if (ms.f && !ms.m.equals("Line"))
				g2d.fill(ms.s);
			else
				g2d.draw(ms.s);
		} // 绘制已记录的图形
		for (Dstr ms : da.strlist) {
			g2d.setColor(ms.c);
			g2d.setFont(ms.f);
			g2d.drawString(ms.s, ms.x, ms.y);
		} // 插入已记录的文本
		if (tempshape != null) {
			g2d.setColor(tempshape.c);
			if (tempshape.f && !tempshape.m.equals("Line"))
				g2d.fill(tempshape.s);
			else
				g2d.draw(tempshape.s);
		} // 记录当前绘制的图形（若非null）
	}

}
