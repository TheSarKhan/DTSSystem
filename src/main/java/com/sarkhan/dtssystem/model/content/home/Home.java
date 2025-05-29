package com.sarkhan.dtssystem.model.content.home;

import com.sarkhan.dtssystem.model.content.home.data.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Home {
    List<Card>cards;
    List<HeaderCompany> headerCompanies;
    List<Partner>partners;
    List<ProgramScope>programScopes;
}
