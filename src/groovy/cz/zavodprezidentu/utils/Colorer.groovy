package cz.zavodprezidentu.utils

/**
 * Returns a random color.
 */
public class Colorer {
    def list = [
            new ColorRepresentation(color: "yellow", circleColor: "yellowCircle"),
            new ColorRepresentation(color: "green", circleColor: "greenCircle"),
            new ColorRepresentation(color: "blue", circleColor: "blueCircle"),
            new ColorRepresentation(color: "red", circleColor: "redCircle")
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

    def nextByLogoColours() {
        def color = list[index];
        index += 1;
        if (index > 2) {
            index = 3;
        }
        return color;
    }
}

class ColorRepresentation {
    String color;
    String circleColor;
}
