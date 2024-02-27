package designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {
    private List<Element> elements = new ArrayList<>();


    public void accept(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void removeElement(Element element) {
        elements.remove(element);
    }
}
