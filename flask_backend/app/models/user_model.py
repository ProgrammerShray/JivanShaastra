from app.utils.db import get_db


class User:
    @staticmethod
    def create_table():
        db = get_db()
        cursor = db.cursor()
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                email VARCHAR(255) UNIQUE NOT NULL,
                password VARCHAR(255) NOT NULL,
                dob DATE NOT NULL
            );
        """)
        db.commit()
        cursor.close()


class UserModel:
    def create_user(self, name, email, password, dob):
        db = get_db()
        cursor = db.cursor()

        query = """
            INSERT INTO users (name, email, password, dob)
            VALUES (%s, %s, %s, %s)
            RETURNING id;
        """

        try:
            cursor.execute(query, (name, email, password.decode(), dob))
            db.commit()
            user_id = cursor.fetchone()[0]
            cursor.close()

            return {
                "success": True,
                "message": "User created",
                "user_id": user_id
            }

        except Exception as e:
            db.rollback()
            cursor.close()
            return {"success": False, "message": str(e)}

    def get_user_by_email(self, email):
        db = get_db()
        cursor = db.cursor()

        cursor.execute(
            "SELECT id, name, email, password, dob FROM users WHERE email = %s",
            (email,)
        )

        row = cursor.fetchone()
        cursor.close()

        if row:
            return {
                "id": row[0],
                "name": row[1],
                "email": row[2],
                "password": row[3],
                "dob": row[4]
            }

        return None
