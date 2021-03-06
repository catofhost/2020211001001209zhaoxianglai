package com.zhaoxianglai.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class Category {

    private int categoryId;
    private String categoryName;
    private String description;
    private boolean active;

    public Category(){}

    public Category(int categoryId, String categoryName, String description, boolean active) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.active = active;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }

    //methods
    public static List<Category> findAllCategory(Connection con){
        List<Category> categoryList = new ArrayList<Category>();
        String sql = "SELECT * FROM Category";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getInt("CategoryId"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setDescription(rs.getString("Description"));
                categoryList.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categoryList;
    }

    public static String findByCategoryId(Connection con,int categoryId){
        String categoryName = null;
        String sql = "SELECT CategoryName FROM Category WHERE CategoryId=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                categoryName = rs.getString("CategoryName");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categoryName;
    }
}
