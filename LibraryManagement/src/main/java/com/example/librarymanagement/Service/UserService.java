package com.example.librarymanagement.Service;

import com.example.librarymanagement.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        if (users.isEmpty())
            return null;

        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if  (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getByBalance(double balance) {
        ArrayList<User> newUsers = new ArrayList<>();

        for (User u : users) {
            if (u.getBalance() >= balance) {
                newUsers.add(u);
            }
        }

        if (newUsers.isEmpty())
            return null;

        return newUsers;
    }

    public ArrayList<User> getByAge(int age) {
        ArrayList<User> newUsers = new ArrayList<>();

        for (User u : users) {
            if (u.getAge() >= age) {
                newUsers.add(u);
            }
        }

        if (newUsers.isEmpty())
            return null;

        return newUsers;
    }
}
