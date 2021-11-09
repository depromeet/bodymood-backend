SELECT 'CREATE DATABASE local'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'local')\gexec
