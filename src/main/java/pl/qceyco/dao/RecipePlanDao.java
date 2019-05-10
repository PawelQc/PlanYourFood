package pl.qceyco.dao;

import pl.qceyco.model.RecipePlan;
import pl.qceyco.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecipePlanDao {

    private static final String GET_RECENT_PLAN_BY_ADMIN_ID = "SELECT day_name.id as day_id, meal_name,  recipe.id as recipe_id, plan_id FROM recipe_plan JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE recipe_plan.plan_id = (SELECT MAX(id) from plan WHERE admin_id = ?) ORDER by day_name.display_order, recipe_plan.display_order limit 1;";

    public RecipePlan getRecentRecipePlanByAdminiD(int adminId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RECENT_PLAN_BY_ADMIN_ID)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
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

}