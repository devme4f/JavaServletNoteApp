# Java Servlet Note App

## Để deploy
1. Khởi chạy mariadb(port 3306)
2. Tạo user `devme@localhost` với password là `password` và grant privilege cho user đó nếu cần.
```sql
create user devme@localhost identified by 'password';
GRANT ALL PRIVILEGES ON *.* TO 'devme'@'localhost';
```
Nếu không, edit JDBC configuration ở file `ConnectionUtils`.

4. Chạy `sql.ini` file để tạo database, tables....

## To do
1. Tính năng update note, favorite, notification, handle non ascii characters trong note.
2. Tính năng login, register, session, upload file.
3. Filter input các parameters(XSS,....)
