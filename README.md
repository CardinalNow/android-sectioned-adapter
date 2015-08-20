# Android Sectioned Adapter #
Android Section Adapter allows you to easily create a RecyclerView.Adapter which will automatically
sort your data into sections, each with a header.

You can use it with the standard header view:

![alt text](https://dl.dropboxusercontent.com/u/33880138/StandardHeader.png "Standard Header")

Or you can use it with a custom header view that you specify:

![alt text](https://dl.dropboxusercontent.com/u/33880138/CustomHeaders.png "Custom Header")

## Feedback ##
I want your feedback!  Send your problems or requests to 
[amorgan@cardinalsolutions.com](mailto:amorgan@cardinalsolutions.com) or open 
an issue on the project.

## Usage ##

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
`onBindItemViewHolder()` to bind the data, much like you would do when creating a normal
`RecyclerView.Adapter`.  SectionedAdapter takes type parameter, which is the type of your model
object.  Finally, you *must* call `setListItems()` with your list of `Categorizable`
data in order to generate the headers and show your data.

## Roadmap ##
The only planned upcoming feature is the ability to customize header views by providing an XML layout.