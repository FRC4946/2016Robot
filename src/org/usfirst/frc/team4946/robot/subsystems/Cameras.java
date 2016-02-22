package org.usfirst.frc.team4946.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.FlipAxis;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;

public class Cameras {

	int rearCam;
	int frontCam;
	Image frame;

	int curSession;

	public Cameras() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		rearCam = NIVision.IMAQdxOpenCamera("cam1",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(rearCam);

		curSession = rearCam;

//		frontCam = NIVision.IMAQdxOpenCamera("cam1",
//				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//		NIVision.IMAQdxConfigureGrab(frontCam);
	}

	public void setCam(boolean isFront) {
		if (isFront) {
//			curSession = frontCam;
		} else {
			curSession = rearCam;
		}

		startCam();
	}

	public void startCam() {
		NIVision.IMAQdxStartAcquisition(curSession);
	}

	public void updateCam() {
		NIVision.IMAQdxGrab(curSession, frame, 1);
		if (curSession == rearCam) {
			NIVision.imaqFlip(frame, frame, FlipAxis.HORIZONTAL_AXIS);
		}
		CameraServer.getInstance().setImage(frame);
	}

}
