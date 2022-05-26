from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import Bcrypt
from flask_login import LoginManager

app = Flask(__name__, template_folder="templates", static_folder="static")

app.config["SQLALCHEMY_DATABASE_URI"] = "mysql+pymysql://admin:CoinGecko2022@172.17.0.1:3306/coingecko_db"
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config["SECRET_KEY"] = "14fb3188089cdac6ba30ddcb"

# SQLAlchemy
db = SQLAlchemy(app)

# Bcrypt
bcrypt = Bcrypt(app)

login_man = LoginManager(app)

from Gecko import routes