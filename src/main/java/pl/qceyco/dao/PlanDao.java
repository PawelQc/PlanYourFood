package pl.qceyco.dao;

import pl.qceyco.model.Plan;
import pl.qceyco.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    private static final String CREATE_PLAN = "INSERT INTO plan(name, description, created, admin_id) VALUES (?,?,NOW(),?);";
    private static final String DELETE_PLAN = "DELETE FROM plan WHERE id = ?;";
    private static final String GET_ALL_PLANS = "SELECT * FROM plan;";
    private static final String GET_PLAN_BY_ID = "SELECT * FROM plan WHERE id = ?;";
    private static final String UPDATE_PLAN = "UPDATE plan SET name = ? , description = ? WHERE id = ?;";
    private static final String GET_ALL_PLANS_BY_ADMIN_ID = "SELECT * FROM plan WHERE admin_id = ?;";
    private static final String COUNT_PLANS_BY_ADMIN_ID = "SELECT count(*) AS plany FROM plan WHERE admin_id=?;";


    public Plan createPlan(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_PLAN,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setInt(3, plan.getAdmin().getId());
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute updateRecipe returned " + result);
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePlan(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN)) {
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setInt(3, plan.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePlan(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN)) {
            statement.setInt(1, plan.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Plan getPlanById(int planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PLAN_BY_ID)) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    setPlanData(plan, resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    public List<Plan> getAllPlans() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_PLANS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Plan plan = new Plan();
                setPlanData(plan, resultSet);
                planList.add(plan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    public List<Plan> getAllPlansbyAdminId(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_PLANS_BY_ADMIN_ID)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Plan plan = new Plan();
                    setPlanData(plan, resultSet);
                    planList.add(plan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    public int countNumberOfPlansByAdminId(int adminId) {
        int numberOfPlans = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_PLANS_BY_ADMIN_ID)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    numberOfPlans = resultSet.getInt("plany");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfPlans;
    }

    private void setPlanData(Plan plan, ResultSet resultSet) throws SQLException {
        plan.setId(resultSet.getInt("id"));
        plan.setName(resultSet.getString("name"));
        plan.setDescription(resultSet.getString("description"));
        plan.setCreated(resultSet.getTimestamp("created"));
        plan.setAdminbyId(resultSet.getInt("admin_id"));
    }

}

