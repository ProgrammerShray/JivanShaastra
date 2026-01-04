from flask import Blueprint, request, jsonify
from flask_jwt_extended import create_access_token, jwt_required, get_jwt_identity
from app.services.user_service import UserService
from app.models.user_model import UserModel
import uuid

auth_bp = Blueprint("auth", __name__)
user_service = UserService()

@auth_bp.route("/signup", methods=["POST"])
def signup():
    data = request.get_json()

    if not data:
        return jsonify({"success": False, "message": "Invalid JSON"}), 400

    data["id"] = str(uuid.uuid4())  # ✅ UUID GENERATED HERE

    result = user_service.create_user(data)

    if not result["success"]:
        return jsonify(result), 400

    return jsonify({
        "success": True,
        "message": "User registered successfully"
    }), 201


@auth_bp.route("/login", methods=["POST"])
def login():
    data = request.get_json()

    result = user_service.login_user(data)

    if not result["success"]:
        return jsonify(result), 401

    token = create_access_token(identity=result["user"]["id"])

    return jsonify({
        "success": True,
        "token": token,
        "user": result["user"]
    }), 200


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
