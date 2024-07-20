import os
from PIL import Image

sender_directory = os.listdir("models/cnn/data/new_images")

for file in sender_directory:
    image = Image.open("models/cnn/data/new_images/" + file)
    image = image.resize((2880, 1800))
    image.save("models/cnn/data/new_images/" + file)
