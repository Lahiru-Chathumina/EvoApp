package edu.icet.service.supplier;

import edu.icet.dto.supplier.Pool;

import java.util.List;

public interface PoolService {
    List <Pool> getAll();
    Pool save (Pool pool);
    Boolean delete (Long id);
    Pool update (Pool pool);
}
