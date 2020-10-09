package writeimage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import java.util.ArrayList;
import javax.swing.*;

public class DrawBoard extends JFrame {
	JPanel menu;
	JButton open, save, color, fill, bgc;
	JPanel tool;
	JButton draw, line, el, rect;
	DrawPanel dpl;
	JTextArea message;
	JScrollPane frame;

	DrawBoard(String title) {
		super();
		this.setTitle(title);
		menu = new JPanel(new GridLayout(1, 5));
		open = new JButton("Open");
		save = new JButton("Save");
		color = new JButton("Color");
		fill = new JButton("Fill");
		bgc = new JButton("BackgroundColor");
		menu.add(open);
		menu.add(save);
		menu.add(color);
		menu.add(fill);
		menu.add(bgc);
		tool = new JPanel(new GridLayout(4, 1));
		draw = new JButton("Draw");
		line = new JButton("Line");
		el = new JButton("Ellipse");
		rect = new JButton("Rectangle");
		tool.add(draw);
		tool.add(line);
		tool.add(el);
		tool.add(rect);
		dpl = new DrawPanel();
		String m = "Please click right mouse button where you wanna to input text\n";
		message = new JTextArea(m, 3, 5);
		frame = new JScrollPane(message);
		this.getContentPane().add("North", menu);
		this.getContentPane().add("West", tool);
		this.getContentPane().add("Center", dpl);
		this.getContentPane().add("South", frame);
		addli();
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	void addli() {
		MTLis m = new MTLis(this);
		//setUndecorated(true);//设置窗体的标题栏不可见
		setExtendedState(Frame.MAXIMIZED_BOTH);
		open.addActionListener(m);
		save.addActionListener(m);
		color.addActionListener(m);
		fill.addActionListener(m);
		bgc.addActionListener(m);
		draw.addActionListener(m);
		line.addActionListener(m);
		el.addActionListener(m);
		rect.addActionListener(m);
	}// 加入监听器

	public static void main(String[] arg) {
		DrawBoard d = new DrawBoard("Test");
	}
}