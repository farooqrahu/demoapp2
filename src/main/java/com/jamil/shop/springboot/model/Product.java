package com.jamil.shop.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String releaseDate;
    private String display;
    private String dimension;
    private String weight;
    private String os;
    private String chipset;
    private String cpu;
    private String memory;
    private String cameras;
    private String battery;
    private String colors;
    private String versions;
    private String price;

    @JsonIgnore
    @ManyToMany(mappedBy = "productList")
    private List<User> userList;

    public Product() {
    }

    public Product(String name, String releaseDate, String display, String dimension, String weight, String os, String chipset, String cpu, String memory, String cameras, String battery, String colors, String versions, String price) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.display = display;
        this.dimension = dimension;
        this.weight = weight;
        this.os = os;
        this.chipset = chipset;
        this.cpu = cpu;
        this.memory = memory;
        this.cameras = cameras;
        this.battery = battery;
        this.colors = colors;
        this.versions = versions;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCameras() {
        return cameras;
    }

    public void setCameras(String cameras) {
        this.cameras = cameras;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
