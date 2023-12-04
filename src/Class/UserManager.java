/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desmondcwf
 */
public class UserManager {

    private List<User> users;
    private static final String FILE_PATH = "src/Database/users.txt";
    private int lastid;

    public UserManager() {
        users = new ArrayList<>();
        loadUsers();
        lastid = calculateLastUserId();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    private int calculateLastUserId() {
        if (users.isEmpty()) {
            return 0;
        } else {
            return users.stream().mapToInt(user -> user.getId()).max().orElse(0);
        }
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String username = data[1];
                String password = data[2];
                String role = data[3];
                double credit = Double.parseDouble(data[4]);
                users.add(new User(id, username, password, role, credit));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        int newUserId = ++lastid;
        user.setId(newUserId);

        if ("vendor".equalsIgnoreCase(user.getRole())) {
            VendorMenu.createVendorMenu(user.getUsername());
        }
        users.add(user);
        saveUsers();
    }

    public void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                bw.write(user.getId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "," + user.getCredit());
                bw.newLine();
            }
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

    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public int getUserIdByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getId();
            }
        }
        return -1; // Return -1 if the username is not found
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
            if ("vendor".equalsIgnoreCase(userToDelete.getRole())) {
                VendorMenu.deleteVendorMenu(userToDelete.getUsername());
            }
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

    public int getNextUserId() {
        return lastid + 1;
    }

}
