import os
import psycopg2
from flask import g
from urllib.parse import urlparse


def get_db():
    if "db" not in g:
        database_url = os.environ.get("DATABASE_URL")

        if not database_url:
            raise RuntimeError("DATABASE_URL environment variable is not set")

        url = urlparse(database_url)

        g.db = psycopg2.connect(
            dbname=url.path[1:],      # remove leading /
            user=url.username,
            password=url.password,
            host=url.hostname,
            port=url.port,
            sslmode="require"         # REQUIRED for Neon
        )

    return g.db


def close_db(e=None):
    db = g.pop("db", None)
    if db is not None:
        db.close()
