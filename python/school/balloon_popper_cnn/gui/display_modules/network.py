import tensorflow as tf
from tensorflow import keras
import cv2
import numpy as np
from PIL import Image

tf.function(reduce_retracing=True)

model = keras.models.load_model("cnn/models/vgg_good_20_20.keras")


def get_predictions():
    return get_selective_search_proposals()


# ————— ccn functions ————— #


def prepare_cnn_image():
    image_resolution = 20

    # edit image
    image = Image.open("screen.png")

    image = image.convert("RGBA").resize(
        [
            int(image.width * (2880 / 1920) // (image_resolution)),
            int(image.height * (1800 / 1200) // (image_resolution)),
        ]
    )
    image = np.asarray(image)[None, :]
    image = image.astype("float32") / 255

    # add padding
    shape = image.shape
    image = image.tolist()
    padded_image = np.zeros(
        [1, 1800 // image_resolution, 2880 // image_resolution, 3]
    ).tolist()
    for i in range(shape[1]):
        for j in range(shape[2]):
            padded_image[0][i][j] = image[0][i][j]
    padded_image = np.asarray(padded_image)

    return image


def get_cnn_prediction():
    balloon_positions = model.predict(prepare_cnn_image(), verbose=0)

    # scale the predictions back up to screen size
    for i in balloon_positions:
        i[0] *= 1920
        i[1] *= 1200
        i[2] *= 1920
        i[3] *= 1200

    return balloon_positions


# ————— yolo functions ————— #

"""
! get selective search boxes
! for the ones that are too big, maybe split them in half widthwise
! classify them
! apply NMS
"""


def get_selective_search_proposals():
    selective_search = cv2.ximgproc.segmentation.createSelectiveSearchSegmentation()
    selective_search.setBaseImage(prepare_cv2_image())
    selective_search.switchToSelectiveSearchFast()

    rects = selective_search.process()

    rects = [
        rects[0] * 20 * (1920 / 2880),
        rects[1] * 20 * (1200 / 1800),
        rects[2] * 20 * (1920 / 2880),
        rects[3] * 20 * (1200 / 1800),
    ]

    balloon_proposals = []

    for rect in rects:
        # too small
        if rect[2] < 200 or rect[3] < 200:
            continue

        balloon_proposals.append(rect)

    return balloon_proposals


def prepare_cv2_image():
    image_resolution = 20  #! bullshit

    # edit image
    image = Image.open("screen.png")

    image = image.convert("RGBA").resize(
        [
            int(image.width * (2880 / 1920) // (image_resolution)),
            int(image.height * (1800 / 1200) // (image_resolution)),
        ]
    )
    image = np.asarray(image)

    return image


# ————— substitute prediction functions ————— #


"""
# only works on stop signs
def get_haar_prediction():
    img = cv2.imread("outputs/screen.png")
    img_gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    # give cv2 data and get predictions
    balloon_data = cv2.CascadeClassifier("stop_data.xml")
    balloon_bounds = balloon_data.detectMultiScale(img_gray, minSize=(100, 100))

    balloon_positions = []
    for x, y, width, height in balloon_bounds:
        balloon_positions.append([x, y, x + width, y + height])

    return balloon_positions


# is stupid
def get_random_prediction(l, d):
    x = random.randint(l[0], l[0] + d[0])
    y = random.randint((900 - l[1]) // 2, l[1] + d[1] - 50)
    return [[x, y, x, y]]
"""
