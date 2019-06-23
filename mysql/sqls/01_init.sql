DROP DATABASE IF EXISTS asakatu;
DROP USER IF EXISTS asakatu;
CREATE USER asakatu;
SET PASSWORD FOR asakatu = 'himitu';
CREATE DATABASE asakatu;
GRANT ALL ON asakatu.* TO asakatu;
