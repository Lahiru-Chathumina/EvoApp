package edu.icet.service.supplier;

import edu.icet.dto.supplier.OutdoorArea;

import java.util.List;

public interface OutdoorAreaService {
    List<OutdoorArea> getAll();
    OutdoorArea save(OutdoorArea outdoorArea);
    Boolean delete(Long id);
    OutdoorArea update(OutdoorArea outdoorArea);
}
