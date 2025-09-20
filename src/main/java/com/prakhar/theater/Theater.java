package com.prakhar.theater;

import java.util.ArrayList;
import java.util.List;

import com.prakhar.screen.Screen;

public class Theater {
    private String theaterId;
    private String name;
    private String location;
    private List<Screen> screens = new ArrayList<>();

    public Theater(String theaterId, String name, String location) {
        this.theaterId = theaterId;
        this.name = name;
        this.location = location;
    }

    public void addScreen(Screen screen){
        this.screens.add(screen);
    }
    
    public String getName() {
        return this.name;
    }
}
