import os
from PIL import Image

directory = os.listdir("cnn/data/images")

for file in directory:
    image = Image.open("cnn/data/images/" + file)
    image = image.transpose(Image.FLIP_LEFT_RIGHT)
    image.save("cnn/data/flipped_images/" + file)
