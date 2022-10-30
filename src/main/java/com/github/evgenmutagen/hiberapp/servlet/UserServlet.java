package com.github.evgenmutagen.hiberapp.servlet;

import com.github.evgenmutagen.hiberapp.data.BaseConnection;
import com.github.evgenmutagen.hiberapp.model.Meal;
import com.github.evgenmutagen.hiberapp.model.Role;
import com.github.evgenmutagen.hiberapp.model.User;
import com.github.evgenmutagen.hiberapp.repository.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "userServlet", urlPatterns = "/")
public class UserServlet extends HttpServlet {

    private UserRepositoryInterface userRepository;
    private MealRepositoryInterface mealRepository;
    private RoleRepositoryInterface roleRepository;

    @Override
    public void init() {
        userRepository = new UserRepository(BaseConnection.getInstance());
        mealRepository = new MealRepository(BaseConnection.getInstance());
        roleRepository = new RoleRepository(BaseConnection.getInstance());
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        switch (action) {
            case ("/delete"):
                deleteAll(request);
                break;
            case ("/users"):
                findAll(request);
                break;
        }

        setRoleList(request);
        getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
    }

    private void setRoleList(HttpServletRequest request) throws SQLException {
        request.setAttribute("roleList", roleRepository.findAll());
    }

    private void findAll(HttpServletRequest req) throws SQLException {
        List<User> allUsers = userRepository.findAll();
        req.setAttribute("listUsers", allUsers);
    }

    private void deleteAll(HttpServletRequest req) throws SQLException {
        userRepository.deleteAll();
        findAll(req);
    }


    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        switch (action) {
            case ("/add"):
                create(request);
                break;
            case ("/get"):
                getUser(request);
                break;
            case ("/delete"):
                deleteUser(request);
                break;
            case ("/userMeals"):
                findAllMealsByUser(request);
                break;

        }
        setRoleList(request);
        if (action.equals("/userMeals")) {
            getServletContext().getRequestDispatcher("/usersMeals.jsp").forward(request, response);
        } else getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
    }

    private void getUser(HttpServletRequest req) throws SQLException {
        Optional<User> user = userRepository.findById(Integer.parseInt(req.getParameter("userId")));

        if (user.isPresent()) req.setAttribute("user", user.get());
        else req.setAttribute("user", "User doesn't exist");
    }

    private void findAllMealsByUser(HttpServletRequest request) throws SQLException {
        Optional<User> user = userRepository.findById(Integer.parseInt(request.getParameter("userId")));
        if (user.isPresent()) {
            List<Meal> listMeals = mealRepository.findAllMealsByUser(Integer.parseInt(request.getParameter("userId")));
            if (listMeals.size() != 0) request.setAttribute("mealList", listMeals);
            else request.setAttribute("mealList", "Meal doesn't exist");
            request.setAttribute("user", user.get());
        }
    }

    private void create(HttpServletRequest req) throws SQLException {
        Optional<Role> role = roleRepository.findById(Integer.parseInt(req.getParameter("role")));
        User user = User.builder()
                .name(req.getParameter("userId"))
                .email(req.getParameter("email"))
                .caloriesPerDay(Integer.parseInt(req.getParameter("caloriesPerDay")))
                .role(role.get())
                .build();
        userRepository.save(user);
        findAll(req);
        req.setAttribute("MessageUser", "User Add");
    }

    private void deleteUser(HttpServletRequest req) throws SQLException {
        userRepository.deleteById(Integer.parseInt(req.getParameter("userId")));
        findAll(req);
    }
}