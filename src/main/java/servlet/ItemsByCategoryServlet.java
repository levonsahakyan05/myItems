package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ItemsByCategoryId")
public class ItemsByCategoryServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = itemManager.getByCategoryId(Integer.parseInt(req.getParameter("category_id")));
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/items.jsp").forward(req, resp);

    }


}

