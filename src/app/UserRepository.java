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
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public Optional<User> findUserByEmail(String email) {
        return users.stream()
                .filter(u -> Objects.equals(u.getEmail(), email))
                .findFirst();
    }

    public Optional<List<User>> findAllUsers() {
        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(users);
        }
    }


    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", "alice@gmail.com"));
        UserRepository ur = new UserRepository(users);
        ur.findUserById(1).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("User not found by ID")
        );

        ur.findUserByEmail("alice@gmail.com").ifPresentOrElse(
                System.out::println,
                () -> System.out.println("User not found by email")
        );

        ur.findUserByEmail("ERROR").ifPresentOrElse(
                System.out::println,
                () -> System.out.println("User not found by email")
        );

        ur.findAllUsers().ifPresentOrElse(
                list -> list.forEach(System.out::println),
                () -> System.out.println("No users found")
        );
    }


}
