from app.utils.db import get_db


class User:
    @staticmethod
    def create_table():
        db = get_db()
        cursor = db.cursor()
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                email VARCHAR(255) UNIQUE NOT NULL,
                password VARCHAR(255) NOT NULL,
                dob DATE NOT NULL
            );
        """)
        db.commit()

class UserModel:
    def create_user(self, name, email, password, dob):
      
        db = get_db()
        cursor = db.cursor(dictionary=True)


        query = """
            INSERT INTO users (name, email, password, dob)
            VALUES (%s, %s, %s, %s)
        """
        try:
            cursor.execute(query, (name, email, password.decode(), dob))
            db.commit()
            return {"success": True, "message": "User created"}
        except Exception as e:
            return {"success": False, "message": str(e)}

    def get_user_by_email(self, email):
        db = get_db()
        cursor = db.cursor(dictionary=True)
        cursor.execute("SELECT * FROM users WHERE email=%s", (email,))
        return cursor.fetchone()