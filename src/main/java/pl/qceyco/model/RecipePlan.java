package pl.qceyco.model;

import pl.qceyco.dao.DayNameDao;
import pl.qceyco.dao.PlanDao;
import pl.qceyco.dao.RecipeDao;

public class RecipePlan {
    private int id;
    private Recipe recipe;
    private String mealName;
    private int displayOrder;
    private DayName dayName;
    private Plan plan;

    public RecipePlan() {
    }

    public RecipePlan(Recipe recipe, String mealName, int displayOrder, DayName dayName, Plan plan) {
        this.recipe = recipe;
        this.mealName = mealName;
        this.displayOrder = displayOrder;
        this.dayName = dayName;
        this.plan = plan;
    }

    public RecipePlan(int id, Recipe recipe, String mealName, int displayOrder, DayName dayName, Plan plan) {
        this.id = id;
        this.recipe = recipe;
        this.mealName = mealName;
        this.displayOrder = displayOrder;
        this.dayName = dayName;
        this.plan = plan;
    }

    public RecipePlan(DayName dayName, String mealName, Recipe recipe, Plan plan) {
        this.dayName = dayName;
        this.mealName = mealName;
        this.recipe = recipe;
        this.plan = plan;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public DayName getDayName() {
        return dayName;
    }

    public void setDayName(DayName dayName) {
        this.dayName = dayName;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "RecipePlan{" +
                "id=" + id +
                ", recipe=" + recipe +
                ", mealName='" + mealName + '\'' +
                ", displayOrder=" + displayOrder +
                ", dayName=" + dayName +
                ", plan=" + plan +
                '}';
    }

    public void setRecipeById(int recipeId) {
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.getRecipeById(recipeId);
        this.recipe = recipe;
    }

    public void setDayNameById(int dayNameId) {
        DayNameDao dayNameDao = new DayNameDao();
        DayName dayName = dayNameDao.getDayNameById(dayNameId);
        this.dayName = dayName;
    }

    public void setPlanById(int planId) {
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.getPlanById(planId);
        this.plan = plan;
    }
}



