package org.naveedh;

import java.util.Comparator;

public class BidComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2-o1;
    }
}
