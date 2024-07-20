import os
from PIL import Image

directory = os.listdir("models/cnn/data/flipped_images")

for file in directory:
    print(file)
    image = Image.open("models/cnn/data/flipped_images/23.png")
    image = image.convert("RGB")
    image.save("models/cnn/data/flipped_images/23.png")
