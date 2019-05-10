package pl.qceyco.dao;

import pl.qceyco.model.Recipe;
import pl.qceyco.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE = "INSERT INTO recipe (name, ingredients, description, created, updated, preparation_time, preparation, admin_id) VALUES (?, ?, ?, now(), now(),?, ?, ?)";
    private static final String DELETE_RECIPE = "DELETE FROM recipe WHERE id = ?;";
    private static final String GET_ALL_RECIPES = "SELECT * FROM recipe;";
    private static final String GET_ALL_RECIPES_BY_ADMIN_ID = "SELECT * FROM recipe WHERE admin_id = ?;";
    private static final String GET_RECIPE_BY_ID = "SELECT * FROM recipe WHERE id = ?;";
    private static final String UPDATE_RECIPE = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, updated = now(), preparation_time = ?, preparation = ?, admin_id = ? WHERE	id = ?;";
    private static final String COUNT_RECIPES_BY_ADMIN_ID = "SELECT COUNT(*) AS przepisy FROM recipe WHERE admin_id=?;";


    public Recipe createRecipe(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_RECIPE,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            getRecipeData(recipe, statement);
            int result = statement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute updateRecipe returned " + result);
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateRecipe(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE)) {
            statement.setInt(7, recipe.getId());
            getRecipeData(recipe, statement);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRecipe(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE)) {
            statement.setInt(1, recipe.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Recipe getRecipeById(int recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RECIPE_BY_ID)) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    setRecipeData(recipe, resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_RECIPES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                setRecipeData(recipe, resultSet);
                recipeList.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public List<Recipe> getAllRecipesByAdminId(int adminID) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_RECIPES_BY_ADMIN_ID)) {
            statement.setInt(1, adminID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipe = new Recipe();
                    setRecipeData(recipe, resultSet);
                    recipeList.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public int countNumberOfRecipesByAdminId(int adminId) {
        int numberOfRecipes = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_RECIPES_BY_ADMIN_ID)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    numberOfRecipes = resultSet.getInt("przepisy");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfRecipes;
    }

    private void setRecipeData(Recipe recipe, ResultSet resultSet) throws SQLException {
        recipe.setId(resultSet.getInt("id"));
        recipe.setName(resultSet.getString("name"));
        recipe.setIngredients(resultSet.getString("ingredients"));
        recipe.setDescription(resultSet.getString("description"));
        recipe.setCreated(resultSet.getTimestamp("created"));
        recipe.setUpdated(resultSet.getTimestamp("updated"));
        recipe.setPreparationTime(resultSet.getInt("preparation_time"));
        recipe.setPreparation(resultSet.getString("preparation"));
        recipe.setAdminbyId((resultSet.getInt("admin_id")));
    }

    private void getRecipeData(Recipe recipe, PreparedStatement statement) throws SQLException {
        statement.setString(1, recipe.getName());
        statement.setString(2, recipe.getIngredients());
        statement.setString(3, recipe.getDescription());
        statement.setInt(4, recipe.getPreparationTime());
        statement.setString(5, recipe.getPreparation());
        statement.setInt(6, recipe.getAdmin().getId());
    }

}
