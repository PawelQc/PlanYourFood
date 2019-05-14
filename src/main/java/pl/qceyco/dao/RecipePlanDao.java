package pl.qceyco.dao;

import pl.qceyco.model.RecipePlan;
import pl.qceyco.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDao {

    private static final String GET_RECENT_PLAN_BY_ADMIN_ID = "SELECT recipe_plan.id, day_name.id as day_id, meal_name,  recipe.id as recipe_id, plan_id FROM recipe_plan JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE recipe_plan.plan_id = (SELECT MAX(id) from plan WHERE admin_id = ?) ORDER by day_name.display_order, recipe_plan.display_order limit 1;";
    private static final String GET_RECIPEPLAN_DETAILS = "SELECT recipe_plan.id, day_name.id as day_id, meal_name, recipe.id as recipe_id FROM `recipe_plan` JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE plan_id = ?  ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String CREATE_RECIPEPLAN = "INSERT INTO recipe_plan VALUES (null, ?, ?, ?, ?, ?);";
    private static final String DELETE_RECIPEPLAN = "DELETE FROM recipe_plan WHERE id = ?;";
    private static final String GET_RECIPEPLAN_BY_ID = "SELECT * FROM recipe_plan WHERE id = ?;";
    private static final String GET_ALL_RECIPEPLANS = "SELECT * FROM recipe_plan;";

    public RecipePlan getRecentRecipePlanByAdminId(int adminId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RECENT_PLAN_BY_ADMIN_ID)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipePlan.setId(resultSet.getInt("id"));
                    recipePlan.setDayNameById(resultSet.getInt("day_id"));
                    recipePlan.setMealName(resultSet.getString("meal_name"));
                    recipePlan.setRecipeById(resultSet.getInt("recipe_id"));
                    recipePlan.setPlanById(resultSet.getInt("plan_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipePlan;
    }

    public List<RecipePlan> getRecipePlanDetailAsList(int planId) {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RECIPEPLAN_DETAILS)) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    RecipePlan recipePlan = new RecipePlan();
                    recipePlan.setId(resultSet.getInt("id"));
                    recipePlan.setDayNameById(resultSet.getInt("day_id"));
                    recipePlan.setMealName(resultSet.getString("meal_name"));
                    recipePlan.setRecipeById(resultSet.getInt("recipe_id"));
                    recipePlanList.add(recipePlan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanList;
    }

    public RecipePlan createRecipePlan(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_RECIPEPLAN,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, recipePlan.getRecipe().getId());
            statement.setString(2, recipePlan.getMealName());
            statement.setInt(3, recipePlan.getDisplayOrder());
            statement.setInt(4, recipePlan.getDayName().getId());
            statement.setInt(5, recipePlan.getPlan().getId());
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute updateRecipe returned " + result);
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));
                    return recipePlan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteRecipePlan(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPEPLAN)) {
            statement.setInt(1, recipePlan.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RecipePlan getRecipePlanById(int recipePlanId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RECIPEPLAN_BY_ID)) {
            statement.setInt(1, recipePlanId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipePlan.setId(resultSet.getInt("id"));
                    recipePlan.setRecipeById(resultSet.getInt("recipe_id"));
                    recipePlan.setMealName(resultSet.getString("meal_name"));
                    recipePlan.setDisplayOrder(resultSet.getInt("display_order"));
                    recipePlan.setDayNameById(resultSet.getInt("day_name_id"));
                    recipePlan.setPlanById(resultSet.getInt("plan_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipePlan;
    }

    public List<RecipePlan> getAllRecipePlans() {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_RECIPEPLANS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                RecipePlan recipePlan = new RecipePlan();
                recipePlan.setId(resultSet.getInt("id"));
                recipePlan.setDayNameById(resultSet.getInt("day_name_id"));
                recipePlan.setMealName(resultSet.getString("meal_name"));
                recipePlan.setRecipeById(resultSet.getInt("recipe_id"));
                recipePlan.setPlanById(resultSet.getInt("plan_id"));
                recipePlanList.add(recipePlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanList;
    }


}
