import matplotlib.pyplot as plt
from skimage.io import imread
from skimage.transform import resize
import numpy as np
import tkinter as tk
from tkinter import filedialog

# Define the pre-trained model and categories
model = ... # Define the pre-trained model here
Categories = [...] # Define the list of categories here

# Open a file dialog to select an image file
root = tk.Tk()
root.withdraw()
file_path = filedialog.askopenfilename()

# Read the image from file and resize it
img = imread(file_path)
img_resize = resize(img, (150,150,3))

# Flatten the resized image and predict its category
l = [img_resize.flatten()]
probability = model.predict_proba(l)
for ind,val in enumerate(Categories):
    print(f'{val} = {probability[0][ind]*100:.2f}%')
print("The predicted image is : " + Categories[model.predict(l)[0]])
