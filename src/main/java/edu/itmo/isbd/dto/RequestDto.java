package edu.itmo.isbd.dto;

import lombok.Data;
import org.springframework.lang.Nullable;
import java.util.List;

@Data
public class RequestDto {
    @Nullable
    private Integer pageNo;
    @Nullable
    private Integer pageSize;
    @Nullable
    private List<String> sortAsc;
    @Nullable
    private List<String> sortDesc;

}
