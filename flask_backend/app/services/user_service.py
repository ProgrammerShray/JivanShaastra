from app.models.user_model import UserModel
import bcrypt

class UserService:

    def create_user(self, data):
        required = ["id", "name", "email", "password"]
        if not all(data.get(k) for k in required):
            return {"success": False, "message": "Missing fields"}

        # 🔐 HASH PASSWORD
        hashed_pw = bcrypt.hashpw(
            data["password"].encode("utf-8"),
            bcrypt.gensalt()
        ).decode("utf-8")

        data["password"] = hashed_pw

        created = UserModel().create_user(data)

        if not created:
            return {"success": False, "message": "User creation failed"}

        return {"success": True}

    def login_user(self, data):
        email = data.get("email")
        password = data.get("password")

        if not email or not password:
            return {"success": False, "message": "Email and password required"}

        user = UserModel().get_user_by_email(email)

        if not user:
            return {"success": False, "message": "Invalid credentials"}

        if not bcrypt.checkpw(
            password.encode("utf-8"),
            user["password"].encode("utf-8")
        ):
            return {"success": False, "message": "Invalid credentials"}

        return {
            "success": True,
            "user": {
                "id": user["id"],
                "name": user["name"],
                "email": user["email"]
            }
        }
