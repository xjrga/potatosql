public void print_find(O_${table.name} ${table.name}) throws SQLException
{
    List<O_${table.name}> list = (List<O_${table.name}>)find(${table.name});
    list.forEach(element -> System.out.println(element));
}

