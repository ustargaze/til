package designpattern.mediator;

import java.util.ArrayList;
import java.util.List;

public abstract class Mediator {
    protected List<Colleague> colleagues = new ArrayList<>();

    public void register(Colleague colleague) {
        System.out.printf("Mediator: register Colleague(%s).\n", colleague.name);
        colleagues.add(colleague);
    }

    public abstract void notifyOthers(Colleague colleague);
}
