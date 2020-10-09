package test.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * 检测图像人脸眼睛
 */
public class OpenCVMain1 {

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
			Imgproc.rectangle(dst, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
		}
		return dst;
	}

	public static void main(String[] args) {
		// 加载库
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// 载入原图
		Mat srcImage = Imgcodecs.imread("D:/CongJing/JEECG/WORK_SPACE/SynPCClient/src/main/java/test/images/timg2.jpg", Imgcodecs.IMREAD_LOAD_GDAL);// 也可以写图片的绝对路径
		Mat dst = dobj(srcImage);
		HighGui.imshow("识别后", dst);
		HighGui.waitKey(0);
	}
}
