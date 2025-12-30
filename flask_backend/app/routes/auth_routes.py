from flask import Blueprint, request, jsonify
from flask_jwt_extended import create_access_token
from app.services.user_service import UserService
from flask_jwt_extended import jwt_required, get_jwt_identity
from app.models.user_model import UserModel

auth_bp = Blueprint("auth", __name__)
user_service = UserService()


@auth_bp.route("/signup", methods=["POST"])
def signup():
    data = request.get_json()
    if not data:
        return jsonify({"success": False, "message": "Invalid JSON"}), 400

    result = user_service.create_user(data)
    return jsonify(result), 201 if result.get("success") else 400

@auth_bp.route("/login", methods=["POST"])
def login():
    data = request.get_json()

    user = user_service.login_user(data)

    if not user["success"]:
        return jsonify(user), 401

    token = create_access_token(identity=user["user"]["id"])

    return jsonify({
        "success": True,
        "token": token,
        "name": user["user"]["name"]
    })


@auth_bp.route("/me", methods=["GET"])
@jwt_required()
def me():
    user_id = get_jwt_identity()
    user = UserModel().get_user_by_id(user_id)

    if not user:
        return jsonify({"success": False}), 404

    return jsonify({
        "success": True,
        "user": user
    }), 200