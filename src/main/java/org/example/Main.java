package org.example;
import org.example.model.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Location budapest = new Location(
                "BUD", "Budapest", 47.4979, 19.0402
        );

        Location vienna = new Location(
                "VIE", "Vienna", 48.2082, 16.3738
        );

        Road road = new Road(budapest, vienna, 243.0);
    }
}
