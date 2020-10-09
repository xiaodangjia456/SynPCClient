package writeimage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import java.util.ArrayList;
import javax.swing.*;

class Dshape implements Serializable {
	private static final long serialVersionUID = 1L;
	Shape s;
	Color c;
	boolean f;// 填充状态
	String m = new String();// 绘画模式

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}
}