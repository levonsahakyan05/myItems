package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Item;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();

    public Category getById(int id) {
        String sql = "select * from Category where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return getCategoryFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getAll() {

        String sql = "select * from category";
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                categories.add(getCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
