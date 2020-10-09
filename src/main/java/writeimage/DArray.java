package writeimage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import java.util.ArrayList;
import javax.swing.*;

class DArray implements Serializable {
	private static final long serialVersionUID = 1L;
	ArrayList<Dshape> shapelist = new ArrayList<Dshape>();
	ArrayList<Dstr> strlist = new ArrayList<Dstr>();
	Color backColor;// 背景色

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}
}