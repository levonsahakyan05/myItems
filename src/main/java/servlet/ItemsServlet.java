package servlet;

import manager.ItemManager;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items")
public class ItemsServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = itemManager.getAll();
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/items.jsp").forward(req, resp);
    }
}
