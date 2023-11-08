package main.java.com;

import main.java.com.models.Model;
import main.java.com.views.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        new Controller(view, model);
    }
}
