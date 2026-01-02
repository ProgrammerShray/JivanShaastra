import uuid
from flask import Blueprint, request, jsonify
from flask_jwt_extended import (
    create_access_token,
    jwt_required,
    get_jwt_identity
)
from app.services.user_service import UserService
from app.models.user_model import UserModel

auth_bp = Blueprint("auth", __name__)
user_service = UserService()

#sign-up route
@auth_bp.route("/signup", methods=["POST"])
def signup():
    try:
        data = request.get_json()

        if not data:
            return jsonify({"success": False, "message": "Invalid JSON"}), 400

        data["id"] = str(uuid.uuid4())

        result = user_service.create_user(data)

        if not result.get("success"):
            return jsonify(result), 400

        return jsonify({
            "success": True,
            "message": "User registered successfully"
        }), 201

    except Exception as e:
        print("SIGNUP ROUTE ERROR:", e)
        return jsonify({
            "success": False,
            "message": "Server error"
        }), 500


#login route
@auth_bp.route("/login", methods=["POST"])
def login():
    data = request.get_json()

    user = user_service.login_user(data)

    if not user.get("success"):
        return jsonify(user), 401

    user_id = user["user"]["id"]

    if not user_id:
        return jsonify({
            "success": False,
            "message": "User ID missing"
        }), 500

    # 🔐 JWT with UUID identity
    token = create_access_token(identity=str(user_id))

    return jsonify({
        "success": True,
        "token": token,
        "name": user["user"]["name"]
    }), 200


#fetching user-name route
@auth_bp.route("/me", methods=["GET"])
@jwt_required()
def me():
    user_id = get_jwt_identity()

    if not user_id:
        return jsonify({"success": False, "message": "Invalid token"}), 401

    user = UserModel().get_user_by_id(user_id)

    if not user:
        return jsonify({"success": False, "message": "User not found"}), 404

    return jsonify({
        "success": True,
        "user": {
            "id": user["id"],
            "name": user["name"],
            "email": user["email"]
        }
    }), 200
