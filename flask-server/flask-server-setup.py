# -*- coding: utf-8 -*-

from flask import Flask, render_template, url_for, request

app = Flask(__name__)

# display index.html
@app.route('/', methods=['GET'])
def index():
    return render_template('index.html')

# accept input images and save them to 'images' file
@app.route('/', methods=['POST'])
def predit():
    imagefile = request.files['imagefile']
    image_path = "./images/" + imagefile.filename
    imagefile.save(image_path)

    return render_template('index.html')


if __name__ == "__main__":
    app.run(port="8989", debug=True)