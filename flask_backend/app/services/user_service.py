from app.models.user_model import UserModel
import bcrypt
from app.utils.db import get_db

class UserService:

    # register_user service
    def create_user(self, data):
        try:
            query = """
                INSERT INTO users (id, name, email, password, dob)
                VALUES (%s, %s, %s, %s, %s)
            """

            values = (
                data["id"],
                data["name"],
                data["email"],
                data["password"],
                data.get("dob")
            )

            UserModel().create_user(query, values)

            return {"success": True}

        except Exception as e:
            print("Signup error:", e)
            return {
                "success": False,
                "message": "User creation failed"
            }

    # login_user service
    def login_user(self, data):
        email = data.get("email")
        password = data.get("password")

        if not email or not password:
            return {
                "success": False,
                "message": "Email and password required"
            }

        user = UserModel().get_user_by_email(email)

        if not user:
            return {
                "success": False,
                "message": "Invalid credentials"
            }

        # Compare passwords safely
        password_matches = bcrypt.checkpw(
            password.encode("utf-8"),
            user["password"].encode("utf-8")
        )

        if not password_matches:
            return {
                "success": False,
                "message": "Invalid credentials"
            }

        return {
            "success": True,
            "message": "Login successful",
            "user": {
                "id": user["id"],
                "name": user["name"],
                "email": user["email"]
            }
        }
