import cv2
from cv2 import aruco
import math

# get camera
camera = cv2.VideoCapture(0)

if not camera.isOpened():
    print("Cannot open camera. ")
    quit()

aruco_dict = aruco.getPredefinedDictionary(aruco.DICT_APRILTAG_16H5)
aruco_parameters = aruco.DetectorParameters()
aruco_detector = aruco.ArucoDetector(aruco_dict, aruco_parameters)

running = True
while running:
    image_received, img = camera.read()

    # get corners
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    corners, ids, rejectedImgPoints = aruco_detector.detectMarkers(gray)
    frame_markers = aruco.drawDetectedMarkers(img, corners, ids)

    # GUI
    frame_markers = cv2.flip(frame_markers, 1)
    cv2.imshow("april_tag", frame_markers)
    if cv2.waitKey(1) == ord("p"):
        camera.release()
        running = False

quit()