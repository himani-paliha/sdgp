from flask import Flask

test = Flask(__name__)

@test.route('/')
def index():
    return "Hello World"


if __name__=="__main__":
    test.run(port=1234)