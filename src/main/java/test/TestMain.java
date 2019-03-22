package test;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * @author lijunming
 * @date 2018/7/22 下午6:00
 */
@SuppressWarnings("serial")
public class TestMain extends JPanel {

	private JPanel webBrowserPanel;
	private static JWebBrowser webBrowser;
	private static TrayIcon trayIcon = null;
	static JFrame frame = new JFrame("从晶科技-同步固件");
	static SystemTray tray = SystemTray.getSystemTray();
	private String url = "http://cjx.congjing.net";

	public TestMain() {
		super(new BorderLayout());
		webBrowserPanel = new JPanel(new BorderLayout());
		webBrowser = new JWebBrowser();
		webBrowser.navigate(url);
		webBrowser.setButtonBarVisible(false);
		webBrowser.setMenuBarVisible(false);
		webBrowser.setBarsVisible(false);
		webBrowser.setStatusBarVisible(false);
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		add(webBrowserPanel, BorderLayout.CENTER);
		// 执行Js代码
		// webBrowser.executeJavascript("alert('hello swing')");
	}

	/**
	 * 在swing里内嵌浏览器
	 * 
	 * @param url
	 *            要访问的url
	 * @param title
	 *            窗体的标题
	 */
	public void openForm(final String title) {
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TestMain tm = new TestMain();
				tm.initFrame(url, title);
			}
		});
		NativeInterface.runEventPump();
	}

	public void initFrame(String url, String title) {
		// 设置窗体关闭的时候不关闭应用程序
		URL ico = getClass().getResource("images/123.png"); //当前编译后class文件所在目录查找
		ImageIcon icon = new ImageIcon(ico);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new TestMain(), BorderLayout.CENTER);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationByPlatform(true);
		// 重置窗体大小
		frame.setResizable(true);
		// 设置窗体的宽度、高度
		frame.setSize(1400, 700);
		// 设置窗体居中显示
		frame.setLocationRelativeTo(frame.getOwner());
		frame.addWindowListener(new WindowAdapter() { // 窗口关闭事件
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			};

			@Override
			public void windowIconified(WindowEvent e) { // 窗口最小化事件
				frame.setVisible(false);
				TestMain tm = new TestMain();
				tm.miniTray();
			};
		});
		
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				//webBrowser.executeJavascript("alert('hello swing')");
				//System.out.println(e.getComponent());
			}
		});

		// 让窗体可见
		frame.setVisible(true);
	}

	private void miniTray() { // 窗口最小化到任务栏托盘
		URL ico = getClass().getResource("images/123.png"); //当前编译后class文件所在目录查找
		ImageIcon trayImg = new ImageIcon(ico);// 托盘图标
		PopupMenu pop = new PopupMenu(); // 增加托盘右击菜单
		MenuItem show = new MenuItem("还原");
		MenuItem exit = new MenuItem("退出");
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 按下还原键
				tray.remove(trayIcon);
				frame.setVisible(true);
				frame.setExtendedState(JFrame.NORMAL);
				frame.toFront();
			}
		});

		exit.addActionListener(new ActionListener() { // 按下退出键
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});

		pop.add(show);
		pop.add(exit);

		trayIcon = new TrayIcon(trayImg.getImage(), "从晶科技-同步固件", pop);
		trayIcon.setImageAutoSize(true);
		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { // 鼠标器双击事件
				if (e.getClickCount() == 2) {
					tray.remove(trayIcon); // 移去托盘图标
					frame.setVisible(true);
					frame.setExtendedState(JFrame.NORMAL); // 还原窗口
					frame.toFront();
				}
			}
		});

		try {
			tray.add(trayIcon);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {
//		openForm("http://cjx.congjing.net", "从晶科技-同步固件");
		TestMain tm = new TestMain();
		tm.openForm("从晶科技-同步固件");
	}
}
