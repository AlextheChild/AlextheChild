import tensorflow as tf
from tensorflow import keras
import numpy as np
from PIL import Image

# import pyautogui as robot
# import cv2
# import random

tf.function(reduce_retracing=True)

global model
model = keras.models.load_model("cnn/models/vgg_better_20_20.keras")


# ————— ccn functions ————— #


def prepare_cnn_image():
    image_resolution = 20  #! bullshit

    # edit image
    image = Image.open("screen.png")

    image = image.convert("RGB").resize(
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

    return padded_image


def get_cnn_prediction():
    balloon_positions = model.predict(prepare_cnn_image(), verbose=0)

    # scale the predictions back up to screen size
    for i in balloon_positions:
        i[0] *= 1920
        i[1] *= 1200
        i[2] *= 1920
        i[3] *= 1200

    return balloon_positions


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


# doesn't work
def get_pyautogui_prediction():
    try:
        pos = robot.locateOnScreen(
            "/Users/ashao26/Dropbox/Code/python/HusShaoAlexandreIntroPythonF/projects/balloon_popper/display_modules/py.png",
            confidence=0.6,
        )
        return [[pos[0], pos[1], pos[0] + pos[2], pos[3]]]
    except robot.ImageNotFoundException:
        return [[0, 0, 0, 0]]


# is stupid
def get_random_prediction(l, d):
    x = random.randint(l[0], l[0] + d[0])
    y = random.randint((900 - l[1]) // 2, l[1] + d[1] - 50)
    return [[x, y, x, y]]
"""
