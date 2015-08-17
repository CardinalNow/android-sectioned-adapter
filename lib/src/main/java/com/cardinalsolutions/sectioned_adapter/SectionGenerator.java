package com.cardinalsolutions.sectioned_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class for generating section headers for a data set.  Each item must implement
 * {@link Categorizable} to provide the name of its category, which will be used as the title for each section.
 */
public class SectionGenerator {
    /**
     * Adds section header objects into a list of {@link Categorizable} data objects.  For each
     * unique category title returned by the list objects, a corresponding section header will be
     * inserted into the list.
     *
     * @param items a {@link List} of <code>Categorizable</code> objects
     * @return the original list of objects with {@link com.cardinalsolutions.sectioned_adapter.SectionedAdapter.SectionHeader}s inserted
     */
    static List<Object> getSectionsForItems(List<? extends Categorizable> items) {
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
