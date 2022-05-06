import java.sql.SQLException;
import java.util.List;
public interface Generic_dao<T>{
void insert(T instance) throws SQLException;    
void update(T instance) throws SQLException;
void delete(T instance) throws SQLException;
List<T> find(T instance) throws SQLException;
List<T> find_all(T instance) throws SQLException;
Integer count(T instance) throws SQLException;
}

