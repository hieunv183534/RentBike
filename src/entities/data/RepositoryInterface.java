package entities.data;

import java.util.List;

public interface RepositoryInterface<DataType> {
    List<DataType> getAll();

     void save(List<DataType> listdata);
}
