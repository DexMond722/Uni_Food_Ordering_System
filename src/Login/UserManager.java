/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desmondcwf
 */
public class UserManager {

    private List<User> users;
    private static final String FILE_PATH = "src/login/users.txt";

    public UserManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                users.add(new User(data[0], data[1], data[2]));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    private void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean deleteUser(String username) {
        User userToDelete = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userToDelete = user;
                break;
            }
        }
        if (userToDelete != null) {
            users.remove(userToDelete);
            saveUsers();
            return true;
        }
        return false;
    }

    public void updateUser(String oldUsername, String newUsername, String newPassword, String newRole) {
        User userToUpdate = getUserByUsername(oldUsername);
        if (userToUpdate != null) {
            userToUpdate.setUsername(newUsername);
            userToUpdate.setPassword(newPassword);
            userToUpdate.setRole(newRole);
            saveUsers();
        }
    }
}
