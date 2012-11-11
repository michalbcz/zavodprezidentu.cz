package cz.zavodprezidentu.utils

/**
 * Returns a random color.
 */
public class Colorer {
    def list = [new ColorRepresentation(color: "red", circleColor: "orangeCircle"),
            new ColorRepresentation(color: "blue", circleColor: "blueCircle"),
            new ColorRepresentation(color: "yellow", circleColor: "yellowCircle"),
            new ColorRepresentation(color: "pink", circleColor: "pinkCircle"),
            new ColorRepresentation(color: "green", circleColor: "greenCircle"),
            //new ColorRepresentation(color: "lightOrange", circleColor: "lightOrangeCircle")
    ]

    int index = 0;
    Random random = new Random()

    def nextCyclic() {
        index = index + 1;
        if (index == list.size()) {
            index = 0;
        }
        return list[index];
    }

    def nextRandom() {
        return list[random.nextInt(list.size())];
    }
}

class ColorRepresentation {
    String color;
    String circleColor;
}
