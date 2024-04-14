package org.example.entities;

public class Card extends _BaseEntity{
    private String name;
    private String region;
    private String type;
    private String rarity;
    private int manaCost;
    private int power;
    private int health;
    private String effect;
    private String description;
    private String levelUp;
    private String flavorText;
    private Set set;
    private String linkImage;

    public Card() {

    }

    public Card(int id, String name, String region, String type, String rarity, int manaCost, int power, int health, String effect, String description, String levelUp, String flavorText, Set set, String linkImage) {
        super(id);
        this.name = name;
        this.region = region;
        this.type = type;
        this.rarity = rarity;
        this.manaCost = manaCost;
        this.power = power;
        this.health = health;
        this.effect = effect;
        this.description = description;
        this.levelUp = levelUp;
        this.flavorText = flavorText;
        this.set = set;
        this.linkImage = linkImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevelUp() {
        return levelUp;
    }

    public void setLevelUp(String levelUp) {
        this.levelUp = levelUp;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id = " + getId() +
                ", name = " + name +
                ", region = " + region +
                ", type = " + type +
                ", rarity = " + rarity +
                ", manaCost = " + manaCost +
                ", power = " + power +
                ", health = " + health +
                ", effect = " + effect +
                ", description = " + description +
                ", level up = " + levelUp +
                ", flavor = " + flavorText +
                ", set = " + set +
                ", image = " + linkImage +
                "} ";
    }
}
