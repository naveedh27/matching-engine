package org.naveedh;

import java.util.Comparator;

public class BidComparator implements Comparator<PQObject> {

    @Override
    public int compare(PQObject o1, PQObject o2) {
        return o2.price - o1.price;
    }
}
