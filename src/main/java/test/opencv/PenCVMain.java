package test.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class PenCVMain {
	public static void main(String[] args) {
		// 加载库
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// 载入原图
		Mat srcImage = Imgcodecs.imread("D:/CongJing/JEECG/WORK_SPACE/SynPCClient/src/main/java/test/images/timg2.jpg", Imgcodecs.IMREAD_LOAD_GDAL);// 也可以写图片的绝对路径
		//在已有的图像上画圆圈
//		Imgproc.circle(srcImage,new Point(50,50),40,new Scalar(255,0,0),2);
//	    Imgproc.circle(srcImage,new Point(50,100),80,new Scalar(0,255,0),5);
		// 显示原图
		Mat dst = dobj(srcImage);
		HighGui.imshow("原图", dst);
//		Mat dstImage = srcImage.clone();
//		Mat dilaImage = srcImage.clone();
//		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15));
//		// 进行腐蚀操作
//		Imgproc.erode(srcImage, dstImage, element);
//		// 进行膨胀操作
//		Imgproc.dilate(srcImage, dilaImage, element);
//		// 显示效果图
//		HighGui.imshow("腐蚀", dstImage);
//		HighGui.imshow("膨胀", dilaImage);
		
		HighGui.waitKey(0);
	}
	

	private static Mat dobj(Mat src) {
		Mat dst = src.clone();

		// 级联分类器类用于对象检测。
		CascadeClassifier objDetector = new CascadeClassifier("D:/Program Files/OpenCV/opencv/sources/data/haarcascades/haarcascade_eye.xml");
		MatOfRect objDetections = new MatOfRect();
		objDetector.detectMultiScale(dst, objDetections);
		if (objDetections.toArray().length <= 0) {
			return src;
		}
		for (Rect rect : objDetections.toArray()) {
				Imgproc.putText(dst, "eyes", new Point(rect.x, rect.y-5), Core.NORM_INF, 1, new Scalar(50, 50, 50), 1);
			
			Imgproc.rectangle(dst, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
		}
		return dst;
	}
}
