import os
from PIL import Image

directory = os.listdir("cnn/data/test/images")

count = 0

for file in directory:
    image = Image.open("cnn/data/test/images/" + file)
    image.save("cnn/0data/test/images/" + str(count) + ".png")

    count += 1
