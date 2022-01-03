package entities.data;

import java.util.List;

public interface DataControllerInterface<DataType> {
    List<DataType> getAll();

     void save(List<DataType> listdata);
}
