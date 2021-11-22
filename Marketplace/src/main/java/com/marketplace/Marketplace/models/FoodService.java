package com.marketplace.Marketplace.models;

public class FoodService extends Service {

    private String foodType;
    private String[] ingredients;
    private int prepTime;

    public FoodService(String id, String serviceType, double cost, String providerId, String providerName, int quantityAvailable, String description, Location location, String foodType, String[] ingredients, int prepTime, String searchString) {
        super(id, serviceType, cost, providerId, providerName, quantityAvailable, description, location, searchString);
        this.foodType = foodType;
        this.ingredients = ingredients;
        this.prepTime = prepTime;
    }

    public FoodService() {
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }
}
