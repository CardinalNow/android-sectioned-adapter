package com.cardinalsolutions.sectioned_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class for generating section headers for a data set from the first character of each item's
 * name.  Each item must implement {@link Categorizable} to provide the name of its category, which
 * will be used as the title for each section.
 */
class SectionGenerator {

    /**
     * TODO
     *
     * @param items
     * @return
     */
    public static List<Object> getSectionsForItems(List<Categorizable> items) {
        /* Creates a mapping of section headers to the position they should be inserted in the list */
        Map<Integer, String> sectionHeaders = new HashMap<>();
        for (int i = 0; i < items.size(); i++) {
            String category = items.get(i).getCategory();
            if (!sectionHeaders.containsValue(category)) {
                sectionHeaders.put(i + sectionHeaders.size(), category);
            }
        }

        /* Merges the list of section headers into the appropriate position in the object list */
        ArrayList<Object> listItems = new ArrayList<>();
        int sectionCount = 0;
        for (int i = 0; i < items.size() + sectionHeaders.size(); i++) {
            if (sectionHeaders.containsKey(i)) {
                listItems.add(new SectionedAdapter.SectionHeader(sectionHeaders.get(i)));
                sectionCount++;
            } else {
                listItems.add(items.get(i - sectionCount));
            }
        }
        return listItems;
    }
}
