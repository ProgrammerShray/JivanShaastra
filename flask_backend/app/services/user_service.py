from app.models.user_model import UserModel
import bcrypt

class UserService:

    def create_user(self, data):
        name = data.get("name")
        email = data.get("email")
        password = data.get("password")
        dob = data.get("dob")

        if not all([name, email, password, dob]):
            return {
                "success": False,
                "message": "Missing fields"
            }

        # Hash password (bytes → string)
        hashed_pw = bcrypt.hashpw(
            password.encode("utf-8"),
            bcrypt.gensalt()
        ).decode("utf-8")

        user_model = UserModel()
        return user_model.create_user(
            name=name,
            email=email,
            password=hashed_pw,
            dob=dob
        )

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
