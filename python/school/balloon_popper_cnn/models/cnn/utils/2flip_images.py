import os
from PIL import Image

directory = os.listdir("models/cnn/data/images")

for file in directory:
    image = Image.open("models/cnn/data/images/" + file)
    image = image.transpose(Image.FLIP_LEFT_RIGHT)
    image.save("models/cnn/data/flipped_images/" + file)
