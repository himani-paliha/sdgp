from skimage.feature import hog
from sklearn.preprocessing import StandardScaler
from sklearn.svm import SVC
import cv2

# Load the pre-trained SVM model
svm_model = SVC(kernel='linear', C=1, gamma='auto')
svm_model.load('svm_model.pkl')  # replace with the path to your saved SVM model

# Load and preprocess the image
image = cv2.imread('test_image.jpg')  # replace with the path to your image
image_gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
image_hog = hog(image_gray, orientations=9, pixels_per_cell=(8, 8), cells_per_block=(2, 2))
image_features = StandardScaler().fit_transform(image_hog.reshape(1, -1))

# Use the SVM model to predict the class of the new image
predicted_class = svm_model.predict(image_features)

print('The predicted class label is:', predicted_class)
