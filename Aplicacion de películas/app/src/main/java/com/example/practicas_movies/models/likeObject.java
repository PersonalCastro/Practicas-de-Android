package com.example.practicas_movies.models;

public class likeObject {

    public String id;
    public String photo;
    public String name;


    public likeObject(String id, String name, String photo){

        this.id = id;
        this.name = name;
        this.photo = photo;

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
}

