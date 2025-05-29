package app;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class UserRepository {

    private final List<User> users;

    public UserRepository(List<User> users) {
        this.users = users;
    }


    public Optional<User> findUserById(int id) {
        Optional<User> userOpt = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        userOpt.ifPresentOrElse(System.out::println, () -> System.out.println("Id not found."));
        return userOpt;
    }

    public Optional<User> findUserByEmail(String email) {
        Optional<User> userOpt = users.stream()
                .filter(u -> Objects.equals(u.getEmail(), email))
                .findFirst();

        userOpt.ifPresentOrElse(System.out::println, () -> System.out.println("E-Mail not found."));
        return userOpt;
    }

    public Optional<List<User>> findAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return Optional.empty();
        } else {
            System.out.println("The amount of users is: " + users.size());
            return Optional.of(users);
        }
    }


    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", "alice@gmail.com"));
        UserRepository ur = new UserRepository(users);
        ur.findUserById(1);
        ur.findUserByEmail("alice@gmail.com");
        ur.findUserByEmail("ERROR");
        ur.findAllUsers();

    }


}
