package com.automation.pojos;

public class Room {
    //if you don't want to serialize some property
    // from POJO -> to JSON it will not have id
  //  private transient int id;
    private int id;
    private String name;
    private String description;
    private int capacity;
    private boolean withTV;
    private boolean isWithWhiteBoard;

    public Room() {
    }

    public Room(String name, String description, int capacity, boolean withTV, boolean isWithWhiteBoard) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.withTV = withTV;
        this.isWithWhiteBoard = isWithWhiteBoard;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", withTV=" + withTV +
                ", isWithWhiteBoard=" + isWithWhiteBoard +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isWithTV() {
        return withTV;
    }

    public void setWithTV(boolean withTV) {
        this.withTV = withTV;
    }

    public boolean isWithWhiteBoard() {
        return isWithWhiteBoard;
    }

    public void setWithWhiteBoard(boolean withWhiteBoard) {
        isWithWhiteBoard = withWhiteBoard;
    }
}
