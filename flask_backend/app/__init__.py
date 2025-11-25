from flask import Flask
from app.config import Config
from app.utils.db import get_db
from app.routes.auth_routes import auth_bp

def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)

    get_db(app)

    app.register_blueprint(auth_bp, url_prefix="/auth")

    return app
