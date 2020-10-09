package writeimage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import java.util.ArrayList;
import javax.swing.*;

class MTLis implements ActionListener {
	DrawBoard bo;

	MTLis(DrawBoard bo) {
		super();
		this.bo = bo;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource().getClass() == JButton.class) {
			JButton mid = (JButton) ae.getSource();
			if (mid.getText().equals("Open")) {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(bo);
				String s = jf.getSelectedFile().toString();
				File f = new File(s);
				try {
					read(f);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bo.dpl.repaint();
				bo.dpl.setBackground(bo.dpl.da.backColor);
				bo.message.append("File open " + s + "\n");
			} // 加载文件并将其绘制出来，同时设置背景色
			if (mid.getText().equals("Save")) {
				if (bo.dpl.tempshape != null)
					bo.dpl.da.shapelist.add(bo.dpl.tempshape);
				JFileChooser jf = new JFileChooser();
				int n = jf.showSaveDialog(bo);
				String s = jf.getSelectedFile().toString();
				File f = new File(s);
				try {
					write(f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bo.message.append("File save " + s + "\n");
			} // 记录当前绘制图形，序列化保存
			if (mid.getText().equals("Color")) {
				JColorChooser jc = new JColorChooser();
				Color c = jc.showDialog(bo, null, null);
				bo.dpl.currColor = c;
				bo.message.append("Color set " + c.toString() + "\n");
			} // 更改前端颜色
			if (mid.getText().equals("Fill")) {
				bo.dpl.fill = !bo.dpl.fill;
				bo.message.append("Fill change " + bo.dpl.fill + "\n");
			} // 更改填充状态
			if (mid.getText().equals("BackgroundColor")) {
				JColorChooser jc = new JColorChooser();
				Color c = jc.showDialog(bo, null, null);
				bo.dpl.setBackground(c);
				bo.dpl.da.backColor = c;
				bo.message.append("BackgroundColor set " + c.toString() + "\n");
			} // 更改背景色
			if (mid.getText().equals("Draw")) {
				bo.dpl.model = "Draw";
				bo.message.append("Model change " + bo.dpl.model + "\n");
			}
			if (mid.getText().equals("Line")) {
				bo.dpl.model = "Line";
				bo.message.append("Model change " + bo.dpl.model + "\n");
			}
			if (mid.getText().equals("Ellipse")) {
				bo.dpl.model = "Ellipse";
				bo.message.append("Model change " + bo.dpl.model + "\n");
			}
			if (mid.getText().equals("Rectangle")) {
				bo.dpl.model = "Rectangle";
				bo.message.append("Model change " + bo.dpl.model + "\n");
			} // 更改绘画模式
		}
	}

	void write(File f) throws IOException {
		FileOutputStream fo = new FileOutputStream(f);
		@SuppressWarnings("resource")
		ObjectOutputStream oo = new ObjectOutputStream(fo);
		oo.writeObject(bo.dpl.da);
	}// 序列化写

	void read(File f) throws IOException, ClassNotFoundException {
		FileInputStream fi = new FileInputStream(f);
		@SuppressWarnings("resource")
		ObjectInputStream oi = new ObjectInputStream(fi);
		DArray dp = (DArray) oi.readObject();
		bo.dpl.da = dp;
	}// 序列化读
}
