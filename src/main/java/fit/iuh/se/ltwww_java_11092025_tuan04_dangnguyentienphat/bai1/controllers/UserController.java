package fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.controllers;

import fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.daos.UserDAO;
import fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.daos.impl.UserDAOImpl;
import fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.entities.User;
import fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.entities.enums.Gender;
import fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@WebServlet(name = "userController", urlPatterns = {"/users", "/users/*"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "new":
                handleShowRegisterForm(req, resp);
                break;
            case "delete":
                handleDeleteUser(req, resp);
                break;
            case "edit":
                handleShowEditForm(req, resp);
                break;
            default:
                handleShowUser(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "insert":
                handleAddUser(req, resp);
                break;
            case "update":
                handleEditUser(req, resp);
                break;
            default:
                handleShowUser(req, resp);
                break;
        }
    }

    // Get method
    private void handleShowUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);
            List<User> listUser = userDao.findAll();

            req.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/user-list.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get method
    private void handleShowRegisterForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/user-add-form.jsp");
        dispatcher.forward(req, resp);
    }

    // Post method
    private void handleAddUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);

            String firstName = req.getParameter("txtFName");
            String lastName = req.getParameter("txtLName");
            String email = req.getParameter("txtEmail");
            String reEmail = req.getParameter("txtEmail");
            String password = req.getParameter("txtPassword");

            int day = Integer.parseInt(req.getParameter("day"));
            int month = Integer.parseInt(req.getParameter("month"));
            int year = Integer.parseInt(req.getParameter("year"));

            LocalDate dOb = LocalDate.of(year, month, day);
            Gender gender = req.getParameter("gender").equalsIgnoreCase(Gender.MALE.name())
                    ? Gender.MALE : Gender.FEMALE;

            User newUser = new User(firstName, lastName, email, password, dOb, gender);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violations = validator.validate(newUser);

            if (violations.isEmpty()) {
                userDao.save(newUser);
                resp.sendRedirect("users");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("views/user-add-form.jsp");

                StringBuilder stringBuilder = new StringBuilder();
                violations.forEach(violation -> {
                    stringBuilder.append(violation.getPropertyPath() + ": " + violation.getMessage());
                    stringBuilder.append("<br />");
                });

                req.setAttribute("user", newUser);
                req.setAttribute("errors", stringBuilder);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get method
    private void handleShowEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);
            int id = Integer.parseInt(req.getParameter("id"));
            User existingUser = userDao.findById(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/user-edit-form.jsp");
            req.setAttribute("user", existingUser);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Post method
    private void handleEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);
            int id = Integer.parseInt(req.getParameter("id"));

            String firstName = req.getParameter("txtFName");
            String lastName = req.getParameter("txtLName");
            String email = req.getParameter("txtEmail");
            String reEmail = req.getParameter("txtEmail");
            String password = req.getParameter("txtPassword");

            int day = Integer.parseInt(req.getParameter("day"));
            int month = Integer.parseInt(req.getParameter("month"));
            int year = Integer.parseInt(req.getParameter("year"));

            LocalDate dOb = LocalDate.of(year, month, day);
            Gender gender = req.getParameter("gender").equalsIgnoreCase(Gender.MALE.name())
                    ? Gender.MALE : Gender.FEMALE;

            User newUser = new User(id, firstName, lastName, email, password, dOb, gender);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violations = validator.validate(newUser);

            if (violations.isEmpty()) {
                userDao.update(newUser);
                resp.sendRedirect("users");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("views/user-edit-form.jsp");

                StringBuilder stringBuilder = new StringBuilder();
                violations.forEach(violation -> {
                    stringBuilder.append(violation.getPropertyPath() + ": " + violation.getMessage());
                    stringBuilder.append("<br />");
                });

                req.setAttribute("user", newUser);
                req.setAttribute("errors", stringBuilder);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);
            int id = Integer.parseInt(req.getParameter("id"));
            userDao.delete(id);
            resp.sendRedirect("users");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        EntityManagerFactoryUtil.close();
        super.destroy();
    }
}
