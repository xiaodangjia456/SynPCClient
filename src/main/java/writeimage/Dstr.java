package writeimage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import java.util.ArrayList;
import javax.swing.*;

class Dstr implements Serializable {
	private static final long serialVersionUID = 1L;
	String s;
	Font f;
	Color c;
	int x, y;// 坐标

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}
}