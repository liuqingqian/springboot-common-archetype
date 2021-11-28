package ${package}.common;

import ${package}.common.dto.UserDTO;

public class UserHolder {

    private UserHolder(){}

    private static ThreadLocal<UserDTO> user = new ThreadLocal<>();

    public static void setUser(UserDTO u) {
        user.set(u);
    }

    public static UserDTO getUser() {
        return user.get();
    }

    public static void removeUser() {
        user.remove();
    }
}
