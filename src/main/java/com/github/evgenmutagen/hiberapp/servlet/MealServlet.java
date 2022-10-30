package com.github.evgenmutagen.hiberapp.servlet;

import com.github.evgenmutagen.hiberapp.data.BaseConnection;
import com.github.evgenmutagen.hiberapp.model.Meal;
import com.github.evgenmutagen.hiberapp.repository.MealRepository;
import com.github.evgenmutagen.hiberapp.repository.MealRepositoryInterface;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.util.Optional;

@WebServlet(name = "mealServlet", urlPatterns = "/meals")
public class MealServlet extends HttpServlet {
    private MealRepositoryInterface mealRepository;

    @Override
    public void init() {
        mealRepository = new MealRepository(BaseConnection.getInstance());
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("listMeals", mealRepository.findAll());

        getServletContext().getRequestDispatcher("/meal.jsp").forward(request, response);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Optional<Meal> meal = mealRepository.findById(Integer.parseInt(request.getParameter("mealId")));
        request.setAttribute("listMeals", mealRepository.findAll());

        if (meal.isPresent()) {
            request.setAttribute("meal", meal.get());
        } else request.setAttribute("meal", "Meal doesn't exist");
        getServletContext().getRequestDispatcher("/meal.jsp").forward(request, response);
    }
}