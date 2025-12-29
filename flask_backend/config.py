import os

class Config:
    DATABASE_URL = os.getenv("DATABASE_URL")
    JWT_SECRET_KEY = os.getenv("JWT_SECRET_KEY")

    if DATABASE_URL and DATABASE_URL.startswith("psql://"):
        DATABASE_URL = DATABASE_URL.replace("psql://", "postgresql://", 1)
