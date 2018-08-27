package com.athena.services;

import com.athena.entities.ModEntry;
import com.athena.entities.Modifier;
import com.athena.model.ModEntryDTO;
import com.athena.model.ModifierDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModEntryService
{
    public List<ModEntryDTO> getModEntries(Modifier modifier, int[] inclusions)
    {
        List<ModEntryDTO> allEntries = new ArrayList<>();

        for(ModEntry modEntry : modifier.getEntries())
        {
            ModEntryDTO entryDto = new ModEntryDTO();

            entryDto.setName(modEntry.getName());
            entryDto.setIndex(modEntry.getIndex());
            entryDto.setCost(modEntry.getCost());
            entryDto.setIncluded(getInclusion(entryDto.getIndex(), inclusions));

            allEntries.add(entryDto);
        }

        return allEntries;
    }

    private boolean getInclusion(int index, int[] inclusions)
    {
        for(int i=0; i<inclusions.length; i++)
        {
            if(inclusions[i] == index)
            {
                return true;
            }
        }
        return false;
    }

    public ModEntryDTO getValues(ModEntryDTO modEntry)
    {
        ModEntryDTO newEntry = new ModEntryDTO();
        newEntry.setName(modEntry.getName());
        newEntry.setCost(modEntry.getCost());
        newEntry.setIndex(modEntry.getIndex());
        newEntry.setIncluded(modEntry.isIncluded());

        return newEntry;
    }
}
