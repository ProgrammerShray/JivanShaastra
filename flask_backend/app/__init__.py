from flask import Flask
from flask_jwt_extended import JWTManager
from app.utils.db import close_db

def create_app():
    app = Flask(__name__)
    app.config.from_object("config.Config")

    # JWT setup
    jwt = JWTManager(app)

    app.teardown_appcontext(close_db)

    from app.routes.auth_routes import auth_bp
    app.register_blueprint(auth_bp, url_prefix="/auth")

    return app
