# Android Sectioned Adapter #
Android Section Adapter allows you to easily create a RecyclerView.Adapter which will automatically
sort your data into sections and add a header above each section.

You can use it with the provided header view:

![alt text](https://dl.dropboxusercontent.com/u/33880138/StandardHeader.png "Standard Header")

Or you can use it with a custom header view that you specify:

![alt text](https://dl.dropboxusercontent.com/u/33880138/CustomHeaders.png "Custom Header")

## Usage ##

### Get it with Gradle ###
Details to be added once published to jcenter
```
compile 'com.example:sectioned-adapter:0.0.0'
```

### Implement Categorizable ###
Your data model object needs to implement Categorizable.getCategory() to return the name of its
category.  For each category name that appears in the data, SectionedAdapter will automatically
create and insert a header view for that category and place all the item views beneath it.  For
example, the `Dog` class will be sectioned according to the breed of the dog.
```
class Dog implements Categorizable
{
    String name;
    String breed;

    public String getCategory()
    {
        return breed;
    }
}
```

The `Person` example is sectioned according to the first letter of his/her last name.
```
class Person implements Categorizable
{
    String firstName;
    String lastName;

    public String getCategory()
    {
        return lastName.substring(0, 1);
    }
}
```

### Subclass SectionedAdapter ###
Sectioned Adapter takes care of generating and inserting the header views, so all your subclass
needs to do is implement `onCreateItemViewHolder()` to create a `ViewHolder` for your items and
`onBindItemViewHolder()` to bind the data, exactly like you would do when creating a normal
`RecyclerView.Adapter`.  SectionedAdapter takes type parameter, the type of your model
object you want to adapt.  Finally, you *must* call `setListItems()` with your list of `Categorizable`
data in order to generate the headers and show your data.

### Use a Custom Header View ###
Sectioned Adapter will automatically generate a header view for your data using a built-in layout
as seen in the Yankees example above.  You can also specify an XML layout that you want to inflate
for each header.  The view *must* contain a Text View with the ID `@android:id/title`.
