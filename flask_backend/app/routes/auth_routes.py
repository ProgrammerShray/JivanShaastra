from flask import Blueprint, request, jsonify
from app.services.user_service import UserService

auth_bp = Blueprint("auth", __name__)
user_service = UserService()

@auth_bp.route("/signup", methods=["POST"])
def signup():
    data = request.json  # contains name, email, password, dob
    result = user_service.create_user(data)
    return jsonify(result)


@auth_bp.route("/login", methods=["POST"])
def login():
    data = request.json
    return jsonify(user_service.login_user(data))
