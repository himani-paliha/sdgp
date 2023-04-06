 
# step 1 - Prepare a training dataset
from sklearn.datasets import make_blobs # example of creating a test dataset
X, y = make_blobs(n_samples=1000, centers=2, n_features=2, random_state=2) # create the inputs and outputs
#print(X.shape, y.shape) # summarize the shape of the arrays
#-------------------------------------------------------------------------------------------------------------------------------------
# step 2 - Fit a Model on the Training Dataset
# fit a logistic regression on the training dataset
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score
model = LogisticRegression(solver='lbfgs') # define model
model.fit(X, y) # fit model
yhat = model.predict(X) # make predictions
acc = accuracy_score(y, yhat) # evaluate predictions
#print(acc)
#---------------------------------------------------------------------------------------------------------------------------------------
# step 3 - Connect Predictions With Inputs to the Model
#new_input = [[2.12309797, -1.41131072]] # define a input
#new_output = model.predict(new_input) # get prediction for new input
#print(new_input, new_output) # summarize input and output
#---------------------------------------------------------------------------------------------------------------------------------------
# Multiple predictions
yhat = model.predict(X) # #make predictions on the entire training dataset
# connect predictions with outputs
for i in range(100):
    print(X[i], yhat[i])