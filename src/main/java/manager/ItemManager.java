package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Item;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();
    private static Connection connection = DBConnectionProvider.getINSTANCE().getConnection();

    public void add(Item item) {
        String sql = "insert into item(title,price,category_id,pic_url,user_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getPicUrl());
            ps.setInt(5, item.getUser().getId());

            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAll() {
        String sql = "select * from item limit 20";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getById(int id) {
        String sql = "select * from item where id  = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getByUserId(int id) {
        String sql = "select * from item where user_id = ?";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getByCategoryId(int id) {
        String sql = "select * from item where category_id = ? "
                + " limit 20";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void removeById(int itemId) {
        String sql = "delete from item where id = " + itemId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt("id"));
        item.setTitle(resultSet.getString("title"));
        item.setPrice(resultSet.getDouble("price"));
        int categoryId = resultSet.getInt("category_id");
        Category category = categoryManager.getById(categoryId);
        item.setPicUrl(resultSet.getString("pic_url"));
        item.setCategory(category);
        int userId = resultSet.getInt("user_id");
        User user = userManager.getById(userId);
        item.setUser(user);
        return item;
    }
}
