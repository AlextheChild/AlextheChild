import os

os.environ["TF_ENABLE_ONEDNN_OPTS"] = "0"

import website as w
import display as d

"""
! grey color for padding
! cv2 color masking
! there are a lot of magic variables/numbers in the gui code so maybe change that
! make framerate better
! clean up global variables
! yolo type network
! problem with this kind of network is that when the model is training you're basically making it guess which balloon you want from the same image
"""


def main():
    # w.open_window()
    d.display_ai_output()
    # w.close_window()
    os.remove("screen.png")


main()
