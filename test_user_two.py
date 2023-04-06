import numpy as np
from keras.preprocessing import image
from keras.applications import vgg16
import matplotlib.pyplot as plt
from pathlib import Path
#Image path 
path = Path("/Testings/ds_5.jpg")
#Load the image
img = image.load_img(path, target_size=(224,224))
image_array = image.img_to_array(img)
x_train = np.expand_dims(image_array, axis=0)
#Normalize the data
x_train = vgg16.preprocess_input(x_train)
data = plt.imread(path)
plt.imshow(data)
plt.show()
model = vgg16.VGG16(weights="imagenet")
prediction = model.predict(x_train)
pred = vgg16.decode_predictions(prediction)
print(pred)
     