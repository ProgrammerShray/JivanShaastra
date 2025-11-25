from app.models.user_model import UserModel
import bcrypt

class UserService:
    def create_user(self, data):
        name = data.get("name")
        email = data.get("email")
        password = data.get("password")
        dob = data.get("dob")  # NEW

        if not all([name, email, password, dob]):
            return {"success": False, "message": "Missing fields"}

        hashed_pw = bcrypt.hashpw(password.encode(), bcrypt.gensalt())

        user = UserModel()
        return user.create_user(name, email, hashed_pw, dob)

    def login_user(self, data):
        email = data.get("email")
        password = data.get("password")

        user = UserModel().get_user_by_email(email)
        if not user:
            return {"success": False, "message": "Invalid credentials"}

        if not bcrypt.checkpw(password.encode(), user["password"].encode()):
            return {"success": False, "message": "Invalid credentials"}

        return {
            "success": True,
            "message": "Login successful",
            "user": {
                "id": user["id"],
                "name": user["name"],
                "email": user["email"],
                "dob": str(user["dob"])  # NEW (optional)
            }
        }
