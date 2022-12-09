package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl  implements UserDao {

    Connection connection=getConnection();
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        String str="CREATE TABLE users (id INT AUTO_INCREMENT, name VARCHAR(40), lastName VARCHAR(40), age INT(3),PRIMARY KEY (id))";

        try(Statement statement=connection.createStatement()) {
            statement.execute(str);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("таблица уже создана");
        }
    }

    public void dropUsersTable() {
        String str="DROP TABLE users ";
        try(Statement statement=connection.createStatement()) {
            statement.execute(str);

        } catch (SQLException e) {
            System.out.println("таблица была удалена ранее");
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String str="INSERT INTO users (name, lastName, age) values('"+name+"','"+lastName+"','"+age+"')";

        try(Statement statement=connection.createStatement()) {
            statement.executeUpdate(str);
            System.out.println("User c именем  "+name+"  добавлен в базу данных");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("пользователь не добавлен");
        }

    }

    public void removeUserById(long id) {

        String str="DELETE FROM users WHERE Id = '"+id+"'";
        try(Statement statement=connection.createStatement()) {
            statement.executeUpdate(str);
            System.out.println("Пользователь удалён");
        } catch (SQLException e) {
            System.out.println("пользователь не удалён");
        }

    }

    public List<User> getAllUsers() {
      List<User> userList =new ArrayList<>();
        String str="SELECT * FROM users";

        try(Statement statement=connection.createStatement()) {
            ResultSet resultSet=statement.executeQuery(str);

            while (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("вывод все беда");
        }
        return userList;
    }

    public void cleanUsersTable() {

        String str="DELETE FROM users WHERE Id > 0";
        try(Statement statement=connection.createStatement()) {
            statement.executeUpdate(str);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("очищение таблицы беда");
        }

    }


}
