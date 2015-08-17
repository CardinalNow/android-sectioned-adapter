package com.cardinalsolutions.sectioned_adapter;

/**
 * An interface for objects that belong to a named category.
 */
public interface Categorizable {
    /**
     * Gets the name of the category that this item belongs to.
     *
     * @return the name <code>String</code>
     */
    String getCategory();
}
