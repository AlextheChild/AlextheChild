import os

os.environ["TF_ENABLE_ONEDNN_OPTS"] = "0"

import website as w
import display as d

#! grey color for padding


def main():
    """
    b for window
    r turn robot on/off
    p close
    """

    # w.open_window()
    d.display_ai_output()
    # w.close_window()


main()
