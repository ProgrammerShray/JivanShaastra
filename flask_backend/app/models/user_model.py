from app.utils.db import get_db

class UserModel:

    def create_user(self, data):
        db = get_db()
        cursor = db.cursor()

        query = """
            INSERT INTO users (id, name, email, password, dob)
            VALUES (%s, %s, %s, %s, %s)
        """

        try:
            cursor.execute(
                query,
                (
                    data["id"],
                    data["name"],
                    data["email"],
                    data["password"],
                    data.get("dob")
                )
            )

            db.commit()
            cursor.close()
            return True

        except Exception as e:
            db.rollback()
            cursor.close()
            print("DB create_user error:", e)
            return False

    def get_user_by_email(self, email):
        db = get_db()
        cursor = db.cursor()

        cursor.execute(
            "SELECT id, name, email, password FROM users WHERE email = %s",
            (email,)
        )

        row = cursor.fetchone()
        cursor.close()

        if not row:
            return None

        return {
            "id": row[0],
            "name": row[1],
            "email": row[2],
            "password": row[3]
        }

    def get_user_by_id(self, user_id):
        db = get_db()
        cursor = db.cursor()

        cursor.execute(
            "SELECT id, name, email FROM users WHERE id = %s",
            (user_id,)
        )

        row = cursor.fetchone()
        cursor.close()

        if not row:
            return None

        return {
            "id": row[0],
            "name": row[1],
            "email": row[2]
        }
