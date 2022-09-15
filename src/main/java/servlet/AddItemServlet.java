package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddItemServlet extends HttpServlet {
        private CategoryManager categoryManager = new CategoryManager();

        private ItemManager itemManager = new ItemManager();
        private UserManager userManager = new UserManager();
        private static final String IMAGE_PATH = "C:\\Users\\Levon\\IdeaProjects\\myItems\\projectImages\\";

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Category> categories = categoryManager.getAll();
            req.setAttribute("category", categories);
            req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            User user = (User) req.getSession().getAttribute("user");
            String title = req.getParameter("title");
            double price = Double.parseDouble(req.getParameter("price"));
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            Part picUrlPart = req.getPart("picUrl");
            String fileName = null;
            if (picUrlPart != null) {
                long nanoTime = System.nanoTime();
                fileName = nanoTime + "_" + picUrlPart.getSubmittedFileName();
                picUrlPart.write(IMAGE_PATH + fileName);

                Item item = Item.builder()
                        .title(title)
                        .price(price)
                        .category(categoryManager.getById(categoryId))
                        .picUrl(fileName)
                        .user(userManager.getById(user.getId()))
                        .build();
                itemManager.add(item);
                resp.sendRedirect("/");
            }
        }

    }

